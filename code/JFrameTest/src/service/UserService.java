package service;

import po.User;
import dao.UserDao;
public class UserService {
	private UserDao ud = new UserDao();
	
	/**
	 * 判断用户是否能够登录
	 * @param user
	 * @return
	 */
	public User userLogin(User user){
               return ud.userQuery(user);
	}
	
	/**
	 * 在数据表中插入新用户
	 * @param user
	 * @return
	 */
	public String userRegist(User user){
		return ud.insertUserInfo(user);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
