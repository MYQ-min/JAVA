package cn.xdl.service;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import cn.xdl.bean.Test;
import cn.xdl.bean.TestResult;
import cn.xdl.bean.User;
import cn.xdl.db.dao.BaseTestDao;
import cn.xdl.db.dao.TestDao_Impl;

public class TestService {
	private static BaseTestDao dao = new TestDao_Impl();
	/**
	 * 用于添加题目的方法
	 * @param test 要添加的题目对象 包含了问题，答案，分数，题号
	 * @return 添加题目的结果 , true表示添加成功
	 */
	public static boolean insert(Test test) {
		return dao.insert(test);
	}
	/**
	 * 用于删除题目的方法
	 * @param num	要删除的题号
	 * @return 删除的结果, true表示删除成功
	 */
	public static boolean delete(int num) {
		return dao.delete(num);
	}
	/**
	 * 用于修改题目的方法
	 * @param test 修改的对象，里面包含了修改的内容和修改的题号
	 * @return 如果修改成功，返回为true，否则返回false
	 */
	public static boolean managerUpdate(Test test){
		return dao.managerUpdate(test);
	}
	/**
	 * 根据题号搜索
	 * @param num 搜索的题号
	 * @return 返回搜索的题目
	 */
	public static Test findTestByNum(int num) {
		return dao.findTestByNum(num);
	}
	/**
	 * 查询所有的题目
	 * @return 返回题目的集合
	 */
	public static List<Test> findAll() {
		return dao.findAll();
	}
	/**
	 * 批量导入所有的题目
	 * @return 返回导入的结果
	 */
	public static boolean addAllTest(Reader in) {
		return dao.addAllTest(in);
	}
	/**
	 * 学生考试信息
	 * @return
	 */
	public static List<Test> StudentTest() {
		return dao.StudentTest();
	}
	/**
	 * 
	 * @param 用户的姓名和分数信息
	 * @return 上传信息结果
	 */
	public static boolean Studenaddresult(ArrayList list) {
		return dao.Studenaddresult(list);
	}
	/**
	 * 用户查询历史成绩
	 * @param name 用户名
	 * @return 用户成绩集合
	 */
	public static List<TestResult> findResult(String name) {
		return dao.findResult(name);
	}
	/**
	 * 用考生导出历史成绩
	 * @param name 路径
	 * @return true 导出成绩成功，false 导出失败
	 */
	public static boolean outResult(ArrayList list) {
		return dao.outResult(list);
	}
}
