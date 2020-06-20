package cn.xdl.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cn.xdl.thread.SocketThread;

public class Main {

	public static void main(String[] args) throws Exception {
		//搭建了一个服务器,  监听的端口号为10086
		ServerSocket ss = new ServerSocket(10086);
		System.out.println("服务器已启动, 等待客户端连接中...");
		//1.	while  死循环  ,循环接收客户端的连接
		while(true) {
			Socket socket = ss.accept();
		//2.	每连接到一个客户端. 则开启一个新的线程 与这个客户端进行交互 !
			new Thread(new SocketThread(socket)).start();
		}
		
	}

}
