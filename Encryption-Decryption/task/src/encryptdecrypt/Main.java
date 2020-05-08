package encryptdecrypt;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<String> cliEntry = List.of(args);

        String mode = getMode(cliEntry);
        String data = getData(cliEntry);
        String alg = cliEntry.contains("-alg") ? cliEntry.get(cliEntry.indexOf("-alg") + 1) : "shift";
        String out = cliEntry.contains("-out") ? cliEntry.get(cliEntry.indexOf("-out") + 1) : "console";
        int key = cliEntry.contains("-key") ? Integer.parseInt(cliEntry.get(cliEntry.indexOf("-key") + 1)) : 0;

        EncryptionAlgorithm algorithm = create(alg);
        AlgorithmSelection selectedAlgorithm = new AlgorithmSelection();
        selectedAlgorithm.setAlgorithm(algorithm);

        String result;

        switch (mode) {
            case "enc":
                result = selectedAlgorithm.encryption(data, key);
                break;
            case "dec":
                result = selectedAlgorithm.decryption(data, key);
                break;
            default:
                result = "";
        }

        outputResult(out, result);
    }

    private static EncryptionAlgorithm create(String alg) {
        if ("shift".equals(alg)) {
            return new ShiftEncryptionAlgorithm();
        } else if ("unicode".equals(alg)) {
            return new UnicodeEncryptionAlgorithm();
        }
        return null;
    }

    private static void outputResult(String out, String data) {
        if ("console".equals(out)) {
            System.out.println(data);
        } else {
            writeFile(out, data);
        }
    }

    private static String getData(List<String> cliEntry) {
        boolean hasFile = cliEntry.contains("-in");
        boolean hasData = cliEntry.contains("-data");

        if (hasData) {
            return cliEntry.get(cliEntry.indexOf("-data") + 1);
        }
        if (hasFile) {
            return readFile(cliEntry.get(cliEntry.indexOf("-in") + 1));
        }
        return "";
    }

    private static String getMode(List<String> cli) {
        return cli.contains("-mode") ? cli.get(cli.indexOf("-mode") + 1) : "enc";
    }

    public static String readFile(String filepath) {
        String data = "";

        try (Scanner scanner = new Scanner(new File(filepath))) {
            while (scanner.hasNext()) {
                data += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
        return data;
    }

    public static void writeFile(String filepath, String data) {
        try (PrintWriter printWriter = new PrintWriter(new File(filepath))) {
            printWriter.write(data);
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
