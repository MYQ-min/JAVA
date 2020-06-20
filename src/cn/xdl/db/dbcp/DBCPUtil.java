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
			//1.	��Ҫ�õ�Properties�ļ���������
			InputStream is = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcp.properties");
			//2.	����properties����,  ���ڶ�ȡ��һ���õ�����
			Properties ps = new Properties();
			//3.	ʹ������������properties����, ���ر��ص�properties�ļ�
			ps.load(is);
			//4.	ʹ�����ӳع�����, �������ӳض���
			dataSource = BasicDataSourceFactory.createDataSource(ps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ���ڴ����ӳ���, ��ȡһ�����Ӷ���Ĳ�������
	 * @return һ�����ݿ�����Ӷ���
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
	 * �����ͷ����ݿ��������Դ
	 * @param conn Ҫ�ͷŵ����Ӷ���
	 * @param state  Ҫ�ͷŵ�ִ�л�������
	 * @param result Ҫ�ͷŵĽ��������
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
