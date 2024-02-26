package View.Dashboard.EncryptionStrategy;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;


public class RSAEncryptionStrategy implements EncryptionStrategy {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RSAEncryptionStrategy() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA key pair error", e);
        }
    }


    @Override
    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data with RSA", e);
        }
    }

    @Override
    public String decrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] decodedBytes = Base64.getDecoder().decode(data);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data with RSA", e);
        }
    }

}
