package com.weiss.j2se.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {
	private String name;
	private String path;

	public MyClassLoader(String name) {
		super(); // 让系统类加载器成为该类加载器的父加载器

		this.name = name;
	}

	public MyClassLoader(ClassLoader parent, String name) {
		super(parent); // 显式指定该类加载器的父加载器

		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = this.loadClassData(name);
		return this.defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) {

		byte[] bs = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(new File(path + name + ".class"));
			bos = new ByteArrayOutputStream();
			int i = 0;
			while ((i = fis.read()) != -1) {
				bos.write(i);
			}
			bs = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
					fis = null;
				}
				if (bos != null) {

					bos.close();
					bos = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bs;
	}

}
