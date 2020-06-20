package cn.xdl.db.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.xdl.bean.Test;
import cn.xdl.bean.TestResult;
import cn.xdl.bean.User;
import cn.xdl.db.dbcp.DBCPUtil;

public class TestDao_Impl implements BaseTestDao {

	private static final String SQL_TEST_INSERT = "insert into test values(test1.nextval,test2.nextval,?,?,?,?,?,?,?)";
	private static final String SQL_TEST_DELETE_BY_NUM = "delete from test where id=?";
	private static final String SQL_TEST_UPDATE_M = "update test set question=?,a=?,b=?,c=?,d=?,score=?,right=? where num=?";
	private static final String SQL_TEST_SELECT_USER_BY_Num = "select * from test where num=?";
	private static final String SQL_TEST_SELECT_ALL = "select * from test";
	private static final String SQL_TEST_TEST = "select * from (select question,a,b,c,d,score,right from test order by dbms_random.value) where rownum<=10";
	private static final String SQL_TEST_ADD_RESULT = "insert into testresult values(testresult1.nextval,to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),?,?)";
	private static final String SQL_TEST_CHECK_RESULT = "select * from TESTRESULT where name=? order by TESTDATE desc";
	/**
	 * ����Ա������Ŀ�Ĺ���
	 * return ������Ŀ�Ľ����true ��ʾ��Ŀ���ӳɹ� false ��Ŀ����ʧ��
	 */
	@Override
	public boolean insert(Test test) {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		// ����SQL���, ׼��һ��Ԥ�����SQLִ�л�������
		try {
			state = conn.prepareStatement(SQL_TEST_INSERT);
			// ���Ԥ����Ĳ��� - ���}
			state.setString(1, test.getQuestion());
			// ���Ԥ����Ĳ��� - ��
			state.setString(2, test.getA());
			state.setString(3, test.getB());
			state.setString(4, test.getC());
			state.setString(5, test.getD());
			
			// ���Ԥ����Ĳ��� - �֔�
			state.setInt(6, test.getScore());
			state.setString(7, test.getRight());
			return state.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}
	/**
	 * ����Աͨ�����ɾ������Ĺ���
	 * return ��Ŀ��ɾ���Ľ�� true ��Ŀɾ���ɹ� false ��Ŀɾ��ʧ��
	 */
	@Override
	public boolean delete(int num) {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			// ����SQL���, ׼��һ��Ԥ�����SQLִ�л�������
			state = conn.prepareStatement(SQL_TEST_DELETE_BY_NUM);
			state.setInt(1, num);
			return state.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}
	/**
	 * ����Աͨ������޸���Ŀ����
	 * return �޸���Ŀ�Ľ�� true ��ʾ��Ŀ�޸ĳɹ� false ��ʾ��Ŀ�޸�ʧ��
	 */
	@Override
	public boolean managerUpdate(Test test) {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			// ����SQL���, ׼��һ��Ԥ�����SQLִ�л�������
			state = conn.prepareStatement(SQL_TEST_UPDATE_M);
			state.setString(1, test.getQuestion());
			state.setString(2, test.getA());
			state.setString(3, test.getB());
			state.setString(4, test.getC());
			state.setString(5, test.getD());
			state.setInt(6, test.getScore());
			state.setString(7, test.getRight());
			state.setInt(8, test.getNum());
			return state.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}
	/**
	 * ͨ����Ų�ѯ����
	 * return ����һ�������Ա������������˿�������⣬�𰸣��Լ�����
	 */
	@Override
	public Test findTestByNum(int num) {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			// ����SQL���, ׼��һ��Ԥ�����SQLִ�л�������
			state = conn.prepareStatement(SQL_TEST_SELECT_USER_BY_Num);
			state.setInt(1, num);
			result = state.executeQuery();
			if (result.next()) {
				String question = result.getString("question");
				String a = result.getString("a");
				String b = result.getString("b");
				String c = result.getString("c");
				String d = result.getString("d");
				int id = result.getInt("id");
				int score = result.getInt("score");
				String right = result.getString("right");
				// ��ѯ�ɹ�
				return new Test(id, num ,question, a,b,c,d,score,right);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, result);
		}
		return null;
	}
	/**
	 * ��ѯ������Ŀ��Ϣ
	 * return ������Ŀ��Ϣ����
	 */
	@Override
	public List<Test> findAll() {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		List<Test> data = new ArrayList<>();
		try {
			state = conn.prepareStatement(SQL_TEST_SELECT_ALL);
			result = state.executeQuery();
			while(result.next()){
				//data.add(new Test(result.getInt("id"),result.getInt("num"),result.getString("question"),result.getString("answer"),result.getInt("score")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * ����Ա����������⹦��
	 * return true������������ɹ� false ������������ʧ��
	 */
	@Override
	public boolean addAllTest(Reader in) {
		BufferedReader bf = new BufferedReader(in);
		boolean flag = false;
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			String sql = "";
			// ����SQL���, ׼��һ��Ԥ�����SQLִ�л�������
			while((sql=bf.readLine())!=null){
				try {
					state = conn.prepareStatement(sql);
					flag = state.executeUpdate() > 0 ? true : false;
				} catch (SQLException e) {
					e.printStackTrace();
					flag = false;
				} 
			}
			if(flag){
				return true;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}
	/**
	 * ѧ�����ԣ���������е�10����Ŀ��ŵ�������,Ȼ��Ѽ��Ϸ��͵��ͻ���
	 */
	@Override
	public List<Test> StudentTest() {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		List<Test> data = new ArrayList<>();
		try {
			state = conn.prepareStatement(SQL_TEST_TEST);
			result = state.executeQuery();
			while(result.next()){
				data.add(new Test(result.getString("question"),result.getString("a"),result.getString("b"),result.getString("c"),
						result.getString("d"),result.getInt("score"),result.getString("right")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * �Ѵӿͻ����ϴ������Ŀ��Գɼ���Ϣ����������ݿ�
	 * return ���ݴ�ŵĽ�� true���ݴ�ųɹ� false ���ݴ��ʧ��
	 */
	@Override
	public boolean Studenaddresult(ArrayList list) {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		// ����SQL���, ׼��һ��Ԥ�����SQLִ�л�������
		try {
			state = conn.prepareStatement(SQL_TEST_ADD_RESULT);
			// ���Ԥ����Ĳ��� - �û���
			state.setString(1, (String) list.get(0));
			// ���Ԥ����Ĳ��� - ����
			state.setInt(2, (int) list.get(1));
			return state.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}
	/**
	 * ��ѯ���гɼ��ķ���
	 * return �����������гɼ��ļ��ϣ������������������ڣ����Է���
	 */
	@Override
	public List<TestResult> findResult(String name) {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		List<TestResult> data = new ArrayList<>();
		
			try {
				state = conn.prepareStatement(SQL_TEST_CHECK_RESULT);
				state.setString(1, name);
				result = state.executeQuery();
				while(result.next()){
					data.add(new TestResult(result.getString("testdate"), name, result.getShort("score")));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		return data;
	}
	/**
	 * ������ʷ�ɼ��ķ���
	 * return ���ص����Ľ�����͸��ͻ��ˣ�true ��ʾ�����ɹ���false��ʾ����ʧ��
	 */
	@Override
	public boolean outResult(ArrayList list) {
		// 1. ��ȡ���Ӷ���
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		PrintStream fos=null;
			try {
				try {
					fos = new PrintStream(new FileOutputStream((String) list.get(1)));
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
				state = conn.prepareStatement(SQL_TEST_CHECK_RESULT);
				state.setString(1, (String) list.get(0));
				result = state.executeQuery();
				while(result.next()){
					TestResult te = new TestResult(result.getString("testdate"), (String) list.get(1), result.getShort("score"));
					fos.println(te.toString());
				}
				return true;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return false;
	}
}
