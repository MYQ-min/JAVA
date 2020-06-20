package cn.xdl.test;

import org.junit.Test;

import cn.xdl.bean.User;
import cn.xdl.view.ClientView;

class Test1 {
	
	@Test
	public void welcomeViewTest() throws Exception {
		User user = ClientView.welcomeView();
		System.out.println(user);
	}
	@Test
	public void studentMenuViewTest() throws Exception {
		int type = ClientView.studentMenuView();
		System.out.println(type);
	}
	
	@Test
	public void managerMenuViewTest() throws Exception {
		int type = ClientView.managerMenuView();
		System.out.println(type);
	}
	@Test
	public void addUserView() throws Exception {
		User user2 = ClientView.addUserView();
		System.out.println(user2);
	}
	@Test
	public void deleteUserView() throws Exception {
		String username = ClientView.deleteUserView();
		System.out.println(username);
	}
	@Test
	public void updateUserView() throws Exception {
		User user = ClientView.updateUserView_m();
		System.out.println(user);
	}
	@Test
	public void findUserView() throws Exception {
		String username = ClientView.findUserByName();
		System.out.println(username);
	}
	
	
	
	
	
	
	
}
