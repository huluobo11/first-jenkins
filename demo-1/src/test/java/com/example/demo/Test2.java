package com.example.demo;

import java.io.File;

public class Test2 {
	
	public static void main(String[] args) {
		File from = new File("D:/aa");
		File to = new File("E:/aa");
		if (!from.exists()) {
			System.out.println("原文件不存在");
		}
		if (!to.exists()) {
			to.mkdirs();
		}
		File[] listFiles = from.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			if (listFiles[i].isDirectory()) {
				new File(to, listFiles[i].getName()).mkdir();
			}else {
				copy(from, to);
			}
		}
	}

	private static void copy(File from, File to) {
		
	}
	
	public static File name(File file) {
		File[] listFiles = file.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			if (listFiles[i].isDirectory()) {
				return new File(file, listFiles[i].getName());
			}else {
				//copy(file, to);
			}
		}
		return file;
		
	}
}