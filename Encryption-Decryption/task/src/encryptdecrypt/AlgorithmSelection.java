package encryptdecrypt;

public class AlgorithmSelection {
    private EncryptionAlgorithm algorithm;

    public void setAlgorithm(EncryptionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String encryption(String data, int key) {
        return algorithm.encode(data,key);
    }

    public String decryption(String data, int key) {
        return algorithm.decode(data, key);
    }
}
