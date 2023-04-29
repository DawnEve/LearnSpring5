package com.mio;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// https://github.com/coco-bigdata/CO-IN/blob/master/backend/web/src/test/java/com/example/EncryptUtil.java
public class EncryptUtil {
    public static int cq = 9;
    public static String cr = "MD5";

    public static String P() {
        return UUID.randomUUID().toString();
    }


    public static String encode(String pass, String salt) {
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(cr, pass, credentialsSalt, cq);
        return obj.toString();
    }

    public static void main(String[] args) {
//        demo1();
        demo2();
    }

    private static String c(final Map<String, String> map) {
        final String sign = EncryptUtil.encode(map.toString(), "").replaceAll("(.{8})", "$1-").toUpperCase();
        return sign.substring(0, sign.length() - 1);
    }


    //测试2：尝试读取授权文件
    private static void demo2() {
        String cwd=System.getProperty("user.dir"); //D:\java_code\LearnSpring5\Utils
        System.out.println(cwd);
    }


    //测试3：尝试生成授权文件??
    private static void demo3() {
        Map<String, String> resultMap = new HashMap<String, String>();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        resultMap.put("code", "C0363EA8-D25DD6C2-C9905161-46DD2C51");
        resultMap.put("organization", "商业版");
        resultMap.put("start", "2020-01-01");
        resultMap.put("edition", "商业版");
        resultMap.put("expiry", "2030-01-01");

        String sign = c(resultMap);// 8CAE88E0-F70DC710-2988BA2E-B07CE5C5
        System.out.println("1>> sign:" + sign);
        resultMap.put("sign", sign);

        //========
        String data = "";
        data = "{\n" +
                "  \"code\" : \"00000000-00000000-00000000-00000000\",\n" +
                "  \"organization\" : \"社区版\",\n" +
                "  \"start\" : \"2020-01-01\",\n" +
                "  \"sign\" : \"63932CC5-E9762317-571074D8-33A8B312\",\n" +
                "  \"edition\" : \"社区版\",\n" +
                "  \"expiry\" : \"2030-01-01\"\n" +
                "}";
        data = JSONObject.toJSONString(resultMap);
        //{"code":"00000000-00000000-00000000-00000000","organization":"商业版","start":"2020-01-01","sign":"8CAE88E0-F70DC710-2988BA2E-B07CE5C5","edition":"商业版","expiry":"2030-01-01"}
        System.out.println(data);

        //===
        InputStream key = null;
        try {
            key = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/com/example/private1.key");

            int length;
            byte[] bytes = new byte[1024];
            StringBuilder privateKey = new StringBuilder();
            while ((length = key.read(bytes)) != -1) {
                privateKey.append(new String(bytes, 0, length));//将数据变为字符串输出
            }
            key.close();//关闭流
            // System.out.println(privateKey);

            byte[] dataBytes = data.getBytes();
            //byte[] encodedData = RSAUtils.encryptByPrivateKey(dataBytes, privateKey.toString());
            //System.out.println("加密后：\r\n" + new String(encodedData)); //加密后乱码是正常的


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    // 测试：尝试加密
    private static void demo1() {
        Map<String, String> resultMap = new HashMap<>();
        String edition = "社区版";
        resultMap.put("edition", edition);
        String result;
        result = EncryptUtil.encode(edition, "examplespace.com");
        System.out.println(result);// f0b5cd928354ad37faa2f2f3cba6225b

        edition = "个人版";
        result = EncryptUtil.encode(edition, "examplespace.com");
        System.out.println(result);// f01ec0c9d6e36e8a07eb4b1ce834c295


        edition = "社区版";
        result = EncryptUtil.encode(edition, "sliverworkspace.com"); //试用版的授权码
        System.out.println(result); //dacbaf863df5baf6d5905fb1cb1b2cca
    }


}
