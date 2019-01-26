package sample;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-01-19
 * VersionV1.0
 * @description
 */
public class HelloImpl implements Hello {
    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }

    @Override
    public String sayBye(String name) {
        return "Bye" + name;
    }
}
