package team.ideart.shiguang.server.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */
public class Encrypt {
    public static String SHA(String decript) {
        if (null == decript || 1 == decript.length()) {
            return "";
        }
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
