package com.hailiang.zookeeper;

import java.util.ResourceBundle;

public class ZooKeeperConstant {

	public static final String CONNECT_STRING;
	public static final String NAMESPACE;
	public static final String PROPETIES_FILE_SAVE_PATH;
	public static final String RELATIVE_NAMESPACE_PATH; //远程目录的节点路径，相对于namespace
	
	static {
		ResourceBundle config = ResourceBundle.getBundle("zk");
		CONNECT_STRING = config.getString("connect.string");
		NAMESPACE = config.getString("namespace");
		PROPETIES_FILE_SAVE_PATH = config.getString("propeties.file.save.path");
		RELATIVE_NAMESPACE_PATH = config.getString("relative.namespace.path");
	}
	
}
