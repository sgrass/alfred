package com.cx.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class Html2Pdf {
	
	public static void test1() throws Exception {
		URLConnection uc = new URL("http://www.baidu.com").openConnection();
    uc.setConnectTimeout(10000);
    uc.setDoOutput(true);
//    InputStream in = new BufferedInputStream(uc.getInputStream());
    
    String outputFile = "e:/test1.pdf";     
    OutputStream os = new FileOutputStream(outputFile);     
    ITextRenderer renderer = new ITextRenderer();
    
    String content = IOUtils.toString(new File("C:/Users/Administrator/Desktop/report.html").toURI().toURL());
//    String content = IOUtils.toString(uc.getInputStream());
    
    System.out.println(content);
    
    renderer.setDocumentFromString(content);
    renderer.layout();     
    renderer.createPDF(os);     
    os.close();
	}
	
	public static void test2(String path1, String path2) throws Exception {
		String inputFile = path1;     
//		String inputFile = "C:/Users/Administrator/Desktop/report.html";     
    String url = new File(inputFile).toURI().toURL().toString();     
    String outputFile = path2;     
    OutputStream os = new FileOutputStream(outputFile);     
    ITextRenderer renderer = new ITextRenderer();     
    renderer.setDocument(url);     

    // 解决中文支持问题     
//    ITextFontResolver fontResolver = renderer.getFontResolver();     
//    fontResolver.addFont("C:/Windows/Fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);     

    // 解决图片的相对路径问题     
//    renderer.getSharedContext().setBaseURL("file:/D:/Work/Demo2do/Yoda/branch/Yoda%20-%20All/conf/template/");     
         
    renderer.layout();     
    renderer.createPDF(os);     
         
    os.close();
	}
	
	public static void main(String[] args) throws Exception {
//		test2(args[0],args[1]);
		test1();
	}
}
