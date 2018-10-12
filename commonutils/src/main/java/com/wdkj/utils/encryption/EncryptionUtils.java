package com.wdkj.utils.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 信息摘要工具
 * 强度从 弱 到 强 依次 是 MD5，sha1，(sha-2)sha256， (sha-2)sha512
 *
 * @author twj
 * @date 2018/9/21 14:00
 */
public class EncryptionUtils {

    /**
     * md5
     *
     * @param strText
     * @return
     */
    public static String MD5(final String strText) {
        return encryption(strText, "md5");
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String SHA1(final String strText) {
        return encryption(strText, "SHA-1");
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String SHA256(final String strText) {
        return encryption(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public static String SHA512(final String strText) {
        return encryption(strText, "SHA-512");
    }

    /**
     * 字符串 SHA 加密
     *
     * @param strText
     * @return
     */
    private static String encryption(final String strText, final String type) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(type);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("没有该算法:" + type, e);
            }
        }

        return strResult;
    }
}
