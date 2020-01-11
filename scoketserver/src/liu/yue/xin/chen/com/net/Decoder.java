package liu.yue.xin.chen.com.net;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 解码器
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月10日
 */
@Slf4j
public class Decoder extends MessageToMessageDecoder<ByteBuf> {

	/**
	 * [包头 2 Bytes] ： 除了包头2个字节外包体的大小</br>
	 * [包体 4 Bytes] ：包的协议ID </br>
	 * [包体 ] ： protobuf 字节数据</br>
	 * 1.先读取2个字节并解析出剩下数据流的长度dataLength</br>
	 * 2.如果剩下的数据流不满足dataLength的长度则继续等待</br>
	 * 3.如果剩下的数据流满足dataLength的长度则放行
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		// out.add(Unpooled.wrappedBuffer(msg));//不进行任何的解析直接放行
		// 可读长度
		int readableBytes = msg.readableBytes();
		if (readableBytes < 4) {
			System.err.println("接受到信息! 可读长度小于4! 不进行解码!");
			return;
		}
		// 标记读取位置
		msg.markReaderIndex();
		int dataLength = msg.readShort();// 读取两个节 查看剩下的数据流长度
		if (msg.readableBytes() < dataLength) {
			log.info("剩下可读长度小于 dataLength! 剩下可读长度 = {} ", msg.readableBytes());
			// 移除读取标准位置
			msg.resetReaderIndex();
			return;
		}
		ByteBuf buf = Unpooled.buffer(dataLength + 2);
		buf.writeShort(dataLength);
		buf.writeBytes(msg);
		// 放行到hander层
		out.add(Unpooled.wrappedBuffer(buf));
	}
}
