package encryptdecrypt;

public interface EncryptionAlgorithm {
    String encode(String data, int key);

    String decode(String data, int key);
}
