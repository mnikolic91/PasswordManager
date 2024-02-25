package View.Dashboard.EncryptionStrategy;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class AESEncryptionStrategy implements EncryptionStrategy {

    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = new byte[] {
            (byte) 0xAE, (byte) 0x15, (byte) 0x4F, (byte) 0x88, (byte) 0x55, (byte) 0x40,
            (byte) 0x48, (byte) 0x07, (byte) 0xC5, (byte) 0xB8, (byte) 0x17, (byte) 0x3D,
            (byte) 0x7A, (byte) 0x0B, (byte) 0xF2, (byte) 0x25
    };

    @Override
    public String encrypt(String data) {
        try {
            SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }
}