package cn.xdl.db.dao;

import java.util.List;

import cn.xdl.bean.User;

/**
 * 规范后期的  所有对于 User表格操作的 接口
 */
public interface BaseUserDao {
	
	/**
	 * 进行登录操作的方法
	 * @param user 用于登录的用户信息 包含了帐号和密码
	 * @return 登录的结果<br>
	 * 			1.	表示管理员登录成功
	 * 			2.	表示学员登录成功
	 * 			-1.	登录失败
	 */
	int login(User user);
	/**
	 * 用于添加学员的方法
	 * @param user 要添加的学员对象 包含了帐号和密码
	 * @return 添加学员的结果 , true表示添加成功
	 */
	boolean insert(User user);
	/**
	 * 用于删除学员的方法
	 * @param username	要删除的学员帐号
	 * @return 删除的结果, true表示删除成功
	 */
	boolean delete(String username);
	
	/**
	 * 学员修改自身密码的操作方法
	 * @param oldUser  旧的学员信息, 包含了帐号和旧的密码
	 * @param newUser 新的学员信息, 包含了学员的新密码
	 * @return 修改的结果, true表示密码修改成功
	 */
	boolean studentUpdate(User oldUser,User newUser);
	/**
	 * 管理员修改密码的操作方法
	 * @param user  学员信息, 包含了帐号和新的密码
	 * @return 修改的结果, true表示密码修改成功
	 */
	boolean managerUpdate(User user);
	/**
	 * 根据帐号查询单个用户的信息
	 * @param username 要查询的用户帐号
	 * @return 查询的用户, 查询失败返回null
	 */
	User findUserByName(String username);
	
	/**
	 * 查询所有的用户
	 * @return 查询的结果, 查询失败, 返回长度为0的List集合
	 */
	List<User> findAll();
}
