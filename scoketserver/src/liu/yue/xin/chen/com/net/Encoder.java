package liu.yue.xin.chen.com.net;

import java.util.List;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 编码器
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月10日
 */
public class Encoder extends MessageToMessageEncoder<byte[]> {

	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		  out.add(Unpooled.wrappedBuffer(msg));
	}
}
