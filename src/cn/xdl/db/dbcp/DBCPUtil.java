package cn.xdl.db.dbcp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil {

	private static DataSource dataSource;
	
	static {
		try {
			//1.	需要得到Properties文件的输入流
			InputStream is = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcp.properties");
			//2.	创建properties对象,  用于读取第一步得到的流
			Properties ps = new Properties();
			//3.	使用上述创建的properties对象, 加载本地的properties文件
			ps.load(is);
			//4.	使用连接池工厂类, 创建连接池对象
			dataSource = BasicDataSourceFactory.createDataSource(ps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 用于从连接池中, 获取一个连接对象的操作方法
	 * @return 一个数据库的连接对象
	 */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 用于释放数据库的链接资源
	 * @param conn 要释放的连接对象
	 * @param state  要释放的执行环境对象
	 * @param result 要释放的结果集对象
	 */
	public static void close(Connection conn,Statement state,ResultSet result) {
		try {
			if(conn!=null) {
				conn.close();
				conn=null;
			}
			if(state!=null) {
				state.close();
				state=null;
			}
			if(result!=null) {
				result.close();
				result = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
