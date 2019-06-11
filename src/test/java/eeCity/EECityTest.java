package eeCity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.athongkun.utils.DateUtil;
import com.athongkun.utils.json.JsonUtils;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: finance-hkjf
 * @description: api调用测试
 * @author: hehang
 * @create: 2019-04-22 11:00
 **/
public class EECityTest {

    @Test
    public void accountBind(){
        System.out.println("绑定银行账号到profile");
        try{
            Map data = new HashMap();
            data.put("account_id","Xl8GrMQ4X7FwNryLZ4QNsWx6Wl7ekZHdkx3xM");
            data.put("profile_id","inpf_sPH6UMoXRzGiixh12E8H8Q");
            data.put("public_token","public-sandbox-1ace643e-ff84-4fea-a476-cfe79557a585");

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(null,data));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doPostHttp("https://dev.fundin.us/hk/open/account_bind/", massage,data);
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
            /**
             * {"ok":"true"}
             */
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPlaidPublicKey(){
        System.out.println("获取plaid public key");
        try{
            Map params = new HashMap<>();
            params.put("page",1);
            params.put("page_size",10);

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(null,null));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/application/plaid_public_key/", massage,params);
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


    @Test
    public void getSellerAccounts(){
        System.out.println("获取seller和account列表");
        try{
            Map params = new HashMap<>();
            params.put("page",1);
            params.put("page_size",10);

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(null,null));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/application/seller_accounts/", massage,params);
            System.out.println("EECity返回josn报文outbuffer: " + outbuffer);
            if(StringUtils.isNotEmpty(outbuffer)) {
                JSONObject resp = (JSONObject) JSON.parse(outbuffer);
                String response = String.valueOf(resp.get("data"));
                System.out.println("返回json报文data:" + response);
                //{"seller_accounts":[{"seller":"ispf_GU3rQASVTGGQD7NsmcTMkg","account":"ac_iC83ujz_QqWueGjZZzZ2QQ","email":"andyhuang@hk.com"}]}
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getIndividualsDetail(){
        System.out.println("获取profile详细信息");
        try{
            String id = "inpf_MlqRrDL0S_-ujzpjQKYh9w";//hanghe@hongkunjinfu.com
            Map params = new HashMap();
            params.put("id",id);
            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(params,null));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/individuals/"+id+"/", massage,new HashMap<>());
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProfileById(){
        System.out.println("更新profile信息");
        try {
            String profile_id = "inpf_MlqRrDL0S_-ujzpjQKYh9w";
            Map params = new HashMap();
            params.put("id", profile_id);

            Map body = new HashMap();
            body.put("accounts","");
            body.put("created_time","");
            body.put("date_of_birth","");
            body.put("dwolla_status","");
            body.put("email_1","");
            body.put("first_name","");
            body.put("dwolla_status","");
            body.put("identifier_document_set","");
            body.put("is_us_investor","");
            body.put("last_name","");
            body.put("phone_1","");
            body.put("pk","");
            body.put("updated_ip","");
            body.put("updated_time","");

            Map massage = new HashMap<>();
            massage.put("Authorization", getAuthorization(params, body));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.httpPatch(getAuthorization(params, body), body,"https://dev.fundin.us/hk/open/individuals/" + profile_id + "/");
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void createPaymentOrder(){
        System.out.println("创建支付订单");
        try{
            Map data = new HashMap();
            data.put("buyer","inpf_MlqRrDL0S_-ujzpjQKYh9w");
            data.put("clause","");
            data.put("created_time",new Date());
            data.put("from_account","ac_WYMzrKKVQImfdcoHiLzLsQ");
            data.put("id","");
            data.put("is_active","");
            data.put("out_id", "8f42a47682fb4ec5a967c86819fe33de");
            //data.put("seller","inpf_sPH6UMoXRzGiixh12E8H8Q");
            //data.put("to_account","ac_NFVuy5V9SOWFCXwP41BEMg");
            data.put("seller","ispf_GU3rQASVTGGQD7NsmcTMkg");   //三方指定值
            data.put("to_account","ac_u98NiEIASHCarJqgZEs9bw");//三方指定值
            data.put("units","1000");
            data.put("updated_time",new Date());

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(null,data));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doPostHttp("https://dev.fundin.us/hk/open/orders/payment/", massage,data);
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
            /**
             * 返回报文
             * {"id":"oa_poor_1A85trG8QFWsN7z-pZwtWg","from_account":"ac_WYMzrKKVQImfdcoHiLzLsQ","units":100,"created_time":"2019-05-15T11:10:47.335165Z","updated_time":"2019-05-15T11:10:47.335197Z","is_active":true,"clause":"","out_id":"033e86aada7246399456d013d12b7343","buyer":"inpf_MlqRrDL0S_-ujzpjQKYh9w","seller":"inpf_sPH6UMoXRzGiixh12E8H8Q","to_account":"ac_NFVuy5V9SOWFCXwP41BEMg"}
             */
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
            massage.put("Authorization",getAuthorization(null,null));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/orders/payment/", massage,params);
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderDetailByOutId(){
        System.out.println("通过out_id获取支付订单详情");
        try{
            Map params = new HashMap<>();
            params.put("page",1);
            params.put("page_size",10);

            String outId = "e9932b576c5a4fc8ba517c8359a1695b";
            Map path = new HashMap<>();
            path.put("out_id",outId);

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(path,null));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/orders/payment/out_id/"+outId+"/", massage,params);
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

    @Test
    public void getOrderDetail(){
        System.out.println("获取支付订单详情");
        try{
            String id = "oa_poor_12Shkid0SIuU0VzpZXvZ7g";
            Map params = new HashMap();
            params.put("id",id);
            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(params,null));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/orders/payment/"+id+"/", massage,new HashMap<>());
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);

            //取出日期，进行格式转换
            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<>();
            map = gson.fromJson(outbuffer,map.getClass());
            String date = (String) map.get("created_time");
            System.out.println("created_time："+transLocalTime(date));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createUsers(){
        System.out.println("创建或关联用户");
        try{
            Map data = new HashMap();
            data.put("email","hehanger@163.com");
            data.put("first_name","");
            data.put("id","");
            data.put("last_name","");
            data.put("profile","");
            data.put("username","");

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(null,data));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doPostHttp("https://dev.fundin.us/hk/open/users/", massage,data);
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUsers(){
        System.out.println("获取关联的用户列表");
        try{
            Map params = new HashMap<>();
            params.put("page",1);
            params.put("page_size",10);

            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(null,null));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/users/", massage,params);
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

    @Test
    public void getUserDetail(){
        System.out.println("获取用户详细信息");
        try{
            String id = "ur_MlqRrDL0S_-ujzpjQKYh9w";
            Map params = new HashMap();
            params.put("id",id);
            Map massage = new HashMap<>();
            massage.put("Authorization",getAuthorization(params,null));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.doGetHttp("https://dev.fundin.us/hk/open/users/"+id+"/", massage,new HashMap<>());
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUserById(){
        System.out.println("更新用户部分信息");
        try {
            String id = "ur_MlqRrDL0S_-ujzpjQKYh9w";
            String profile_id = "inpf_MlqRrDL0S_-ujzpjQKYh9w";
            Map params = new HashMap();
            params.put("id", id);

            Map body = new HashMap();
            body.put("email","hanghe@hongkunjinfu.com");
            body.put("first_name","he");
            body.put("id",id);
            body.put("last_name","hang");
            body.put("profile",profile_id);
            body.put("username","hehang");

            Map massage = new HashMap<>();
            massage.put("Authorization", getAuthorization(params, body));
            System.out.println("访问报文:" + massage);

            String outbuffer = HttpUtils.httpPatch(getAuthorization(params, body), body,"https://dev.fundin.us/hk/open/users/" + id + "/");
            System.out.println("EECity返回json报文outbuffer: " + outbuffer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Description: 签名
     * @param bodyMap  等于各接口Parameters部分，type=Body的参数定义；其内容为http body提交的json字典；按字典键从小到大排序，键对应值按以上顺序拼接起来的字符串；
     *              举例说明：
     Body={"email":"a@qq.com", "first_name":"a", "last_name":"b","username":"def"}，
    name排序结果：["email", "first_name", "last_name","username"]，body内容为：a@qq.comabdef*
     * @return: java.lang.String
     * @Author: hanghe@hongkunjinfu.com
     * @Date: 2019/5/10 10:18
     */
    public String getAuthorization(Map pathMap,Map bodyMap){
        //报文头 格式 appid:time:uuid:sign
        String authentication = "";
        String appid = "app_QaZpj8wMRqum_1yjoILqXg";   //从平台申请获得的application id
        String time = JsonUtils.toJson(DateUtil.getCurrentDateTimeStr()).replaceAll("\"","");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");//唯一字符串
        String path = getSortJsonString(pathMap);
        String body = getSortJsonString(bodyMap);
        System.out.println("time："+time);
        System.out.println("uuid："+uuid);
        System.out.println("path："+path);
        System.out.println("body："+body);

        try {
            String signContent = time + uuid + path + body;

            //哈希算法
            //String hash = RSAAlgorithm.byteArrayToHexStr(RSAAlgorithm.getHash(signContent));
            //System.out.println("hash: " + hash);
            //rsa签名、base64编码（签名以key/hk_privateKey.properties为准）
            String signData = RSAAlgorithm.generateSign(signContent);
            System.out.println("sign: " + signData);
            //签名验证
            boolean ok = RSAAlgorithm.verifySign(signContent, signData);
            System.out.println("verify: " + ok);

            authentication = "app " + appid + ":" + time + ":" + uuid + ":" + signData;
            System.out.println("Authorization："+authentication);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return authentication;
        }

    }

    /**
     * @Description: 按key排序之后，value拼成字符串
     * @param map
     * @return: java.lang.String
     * @Author: hanghe@hongkunjinfu.com
     * @Date: 2019/4/24 10:10
     */
    public static String getSortJsonString(Map<String, Object> map){
        Map<String, Object> resultMap = sortMapByKey(map);
        if(CollectionUtils.isEmpty(resultMap)){
            return "";
        }
        StringBuffer jsonStr = new StringBuffer();
        resultMap.keySet().forEach(e->{
            jsonStr.append(resultMap.get(e).toString());
        });

        return jsonStr.toString();
    }

    /**
     * @Description: 使用 Map按key进行排序
     * @param map
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: hanghe@hongkunjinfu.com
     * @Date: 2019/5/29 15:49
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<>((String str1, String str2) -> (str1.compareTo(str2)));

        sortMap.putAll(map);

        return sortMap;
    }

    /**
     * @Description: 转换成当地时间
     * @param date
     * @return: java.lang.String
     * @Author: hanghe@hongkunjinfu.com
     * @Date: 2019/5/29 15:55
     */
    public String transLocalTime(String date){
        String time = null;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
            //设置时区UTC
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            //格式化，转当地时区时间
            Date localCSTTime = sdf.parse(date);
            //CST日期格式转换
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getDefault());
            time = sdf.format(localCSTTime);
        }catch (ParseException e){

        }

        return time;
    }

}
