package com.mio;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class RSADemo {
    Map<String, Object> keyMap;

    //初始化方法：生成一对密钥和公钥
    RSADemo() throws Exception {
        keyMap=initKey(1024);
    }

    public static void main(String[] args) throws Exception {
//        demo1();
        demo2();
    }

    private static void demo2() throws Exception {
        RSADemo demo=new RSADemo();

        //原始信息
        String msg="锄禾日当午";
        System.out.println("1 明文: "+msg);
        //加密
        String msgSecret=encrypt(msg, (String) demo.keyMap.get("publicKeyStr"));
        System.out.println("2 密文: "+msgSecret);
        //解密
        String msg3=decrypt(msgSecret, (String) demo.keyMap.get("privateKeyStr"));
        System.out.println("3 解密后: "+ msg3);
    }

    private static void demo1() throws Exception {
        RSADemo demo=new RSADemo();

        //公钥
        Object publicKey = demo.keyMap.get("publicKey");
        String publicKeyStr = (String)demo.keyMap.get("publicKeyStr");
        System.out.println(publicKey);
        //System.out.println(publicKeyStr);
        System.out.println( RSADemo.getPublicKey(publicKeyStr) );
        //私钥
        System.out.println();
        Object privateKey = demo.keyMap.get("privateKey");
        String privateKeyStr = (String) demo.keyMap.get("privateKeyStr");
        //System.out.println(privateKey);
        //System.out.println(privateKeyStr);
        //System.out.println(RSADemo.getPrivateKey(privateKeyStr));
    }

    public static Map<String, Object> initKey(int keySize) throws Exception {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(keySize);
        //生成密钥对
        KeyPair keyPair = rsa.generateKeyPair();

        //分别获取 公钥和私钥
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        //输出：是数字
        //System.out.println("publicKey: " + publicKey);
        //System.out.println("privateKey: " + privateKey);

        //转为字符串
        String publicKeyStr = key2String((Key) publicKey);
        String privateKeyStr = key2String((Key) privateKey);

        //输出：是数字
        System.out.println("publicKeyStr: " + publicKeyStr);
        System.out.println("privateKeyStr: " + privateKeyStr);


        //返回值
        Map<String, Object> map = new HashMap<>();
        map.put("publicKey", publicKey);
        map.put("privateKey", privateKey);
        //获取字符串
        map.put("publicKeyStr",publicKeyStr);
        map.put("privateKeyStr", privateKeyStr);

        return map;
    }

    //key to string
    public static String key2String(Key key){
        return new String(Base64.getEncoder().encode(( key ).getEncoded()));
    }

    //string 2 key
    public static byte[] String2Key(String key){
        return Base64.getDecoder().decode(key);
    }

    //获取公钥
    static PublicKey getPublicKey(String publicKeyStr) throws Exception {
        byte[] publicKeyByte = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyByte);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //获取私钥
    static PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
        byte[] privateStr = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateStr);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    // 加密
    public static String encrypt(String message, String publicKeyStr){
        System.out.println("明文: " + message);
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKeyStr) );
            byte[] bytes = cipher.doFinal(message.getBytes("UTF-8"));
            System.out.println(bytes);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //私钥解密
    public static String decrypt(String messageSecret, String privateKeyStr){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKeyStr));
            //解密
            byte[] msgDecoded = Base64.getDecoder().decode(messageSecret.getBytes("UTF-8"));
            byte[] bytes = cipher.doFinal(msgDecoded);
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
