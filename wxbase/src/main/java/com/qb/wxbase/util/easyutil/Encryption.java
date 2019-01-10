package com.qb.wxbase.util.easyutil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * User:贾恒飞 --> 17515250730@163.com
 * create by 2018/4/24 from idea.
 * describe:字符串加密策略
 */
public class Encryption {
    private static Encryption encryption;
    private Encryption (){}
    public static Encryption getEncryption(){
        if (encryption==null)encryption=new Encryption();
        return encryption;
    }

    /**
     * 默认的盐值(加密key)
     */
    private static String key = "";

    /**
     * 设置key
     * @param key1 新的key
     */
    public void setKey(String key1){
        key = key1;
    }

    /**
     * AES加密
     * @param str 待加密字符串
     * @return 加密后的字节数组
     */
    public byte[] aes(String str){
        return encryptAES(str,key);
    }

    /**
     * AES解密
     * @param bt 加密数组
     * @return 解密的字符串
     */
    public String sea(byte[] bt){
        return decryptAES(bt,key);
    }

    /**
     * SHA加密策略
     * @param str 待加密字符串
     * @return 加密后字符串
     */
    public String sha(String str){
        return SHA1(str,"SHA",key);
    }

    /**
     * SHA-1加密策略
     * @param str 待加密字符串
     * @return 加密后字符串
     */
    public String sha1(String str){
        return SHA1(str,"SHA-1",key);
    }

    /**
     * MD5加密策略
     * @param str 待加密字符串
     * @return 加密后字符串
     */
    public String md5(String str){
        return MD5(str,key);
    }

    /**
     * AES加密
     * @param str 待加密字符串
     * @param secretKeyBase 用于生成密钥的基础字符串
     * @return 加密字节数组
     */
    private byte[] encryptAES(String str, String secretKeyBase) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(secretKeyBase.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = str.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     * @param strByteArray 待解密字节数组
     * @param secretKeyBase 用于生成密钥的基础字符串， 需要注意的是EAS是对称加密，所以secretKeyBase在加密解密时要一样的
     * @return 解密后字符串
     */
    private String decryptAES(byte[] strByteArray, String secretKeyBase) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(secretKeyBase.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            String result = new String(cipher.doFinal(strByteArray),"UTF-8");
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SHA-1和SHA加密
     * @param str 待加密字符串
     * @param type 加密类型[SHA-1]or[SHA]
     * @return 加密串
     */
    private String SHA1(String str,String type,String key) {
        try {
            str = str + key;
            MessageDigest digest = MessageDigest
                    .getInstance(type); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5加密
     * @param str 待加密字符串
     * @return 加密串
     */
    private String MD5(String str,String key) {
        try {
            str = str + key;
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(str.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}