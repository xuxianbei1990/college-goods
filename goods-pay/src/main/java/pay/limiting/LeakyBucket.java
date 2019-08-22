package pay.limiting;

/**
 * User: xuxianbei
 * Date: 2019/8/21
 * Time: 17:39
 * Version:V1.0
 */
public class LeakyBucket extends Thread {

    private static LeakyBucket instance =  new LeakyBucket(10, 5);

    static {
        instance.start();
    }
    //容量
    private int capacity;

    //速度
    private int rate;

    private volatile int water;


    public LeakyBucket(int capacity, int rate) {
        water = 0;
        this.capacity = capacity;
        this.rate = rate;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(rate * 1000);
                water = Math.max(0, water - 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized boolean append() {
        try {
            Thread.sleep(rate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (water + 1 < capacity) {
            water += 1;
            return true;
        } else {
            return false;
        }
    }


    private LeakyBucket() {

    }

    public static LeakyBucket getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        LeakyBucket leakyBucket = new LeakyBucket(10, 1);
        leakyBucket.start();
        for (int i = 0; i < 100; i++) {
            boolean b = leakyBucket.append();
            if (b) {
                //执行业务代码
                System.out.println(i);
            }
        }
    }
}
