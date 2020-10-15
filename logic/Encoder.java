package logic;

import tools.Tool;

public class Encoder {

    public static byte[] stringManipulationEncoder(byte[] bytes) {
        int bitCount = bytes.length * 8;
        byte[] encodedBytes = new byte[bitCount / 3 + (bitCount % 3 == 0 ? 0 : 1)];
        StringBuilder BuildBytes = new StringBuilder();
        int j = 0;
        short xor = 0;
        for (byte b : bytes) {
            String currentByte = Tool.byteToBinary(b);
            for (char i : currentByte.toCharArray()) {
                xor += Short.parseShort(String.valueOf(i));
                BuildBytes.append(i).append(i);

                // add parity bit to last
                if (BuildBytes.length() == 6) {
                    // if even add 1 hence add 0
                    xor %= 2;
                    BuildBytes.append(xor).append(xor);
                    encodedBytes[j++] = (byte) Integer.parseInt(BuildBytes.toString(), 2);
                    // reset byte and xor
                    BuildBytes.setLength(0);
                    xor = 0;
                }
            }
        }

        // add 000000 as last if length is not fulfilled
        if (j < encodedBytes.length) {
            while (BuildBytes.length() < 6) {
                BuildBytes.append("00");
                xor %= 2;
                BuildBytes.append(xor).append(xor);
                encodedBytes[j] = (byte) Integer.parseInt(BuildBytes.toString(), 2);

            }
        }
        return encodedBytes;
    }

}
