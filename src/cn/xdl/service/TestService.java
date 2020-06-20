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
	 * ���������Ŀ�ķ���
	 * @param test Ҫ��ӵ���Ŀ���� ���������⣬�𰸣����������
	 * @return �����Ŀ�Ľ�� , true��ʾ��ӳɹ�
	 */
	public static boolean insert(Test test) {
		return dao.insert(test);
	}
	/**
	 * ����ɾ����Ŀ�ķ���
	 * @param num	Ҫɾ�������
	 * @return ɾ���Ľ��, true��ʾɾ���ɹ�
	 */
	public static boolean delete(int num) {
		return dao.delete(num);
	}
	/**
	 * �����޸���Ŀ�ķ���
	 * @param test �޸ĵĶ�������������޸ĵ����ݺ��޸ĵ����
	 * @return ����޸ĳɹ�������Ϊtrue�����򷵻�false
	 */
	public static boolean managerUpdate(Test test){
		return dao.managerUpdate(test);
	}
	/**
	 * �����������
	 * @param num ���������
	 * @return ������������Ŀ
	 */
	public static Test findTestByNum(int num) {
		return dao.findTestByNum(num);
	}
	/**
	 * ��ѯ���е���Ŀ
	 * @return ������Ŀ�ļ���
	 */
	public static List<Test> findAll() {
		return dao.findAll();
	}
	/**
	 * �����������е���Ŀ
	 * @return ���ص���Ľ��
	 */
	public static boolean addAllTest(Reader in) {
		return dao.addAllTest(in);
	}
	/**
	 * ѧ��������Ϣ
	 * @return
	 */
	public static List<Test> StudentTest() {
		return dao.StudentTest();
	}
	/**
	 * 
	 * @param �û��������ͷ�����Ϣ
	 * @return �ϴ���Ϣ���
	 */
	public static boolean Studenaddresult(ArrayList list) {
		return dao.Studenaddresult(list);
	}
	/**
	 * �û���ѯ��ʷ�ɼ�
	 * @param name �û���
	 * @return �û��ɼ�����
	 */
	public static List<TestResult> findResult(String name) {
		return dao.findResult(name);
	}
	/**
	 * �ÿ���������ʷ�ɼ�
	 * @param name ·��
	 * @return true �����ɼ��ɹ���false ����ʧ��
	 */
	public static boolean outResult(ArrayList list) {
		return dao.outResult(list);
	}
}
