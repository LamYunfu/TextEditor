package Frame;
import java.sql.*;
public class DataBaseInit {
	/*
	 *连接数据库
	 *返回数据库连接对象
	 */
	public static Connection getConnection(){
		//连接数据库
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:C:/Users/蓝云甫/test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  //  System.out.println("Opened database successfully");    
	    return c;
	}
	
	/*
	 * 初始化数据库
	 */
	public static void initDataBase(){
		//获得数据库连接
		Connection c = getConnection();
		Statement stat = null;
		//创建用户信息表
		String sql1 = "DROP TABLE IF EXISTS userInfo;" + 
					"create table if not exists userInfo(" +
					"id INTEGER primary key AUTOINCREMENT," + 
					"uName varchar(20),"+
					"password varchar(20)," + 
					"loginID varchar(20));";
		//创建文件夹表
		String sql2 = "DROP TABLE IF EXISTS folder;" + 
					"create table if not exists folder(" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT," +
					"fName varchar(25)," +
					"owner INTEGER);";
		//创建文件表
		String sql3 = "DROP TABLE IF EXISTS file;"
				+ "create table if not exists file("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "title varchar(25),"
				+ "tag varchar(25),"
				+ "createTime time,"
				+ "modifiedTime time,"
				+ "fileSize varchar(15),"
				+ "folder INTEGER);";
		
		//在用户表中插入原始用户编号
		String sql4 = "insert into userInfo (id, loginID)values(1, " + 100000 + ");";
		String sql5 = "insert into userInfo (loginID, password, uName) values('15020031023', '15020031023','LanYunFu');";
		try {
			stat = c.createStatement();
			stat.executeUpdate(sql1 + sql2 + sql3 + sql4 + sql5);
//			System.out.println(c == null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库初始化失败!");
			e.printStackTrace();
		}finally{
			try{
				if(stat != null){
					stat.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initDataBase();
//		Connection c = getConnection();
	}

}
