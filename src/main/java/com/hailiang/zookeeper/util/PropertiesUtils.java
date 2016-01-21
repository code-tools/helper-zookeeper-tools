package com.hailiang.zookeeper.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * properties文件处理类
 * 
 * @classname com.hailiang.zookeeper.util.PropertiesUtils
 * @description TODO
 * @author hailiang.jiang
 * @date 2016年1月21日 下午4:03:43
 * @version: v1.0.0
 * @see
 */
public final class PropertiesUtils {
	
	public static Map<String, String> loadFile(File file) {
		Map<String, String> zkNodePairMap = new HashMap<String, String>();
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(file));
			Set<Map.Entry<Object, Object>> entrySet = prop.entrySet();
			for (Map.Entry<Object, Object> entry : entrySet) {
				zkNodePairMap.put((String) entry.getKey(), (String) entry.getValue());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return zkNodePairMap;
	}
	
	
	public static Map<String, String> loadFile(List<File> fileList) {
		Map<String, String> zkNodePairMap = new HashMap<String, String>();
		
		for (File file : fileList) {
			zkNodePairMap.putAll(loadFile(file));
		}
		
		return zkNodePairMap;
	}
}
