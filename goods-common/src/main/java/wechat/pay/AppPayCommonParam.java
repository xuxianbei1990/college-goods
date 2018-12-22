package wechat.pay;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Name app参数填充
 *
 * @author xuxb
 * Date 2018-11-17
 * VersionV1.0
 * @description 使用 MD5（MD5算法碰撞概率在1分之2的256次方左右）进行热加载。
 */
public class AppPayCommonParam extends PayCommonParam {

    private String oldMd5;

    @Override
    public void fillData() {
        Properties properties = new Properties();
        try {
            //如果文件发生变化，进行重新加载
            String md5 = DigestUtils.md5Hex(new FileInputStream("/config/wechat.properties"));
            if (!md5.equals(oldMd5)) {
                properties.load(AppPayCommonParam.class.getClassLoader().getResourceAsStream("/config/wechat.properties"));
                oldMd5 = md5;
            }
            this.secret = properties.getProperty("JSAPI_PAY_SECRET");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
