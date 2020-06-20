package cn.xdl.thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.xdl.bean.Test;
import cn.xdl.bean.TestResult;
import cn.xdl.bean.User;
import cn.xdl.service.TestService;
import cn.xdl.service.UserService;

/**
 * �ͻ��������������ʹ��Java���Խ��б�д�ĳ��� . 
 * Ϊ�˷���, ����ʹ��ʹ�ö�����, �������ݵĴ���
 *
 *
 */
public class SocketThread implements Runnable {

	private Socket socket;
	/**
	 * ������ͻ��˷������ݵ� ���������
	 */
	private ObjectOutputStream oos;
	/**
	 * ���ڴӿͻ��˶�ȡ���ݵ� ����������
	 */
	private ObjectInputStream ois;
	/**
	 * ��������������͵���������
	 */
	private HashMap<String,Object> toData;
	/**
	 * ���ڴӷ������������ݵ���������
	 */
	private HashMap<String,Object> fromData;
	private boolean threadFlag = true;
	public SocketThread(Socket socket) {
		super();
		this.socket = socket;
		try {
			ois = new  ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(threadFlag) {
			try {
				fromMessage();
				service();
				toMessage();
			} catch (Exception e) {
				//e.printStackTrace();
				threadFlag = false;
			}
		}
	}
	private void service() {
		Integer type = (Integer) fromData.get("type");
		switch (type) {
		case 10001:{
			//��¼����
			//1.	���տͻ��˷��͵�data , ת��Ϊ��ȷ������
			User user = (User) fromData.get("data");
			//2.	����UserService  ,�����ݿ���н���
			int loginFlag = UserService.login(user);
			//3.	�������ݿⷵ�صĽ��, ���ͻ�����Ӧ��ͬ����Ϣ
			switch (loginFlag) {
			case 1:
				//����Ա��¼�ɹ�
				setToMessageData(20001,null);
				break;
			case 2:
				//ѧԱ��¼�ɹ�
				setToMessageData(20002,null);
				break;
			case -1:
				//��¼ʧ��
				setToMessageData(20003,null);
				break;
			}
		}
			break;
		case 10002:
			//ѧԱ�޸���������
			//1.	���տͻ��˷��͵�����
			User[] users = (User[]) fromData.get("data");
			//2.	�������ݿ�
			boolean udpateFlag = UserService.studentUpdate(users[0],users[1]);
			//3.	�������ݿⷵ�صĽ�� , ���ͻ��˻ظ���ͬ������
			if(udpateFlag) {
				//ѧԱ�޸���������ɹ�
				setToMessageData(20004, null);
			}else {
				//ѧԱ�޸���������ʧ��
				setToMessageData(20005, null);
			}
		break;
		case 10003:{
			//���ӿ���ѧԱ
			User user = (User) fromData.get("data");
			boolean insertFlag = UserService.insert(user);
			if(insertFlag) {
				//ѧԱ���ӳɹ�
				setToMessageData(20006, null);
			}else{
				//ѧԱ����ʧ��
				setToMessageData(20007, null);
			}
		}
		break;
		case 10004:{
			//ɾ������ѧԱ
			String username = (String) fromData.get("data");
			boolean deleteFlag = UserService.delete(username);
			if(deleteFlag) {
				//ѧԱɾ���ɹ�
				setToMessageData(20008, null);
			}else{
				//ѧԱɾ��ʧ��
				setToMessageData(20009, null);
			}
		}
		break;
		case 10005:{
			//����Ա�޸Ŀ���ѧԱ
			User user = (User) fromData.get("data");
			boolean updateFlag = UserService.managerUpdate(user);
			if(updateFlag) {
				//ѧԱ�޸ĳɹ�
				setToMessageData(20010, null);
			}else{
				//ѧԱ�޸�ʧ��
				setToMessageData(20011, null);
			}
		}
		break;
		case 10006:{
			//����Ա�޸Ŀ���ѧԱ
			String username = (String) fromData.get("data");
			User user = UserService.findUserByName(username);
			if(user!=null) {
				//ѧԱ��ѯ�ɹ�
				setToMessageData(20012, user);
			}else{
				//ѧԱ��ѯʧ��
				setToMessageData(20013, null);
			}
		}
		break;
		case 10007:{
			//������Ŀ
			Test test = (Test) fromData.get("data");
			boolean insertFlag = TestService.insert(test);
			if(insertFlag) {
				//���ӿ���ɹ�
				setToMessageData(20014, null);
			}else{
				//���ӿ���ʧ��
				setToMessageData(20015, null);
			}
		}
		break;
		case 10008:{
			//ɾ����Ŀ
			int num = (int) fromData.get("data");
			boolean deleteFlag = TestService.delete(num);
			if(deleteFlag) {
				//��Ŀɾ���ɹ�
				setToMessageData(20016, null);
			}else{
				//��Ŀɾ��ʧ��
				setToMessageData(20017, null);
			}
		}
		break;
		case 10009:{
			//�޸���Ŀ
			Test test = (Test) fromData.get("data");
			boolean deleteFlag = TestService.managerUpdate(test);
			if(deleteFlag) {
				//��Ŀ�޸ĳɹ�
				setToMessageData(20018, null);
			}else{
				//��Ŀ�޸�ʧ��
				setToMessageData(20019, null);
			}
		}
		break;
		case 10010:{
			//����Ա��ѯ������Ŀ
			int num = (int) fromData.get("data");
			Test test = TestService.findTestByNum(num);
			if(test!=null) {
				//ѧԱ��ѯ�ɹ�
				setToMessageData(20020, test);
			}else{
				//ѧԱ��ѯʧ��
				setToMessageData(20021, null);
			}
		}
		break;
		case 10011:{
			//�������뿼����Ŀ
			try {
				String lu= (String) fromData.get("data");
				boolean result = TestService.addAllTest(new InputStreamReader(new FileInputStream(lu)));
				if(result) {
					//�������뿼����Ŀ�ɹ�
					setToMessageData(20022, result);
				}else{
					//�������뿼����Ŀʧ��
					setToMessageData(20023, result);
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}
		break;
		//ѧ�����ԣ���������е�10����Ŀ��ŵ�������,Ȼ��Ѽ��Ϸ��͵��ͻ���
		case 30002:{
			@SuppressWarnings("unchecked")
			List<Test> list= (List<Test>) fromData.get("data");
			list = TestService.StudentTest();
			setToMessageData(20024, list);
		}
		break;
		//�Ѵӿͻ����ϴ������Ŀ��Գɼ���Ϣ����������ݿ�
		case 10012:{
			
			@SuppressWarnings("rawtypes")
			ArrayList list= (ArrayList) fromData.get("data");
			boolean flag = TestService.Studenaddresult(list);
			
			if(flag) {
				//��Ϣ�ϴ��ɹ�
				setToMessageData(20025, null);
			}else{
				//��Ϣ�ϴ�ʧ��
				setToMessageData(20026, null);
			}
		}
		//�û���ѯ��ʷ�ɼ�
		break;
		case 30003:{
			@SuppressWarnings("unchecked")
			String uu= (String) fromData.get("data");
			List<TestResult> list = new ArrayList<>();
			list = TestService.findResult(uu);
			setToMessageData(20027, list);
		}
		break;
		//�ÿ���������ʷ�ɼ�
		case 30004:{
			@SuppressWarnings("rawtypes")
			ArrayList list= (ArrayList) fromData.get("data");
			boolean flag = TestService.outResult(list);
			if(flag){
				setToMessageData(20028, null);
			}else{
				setToMessageData(20029, null);
			}
			
		}
		break;
		default:
			break;
		}
	}
	/**
	 * ���������տͻ��˷��������ݷ���
	 * @throws Exception
	 */
	private void fromMessage() throws Exception{
		fromData = (HashMap<String, Object>) ois.readObject();
	}
	private void setToMessageData(int type,Object data) {
		toData = new HashMap<>();
		toData.put("type", type);
		toData.put("data", data);
	}
	/**
	 * �������������ݵ��ͻ��˵ķ���
	 */
	private void toMessage(){
		try {
			oos.writeObject(toData);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
