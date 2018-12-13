package com.example.demo;

public class TestData {

	public static void main(String[] args) {
		
		
		Piao p = new Piao("一号");
		Piao p1 = new Piao("二号");
		Piao p2 = new Piao("三号");
		Piao p3 = new Piao("四号");
		p.start();
		p.getClass();
		p1.start();
		p1.getClass();
		p2.start();
		p2.getClass();
		p3.start();
		p3.getClass();

	}
}

class Piao extends Thread {
	public static Integer ticket = 1000;

	public Piao(String name) {
		super(name);
	}

	public void run() {
		while (true) {
			synchronized (Piao.class) {
				if (ticket <= 0) {
					break;
				}
				System.out.println(Thread.currentThread().getName() + "窗口卖了第" + ticket + "张票");
				ticket--;
			}
		}
	}
}
