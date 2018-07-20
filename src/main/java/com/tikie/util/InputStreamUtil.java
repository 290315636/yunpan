/**
 * 
 */
package com.tikie.util;

import java.io.*;

/**
 * @author FengZhen
 *
 */
public class InputStreamUtil {

	public static byte[] input2Byte(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}

	public static final InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}
	
	public static void mkFile(String filePath, byte[] stream) throws IOException {
		InputStream is = InputStreamUtil.byte2Input(stream);
		File file = new File(filePath);
		inputStream2File(is, file);
	}

	public static void inputStream2File(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
