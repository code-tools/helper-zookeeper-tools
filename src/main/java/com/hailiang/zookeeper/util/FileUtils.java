package com.hailiang.zookeeper.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件工具类
 * 
 * @classname com.hailiang.zookeeper.util.FileUtils
 * @description TODO
 * @author hailiang.jiang
 * @date 2016年1月21日 下午3:42:16
 * @version: v1.0.0
 * @see
 */
public final class FileUtils {
	
	public static boolean isDirectory(File file) {
		return file.isDirectory();
	}

	/**
	 * 扫描指定的目录
	 * @methodName com.hailiang.zookeeper.util.FileUtils.scanDirectory
	 * @author hailiang.jiang
	 * @date 2016年1月21日 下午3:42:22
	 * @version: v1.0.0
	 */
	public static List<File> scanDirectory(String directory, String fileNamePattern) {
		File file = new File(directory);
		
		if (!isDirectory(file)) {
			throw new RuntimeException(directory + "不是一个目录");
		}
		
		return listFile(file, fileNamePattern);
	}
	

	private static List<File> listFile(File directory, final String fileNamePattern) {
		String[] fileArr = null;
		if (fileNamePattern != null && fileNamePattern.trim().length() > 0) {
			fileArr = directory.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					Pattern pattern = Pattern.compile(fileNamePattern);
					Matcher matcher = pattern.matcher(name);
					return matcher.matches();
				}
			});
		} else {
			fileArr = directory.list();
		}
		
		
		List<File> fileList = new ArrayList<File>();
		
		for (String filename : fileArr) {
			File tempFile = new File(directory.getAbsoluteFile() + File.separator + filename);
			if (isDirectory(tempFile)) {
				return listFile(tempFile, fileNamePattern);
			} else {
				fileList.add(tempFile);
			}
		}
		
		return fileList;
	}
	
	
	public static void main(String[] args) {
		List<File> fileList = scanDirectory("C:\\test\\tmall", ".*\\.properties");
		System.out.println(fileList);
	}
}
