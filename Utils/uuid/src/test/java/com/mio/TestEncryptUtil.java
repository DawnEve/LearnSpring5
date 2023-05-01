package com.mio;

import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class TestEncryptUtil {

    @Test
    public void test1(){
        try{
            InputStream in=null;
            byte[] cipherData=null;

            // 读取授权文件 密文
            in=new FileInputStream(System.getProperty("user.dir") +
                    "/src/test/java/com/mio/file/test.licence");
            final int len=in.available();
            System.out.println("len="+len);
            cipherData = new byte[len];
            in.read(cipherData);

            // 读取证书
            InputStream pubIn;
            pubIn=new FileInputStream(System.getProperty("user.dir")+
                    "/src/test/java/com/mio/file/public.crt");
            pubIn=new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/193_id_rsa.pub");
            //System.out.println(ReadTest.read(pubIn));
            String read = ReadTest.read(pubIn);
            read=read.replace("-----BEGIN CERTIFICATE-----", "");
            read=read.replace("-----END CERTIFICATE-----", "");
            read=read.replace("\n", "");

            read=read.split(" ")[1];
            System.out.println(read);
            System.out.println("1. =====*/");

            PublicKey pubKey =getPublicKey( read );
            //.replace("\r\n", “”))

            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, pubKey);

            byte[] bytes = cipher.doFinal(cipherData);
            String result=new String(bytes);
            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static PublicKey getPublicKey(final String pubKeyBase64) throws Exception {
        final byte[] encPubKey= Base64.getDecoder().decode(pubKeyBase64);
        X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(encPubKey);
        return KeyFactory.getInstance("RSA").generatePublic(encPubKeySpec);
    }
}
