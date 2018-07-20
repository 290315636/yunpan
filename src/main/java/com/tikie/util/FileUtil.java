/**
 * 
 */
package com.tikie.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaocs
 *
 */
public class FileUtil {
	/**
     * 下载文件
     * @param response
     * @param filePat 包括文件名如：c:/a.txt
     * @param fileName 文件名如：a.txt
     */
    public static void downFile(HttpServletResponse response,String filePath,String fileName){
        try {
            response.setCharacterEncoding("gkb");
            response.setContentType("text/plain");
            response.setHeader("Location",fileName);
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"),"ISO8859-1"));
            FileInputStream  fis=new FileInputStream(filePath);
            OutputStream  os=response.getOutputStream();
            byte[] buf=new byte[1024];
            int c=0;
            while((c=fis.read(buf))!=-1){
                os.write(buf, 0, c);
            }
            os.flush();
            os.close();
            if(fis!=null){
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 检查文件是否存在，存在返回true
     * @param destFileName
     * @return
     */
    public static boolean checkFileIsExists(String destFileName){
        File file = new File(destFileName);
        if (file.exists()) {
            return true;
        }else{
            return false;
        }
    }
    /**
     * 复制文件
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyFile(File source, File dest){  
          InputStream input = null;  
          OutputStream output = null;  
          try {
              input = new FileInputStream(source);
              output = new FileOutputStream(dest);    
              byte[] buf = new byte[1024];    
              int bytesRead;    
              while ((bytesRead = input.read(buf))>-1) {
                output.write(buf, 0, bytesRead);
              }
              output.close();
              input.close();
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    
    /**
     * 把输入流保存到指定文件
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void saveFile(InputStream source, File dest){  
          InputStream input = null;  
          OutputStream output = null;  
          try {
              input =source;
              output = new FileOutputStream(dest);    
              byte[] buf = new byte[1024];    
              int bytesRead;    
              while ((bytesRead = input.read(buf))>-1) {
                output.write(buf, 0, bytesRead);
              }
              output.close();
              input.close();
          }catch(Exception e){
              e.printStackTrace();
          }
    }
    /**
     * 创建文件
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            return false;
        }
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                return false;
            }
        }
        try {
            if (file.createNewFile()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建目录
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return false;
        }
        if (!destDirName.endsWith(File.separator))
            destDirName = destDirName + File.separator;
        if (dir.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     */
    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (!file.exists()) {
            return flag;
        } else {
            if (file.isFile()) {
                return deleteFile(sPath);
            } else {
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     */
    public static boolean deleteDirectory(String sPath) {
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    

    /*public static void main(String[] args) {
        String dir = "D:\\sgtsc_files\\393\\02\\";
        createDir(dir);
        String filename = "test1.txt";
        String subdir = "subdir";
        createDir(dir + subdir);
        createFile(dir + filename);
        createFile(dir + subdir + filename);
        DeleteFolder(dir);
    }*/
}
