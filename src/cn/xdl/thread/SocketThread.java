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
 * 客户端与服务器都是使用Java语言进行编写的程序 . 
 * 为了方便, 我们使用使用对象流, 进行数据的传递
 *
 *
 */
public class SocketThread implements Runnable {

	private Socket socket;
	/**
	 * 用于向客户端发送数据的 对象输出流
	 */
	private ObjectOutputStream oos;
	/**
	 * 用于从客户端读取数据的 对象输入流
	 */
	private ObjectInputStream ois;
	/**
	 * 用于向服务器发送的数据容器
	 */
	private HashMap<String,Object> toData;
	/**
	 * 用于从服务器接收数据的数据容器
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
			//登录操作
			//1.	接收客户端发送的data , 转换为正确的类型
			User user = (User) fromData.get("data");
			//2.	操作UserService  ,与数据库进行交互
			int loginFlag = UserService.login(user);
			//3.	根据数据库返回的结果, 给客户端响应不同的消息
			switch (loginFlag) {
			case 1:
				//管理员登录成功
				setToMessageData(20001,null);
				break;
			case 2:
				//学员登录成功
				setToMessageData(20002,null);
				break;
			case -1:
				//登录失败
				setToMessageData(20003,null);
				break;
			}
		}
			break;
		case 10002:
			//学员修改自身密码
			//1.	接收客户端发送的数据
			User[] users = (User[]) fromData.get("data");
			//2.	操作数据库
			boolean udpateFlag = UserService.studentUpdate(users[0],users[1]);
			//3.	根据数据库返回的结果 , 给客户端回复不同的内容
			if(udpateFlag) {
				//学员修改自身密码成功
				setToMessageData(20004, null);
			}else {
				//学员修改自身密码失败
				setToMessageData(20005, null);
			}
		break;
		case 10003:{
			//增加考试学员
			User user = (User) fromData.get("data");
			boolean insertFlag = UserService.insert(user);
			if(insertFlag) {
				//学员增加成功
				setToMessageData(20006, null);
			}else{
				//学员增加失败
				setToMessageData(20007, null);
			}
		}
		break;
		case 10004:{
			//删除考试学员
			String username = (String) fromData.get("data");
			boolean deleteFlag = UserService.delete(username);
			if(deleteFlag) {
				//学员删除成功
				setToMessageData(20008, null);
			}else{
				//学员删除失败
				setToMessageData(20009, null);
			}
		}
		break;
		case 10005:{
			//管理员修改考试学员
			User user = (User) fromData.get("data");
			boolean updateFlag = UserService.managerUpdate(user);
			if(updateFlag) {
				//学员修改成功
				setToMessageData(20010, null);
			}else{
				//学员修改失败
				setToMessageData(20011, null);
			}
		}
		break;
		case 10006:{
			//管理员修改考试学员
			String username = (String) fromData.get("data");
			User user = UserService.findUserByName(username);
			if(user!=null) {
				//学员查询成功
				setToMessageData(20012, user);
			}else{
				//学员查询失败
				setToMessageData(20013, null);
			}
		}
		break;
		case 10007:{
			//增加题目
			Test test = (Test) fromData.get("data");
			boolean insertFlag = TestService.insert(test);
			if(insertFlag) {
				//增加考题成功
				setToMessageData(20014, null);
			}else{
				//增加考题失败
				setToMessageData(20015, null);
			}
		}
		break;
		case 10008:{
			//删除题目
			int num = (int) fromData.get("data");
			boolean deleteFlag = TestService.delete(num);
			if(deleteFlag) {
				//题目删除成功
				setToMessageData(20016, null);
			}else{
				//题目删除失败
				setToMessageData(20017, null);
			}
		}
		break;
		case 10009:{
			//修改题目
			Test test = (Test) fromData.get("data");
			boolean deleteFlag = TestService.managerUpdate(test);
			if(deleteFlag) {
				//题目修改成功
				setToMessageData(20018, null);
			}else{
				//题目修改失败
				setToMessageData(20019, null);
			}
		}
		break;
		case 10010:{
			//管理员查询考试题目
			int num = (int) fromData.get("data");
			Test test = TestService.findTestByNum(num);
			if(test!=null) {
				//学员查询成功
				setToMessageData(20020, test);
			}else{
				//学员查询失败
				setToMessageData(20021, null);
			}
		}
		break;
		case 10011:{
			//批量导入考试题目
			try {
				String lu= (String) fromData.get("data");
				boolean result = TestService.addAllTest(new InputStreamReader(new FileInputStream(lu)));
				if(result) {
					//批量导入考试题目成功
					setToMessageData(20022, result);
				}else{
					//批量导入考试题目失败
					setToMessageData(20023, result);
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}
		break;
		//学生考试，将随机抽中的10道题目存放到集合中,然后把集合发送到客户端
		case 30002:{
			@SuppressWarnings("unchecked")
			List<Test> list= (List<Test>) fromData.get("data");
			list = TestService.StudentTest();
			setToMessageData(20024, list);
		}
		break;
		//把从客户端上传上来的考试成绩信息，存放入数据库
		case 10012:{
			
			@SuppressWarnings("rawtypes")
			ArrayList list= (ArrayList) fromData.get("data");
			boolean flag = TestService.Studenaddresult(list);
			
			if(flag) {
				//信息上传成功
				setToMessageData(20025, null);
			}else{
				//信息上传失败
				setToMessageData(20026, null);
			}
		}
		//用户查询历史成绩
		break;
		case 30003:{
			@SuppressWarnings("unchecked")
			String uu= (String) fromData.get("data");
			List<TestResult> list = new ArrayList<>();
			list = TestService.findResult(uu);
			setToMessageData(20027, list);
		}
		break;
		//用考生导出历史成绩
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
	 * 服务器接收客户端发来的数据方法
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
	 * 服务器发送数据到客户端的方法
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
