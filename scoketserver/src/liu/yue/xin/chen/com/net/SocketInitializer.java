package liu.yue.xin.chen.com.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import liu.yue.xin.chen.com.handler.ServerHandler;
import lombok.AllArgsConstructor;

/**
 * socket 初始器
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月11日
 */
@AllArgsConstructor
public class SocketInitializer extends ChannelInitializer < SocketChannel > {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // TODO Auto-generated method stub
        ChannelPipeline pipeline = ch.pipeline();
        System.out.println("报告");
        System.out.println("信息：有一客户端链接到本服务端");
        System.out.println("IP:" + ch.localAddress().getHostName());
        System.out.println("Port:" + ch.localAddress().getPort());
        System.out.println("报告完毕");
        
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast("decoder",new Decoder());//解码器
        pipeline.addLast("encoder", new Encoder());//编码器
//        pipeline.addLast(new ByteArrayEncoder());
        pipeline.addLast(new ServerHandler()); // 客户端触发操作
    }

}
