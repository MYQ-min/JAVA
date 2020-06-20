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
	 * 管理员增加题目的功能
	 * return 增加题目的结果，true 表示题目增加成功 false 题目增加失败
	 */
	@Override
	public boolean insert(Test test) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		// 根据SQL语句, 准备一个预编译的SQL执行环境对象
		try {
			state = conn.prepareStatement(SQL_TEST_INSERT);
			// 填充预编译的参数 - 問題
			state.setString(1, test.getQuestion());
			// 填充预编译的参数 - 答案
			state.setString(2, test.getA());
			state.setString(3, test.getB());
			state.setString(4, test.getC());
			state.setString(5, test.getD());
			
			// 填充预编译的参数 - 分數
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
	 * 管理员通过题号删除考题的功能
	 * return 题目被删除的结果 true 题目删除成功 false 题目删除失败
	 */
	@Override
	public boolean delete(int num) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
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
	 * 管理员通过题号修改题目功能
	 * return 修改题目的结果 true 表示题目修改成功 false 表示题目修改失败
	 */
	@Override
	public boolean managerUpdate(Test test) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
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
	 * 通过题号查询考题
	 * return 返回一个考题成员对象，里面包含了考题的问题，答案，以及分数
	 */
	@Override
	public Test findTestByNum(int num) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
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
				// 查询成功
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
	 * 查询所有题目信息
	 * return 所有题目信息集合
	 */
	@Override
	public List<Test> findAll() {
		// 1. 获取连接对象
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
	 * 管理员批量导入题库功能
	 * return true试题批量导入成功 false 试题批量导入失败
	 */
	@Override
	public boolean addAllTest(Reader in) {
		BufferedReader bf = new BufferedReader(in);
		boolean flag = false;
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			String sql = "";
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
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
	 * 学生考试，将随机抽中的10道题目存放到集合中,然后把集合发送到客户端
	 */
	@Override
	public List<Test> StudentTest() {
		// 1. 获取连接对象
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
	 * 把从客户端上传上来的考试成绩信息，存放入数据库
	 * return 数据存放的结果 true数据存放成功 false 数据存放失败
	 */
	@Override
	public boolean Studenaddresult(ArrayList list) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		// 根据SQL语句, 准备一个预编译的SQL执行环境对象
		try {
			state = conn.prepareStatement(SQL_TEST_ADD_RESULT);
			// 填充预编译的参数 - 用户名
			state.setString(1, (String) list.get(0));
			// 填充预编译的参数 - 分数
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
	 * 查询所有成绩的方法
	 * return 包含考生所有成绩的集合，包含姓名，考试日期，考试分数
	 */
	@Override
	public List<TestResult> findResult(String name) {
		// 1. 获取连接对象
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
	 * 导出历史成绩的方法
	 * return 返回导出的结果发送给客户端，true 表示导出成功，false表示导出失败
	 */
	@Override
	public boolean outResult(ArrayList list) {
		// 1. 获取连接对象
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
