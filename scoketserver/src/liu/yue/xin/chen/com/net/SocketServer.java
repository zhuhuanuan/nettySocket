package liu.yue.xin.chen.com.net;

import java.net.ServerSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty SocketServer
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public class SocketServer implements Runnable {

	/**
	 * channel 初始器
	 */
	private ChannelInitializer<SocketChannel> channelInitializer;

	/**
	 * 服务端口
	 */
	private int port;

	/**
	 * SocketServer 构造器
	 * 
	 * @param pipelineFactory
	 * @param port
	 */
	public SocketServer(ChannelInitializer<SocketChannel> pipelineFactory, int port) {
		this.channelInitializer = pipelineFactory;
		this.port = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			bind();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * 服务IP绑定
	 * 
	 * @throws InterruptedException
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2020年1月11日
	 * @return
	 */
	private boolean bind() throws InterruptedException {
		// TODO Auto-generated method stub
		if (channelInitializer == null || port <= 0) {
			System.err.println("socker server initializer fail !!!");
			return false;
		}
		if (isPortAvailable(port)) {
			System.err.println("Server is establishing to listening at :" + port);
		} else {
			System.err.println("Server's port :" + port + " not available");
			System.exit(-1);
			return false;
		}
		// Configure the server.
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(channelInitializer);
			// Start the server.
			ChannelFuture f = b.bind(port).sync();
			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
			System.err.println("netty 服务器启动成功！端口 " + port);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Shut down all event loops to terminate all threads.
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			// Wait until all threads are terminated.
			bossGroup.terminationFuture().sync();
			workerGroup.terminationFuture().sync();
		}
		return true;
	}

	/**
	 * 检查端口是否被占用
	 * 
	 * @param port
	 * @return
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2019年12月19日
	 */
	public static final boolean isPortAvailable(int port) {
		try {
			ServerSocket ss = new ServerSocket(port);
			ss.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
