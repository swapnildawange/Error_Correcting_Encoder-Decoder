package logic;

import java.util.Random;

import tools.Tool;

public class ErrorSimulator {
    static Random RND = new Random();

    public static byte[] stringManipulatorError(byte[] bytes) {
        byte[] errorBytes = new byte[bytes.length];
        int j = 0;
        StringBuilder binaryString = new StringBuilder();
        for (byte b : bytes) {
            binaryString.append(Tool.byteToBinary(b));
            int n = RND.nextInt(8);
            binaryString.setCharAt(n, binaryString.charAt(n) == '1' ? '0' : '1');
            errorBytes[j++] = (byte) Integer.parseInt(binaryString.toString(), 2);
            // System.out.println(binaryString);
            binaryString.setLength(0);

        }
        return errorBytes;
    }
}
