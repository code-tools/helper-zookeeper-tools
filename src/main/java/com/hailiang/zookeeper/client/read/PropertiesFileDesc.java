package com.hailiang.zookeeper.client.read;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.hailiang.zookeeper.ZooKeeperConstant;

/**
 * properties文件的描述
 * 
 * @classname com.hailiang.zookeeper.read.PropertiesFileDesc
 * @description TODO
 * @author hailiang.jiang
 * @date 2015年12月1日 下午8:31:42
 * @version: v1.0.0
 * @see
 */
public class PropertiesFileDesc {
	private String filename;//文件名
	private String fileConent;//文件内容
	
	private final static String fileExtensionName = ".properties"; //文件扩展名
	public PropertiesFileDesc(String prefixFilename, String fileConent) {
		super();
		this.filename = prefixFilename;
		this.fileConent = fileConent;
	}
	
	public File generate() {
		File file = new File(ZooKeeperConstant.PROPETIES_FILE_SAVE_PATH + "/" + filename + fileExtensionName);
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			bw.write(fileConent != null ? fileConent : "");
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return file;
	}
	
}
