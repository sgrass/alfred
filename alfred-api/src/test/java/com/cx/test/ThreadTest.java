package com.cx.test;


public class ThreadTest {
	public static int count=50;
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j=0; j<count; j++) {
					try {
						UrlConnection.main(new String[]{"t1/"+j});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j=0; j<count; j++) {
					try {
						UrlConnection.main(new String[]{"t2/"+j});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j=0; j<count; j++) {
					try {
						UrlConnection.main(new String[]{"t3/"+j});
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j=0; j<count; j++) {
					try {
						UrlConnection.main(new String[]{"t4/"+j});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t1.start();t3.start();t2.start();t4.start();
	}
}	
