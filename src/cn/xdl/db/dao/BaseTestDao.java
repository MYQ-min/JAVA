package cn.xdl.db.dao;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import cn.xdl.bean.Test;
import cn.xdl.bean.TestResult;
import cn.xdl.bean.User;

/**
 * 对Test表操作的接口
 * @author Salieri
 *
 */
public interface BaseTestDao {
	
	/**
	 * 用于添加考題的方法
	 * @param test 要添加的考題对象 包含了問題和答案以及题号
	 * @return 添加考题的结果 , true表示添加成功
	 */
	boolean insert(Test test);
	/**
	 * 用于删除考題的方法
	 * @param num	要删除的考題帐号
	 * @return 删除的结果, true表示删除成功
	 */
	boolean delete(int num);
	/**
	 * 管理员修改考题的操作方法
	 * @param oldnum  旧的考题信息, 包含了旧的考题账问题以及答案
	 * @param newnum 新的考题信息, 包含了新的考题问题以及答案
	 * @return 修改的结果, true表示考题修改成功
	 */
	boolean managerUpdate(Test test);
	/**
	 * 根据题号查询单个考题的信息
	 * @param num 要查询的题号
	 * @return 查询的考题, 查询失败返回null
	 */
	Test findTestByNum(int num);
	
	/**
	 * 查询所有的题库
	 * @return 查询的结果, 查询失败, 返回长度为0的List集合
	 */
	List<Test> findAll();
	/**
	 * 用于批量导入考试题目
	 * @param 导入题目文件对象
	 * @return 导入的结果, true表示导入成功
	 */
	boolean addAllTest(Reader in);
	/**
	 * 用于返回考生考试题目
	 * @param 导入题目文件对象
	 * @return 随机题库
	 */
	List<Test> StudentTest();
	/**
	 * 用于存入考生成绩
	 * @param 成绩信息对象
	 * @return 成绩是否成功
	 */
	boolean Studenaddresult(ArrayList list);
	/**
	 * 查询用户所有成绩
	 * @return 查询的成绩集合
	 */
	List<TestResult> findResult(String name);
	/**
	 * 导出考生所有成绩
	 * @return 导出是否成功
	 */
	boolean outResult(ArrayList list);
	
}
