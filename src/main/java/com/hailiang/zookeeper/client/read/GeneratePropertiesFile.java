package com.hailiang.zookeeper.client.read;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;

import com.hailiang.zookeeper.ZooKeeperConstant;
import com.hailiang.zookeeper.client.ZooKeeperClientFactory;

public class GeneratePropertiesFile {
	
	public void generate() {
		CuratorFramework client = null;
		try {
			client = ZooKeeperClientFactory.startCuratorFramework();
			
			List<String> nodeKeys = client.getChildren().forPath(ZooKeeperConstant.RELATIVE_NAMESPACE_PATH);
			
			CuratorFramework newClient = client.usingNamespace(client.getNamespace() + ZooKeeperConstant.RELATIVE_NAMESPACE_PATH);
			
			for (String key : nodeKeys) {
				//生产以key值为名的properties
				int numChildren = newClient.checkExists().forPath("/" + key).getNumChildren();
				
				StringBuilder contentPropertiesSb = new StringBuilder();
				if (numChildren > 0) {
					List<String> keyList = newClient.getChildren().forPath("/" + key);
					Collections.sort(keyList);
					CuratorFramework cf = newClient.usingNamespace(newClient.getNamespace() + "/" + key);
					for (String k : keyList) {
						String lineStr = new KeyValue(k, new String(cf.getData().forPath("/" + k), "UTF-8")).getPair();
						contentPropertiesSb.append(lineStr).append('\n');
					}
				}
				
				File file = new PropertiesFileDesc(key, contentPropertiesSb.toString()).generate();
				System.out.println(file.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CloseableUtils.closeQuietly(client);
	}
	
	private static class KeyValue {
		private String key;
		private String value;
		public KeyValue(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		public String getPair() {
			return this.key + "=" + this.value;
		}
	}
}