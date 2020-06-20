package cn.xdl.service;

import java.util.List;

import cn.xdl.bean.User;
import cn.xdl.db.dao.BaseUserDao;
import cn.xdl.db.dao.UserDao_Imp;

public class UserService {

	private static BaseUserDao dao = new UserDao_Imp();
	
	/**
	 * 进行登录操作的方法
	 * @param user 用于登录的用户信息 包含了帐号和密码
	 * @return 登录的结果<br>
	 * 			1.	表示管理员登录成功
	 * 			2.	表示学员登录成功
	 * 			-1.	登录失败
	 */
	public static int login(User user) {
		return dao.login(user);
	}
	/**
	 * 用于添加学员的方法
	 * @param user 要添加的学员对象 包含了帐号和密码
	 * @return 添加学员的结果 , true表示添加成功
	 */
	public static boolean insert(User user) {
		return dao.insert(user);
	}
	/**
	 * 用于删除学员的方法
	 * @param username	要删除的学员帐号
	 * @return 删除的结果, true表示删除成功
	 */
	public static boolean delete(String username) {
		if("admin".equals(username)) {
			//管理员不允许 删除
			return false;
		}
		return dao.delete(username);
	}
	
	/**
	 * 学员修改自身密码的操作方法
	 * @param oldUser  旧的学员信息, 包含了帐号和旧的密码
	 * @param newUser 新的学员信息, 包含了学员的新密码
	 * @return 修改的结果, true表示密码修改成功
	 */
	public static boolean studentUpdate(User oldUser,User newUser) {
		if("admin".equals(oldUser.getUsername())) {
			//管理员不允许 修改
			return false;
		}
		return dao.studentUpdate(oldUser, newUser);
	}
	/**
	 * 管理员修改密码的操作方法
	 * @param user  学员信息, 包含了帐号和新的密码
	 * @return 修改的结果, true表示密码修改成功
	 */
	public static boolean managerUpdate(User user) {
		if("admin".equals(user.getUsername())) {
			//管理员不允许 修改
			return false;
		}
		return dao.managerUpdate(user);
	}
	/**
	 * 根据帐号查询单个用户的信息
	 * @param username 要查询的用户帐号
	 * @return 查询的用户, 查询失败返回null
	 */
	public static User findUserByName(String username) {
		return dao.findUserByName(username);
	}
	
	/**
	 * 查询所有的用户
	 * @return 查询的结果, 查询失败, 返回长度为0的List集合
	 */
	public static List<User> findAll(){
		return dao.findAll();
	}
}
