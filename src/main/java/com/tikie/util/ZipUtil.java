/**
 * 
 */
package com.tikie.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author FengZhen
 * zip工具类
 */
public class ZipUtil {

	/**
	 * 将多个文件打包为zip
	 * */
	public static void files2Zip(String[] filePaths, String desPath) {
		// 需要压缩的文件--包括文件地址和文件名
		File zipFile = new File(desPath);
		ZipOutputStream zipStream = null;
		FileInputStream zipSource = null;
		BufferedInputStream bufferStream = null;
		try {
			// 构造最终压缩包的输出流
			zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
			for (int i = 0; i < filePaths.length; i++) {
				File file = new File(filePaths[i]);
				// 将需要压缩的文件格式化为输入流
				zipSource = new FileInputStream(file);
				// 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
				ZipEntry zipEntry = new ZipEntry(file.getName());
				// 定位该压缩条目位置，开始写入文件到压缩包中
				zipStream.putNextEntry(zipEntry);
				// 输入缓冲流
				bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
				int read = 0;
				// 创建读写缓冲区
				byte[] buf = new byte[1024 * 10];
				while ((read = bufferStream.read(buf, 0, 1024 * 10)) != -1) {
					zipStream.write(buf, 0, read);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			try {
				if (null != bufferStream)
					bufferStream.close();
				if (null != zipStream)
					zipStream.close();
				if (null != zipSource)
					zipSource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
