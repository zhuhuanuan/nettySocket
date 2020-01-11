package liu.yue.xin.chen.com.net.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import liu.yue.xin.chen.com.protoc.GameProto.c2s_login_user;
import liu.yue.xin.chen.com.protoc.GameProto.c2s_login_user.Builder;

/**
 * 客户端处理器
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//与服务端链接成功 并发送一个登录的请求
		//组建protobuf的字节数据
		Builder builder = c2s_login_user.newBuilder();
		builder.setAccount("123456789");
		builder.setPassword("123456789");
		byte[] byteArray = builder.build().toByteArray();
		
		//发送的长度 = 2 +4 +n
		int length = 6 + byteArray.length;
		int dataLength = 4 + byteArray.length;
		// 4个字节长度的命令号
		int cmd = 1001;// 命令号
		
		//获取发送长度的字节ByteBuf流
		ByteBuf buf = Unpooled.buffer(length);
		buf.writeShort(dataLength);//[包头 2 Bytes] ： 除了包头2个字节外包体的大小
		buf.writeInt(cmd);//    [包体 4 Bytes] ：包的协议ID
		buf.writeBytes(byteArray);//  [包体 ] ： protobuf 字节数据
		
		byte[] newdata = new byte[buf.readableBytes()];
		buf.readBytes(newdata);
		
		//发送数据
		ctx.writeAndFlush(Unpooled.copiedBuffer(newdata)); // 必须有flush
	}

	/**
	 * 处理服务端发送过来的消息
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("读取服务端通道信息..");
	}

}
