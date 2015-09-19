package com.cx.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ScannerTest {
	
	public static void test() throws Exception {
		//nio 
		byte[] bytes = Files.readAllBytes(Paths.get("C:/Users/Administrator/Desktop/report.html"));
    String text = new String(bytes);
    System.out.println(text);
	}
	
	public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(new File("C:/Users/Administrator/Desktop/report.html"), "UTF-8");
    String text = scanner.useDelimiter("\\A").next();
    System.out.println(text);
		
	}
}
