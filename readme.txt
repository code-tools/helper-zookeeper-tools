ZooKeeper帮助类
	1. 执行单元测试，导出配置生成properties
		(1) %project%/src/main/resources/zk.properties
		(2) 执行单元测试类：com.hailiang.zookeeper.read.GeneratePropertiesFileTest.testGenerate
		
		
	2. 执行单元测试，读取properties文件，生成zk节点
		实现思路：
			(1) 指定zk节点所在的根目录(此处根节点，用%ZK_ROOT_NODE%代替)
			(2) 扫描本地指定的目录下的*.properties文件，生成节点%ZK_ROOT_NODE%/*(properties文件名)/properties文件中的key = properties文件中value
			(3) 将(2)描述成一个对象
			(4) 根据对象创建节点