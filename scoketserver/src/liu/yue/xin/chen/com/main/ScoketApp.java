package liu.yue.xin.chen.com.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import liu.yue.xin.chen.com.net.SocketServer;
import lombok.extern.slf4j.Slf4j;

/**
 * socket 启动类
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月10日
 */
@Slf4j
public class ScoketApp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		context.start();
		SocketServer socket = context.getBean(SocketServer.class);
		new Thread(socket).start();
	}
}
