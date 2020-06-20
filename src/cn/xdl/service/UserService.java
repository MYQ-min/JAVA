package cn.xdl.service;

import java.util.List;

import cn.xdl.bean.User;
import cn.xdl.db.dao.BaseUserDao;
import cn.xdl.db.dao.UserDao_Imp;

public class UserService {

	private static BaseUserDao dao = new UserDao_Imp();
	
	/**
	 * ���е�¼�����ķ���
	 * @param user ���ڵ�¼���û���Ϣ �������ʺź�����
	 * @return ��¼�Ľ��<br>
	 * 			1.	��ʾ����Ա��¼�ɹ�
	 * 			2.	��ʾѧԱ��¼�ɹ�
	 * 			-1.	��¼ʧ��
	 */
	public static int login(User user) {
		return dao.login(user);
	}
	/**
	 * �������ѧԱ�ķ���
	 * @param user Ҫ��ӵ�ѧԱ���� �������ʺź�����
	 * @return ���ѧԱ�Ľ�� , true��ʾ��ӳɹ�
	 */
	public static boolean insert(User user) {
		return dao.insert(user);
	}
	/**
	 * ����ɾ��ѧԱ�ķ���
	 * @param username	Ҫɾ����ѧԱ�ʺ�
	 * @return ɾ���Ľ��, true��ʾɾ���ɹ�
	 */
	public static boolean delete(String username) {
		if("admin".equals(username)) {
			//����Ա������ ɾ��
			return false;
		}
		return dao.delete(username);
	}
	
	/**
	 * ѧԱ�޸���������Ĳ�������
	 * @param oldUser  �ɵ�ѧԱ��Ϣ, �������ʺź;ɵ�����
	 * @param newUser �µ�ѧԱ��Ϣ, ������ѧԱ��������
	 * @return �޸ĵĽ��, true��ʾ�����޸ĳɹ�
	 */
	public static boolean studentUpdate(User oldUser,User newUser) {
		if("admin".equals(oldUser.getUsername())) {
			//����Ա������ �޸�
			return false;
		}
		return dao.studentUpdate(oldUser, newUser);
	}
	/**
	 * ����Ա�޸�����Ĳ�������
	 * @param user  ѧԱ��Ϣ, �������ʺź��µ�����
	 * @return �޸ĵĽ��, true��ʾ�����޸ĳɹ�
	 */
	public static boolean managerUpdate(User user) {
		if("admin".equals(user.getUsername())) {
			//����Ա������ �޸�
			return false;
		}
		return dao.managerUpdate(user);
	}
	/**
	 * �����ʺŲ�ѯ�����û�����Ϣ
	 * @param username Ҫ��ѯ���û��ʺ�
	 * @return ��ѯ���û�, ��ѯʧ�ܷ���null
	 */
	public static User findUserByName(String username) {
		return dao.findUserByName(username);
	}
	
	/**
	 * ��ѯ���е��û�
	 * @return ��ѯ�Ľ��, ��ѯʧ��, ���س���Ϊ0��List����
	 */
	public static List<User> findAll(){
		return dao.findAll();
	}
}
