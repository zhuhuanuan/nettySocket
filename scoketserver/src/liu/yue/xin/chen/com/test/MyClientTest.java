package liu.yue.xin.chen.com.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import liu.yue.xin.chen.com.net.client.MyClientInitializer;

/**
 * 模拟客户端发送
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public class MyClientTest {
	public static void main(String[] args) {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyClientInitializer());
			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}

	}
}
