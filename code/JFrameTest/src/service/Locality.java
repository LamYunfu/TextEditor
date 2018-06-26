/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


/**
 *提供本地化方法，将用户信息，文件夹信息，文件信息本地化
 * @author 蓝云甫
 */
public class Locality {
    static final String mainDirectory = "C:/j2EE/";
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String LINE_SEPARATOR = System.lineSeparator();
    /**
     * 创建文件夹
     * @param userName
     * @return 
     */
    public static boolean createDirectory(String folder){
        Path path = getPath(folder);
        try {
            Files.createDirectories(path);
        } catch (IOException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * 删除文件夹
     * @param folder
     * @return 
     */
    public static boolean deleteDirectory(String folder){
        folder = mainDirectory + folder;
        folder = folder.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);       //路径转化成本地平台化
        File file = new File(folder);
        return deleteDir(file);
    }
    
    /**
     * 创建空文件
     * @param filePath
     * @return 
     */
    public static boolean createFile(String filePath){
        Path path = getPath(filePath);
       // System.out.println(path.toString());
        try {
            Files.createFile(path);
	} catch (IOException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
            return false;
	}
        return true;
    }
    
    /**
     * 将本地文件读到前界面显示
     * @param filePath
     * @param textarea
     * @return 
     */
    public static boolean readFile(String filePath, JTextArea textarea){
        filePath = mainDirectory + filePath;
        FileReader fr = null;
        BufferedReader bfr = null;
        try {
            fr = new FileReader(filePath);
//            bfr = new BufferedReader(fr);
            int s;
            while((s = fr.read()) != -1){
                textarea.append((char)s + "");                 //把文件的内容写到主面板上
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            try {
                if(fr != null && bfr != null){
                    fr.close();
                    bfr.close();
                } 
            } catch (IOException ex) {
                Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    /**
     * 将主界面中TextArea中的内容写到文件中
     * @param filePath
     * @param textarea
     * @return 
     */
    public static boolean writeFile(String filePath, JTextArea textarea){
        filePath = mainDirectory + filePath;
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            String content = textarea.getText();
            for(int i = 0;i < content.length();i++){
                if(content.charAt(i)==10){                  //如果是换行符，换成本地换行符
                    fw.write(LINE_SEPARATOR);
                }
                else{
                    fw.write(content.charAt(i));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {  
            if(fw != null){
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
        
    /**
     * 返回当前路径的Path对象
     * @param folder
     * @return 
     */
    private static Path getPath(String folder){
        String directory = mainDirectory + folder;
        directory = directory.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);       //路径转化成本地平台化
        Path path = Paths.get(directory);
        return path;
    }
     
       /**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    private static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除子目录
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}

