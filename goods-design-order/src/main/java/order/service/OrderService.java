package order.service;

/**
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 14:47
 * Version:V1.0
 */
public interface OrderService extends OrderServicePlace, Pay, CancelOrder {

    void list();

    // 不过我觉得这个请求在服务器压力大时候，放到app更加合适
    // 问题？我怎么知道这个边境
    void listDetail();

    // 订单商品信息。问题？服务器压力大移到app端
    void goods();

    // 统计计数。问题？计数是一个非常耗时的操作，是不是做定时任务由一个单独服务处理好些？
    // 总之这个可能会有性能压力
    void statisticsCount();

}
