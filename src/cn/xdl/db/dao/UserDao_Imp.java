package cn.xdl.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.xdl.bean.User;
import cn.xdl.db.dbcp.DBCPUtil;

public class UserDao_Imp implements BaseUserDao {

	private static final String SQL_USER_LOGIN = "select ismanager from usertable where username=? and password=?";
	private static final String SQL_USER_INSERT = "insert into usertable values(usert.nextval,?,?,2)";
	private static final String SQL_USER_DELETE_BY_USERNAME = "delete from usertable where username=?";
	private static final String SQL_USER_UPDATE_S = "update usertable set password=? where username=? and password=?";
	private static final String SQL_USER_UPDATE_M = "update usertable set password=? where username=?";
	private static final String SQL_USER_SELECT_USER_BY_NAME = "select * from usertable where username=?";
	private static final String SQL_USER_SELECT_ALL = "select * from usertable";

	@Override
	public int login(User user) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
			state = conn.prepareStatement(SQL_USER_LOGIN);
			// 填充预编译的参数 - 帐号
			state.setString(1, user.getUsername());
			// 填充预编译的参数 - 密码
			state.setString(2, user.getPassword());
			// 执行SQL语句, 并得到结果集
			result = state.executeQuery();
			// 判断游标是否可以下移(是否存在一行查询的结果)
			if (result.next()) {
				// 登陆成功 , 获取 查询的ismanager列的信息
				int m = result.getInt("ismanager");
				// 根据成功的信息, 返回对应的管理员标记
				return m;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, result);
		}
		return -1;
	}

	@Override
	public boolean insert(User user) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
			state = conn.prepareStatement(SQL_USER_INSERT);
			state.setString(1, user.getUsername());
			state.setString(2, user.getPassword());
			return state.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}

	@Override
	public boolean delete(String username) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
			state = conn.prepareStatement(SQL_USER_DELETE_BY_USERNAME);
			state.setString(1, username);
			return state.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}

	@Override
	public boolean studentUpdate(User oldUser, User newUser) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
			state = conn.prepareStatement(SQL_USER_UPDATE_S);
			state.setString(1, newUser.getPassword());
			state.setString(2, oldUser.getUsername());
			state.setString(3, oldUser.getPassword());

			return state.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}

	@Override
	public boolean managerUpdate(User user) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
			state = conn.prepareStatement(SQL_USER_UPDATE_M);
			state.setString(1, user.getPassword());
			state.setString(2, user.getUsername());

			return state.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, null);
		}
		return false;
	}

	@Override
	public User findUserByName(String username) {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
			state = conn.prepareStatement(SQL_USER_SELECT_USER_BY_NAME);
			state.setString(1, username);
			result = state.executeQuery();
			if (result.next()) {
				int isManager = result.getInt("isManager");
				String password = result.getString("password");
				int id = result.getInt("id");
				// 查询成功
				return new User(id, username, password, isManager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, result);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		// 1. 获取连接对象
		Connection conn = DBCPUtil.getConnection();
		PreparedStatement state = null;
		ResultSet result = null;
		
		List<User> data = new ArrayList<>();
		try {
			// 根据SQL语句, 准备一个预编译的SQL执行环境对象
			state = conn.prepareStatement(SQL_USER_SELECT_ALL);
			result = state.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String username = result.getString("username");
				String password = result.getString("password");
				int isManager = result.getInt("isManager");
				// 查询成功
				data.add(new User(id, username, password, isManager));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(conn, state, result);
		}
		return data;
	}

}
