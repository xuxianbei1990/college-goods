package pay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:08
 * Version:V1.0
 */
@RestController
@RequestMapping("/manager")
public class PayCenterManagerController {

    @PostMapping("/registry/seller")
    public int registrySeller(Integer sellerId, Integer payTypeId){
        return 0;
    }

    /**
     * 获取seller支付方式列表
     * @param sellerId
     * @return
     */
    @GetMapping("/seller/list")
    public int getSellerList(Integer sellerId){
        return 0;
    }

    @PostMapping("/seller/update")
    public int sellerUpdate(Integer sellerId, Object payTypeDTO) {
        return 0;
    }

    @GetMapping("/seller/delete")
    public int sellerDelete(Integer sellerId, Integer payTypeId) {
        return 0;
    }

    @GetMapping("/payType/add")
    public int payTypeAdd(){
        return 0;
    }

    @GetMapping("/payType/list")
    public int payTypeList(){
        return 0;
    }

    @PostMapping("/payType/update")
    public int payTypeUpdate(Integer sellerId){
        return 0;
    }

    @GetMapping("/payType/delete")
    public int payTypeDelete(Integer sellerId, Integer payTypeId){
        return 0;
    }
}
