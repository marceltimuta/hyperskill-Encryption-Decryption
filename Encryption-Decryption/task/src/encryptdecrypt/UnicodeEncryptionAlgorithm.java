package encryptdecrypt;

public class UnicodeEncryptionAlgorithm implements EncryptionAlgorithm{



    @Override
    public String encode(String data, int key) {
        String output = "";

        for (int i = 0; i < data.length(); i++) {
            output += (char) (data.charAt(i) + key > 127 ? (data.charAt(i) + key) % 127 : data.charAt(i) + key);
        }
        return output;
    }

    @Override
    public String decode(String data, int key) {
        String output = "";

        for (int i = 0; i < data.length(); i++) {
            output += (char) (data.charAt(i) - key < 0 ? 127 - data.charAt(i) : data.charAt(i) - key);
        }
        return output;
    }
}
