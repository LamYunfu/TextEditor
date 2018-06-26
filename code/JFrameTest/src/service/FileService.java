/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.FileDao;
import java.util.ArrayList;
import po.File;

/**
 *
 * @author 蓝云甫
 */
public class FileService {
    /**
     * 创建文件
     * 返回文件在数据库中的完整信息
     * @param file
     * @return 
     */
    public static File createFile(File file){
        return FileDao.insertFile(file);
        /**
         * 在本地磁盘创建文件夹
         */
    }
    
    /**
     * 更新文件的相关信息
     * @param file
     * @return 
     */
    public static boolean updateFile(File file){
        return FileDao.updateFile(file);
    }
    public static ArrayList<File> findFileByFolderID(int folderID){
        return FileDao.findFileByFolderID(folderID);
    }
    
    public static File findFileByFileNameFolderID(int folderID, String fileName){
        return FileDao.findFileByFolderFileName(folderID, fileName);
    }
    
    /**
     * 通过用户名寻找文件
     * @param userID
     * @return
     */
    public static ArrayList<File> findFileByUserID(int userID){
        return FileDao.findFileByUserID(userID);
    }
    
    public static boolean deleteFileByFolderID(int folderID){
    	return FileDao.deleteFileByFolderID(folderID);
    }
}
