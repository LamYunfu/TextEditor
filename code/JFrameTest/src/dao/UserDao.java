package dao;

import java.sql.*;

import Frame.DataBaseInit;
import po.User;

/*
 * 用户信息数据库操作
 */
public class UserDao {
	/*
	 * 给出当前用户的ID
	 * 设置下一个注册用户的ID
	 * 存储在用户数据表的第一个位置
	 */
	public String getCurrentLoginID(Connection c){
		//首先拿到当前最后一个用户的ID然后再自加1，再写回数据库
		PreparedStatement pst = null;
		String sql = "select * from userInfo";
		String currentID = null;
		try{
			pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			String currentLast = null;					//当前最后一个ID号码
			if(rs.next()){
				currentLast = rs.getString("loginID");
				currentID = currentLast;
				System.out.println(currentID);
			}
			int next = Integer.parseInt(currentLast);
			currentLast = (++next) + "";
	//		System.out.println(currentLast);
			sql = "update userInfo set loginID = ? where id = 1;";
			pst = c.prepareStatement(sql);
			pst.setString(1, currentLast);
			pst.executeUpdate();
		}catch(SQLException ex){
			System.out.println("拿出当前编号失败");
			ex.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return currentID;
	}
	
	/*
	 * 查询userInfo数据表
	 * 返回true：代表用户存在
	 * 返回false: 代表用户不存在
	 */
	public User userQuery(User user){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from userInfo where loginID = ? and password = ?;";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, user.getAccount());
			pst.setString(2, user.getPassword());
			rs = pst.executeQuery();
			try {
				if(rs.next()){
					user.setId(rs.getInt("id"));
                                        user.setName(rs.getString("uName"));
					return user;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(SQLException ex){
			System.out.println("查询出错");
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
		return null;
	}
	
	/**
	 * 插入userInfo数据表
	 * 添加一个用户的信息
	 * 返回这个用户的完整信息
	 */
	public String insertUserInfo(User user){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		String currentID = getCurrentLoginID(c);
		user.setAccount(currentID);
		String sql = "insert into userInfo (uName, password, loginID) values(?, ?, ?);";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getAccount());
			pst.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("添加用户失败");
			ex.printStackTrace();
			return null;
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
		return currentID;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDao ud = new UserDao();
		User u = new User();
		u.setName("蓝云甫");
		u.setPassword("123");
	    ud.insertUserInfo(u); 
	    u.setAccount("100000");
		u = ud.userQuery(u);
		System.out.println(u == null);
		System.out.println(u.getId() + " " + u.getAccount());
	}

}
