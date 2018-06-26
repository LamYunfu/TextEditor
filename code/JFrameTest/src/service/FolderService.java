package service;

import dao.FolderDao;
import po.Folder;
import po.User;

public class FolderService {
	/**
	 *1. 调用dao层相关方法，将新建文件夹信息写入数据库
	 *2.在本地磁盘创建相应的文件夹
	 *folderName: 新建文件夹名称
	 *userID: 文件夹拥有者用户id
	 *返回：插入是否成功
	 */
	public static Folder createFolder(String folderName, User user){
		Folder folder = new Folder();
		folder.setName(folderName);
		folder.setuID(user.getId());
		//调用方法在本地创建文件夹
		return FolderDao.insertFolder(folder);
	}
	public static Folder searchFolderByNameUserID(int userID, String folderName){
            return FolderDao.findFolderByFolderName(userID, folderName);
        }
	/**
	 * 根据文件夹在数据库中得到文件夹中的文件列表
	 */
	public static void getFolderFile(){
		
	}
	
	public static void deleteFolder(Folder folder){
		FolderDao.deleteFolder(folder);
		//调用文件操作的方法删除本地文件夹
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
