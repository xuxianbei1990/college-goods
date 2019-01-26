import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.Hello;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-01-19
 * VersionV1.0
 * @description
 */
public class ConsumerApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        Hello demoService = context.getBean(Hello.class);
        System.out.println("consumer");
        System.out.println(demoService.sayBye("xxb"));
    }

}
