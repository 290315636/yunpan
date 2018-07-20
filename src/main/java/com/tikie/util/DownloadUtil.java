/**
 * 
 */
package com.tikie.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author FengZhen
 *
 */
public class DownloadUtil {

	public static HttpServletResponse download(String path,HttpServletRequest request, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                filename = URLEncoder.encode(filename, "UTF-8");  
            } else {  
                filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");  
            }
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
	
	public static void downloadLocal(String path,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException {
		// path是指欲下载的文件的路径。
        File file = new File(path);
        // 取得文件名。
        String filename = file.getName();
        try{
	        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
	            filename = URLEncoder.encode(filename, "UTF-8");  
	        } else {  
	            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");  
	        }
        }catch (UnsupportedEncodingException e){
        	e.printStackTrace();
        }
        // 读到流中
	    InputStream inStream = new FileInputStream(path);// 文件的存放路径
	    // 设置输出的格式
	    response.reset();
	    response.setContentType("bin");
	    response.addHeader("Content-Disposition", "attachment; filename=" + filename);
	    // 循环取出流中的数据
	    byte[] b = new byte[10000];
	    int len;
	    try {
	      while ((len = inStream.read(b)) > 0)
	        response.getOutputStream().write(b, 0, len);
	      inStream.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

//	public static void downloadStream(String fileName,byte[] stream,HttpServletResponse response) throws IOException {
//		InputStream is = InputStreamUtil.byte2Input(stream);
//		FileOutputStream fos = new FileOutputStream(fileName);
//		byte[] b = new byte[10000];
//		while((is.read(b)) != -1){
//			fos.write(b);
//		}
//		is.close();
//		fos.close();
//		// path是指欲下载的文件的路径。
//        File file = new File(fileName);
//        // 取得文件名。
//        String filename = file.getName();
//	    // 读到流中
//	    InputStream inStream = new FileInputStream(fileName);
//	    // 设置输出的格式
//	    response.reset();
//	    response.setContentType("bin");
//	    response.addHeader("Content-Disposition", "attachment; filename=" + filename);
//	    int len;
//	    try {
//	      while ((len = inStream.read(b)) > 0)
//	        response.getOutputStream().write(b, 0, len);
//	      inStream.close();
//	    } catch (IOException e) {
//	      e.printStackTrace();
//	    }
//		
//	}
	
}
