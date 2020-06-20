package cn.xdl.db.dao;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import cn.xdl.bean.Test;
import cn.xdl.bean.TestResult;
import cn.xdl.bean.User;

/**
 * ��Test������Ľӿ�
 * @author Salieri
 *
 */
public interface BaseTestDao {
	
	/**
	 * ������ӿ��}�ķ���
	 * @param test Ҫ��ӵĿ��}���� �����ˆ��}�ʹ��Լ����
	 * @return ��ӿ���Ľ�� , true��ʾ��ӳɹ�
	 */
	boolean insert(Test test);
	/**
	 * ����ɾ�����}�ķ���
	 * @param num	Ҫɾ���Ŀ��}�ʺ�
	 * @return ɾ���Ľ��, true��ʾɾ���ɹ�
	 */
	boolean delete(int num);
	/**
	 * ����Ա�޸Ŀ���Ĳ�������
	 * @param oldnum  �ɵĿ�����Ϣ, �����˾ɵĿ����������Լ���
	 * @param newnum �µĿ�����Ϣ, �������µĿ��������Լ���
	 * @return �޸ĵĽ��, true��ʾ�����޸ĳɹ�
	 */
	boolean managerUpdate(Test test);
	/**
	 * ������Ų�ѯ�����������Ϣ
	 * @param num Ҫ��ѯ�����
	 * @return ��ѯ�Ŀ���, ��ѯʧ�ܷ���null
	 */
	Test findTestByNum(int num);
	
	/**
	 * ��ѯ���е����
	 * @return ��ѯ�Ľ��, ��ѯʧ��, ���س���Ϊ0��List����
	 */
	List<Test> findAll();
	/**
	 * �����������뿼����Ŀ
	 * @param ������Ŀ�ļ�����
	 * @return ����Ľ��, true��ʾ����ɹ�
	 */
	boolean addAllTest(Reader in);
	/**
	 * ���ڷ��ؿ���������Ŀ
	 * @param ������Ŀ�ļ�����
	 * @return ������
	 */
	List<Test> StudentTest();
	/**
	 * ���ڴ��뿼���ɼ�
	 * @param �ɼ���Ϣ����
	 * @return �ɼ��Ƿ�ɹ�
	 */
	boolean Studenaddresult(ArrayList list);
	/**
	 * ��ѯ�û����гɼ�
	 * @return ��ѯ�ĳɼ�����
	 */
	List<TestResult> findResult(String name);
	/**
	 * �����������гɼ�
	 * @return �����Ƿ�ɹ�
	 */
	boolean outResult(ArrayList list);
	
}
