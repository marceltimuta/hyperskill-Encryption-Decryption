package encryptdecrypt;

public class ShiftEncryptionAlgorithm implements EncryptionAlgorithm {

    @Override
    public String encode(String data, int key) {
        StringBuilder result = new StringBuilder();
        char currentChar;

        for (int i = 0; i < data.length(); i++) {
            currentChar = data.charAt(i);
            if (Character.isLetter(currentChar)) {
                if (Character.isUpperCase(currentChar)) {
                    char ch = (char) (((int) currentChar + key - 65) % 26 + 65);
                    result.append(ch);
                } else {
                    char ch = (char) (((int) currentChar + key - 97) % 26 + 97);
                    result.append(ch);
                }
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    @Override
    public String decode(String data, int key) {
        return encode(data, 26 - key);
    }
}
