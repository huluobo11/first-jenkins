package com.example.demo;

import java.io.OutputStream;
import java.io.PrintStream;

public class TestStream {
	
	public TestStream name() {
		return null;
	}
	
	public static void main(String[] args) { 
		int a,b;
		a =4;
		b=5;
		method(a,b);
		System.out.println("a="+a+",b="+b);
	}

	private static void method(int a, int b) {
		
		PrintStream printStream = new PrintStream(System.out) {
			@Override
			public void println(String x) {
				super.println("a="+a*100+",b="+b*100);
			}
		};
		System.setOut(printStream);
	}
	
	private static void method2(int a, int b) {
		
		// 新建一个printStreamObj类，他是PrintStream的子类，然后在该类中，先添加一个构造方法，再重写println方法，
		class PrintStreamObj extends PrintStream{
			
			// 添加一个构造方法，
			public PrintStreamObj(OutputStream out) {
				super(out);
			}
			
			// 重写 println 方法
			
			
			@Override
			public void println(String x) {
				super.println("a="+a*100+",b="+b*100);
			}
			
		}
		
		// new一个printStreamObj类型的对象
		PrintStream printStream = new PrintStreamObj(System.out);
		
		// 然后把该对象设置为System类的out属性 的值 。
		// 也就改变了标准输出流。
		System.setOut(printStream);
	}

	
	
}