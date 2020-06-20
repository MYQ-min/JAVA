package cn.xdl.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cn.xdl.bean.Test;
import cn.xdl.bean.User;

public class ClientView {

	/**
	 * 用来在客户端中, 接收用户输入的 对象
	 */
	private static Scanner input = new Scanner(System.in);
	/**
	 * 当应用打开时的 欢迎视图
	 */
	public static User welcomeView() {
		System.out.println("**************************************");
		System.out.println("******\t欢迎来到在线考试系统\t\t******");
		System.out.println("******\t请根据系统提示进行操作:\t******");
		System.out.println("**************************************");
		System.out.println("******\t请输入您的账号: \t\t******");
		//接收用户的帐号
		String username = input.nextLine();
		System.out.println("******\t请输入您的密码: \t\t******");
		//接收用户的密码
		String password = input.nextLine();
		return new User(username, password);
	}
	
	/**
	 *  用于弹出学员菜单
	 *    @return
	 * 		返回的是学员选择的菜单内容: 
	 * 		<br>0:	退出 
	 *	    <br>1:	修改密码 
	 *		<br>2:	开始考试 
	 *		<br>3:	查询历史成绩 
	 *		<br>4:	导出所有成绩 
	 * 
	 */

	 
	public static int studentMenuView() {
		System.out.println("**************************************");
		System.out.println("******\t请根据系统提示进行操作:\t******");
		System.out.println("******\t0:	退出 \t\t******");
		System.out.println("******\t1:	修改密码 \t\t******");
		System.out.println("******\t2:	开始考试 \t\t******");
		System.out.println("******\t3:	查询历史成绩 \t******");
		System.out.println("******\t4:	导出所有成绩 \t******");
		//接收用户选择的菜单内容
		String text = input.nextLine();
		int type = -1;
		try {
			type = Integer.parseInt(text);
		}catch(NumberFormatException e) {
			// 当用户输入的内容, 不是数字,  则出现此异常
		}
		//如果type依然为-1.  则表示用户可能输入了-1 或 输入了非数字
		if(type<0||type>4) {
			//不合理操作
			System.out.println("******\t帅逼, 你输入错了\t\t******");
			return studentMenuView();
		}
		return type;
	}
	/**
	 * 用于弹出学员菜单
	 * @return
	 * 		返回的是管理员选择的菜单内容: 
	 * 		<br>0:	退出 
			<br>1:	增加考试学员 
			<br>2:	删除考试学员 
			<br>3:	修改考试学员 
			<br>4:	查询考试学员 
			<br>5:	增加考试试题
			<br>6:	删除考试试题
			<br>7:	修改考试试题
			<br>8:	查询考试试题
			<br>9:	批量导入考试试题
	 */
	public static int managerMenuView() {
		System.out.println("**************************************");
		System.out.println("******\t请根据系统提示进行操作:\t******");
		System.out.println("******\t0:	退出 \t\t******");
		System.out.println("******\t1:	增加考试学员  \t******");
		System.out.println("******\t2:	删除考试学员  \t******");
		System.out.println("******\t3:	修改考试学员  \t******");
		System.out.println("******\t4:	查询考试学员  \t******");
		System.out.println("******\t5:	增加考试试题 \t******");
		System.out.println("******\t6:	删除考试试题 \t******");
		System.out.println("******\t7:	修改考试试题 \t******");
		System.out.println("******\t8:	查询考试试题 \t******");
		System.out.println("******\t9:	批量导入考试试题 \t******");
		//接收用户选择的菜单内容
		
		String text =new Scanner(System.in).nextLine();
		
		System.out.println(text);
		int type = -1;
		try {
			type = Integer.parseInt(text);
		}catch(NumberFormatException e) {
			// 当用户输入的内容, 不是数字,  则出现此异常
			e.printStackTrace();
		}
		
		//如果type依然为-1.  则表示用户可能输入了-1 或 输入了非数字
		if(type<0||type>9) {
			//不合理操作
			System.out.println("******\t帅逼, 你输入错了\t\t******");
			return managerMenuView();
		}
		return type;
	}
	
	/**
	 * 用于学员修改自身密码的操作
	 * @param user 传递的用户对象,  包含了用户的帐号 密码为空
	 * 		当方法执行完毕后, 旧的密码会存储在user对象中的pssword属性中
	 * @return 用户对象,  包含了新的密码
	 */
	public static User updateUserPassView_s(User user) {
		System.out.println("**************************************");
		System.out.println("******\t修改密码操作\t\t******");
		System.out.println("******\t请输入你的原密码:\t\t******");
		String oldPass = input.nextLine();
		System.out.println("******\t请输入你的新密码:\t\t******");
		String newPass1 = input.nextLine();
		System.out.println("******\t请确认你的新密码:\t\t******");
		String newPass2 = input.nextLine();
		
		if(newPass1!=null && newPass1.equals(newPass2)) {
			//密码不为空, 且两次输入相同
			user.setPassword(oldPass);
			return new User(null, newPass1);
		}else {
			//密码为空 或 两次输入 不一致
			System.out.println("两次密码输入不一致, 请重新输入");
			return updateUserPassView_s(user);
		}
	}
	
	/**
	 * 管理员 添加学员的操作方法
	 * @return 要添加的学员对象  , 包含了账号和密码
	 */
	public static User addUserView() {
		System.out.println("**************************************");
		System.out.println("******\t添加学员操作\t\t******");
		System.out.println("******\t请输入新的学员帐号\t\t******");
		String username = input.nextLine();
		System.out.println("******\t请输入新的学员密码\t\t******");
		String password = input.nextLine();
		return new User(username, password);
	}
	/**
	 * 管理员 删除学员的操作方法
	 * @return 要删除的学员帐号
	 */
	public static String deleteUserView() {
		System.out.println("**************************************");
		System.out.println("******\t删除学员操作\t\t******");
		System.out.println("******\t请输入要删除的学员帐号\t******");
		String username = input.nextLine();
		return username;
	}
	/**
	 * 管理员 修改学员密码的操作方法
	 * @return 用户对象, 包含了用户的帐号 以及 新的密码
	 */
	public static User updateUserView_m() {
		System.out.println("**************************************");
		System.out.println("******\t修改学员密码操作\t\t******");
		System.out.println("******\t请输入学员的帐号\t\t******");
		String username = input.nextLine();
		System.out.println("******\t请输入学员的新密码\t\t******");
		String password = input.nextLine();
		return new User(username, password);
	}
	/**
	 * 通过帐号, 查询用户的操作方法
	 * @return  要查询的学员帐号
	 */
	public static String findUserByName() {
		System.out.println("**************************************");
		System.out.println("******\t查询学员操作\t\t******");
		System.out.println("******\t请输入要查询的学员帐号\t******");
		String username = input.nextLine();
		return username;
	}


	
	
	/**
	 * 管理员 添加题目的操作方法
	 * @return 要添加的题目对象  , 包含了问题，答案
	 */
	public static Test addTsetView() {
		System.out.println("**************************************");
		System.out.println("******\t添加题目的操作\t\t******");
		System.out.println("******\t输入问题\t\t\t******");
		String question = input.nextLine();
		System.out.println("******\t输入答案A\t\t\t******");
		String str1 = input.nextLine();
		System.out.println("******\t输入答案B\t\t\t******");
		String str2 = input.nextLine();
		System.out.println("******\t输入答案C\t\t\t******");
		String str3 = input.nextLine();
		System.out.println("******\t输入答案D\t\t\t******");
		String str4 = input.nextLine();
		System.out.println("******\t输入正确答案\t\t******");
		String right = input.nextLine();
		System.out.println("******\t添加分数\t\t\t******");
		int score = Integer.parseInt(input.nextLine());
		return new Test(question,str1,str2,str3,str4,score,right);
		
	}
	/**
	 * 删除题目操作
	 * @return 删除的题目题号
	 */
	public static int deleteTestView() {
		System.out.println("**************************************");
		System.out.println("******\t删除题目操作\t\t******");
		System.out.println("******\t请输入要删除的题目题号\t******");
		int num = Integer.parseInt(input.nextLine());
		return num;
	}
	/**
	 * 修改题目操作
	 * @return 
	 */
	public static Test updateTestView_m() {
		System.out.println("**************************************");
		System.out.println("******\t修改题目操作\t\t******");
		System.out.println("******\t请输入要修改的题号\t\t******");
		int num = Integer.parseInt(input.nextLine());
		System.out.println("******\t输入问题\t\t\t******");
		String question = input.nextLine();
		System.out.println("******\t输入答案A\t\t\t******");
		String str1 = input.nextLine();
		System.out.println("******\t输入答案B\t\t\t******");
		String str2 = input.nextLine();
		System.out.println("******\t输入答案C\t\t\t******");
		String str3 = input.nextLine();
		System.out.println("******\t输入答案D\t\t\t******");
		String str4 = input.nextLine();
		System.out.println("******\t输入正确答案\t\t******");
		String right = input.nextLine();
		System.out.println("******\t添加分数\t\t\t******");
		int score = Integer.parseInt(input.nextLine());
		return new Test(num, question,str1,str2,str3,str4,score,right);
	}
	/**
	 * 查询题目
	 * @return 查询的题号
	 */
	public static int findTestByNum() {
		System.out.println("**************************************");
		System.out.println("******\t查询题目界面\t\t******");
		System.out.println("******\t输入要查询的题号\t\t******");
		int num = Integer.parseInt(input.nextLine());
		return num;
	}
	
	/**
	 * 添加题库
	 * @return  
	 */
	public static String addAllTest() {
		
		System.out.println("**************************************");
		System.out.println("******\t添加题库操作\t\t******");
		System.out.println("******\t输入添加题库文本路径\t\t******");
		
		String lu =input.nextLine();
		return lu;
	}
	/**
	 * 开始考试操作
	 * @return  
	 */
	public static List<Test> startTest() {
		List<Test> list =new ArrayList<>();
		System.out.println("**************************************");
		System.out.println("******\t开始考试界面\t\t******");
		System.out.println("******\t开始考试\t\t\t******");
		return list;
		
	}
	/**
	 * 查询成绩界面
	 * @return  
	 */
	public static void checkTest() {
		List<Test> list =new ArrayList<>();
		System.out.println("**************************************");
		System.out.println("******\t查询历史成绩界面\t\t******");
		System.out.println("******\t查询信息如下\t\t******");	
	}
	/**
	 * 导出成绩
	 * @return  导出成绩名和路径
	 */
	public static String outTest() {
		
		System.out.println("**************************************");
		System.out.println("******\t导出历史成绩操作\t\t******");
		System.out.println("******\t输入导出路径\t\t******");
		String lu =input.nextLine();
		System.out.println("******\t输入导出文件名\t\t******");
		String txt =input.nextLine();
		return lu+txt;
	}
}
