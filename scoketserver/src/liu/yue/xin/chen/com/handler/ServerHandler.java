package liu.yue.xin.chen.com.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import liu.yue.xin.chen.com.protoc.GameProto;
import liu.yue.xin.chen.com.protoc.GameProto.c2s_login_user;

/**
 * 服务器Handler
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月10日
 */
public class ServerHandler  extends SimpleChannelInboundHandler<ByteBuf> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		// TODO Auto-generated method stub
		short readShort = msg.readShort();//2个字节
		int cmd = msg.readInt();//4个字节
		int dataLeng= readShort -4;//protobuf的长度   
		byte[] result1 = new byte[dataLeng];
		msg.readBytes(result1);
		c2s_login_user parseFrom = GameProto.c2s_login_user.parseFrom(result1);
		System.err.println("收到客户端的protobuf信息 = "+parseFrom.toString()); 
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		System.err.println("客户端链接上！");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
}
