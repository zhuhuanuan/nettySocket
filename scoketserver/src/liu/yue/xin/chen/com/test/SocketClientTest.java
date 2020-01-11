//package liu.yue.xin.chen.com.test;
//
//import liu.yue.xin.chen.com.net.SocketInitializer;
//import liu.yue.xin.chen.com.net.client.ClientHandler;
//import liu.yue.xin.chen.com.net.client.ServerManager;
//import liu.yue.xin.chen.com.net.client.SockectClient;
//
///**
// * 客户端测试
// * 
// * @bk https://home.cnblogs.com/u/huanuan/
// * @Author 六月星辰
// * @Date 2019年12月21日
// */
//public class SocketClientTest {
//
//	public static void main(String[] args) {
//		try {
//			System.err.println("客户端测试！");
//			// 启动Netty Client
//			Thread thread = new Thread(new SockectClient(new SocketInitializer(new ClientHandler()), "127.0.0.1", 8888));
//			thread.setName("SockectClient");
//			thread.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ServerManager.getIns().test();
//	}
//
//}
