package cn.xdl.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import cn.xdl.bean.Test;
import cn.xdl.bean.TestResult;
import cn.xdl.bean.User;
import cn.xdl.view.ClientView;

public class Main {

	/**
	 * ������������������ݵ���
	 */
	private static ObjectOutputStream oos ;
	/**
	 * ���ڴӷ������������ݵ���
	 */
	private static ObjectInputStream ois ;
	/**
	 * ��������������͵���������
	 */
	private static HashMap<String,Object> toData;
	/**
	 * ���ڴӷ������������ݵ���������
	 */
	private static String usert;
	private static HashMap<String,Object> fromData;
	private static String username = null;
	public static void main(String[] args) throws Exception{
		
		Socket socket = new Socket("localhost",10086);
		oos = new  ObjectOutputStream(socket.getOutputStream());
		ois = new  ObjectInputStream(socket.getInputStream());
		//��ʾ�û� �����˺�����, 
		User user = ClientView.welcomeView();
		username = user.getUsername();
		usert=username;
		toMessage(10001,user);
		fromMessage();
		Integer type = (Integer) fromData.get("type");
		switch (type) {
		case 20001:
			//����Ա��¼�ɹ�
			managerClient();
			break;
		case 20002:
			//ѧԱ��¼�ɹ�
			studentClient();
			break;
		case 20003:
			//��¼ʧ��
			System.out.println("���ź�, ������� ,�����ѹر�");
			break;
		}
	}

	private static void studentClient() {
		System.out.println("******\t��ӭ��¼ѧԱϵͳ\t\t******");
		while(true) {
			int menu = ClientView.studentMenuView();
			switch (menu) {
			case 0://�˳�
				System.exit(0);
				break;
			case 1://�޸�����
				User oldUser = new User(username,null);
				User newUser = ClientView.updateUserPassView_s(oldUser);
				toMessage(10002, new User[] {oldUser,newUser});
				break;
			case 2://��ʼ����
				List<Test> list =ClientView.startTest();
				toMessage(30002, list);
				break;
			case 3://��ѯ��ʷ�ɼ�
				ClientView.checkTest();
				toMessage(30003, usert);
				break;
			case 4://�������гɼ�
				String out = ClientView.outTest();
				ArrayList arr = new ArrayList();
				arr.add(usert);
				arr.add(out);
				toMessage(30004,arr);
				break;
			}
			fromMessage();
			service();
		}
	}

	@SuppressWarnings("unchecked")
	private static void service() {
		Integer type = (Integer) fromData.get("type");
		switch (type) {
		case 20004:
			//ѧԱ�޸���������ɹ�
			System.out.println("��ϲ��, �����޸ĳɹ��� ! ");
			break;
		case 20005:
			//ѧԱ�޸���������ʧ��we
			System.out.println("���ź�, �����޸�ʧ����, ԭ�����������");
			break;
		case 20006:
			//����Ա���ӿ���ѧԱ�ɹ�
			System.out.println("��ϲ��, ѧԱ���ӳɹ� ! ");
			break;
		case 20007:
			//����Ա���ӿ���ѧԱʧ��
			System.out.println("���ź�, ѧԱ����ʧ��");
			break;
		case 20008:
			//����Աɾ������ѧԱ�ɹ�
			System.out.println("��ϲ��, ѧԱɾ���ɹ� ! ");
			break;
		case 20009:
			//����Աɾ������ѧԱʧ��
			System.out.println("���ź�, ѧԱɾ��ʧ��");
			break;
		case 20010:
			//����Ա�޸Ŀ���ѧԱ�ɹ�
			System.out.println("��ϲ��, ѧԱ�����޸ĳɹ� ! ");
			break;
		case 20011:
			//����Ա�޸Ŀ���ѧԱʧ��
			System.out.println("���ź�, ѧԱ�����޸�ʧ��");
			break;
		case 20012:
			//����Ա��ѯ����ѧԱ�ɹ�
			User user = (User) fromData.get("data");
			System.out.println("��ѯ�ɹ� ,��Ϣ����:");
			System.out.println(user);
			break;
		case 20013:
			//����Ա��ѯ����ѧԱʧ��
			System.out.println("���ź�, ѧԱ������ !");
			break;
		case 20014:
			//��Ŀ��ӳɹ�
			System.out.println("��ϲ��, ��Ŀ���ӳɹ� ! ");
			break;
		case 20015:
			//������Ŀʧ��
			System.out.println("���ź�, ��Ŀ����ʧ��");
			break;
		case 20016:
			//��Ŀɾ���ɹ�
			System.out.println("��ϲ��, ��Ŀɾ���ɹ� ! ");
			break;
		case 20017:
			//ɾ����Ŀʧ��
			System.out.println("���ź�, ��Ŀɾ��ʧ��");
			break;
		case 20018:
			//��Ŀ�޸ĳɹ�
			System.out.println("��ϲ��, ��Ŀ�޸ĳɹ� ! ");
			break;
		case 20019:
			//��Ŀ�޸�ʧ��
			System.out.println("���ź�, ��Ŀ�޸�ʧ��");
			break;
		case 20020:
			//��Ŀ��ѯ�ɹ�
			Test test = (Test) fromData.get("data");
			System.out.println("��ѯ�ɹ� ,��Ϣ����:");
			System.out.println(test);
			break;
		case 20021:
			//��Ŀ��ѯʧ��
			System.out.println("���ź�, ��Ŀ������");
			break;
		case 20022:
			//��Ŀ��ѯ�ɹ�	
			System.out.println("����ɹ�");
			break;
		case 20023:
			//��Ŀ��ѯʧ��
			System.out.println("���ź�, ����ʧ��");
			break;
		case 20024:
			//ѧ����ʼ����
			@SuppressWarnings("unchecked")
			List<Test> list = (List<Test>) fromData.get("data");
			int totalscore = testtest(list);
			System.out.println("���Խ��������ε÷�:"+totalscore);
			@SuppressWarnings("rawtypes")
			ArrayList arr = new ArrayList();
			arr.add(usert);
			arr.add(totalscore);
			toMessage(10012,arr);
			fromMessage();
			service();
			break;
		case 20025:
			//�ɼ��ϴ��ɹ�
			System.out.println("�ɼ��ϴ��ɹ� ! ");
			break;
		case 20026:
			//�ɼ��ϴ�ʧ��
			System.out.println("�ɼ��ϴ�ʧ��");
			break;
		case 20027:
			//��ѯ���ĳɼ�
			List<TestResult> list1 = (List<TestResult>) fromData.get("data");
			for (TestResult test2 : list1) {
				System.out.println(test2);
			}
			break;
		case 20028:
			//�ɼ������ɹ�
			System.out.println("�ɼ������ɹ� ! ");
			break;
		case 20029:
			//�ɼ�����ʧ��
			System.out.println("�ɼ�����ʧ��");
			break;

		default:
			break;
		}
	}

	private static void managerClient() {
		System.out.println("******\t��ӭ���л���\t\t******");
		while(true) {
			int menu = ClientView.managerMenuView();
			switch (menu) {
			case 0://�˳�
				System.exit(0);
				break;
			case 1://���ӿ���ѧԱ
			{
				User user = ClientView.addUserView();
				toMessage(10003, user);
			}
				break;
			case 2://ɾ������ѧԱ
			{
			
				String username = ClientView.deleteUserView();
				toMessage(10004, username);
				
			}
				break;
			case 3://�޸Ŀ���ѧԱ
			{
				User user = ClientView.updateUserView_m();
				toMessage(10005,user);
			}
			break;
			case 4://��ѯ����ѧԱ
			{
			
				String username = ClientView.findUserByName();
				toMessage(10006,username);
			}
				break;
			case 5://���ӿ�������
				Test test = ClientView.addTsetView();
				toMessage(10007, test);
				break;
			case 6://ɾ����������
				int num = ClientView.deleteTestView();
				toMessage(10008, num);
				break;
			case 7://�޸Ŀ�������
				Test test1 = ClientView.updateTestView_m();
				toMessage(10009, test1);
				break;
			case 8://��ѯ��������
				int num1 = ClientView.findTestByNum();
				toMessage(10010, num1);
				break;
			case 9://�������뿼������
				String test11 = ClientView.addAllTest();
				toMessage(10011, test11);
				
				break;
			}
			fromMessage();
			service();
		}
	}

	/**
	 * �˷�������, �����������������
	 * @param type
	 * @param data
	 */
	private static void toMessage(int type,Object data){
		//ÿ�η���HashMap �ڴ��ַ ���벻ͬ
		toData = new HashMap<>();
		toData.put("type", type);
		toData.put("data", data);
		try {
			oos.writeObject(toData);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private static void fromMessage() {
		try {
			fromData = (HashMap<String, Object>) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int  testtest(List<Test> list) {
		int score = 0;
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<list.size();i++){
			Test test = list.get(i);
			System.out.println(test.getQuestion());
			System.out.println("A:"+test.getA()+"  B:"+test.getA()+"  C:"+test.getA()+"  D:"+list.get(i).getA());
			System.out.println("��ѡ����ȷ��:");
			String result = sc.next();
			if(test.getRight().equals(result)){
				score = score+test.getScore();
			}else{
				score = score;
			}
		}
		return score;
		
	}
	
	
	
}
