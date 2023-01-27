package com.user.cifrado;

    import javax.crypto.*;
    import javax.crypto.spec.DESKeySpec;
    import org.apache.commons.codec.binary.Base64;

public class CifrarConDES {

    public String cifrar(String value) {
        try {
            DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key=keyFactory.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cleartext = value.getBytes("UTF8");
            String encryptedRut = Base64.encodeBase64String(cipher.doFinal(cleartext));

            return encryptedRut;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
