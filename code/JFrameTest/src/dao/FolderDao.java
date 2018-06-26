package dao;

import java.sql.*;
import java.util.ArrayList;

import Frame.DataBaseInit;
import po.Folder;

/*
 * 文件夹数据库操作
 */
public class FolderDao {

   
    public static Folder findFolderByFolderName(int userID, String folderName){
        Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from folder where owner = ? and fName = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setInt(1, userID);
                        pst.setString(2, folderName);
			rs = pst.executeQuery();
			
			try {
                            if(rs.next()){
                            Folder folder = new Folder();
                            folder.setName(rs.getString("fName"));
                            folder.setId(rs.getInt("id"));
                            folder.setuID(rs.getInt("owner"));
                            return folder;
                            }
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}catch(SQLException e){
			System.out.println("查找文件夹失败！");
			e.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return null;
    }
	/*
	 * 根据用户的编号寻找对应的文件夹并且返回
	 */
	public static ArrayList<Folder> findFolderByUserID(int userID){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Folder> list = new ArrayList<Folder>();
		String sql = "select * from folder where owner = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			if(rs != null){
				try {
					while(rs.next()){
						Folder folder = new Folder();
						folder.setName(rs.getString("fName"));
						folder.setId(rs.getInt("id"));
						folder.setuID(rs.getInt("owner"));
						list.add(folder);
					}
					return list;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(SQLException e){
			System.out.println("查找文件夹失败！");
			e.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/*
	 * 在文件夹表中插入新建的文件夹信息
	 */
	public static Folder insertFolder(Folder folder){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into folder (fName, owner)values(?, ?);";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, folder.getName());
			pst.setInt(2, folder.getOwnerID());
			pst.execute();
                        folder = findFolderByFolderName(folder.getOwnerID(), folder.getName());
		}catch(SQLException e){
			System.out.println("插入文件夹表失败！");
			e.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
                return folder;
	}
	
	/*
	 * 根据文件夹的名称
	 * 删除选中的文件夹
	 */
	public static boolean deleteFolder(Folder folder){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from folder where fName = ? and owner = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, folder.getName());
			pst.setInt(2, folder.getOwnerID());
			pst.execute();
		}catch(SQLException ex){
			System.out.println("删除失败");
			ex.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Folder f = new Folder();
		f.setName("好啊哈哈");
		f.setuID(2);
		insertFolder(f);
		ArrayList<Folder> list = findFolderByUserID(2);
		for(int i = 0;i < list.size();i++){
			f = list.get(i);
			System.out.println(f.getId() + " " + f.getName() + " " + f.getOwnerID());
		}
	}

}
