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
	 * 用于向服务器发送数据的流
	 */
	private static ObjectOutputStream oos ;
	/**
	 * 用于从服务器接收数据的流
	 */
	private static ObjectInputStream ois ;
	/**
	 * 用于向服务器发送的数据容器
	 */
	private static HashMap<String,Object> toData;
	/**
	 * 用于从服务器接收数据的数据容器
	 */
	private static String usert;
	private static HashMap<String,Object> fromData;
	private static String username = null;
	public static void main(String[] args) throws Exception{
		
		Socket socket = new Socket("localhost",10086);
		oos = new  ObjectOutputStream(socket.getOutputStream());
		ois = new  ObjectInputStream(socket.getInputStream());
		//提示用户 输入账号密码, 
		User user = ClientView.welcomeView();
		username = user.getUsername();
		usert=username;
		toMessage(10001,user);
		fromMessage();
		Integer type = (Integer) fromData.get("type");
		switch (type) {
		case 20001:
			//管理员登录成功
			managerClient();
			break;
		case 20002:
			//学员登录成功
			studentClient();
			break;
		case 20003:
			//登录失败
			System.out.println("很遗憾, 输入错误 ,程序已关闭");
			break;
		}
	}

	private static void studentClient() {
		System.out.println("******\t欢迎登录学员系统\t\t******");
		while(true) {
			int menu = ClientView.studentMenuView();
			switch (menu) {
			case 0://退出
				System.exit(0);
				break;
			case 1://修改密码
				User oldUser = new User(username,null);
				User newUser = ClientView.updateUserPassView_s(oldUser);
				toMessage(10002, new User[] {oldUser,newUser});
				break;
			case 2://开始考试
				List<Test> list =ClientView.startTest();
				toMessage(30002, list);
				break;
			case 3://查询历史成绩
				ClientView.checkTest();
				toMessage(30003, usert);
				break;
			case 4://导出所有成绩
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
			//学员修改自身密码成功
			System.out.println("恭喜你, 密码修改成功了 ! ");
			break;
		case 20005:
			//学员修改自身密码失败we
			System.out.println("很遗憾, 密码修改失败了, 原密码输入错误");
			break;
		case 20006:
			//管理员增加考试学员成功
			System.out.println("恭喜你, 学员增加成功 ! ");
			break;
		case 20007:
			//管理员增加考试学员失败
			System.out.println("很遗憾, 学员增加失败");
			break;
		case 20008:
			//管理员删除考试学员成功
			System.out.println("恭喜你, 学员删除成功 ! ");
			break;
		case 20009:
			//管理员删除考试学员失败
			System.out.println("很遗憾, 学员删除失败");
			break;
		case 20010:
			//管理员修改考试学员成功
			System.out.println("恭喜你, 学员密码修改成功 ! ");
			break;
		case 20011:
			//管理员修改考试学员失败
			System.out.println("很遗憾, 学员密码修改失败");
			break;
		case 20012:
			//管理员查询考试学员成功
			User user = (User) fromData.get("data");
			System.out.println("查询成功 ,信息如下:");
			System.out.println(user);
			break;
		case 20013:
			//管理员查询考试学员失败
			System.out.println("很遗憾, 学员不存在 !");
			break;
		case 20014:
			//题目添加成功
			System.out.println("恭喜你, 题目增加成功 ! ");
			break;
		case 20015:
			//增加题目失败
			System.out.println("很遗憾, 题目增加失败");
			break;
		case 20016:
			//题目删除成功
			System.out.println("恭喜你, 题目删除成功 ! ");
			break;
		case 20017:
			//删除题目失败
			System.out.println("很遗憾, 题目删除失败");
			break;
		case 20018:
			//题目修改成功
			System.out.println("恭喜你, 题目修改成功 ! ");
			break;
		case 20019:
			//题目修改失败
			System.out.println("很遗憾, 题目修改失败");
			break;
		case 20020:
			//题目查询成功
			Test test = (Test) fromData.get("data");
			System.out.println("查询成功 ,信息如下:");
			System.out.println(test);
			break;
		case 20021:
			//题目查询失败
			System.out.println("很遗憾, 题目不存在");
			break;
		case 20022:
			//题目查询成功	
			System.out.println("导入成功");
			break;
		case 20023:
			//题目查询失败
			System.out.println("很遗憾, 导入失败");
			break;
		case 20024:
			//学生开始考试
			@SuppressWarnings("unchecked")
			List<Test> list = (List<Test>) fromData.get("data");
			int totalscore = testtest(list);
			System.out.println("考试结束，本次得分:"+totalscore);
			@SuppressWarnings("rawtypes")
			ArrayList arr = new ArrayList();
			arr.add(usert);
			arr.add(totalscore);
			toMessage(10012,arr);
			fromMessage();
			service();
			break;
		case 20025:
			//成绩上传成功
			System.out.println("成绩上传成功 ! ");
			break;
		case 20026:
			//成绩上传失败
			System.out.println("成绩上传失败");
			break;
		case 20027:
			//查询到的成绩
			List<TestResult> list1 = (List<TestResult>) fromData.get("data");
			for (TestResult test2 : list1) {
				System.out.println(test2);
			}
			break;
		case 20028:
			//成绩导出成功
			System.out.println("成绩导出成功 ! ");
			break;
		case 20029:
			//成绩导出失败
			System.out.println("成绩导出失败");
			break;

		default:
			break;
		}
	}

	private static void managerClient() {
		System.out.println("******\t欢迎大佬回来\t\t******");
		while(true) {
			int menu = ClientView.managerMenuView();
			switch (menu) {
			case 0://退出
				System.exit(0);
				break;
			case 1://增加考试学员
			{
				User user = ClientView.addUserView();
				toMessage(10003, user);
			}
				break;
			case 2://删除考试学员
			{
			
				String username = ClientView.deleteUserView();
				toMessage(10004, username);
				
			}
				break;
			case 3://修改考试学员
			{
				User user = ClientView.updateUserView_m();
				toMessage(10005,user);
			}
			break;
			case 4://查询考试学员
			{
			
				String username = ClientView.findUserByName();
				toMessage(10006,username);
			}
				break;
			case 5://增加考试试题
				Test test = ClientView.addTsetView();
				toMessage(10007, test);
				break;
			case 6://删除考试试题
				int num = ClientView.deleteTestView();
				toMessage(10008, num);
				break;
			case 7://修改考试试题
				Test test1 = ClientView.updateTestView_m();
				toMessage(10009, test1);
				break;
			case 8://查询考试试题
				int num1 = ClientView.findTestByNum();
				toMessage(10010, num1);
				break;
			case 9://批量导入考试试题
				String test11 = ClientView.addAllTest();
				toMessage(10011, test11);
				
				break;
			}
			fromMessage();
			service();
		}
	}

	/**
	 * 此方法调用, 会向服务器发送数据
	 * @param type
	 * @param data
	 */
	private static void toMessage(int type,Object data){
		//每次发的HashMap 内存地址 必须不同
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
			System.out.println("请选择正确答案:");
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
