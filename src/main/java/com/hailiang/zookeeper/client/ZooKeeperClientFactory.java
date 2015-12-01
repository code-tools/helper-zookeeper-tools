package com.hailiang.zookeeper.client;

import static com.hailiang.zookeeper.ZooKeeperConstant.CONNECT_STRING;
import static com.hailiang.zookeeper.ZooKeeperConstant.NAMESPACE;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * 工程类-ZooKeeper客户端
 * 
 * @classname com.hailiang.zookeeper.client.ZooKeeperClientFactory
 * @description TODO
 * @author hailiang.jiang
 * @date 2015年12月1日 下午7:55:31
 * @version: v1.0.0
 * @see
 */
public class ZooKeeperClientFactory {

	public static CuratorFramework newCuratorFramework() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, Integer.MAX_VALUE);
		
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(CONNECT_STRING)
				.connectionTimeoutMs(30000)
				.sessionTimeoutMs(30000)
				.namespace(NAMESPACE)
				.retryPolicy(retryPolicy)
				.defaultData(null)
				.build();
		return client;
	}
	
	public static void start(CuratorFramework client) {
		if (client == null) {
			throw new IllegalArgumentException("参数[client]有误");
		}
		client.start();
	}
	
	public static void close(CuratorFramework client) {
		CloseableUtils.closeQuietly(client);
	}
	
	public static CuratorFramework startCuratorFramework() {
		CuratorFramework client = newCuratorFramework();
		start(client);
		return client;
	}
	
}
