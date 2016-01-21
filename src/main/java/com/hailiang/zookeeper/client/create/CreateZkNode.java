package com.hailiang.zookeeper.client.create;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;

import com.hailiang.zookeeper.client.ZooKeeperClientFactory;
import com.hailiang.zookeeper.util.FileUtils;
import com.hailiang.zookeeper.util.PropertiesUtils;

public class CreateZkNode {
	
	private final static String PATTERN_PROPERTIES_FILE = ".*\\.properties";
	
	private String scanDirectory;
	
	
	public CreateZkNode(String scanDirectory) {
		this.scanDirectory = scanDirectory;
	}
	
	/**
	 * (1) 指定zk节点所在的根目录(此处根节点，用%ZK_ROOT_NODE%代替)
	 * (2) 扫描本地指定的目录下的*.properties文件，生成节点%ZK_ROOT_NODE%/*(properties文件名)/properties文件中的key = properties文件中value
	 * (3) 将(2)描述成一个对象Map
	 * (4) 根据对象创建节点
	 */
	public void create(String zkRootNode) {
		//扫描目录下的properties文件
		List<File> propertiesFileList = FileUtils.scanDirectory(this.scanDirectory, PATTERN_PROPERTIES_FILE);
		if (propertiesFileList == null || propertiesFileList.isEmpty()) {
			System.out.println("目录下" + this.scanDirectory + "没有指定类型的文件");
			return;
		}
		
		Map<String, String> zkNodePair = PropertiesUtils.loadFile(propertiesFileList);
		
		
		CuratorFramework client = null;
		try {
			client = ZooKeeperClientFactory.startCuratorFramework();
			
			for (Map.Entry<String, String> entry : zkNodePair.entrySet()) {
				client.create().creatingParentsIfNeeded().forPath(zkRootNode + "/" + entry.getKey(), entry.getValue().getBytes("UTF-8"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}
	}
	
}
