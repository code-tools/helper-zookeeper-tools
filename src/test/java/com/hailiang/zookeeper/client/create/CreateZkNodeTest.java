package com.hailiang.zookeeper.client.create;

import org.junit.Test;

import com.hailiang.zookeeper.client.create.CreateZkNode;

public class CreateZkNodeTest {

	@Test
	public void testCreateNode() {
		CreateZkNode zk = new CreateZkNode("C:/test/tmall");
		//注意，都是以命名空间为前缀见(zk.properties中的namespace=/sysconfig/primservice
		zk.create("/soprocess/service/dev");
	}
}
