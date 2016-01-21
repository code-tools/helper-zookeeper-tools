package com.hailiang.zookeeper.client.delete;

import org.junit.Test;

public class DeleteZkNodeTest {

	@Test
	public void testDeleteNode() {
		DeleteZkNode zk = new DeleteZkNode();
		//注意，都是以命名空间为前缀，删除/sysconfig/primservice/soprocess/service/dev路径下的所有节点
		zk.delete("/sysconfig/primservice", "/soprocess/service/dev"); 
	}
}
