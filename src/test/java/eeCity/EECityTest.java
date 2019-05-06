package eeCity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.athongkun.utils.DateUtil;
import com.athongkun.utils.json.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @program: finance-hkjf
 * @description: api调用测试
 * @author: hehang
 * @create: 2019-04-22 11:00
 **/
public class EECityTest {

    @Test
    public void createUsers(){
        System.out.println("创建或关联用户");
        try{
            Map params = new HashMap<>();
            params.put("data","users");

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization());
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doPostHttp("https://dev.fundin.us/hk/open/users", massage,params);
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrders(){
        System.out.println("获取支付订单列表");
        try{
            Map params = new HashMap<>();
            params.put("page",1);
            params.put("page_size",10);

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization());
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/orders/payment", massage,params);
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserDetail(){
        System.out.println("获取用户详细信息");
        try{
            Map params = new HashMap<>();
            params.put("id",1);

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization());
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/users", massage,params);
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
    * @Description: 获取plaid public key
     * @param 
    * @return: void 
    * @Author: hanghe@hongkunjinfu.com
    * @Date: 2019/4/23 9:38
    */
    @Test
    public void getPlaidPublicKey(){
        System.out.println("获取plaid public key");
        try{
            Map params = new HashMap<>();
            params.put("page",1);
            params.put("page_size",10);

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization());
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/application/plaid_public_key", massage,params);
            System.out.println("EECity返回josn报文outbuffer: " + outbuffer);
            if(StringUtils.isNotEmpty(outbuffer)) {
                JSONObject resp = (JSONObject) JSON.parse(outbuffer);
                String response = String.valueOf(resp.get("data"));
                System.out.println("返回json报文data:" + response);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /** 
    * @Description: 获取关联的用户列表
     * @param 
    * @return: void 
    * @Author: hanghe@hongkunjinfu.com
    * @Date: 2019/4/23 9:38
    */
    @Test
    public void getUsers(){
        System.out.println("获取users");
        try{
            Map params = new HashMap<>();
            params.put("page",1);
            params.put("page_size",10);

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization());
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/users", massage,params);
            System.out.println("EECity返回josn报文outbuffer: " + outbuffer);
            if(StringUtils.isNotEmpty(outbuffer)) {
                JSONObject resp = (JSONObject) JSON.parse(outbuffer);
                String response = String.valueOf(resp.get("data"));
                System.out.println("返回json报文data:" + response);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /** 
    * @Description: 签名
    * @param
    * @return: java.lang.String 
    * @Author: hanghe@hongkunjinfu.com
    * @Date: 2019/4/25 16:18
    */
    public String getAuthorization(){
        //报文头 格式 appid:time:uuid:sign
        String authentication = "";
        String appid = "app_QaZpj8wMRqum_1yjoILqXg";   //从平台申请获得的application id
        String time = JsonUtils.toJson(DateUtil.getCurrentDateTimeStr()).replaceAll("\"","");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");//唯一字符串
        String sign = "";
        System.out.println("时间："+time);
        System.out.println("uuid："+uuid);
        try {
            //String rSignature = RSAUtils.sha256X16(time + uuid + "" + "", "UTF-8");
            //System.out.println("SHA256结果：" + rSignature);

            //哈希算法
            String hash = RSAAlgorithm.byteArrayToHexStr(RSAAlgorithm.getHash(time + uuid));
            System.out.println("hash: " + hash);
            //rsa签名、base64编码（签名以key/hk_privateKey.properties为准）
            String signData = RSAAlgorithm.generateSign(time + uuid);
            System.out.println("sign: " + signData);
            //签名验证
            boolean ok = RSAAlgorithm.verifySign(time + uuid, signData);
            System.out.println("verify: " + ok);

            //String encryString = RSAUtils.sign(rSignature).toString();
            //System.out.println("RSA私钥加密结果：" + encryString);

            //String encryString = RSAUtils.encryptByPrivate(rSignature.getBytes("UTF-8"), null);
            //System.out.println("RSA私钥加密结果：" + encryString);

            //String signStr = RSAUtils.decryptByPublic(encryString,null);
            //System.out.println("RSA公钥解密的结果："+ signStr);
            //
            //String encryString2 = RSAUtils.encryptByPublic(rSignature.getBytes("UTF-8"), null);
            //System.out.println("RSA公钥加密结果：" + encryString2);
            //
            //String signStr2 = RSAUtils.decryptByPrivate(encryString,null);
            //System.out.println("RSA私钥解密的结果："+ signStr2);

            //sign = SecurityUtil.Base64Encode(signData).replaceAll("\r|\n", "");
            System.out.println("sign=" + signData);

            authentication = "app " + appid + ":" + time + ":" + uuid + ":" + signData;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return authentication;
        }

    }

    /** 
    * @Description: 按key排序之后，value拼成字符串
     * @param json
    * @return: java.lang.String 
    * @Author: hanghe@hongkunjinfu.com
    * @Date: 2019/4/24 10:10
    */
    public static String getSortJsonString(JSONObject json){
        Map<String, Object> map = (Map)json;
        Map<String, Object> resultMap = sortMapByKey(map);
        StringBuffer jsonStr = new StringBuffer();
        resultMap.keySet().forEach(e->{
            jsonStr.append(resultMap.get(e).toString());
        });

        return jsonStr.toString();
    }

    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<>((String str1, String str2) -> (str1.compareTo(str2)));

        sortMap.putAll(map);

        return sortMap;
    }

}
