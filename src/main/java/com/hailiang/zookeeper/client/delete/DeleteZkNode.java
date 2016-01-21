package com.hailiang.zookeeper.client.delete;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;

import com.hailiang.zookeeper.client.ZooKeeperClientFactory;


/**
 * 删除指定节点下的，所有节点
 * 
 * @classname com.hailiang.zookeeper.client.delete.DeleteZkNode
 * @description TODO
 * @author hailiang.jiang
 * @date 2016年1月21日 下午5:10:04
 * @version: v1.0.0
 * @see
 */
public class DeleteZkNode {
	
	public void delete(String namespace, String zkNodePath) {
		CuratorFramework client = null;
		try {
			client = ZooKeeperClientFactory.startCuratorFramework();
			
			List<String> nodeKeys = client.getChildren().forPath(zkNodePath);
			
			CuratorFramework newClient = client.usingNamespace(client.getNamespace() + zkNodePath);
			
			for (String key : nodeKeys) {
				//生产以key值为名的properties
				int numChildren = newClient.checkExists().forPath("/" + key).getNumChildren();
				if (numChildren == 0) {
					newClient.delete().forPath("/" + key);
					System.out.println("删除节点: " + newClient.getNamespace() + "/" + key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}
	}
	
	
	
	
}
