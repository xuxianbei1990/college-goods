import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-01-19
 * VersionV1.0
 * @description
 */
@Slf4j
public class ProviderApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "dubbo-server.xml");
        context.start();
        log.info("dubbo service begin to start");
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
