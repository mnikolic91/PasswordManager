package View.Dashboard.EncryptionStrategy;

public class CaesarCipherStrategy implements EncryptionStrategy {

    @Override
    public String encrypt(String data) {
        return shiftCharacters(data, 1);
    }

    @Override
    public String decrypt(String data) {
        return shiftCharacters(data, -1);
    }

    private String shiftCharacters(String data, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            sb.append((char) (c + shift));
        }
        return sb.toString();
    }
}
