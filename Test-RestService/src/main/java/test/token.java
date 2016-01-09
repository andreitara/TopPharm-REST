package test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrei on 1/5/2016.
 */
public class token {

    public static void main(String[] args){
        try {
            String date = new SimpleDateFormat("yyyyMMDDHHmmss").format(new Date());
            String text = "{\"username\":\"123456789112345678911234567890\",\"date\":\""+date+"\"}";
            String key = "c48da47136f93444"; // 128 bit key

            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            String str = Hex.encodeHexString(encrypted);
            System.err.println(str);

            // decrypt the text
            byte[] bytes = Hex.decodeHex(str.toCharArray());
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(bytes));
            System.err.println(decrypted);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }


}
