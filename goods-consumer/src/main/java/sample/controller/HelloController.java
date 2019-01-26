package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sample.Hello;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-01-19
 * VersionV1.0
 * @description
 *
 * https://www.cnblogs.com/s648667069/p/6513335.html
 */
@RestController
public class HelloController {
    @Autowired
    @Qualifier("goodsConsumer")
    private Hello hello;

    @GetMapping("hello/{name}")
    public String sayHello(@PathVariable String name) {
        return hello.sayHello(name);
    }

    @GetMapping("goodbye/{name}")
    public String sayBye(@PathVariable String name) {
        return hello.sayBye(name);
    }
}
