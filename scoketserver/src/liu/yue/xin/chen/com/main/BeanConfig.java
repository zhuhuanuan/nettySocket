package liu.yue.xin.chen.com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import liu.yue.xin.chen.com.net.SocketInitializer;
import liu.yue.xin.chen.com.net.SocketServer;
/**
 * 服务配置
 * 
 * @bk https://home.cnblogs.com/u/huanuan/
 * @简书 https://www.jianshu.com/u/d29cc7d7ca49
 * @Author 六月星辰
 * @Date 2020年1月10日
 */
@Configuration
@PropertySource("classpath:/scoket.properties")
@ImportResource("classpath:/quartz.xml")
public class BeanConfig {
    @Autowired
    Environment env;
    @Autowired
    void init() {
        // 系统配置 设置
        String ip = env.getProperty("ip");
        int port = env.getProperty("port", int.class);
        System.err.println("ip = " + ip + "port = " + port);
    }

    @Bean
    SocketServer socketServer() {
        return new SocketServer(scoketInitializer(), env.getProperty("port", int.class));
    }
    @Bean
    SocketInitializer scoketInitializer() {
        return new SocketInitializer();
    }
}
