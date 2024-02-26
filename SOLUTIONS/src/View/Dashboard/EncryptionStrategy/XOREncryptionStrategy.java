package View.Dashboard.EncryptionStrategy;

public class XOREncryptionStrategy implements EncryptionStrategy {

    @Override
    public String encrypt(String data) {
        return shiftCharacters(data, 2);
    }

    @Override
    public String decrypt(String data) {
        return shiftCharacters(data, -2);
    }

    private String shiftCharacters(String data, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            sb.append((char) (c + shift));
        }
        return sb.toString();
    }
}
