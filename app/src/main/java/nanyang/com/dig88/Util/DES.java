
package nanyang.com.dig88.Util;

import android.util.Log;

import java.io.IOException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
    String key;

    public DES() {

    }

    public DES(String key) {
        this.key = key;
    }

    public static void main(String args[]) {
//        try {
//
//            DES d = new DES("12345678");
//            String p=d.encrypt("cagent=TST_AGIN/\\\\/loginname=ptest98/\\\\/method=ice");
//
//            System.out.println("密文:"+p);
//
//            String j = d.decrypt("XEWtB/KuTH9NvIzgPuC0VAKUSHHN7XApiCvRjpyjWvjQud14MG/wMBu8i5A89t5Y5AvgNgoyNxW5wwxCZxS/cndFHr+uZY6V/1nWkH36yxnFw+ktSiWUnPt1biM2AU4Uj50vVsoPvoO3blPqDTzcdA==");
//            System.out.println("解密:"+j);
//
//                } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public byte[] desEncrypt(byte[] plainText) throws Exception {
        SecureRandom sr = new SecureRandom();
        Log.d("encryptedData", "SecureRandom: "+sr.toString());
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        byte data[] = plainText;
        byte encryptedData[] = cipher.doFinal(data);
        for (int i = 0; i <encryptedData.length ; i++) {
            Log.d("encryptedData", "desEncrypt: "+encryptedData[i]);

        }
        return encryptedData;
    }

    public byte[] desDecrypt(byte[] encryptText) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte encryptedData[] = encryptText;
        byte decryptedData[] = cipher.doFinal(encryptedData);
        return decryptedData;
    }

    public String encrypt(String input) throws Exception {
        return base64Encode(desEncrypt(input.getBytes())).replaceAll("\\s*", "");
    }

    public String decrypt(String input) throws Exception {
        byte[] result = base64Decode(input);
        return new String(desDecrypt(result));
    }

    public String base64Encode(byte[] s) {
        if (s == null)
            return null;
        return Base64.encode(s);
//        BASE64Encoder b = new sun.misc.BASE64Encoder();
//        return b.encode(s);
    }

    public byte[] base64Decode(String s) throws IOException {
        if (s == null) {
            return null;
        }
        return Base64.decode(s);
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] b = decoder.decodeBuffer(s);
//        return b;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
