package cn.xdl.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cn.xdl.thread.SocketThread;

public class Main {

	public static void main(String[] args) throws Exception {
		//���һ��������,  �����Ķ˿ں�Ϊ10086
		ServerSocket ss = new ServerSocket(10086);
		System.out.println("������������, �ȴ��ͻ���������...");
		//1.	while  ��ѭ��  ,ѭ�����տͻ��˵�����
		while(true) {
			Socket socket = ss.accept();
		//2.	ÿ���ӵ�һ���ͻ���. ����һ���µ��߳� ������ͻ��˽��н��� !
			new Thread(new SocketThread(socket)).start();
		}
		
	}

}
