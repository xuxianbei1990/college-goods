package order.util.observe;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 18:10
 * Version:V1.0
 */
@Component
public class WatchPublish {

    List<Observe> observes = new ArrayList<>();

    public boolean add(Observe observe) {
        return observes.add(observe);
    }

    public boolean remove(Observe observe) {
        return observes.remove(observe);
    }

    public void notifyObserves() {
        observes.stream().forEach(observe -> observe.listen());
    }


}
