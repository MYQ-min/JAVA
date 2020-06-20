package cn.xdl.db.dao;

import java.util.List;

import cn.xdl.bean.User;

/**
 * �淶���ڵ�  ���ж��� User�������� �ӿ�
 */
public interface BaseUserDao {
	
	/**
	 * ���е�¼�����ķ���
	 * @param user ���ڵ�¼���û���Ϣ �������ʺź�����
	 * @return ��¼�Ľ��<br>
	 * 			1.	��ʾ����Ա��¼�ɹ�
	 * 			2.	��ʾѧԱ��¼�ɹ�
	 * 			-1.	��¼ʧ��
	 */
	int login(User user);
	/**
	 * �������ѧԱ�ķ���
	 * @param user Ҫ��ӵ�ѧԱ���� �������ʺź�����
	 * @return ���ѧԱ�Ľ�� , true��ʾ��ӳɹ�
	 */
	boolean insert(User user);
	/**
	 * ����ɾ��ѧԱ�ķ���
	 * @param username	Ҫɾ����ѧԱ�ʺ�
	 * @return ɾ���Ľ��, true��ʾɾ���ɹ�
	 */
	boolean delete(String username);
	
	/**
	 * ѧԱ�޸���������Ĳ�������
	 * @param oldUser  �ɵ�ѧԱ��Ϣ, �������ʺź;ɵ�����
	 * @param newUser �µ�ѧԱ��Ϣ, ������ѧԱ��������
	 * @return �޸ĵĽ��, true��ʾ�����޸ĳɹ�
	 */
	boolean studentUpdate(User oldUser,User newUser);
	/**
	 * ����Ա�޸�����Ĳ�������
	 * @param user  ѧԱ��Ϣ, �������ʺź��µ�����
	 * @return �޸ĵĽ��, true��ʾ�����޸ĳɹ�
	 */
	boolean managerUpdate(User user);
	/**
	 * �����ʺŲ�ѯ�����û�����Ϣ
	 * @param username Ҫ��ѯ���û��ʺ�
	 * @return ��ѯ���û�, ��ѯʧ�ܷ���null
	 */
	User findUserByName(String username);
	
	/**
	 * ��ѯ���е��û�
	 * @return ��ѯ�Ľ��, ��ѯʧ��, ���س���Ϊ0��List����
	 */
	List<User> findAll();
}
