package liu.yue.xin.chen.com.net.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import liu.yue.xin.chen.com.net.SocketInitializer;

/**
 * socket 客户端
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @Author 六月星辰
 * @Date 2019年12月20日
 */
public class SockectClient implements Runnable {
	/**
	 * host 地址
	 */
	private String host = "";

	/**
	 * 端口
	 */
	private int port;

	/**
	 * netty socket 初始器
	 */
	private SocketInitializer initializer;
	/**
	 * 连接失败后等待重连时间 单位 秒
	 */
	private int maxWaitTime = 5000;// 5秒

	/**
	 * 
	 * @param initializer
	 * @param host
	 * @param port
	 */
	public SockectClient(SocketInitializer initializer, String host, int port) {
		this.initializer = initializer;
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			connect(group, initializer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	/**
	 * 链接服务端
	 * 
	 * @param group
	 * @param pipelineFactory
	 * @throws Exception
	 * @bk https://home.cnblogs.com/u/huanuan/
	 * @Author 六月星辰
	 * @Date 2019年12月21日
	 */
	private void connect(EventLoopGroup group, ChannelInboundHandlerAdapter pipelineFactory) throws Exception {
		Bootstrap b = new Bootstrap();
		b.option(ChannelOption.SO_KEEPALIVE, true)
		.option(ChannelOption.SO_SNDBUF, 1024 * 1024)
		.option(ChannelOption.SO_RCVBUF, 1024 * 1024)
		.option(ChannelOption.TCP_NODELAY, true)
		.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
		.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		b.group(group).channel(NioSocketChannel.class).handler(pipelineFactory);
		ChannelFuture ch = b.connect(host, port).sync();
		ch.channel().closeFuture().sync();
	}

}
