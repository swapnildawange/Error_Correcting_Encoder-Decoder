package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class Tool {
    public static byte[] getFileAsBytes(File file) {
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {

            e.printStackTrace();
        }
        return fileContent;
    }

    public static String byteToBinary(byte b) {
        StringBuilder byteArray = new StringBuilder(Integer.toBinaryString(b));
        while (byteArray.length() < 8) {
            byteArray.insert(0, '0');
        }
        return byteArray.toString().substring(byteArray.length() - 8, byteArray.length());
    }

    public static void writeToFile(File file, byte[] bytes) {
        try (FileOutputStream fout = new FileOutputStream(file)) {
            fout.write(bytes);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
