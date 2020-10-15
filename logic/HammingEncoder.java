package logic;

import tools.Tool;

public class HammingEncoder {
    public static byte[] biteWiseEncoder(StringBuilder binaryArray) {
     
        byte[] byteArray = new byte[binaryArray.length() / 4];
        int m = 0;
        int xor = 0;
        int j = 0;
        for (int i = 0; i < binaryArray.length() / 4; i++) {
            char[] buildByte = { '0', '0', '0', binaryArray.charAt(j++), '0', binaryArray.charAt(j++),
                    binaryArray.charAt(j++), binaryArray.charAt(j++), '0' };

            // p1 parity
            xor = 0;
            for (int l = 1; l < 8; l += 2) {
                xor ^= Integer.parseInt(String.valueOf(buildByte[l]));

            }
            buildByte[1] = Character.forDigit(xor, 10);
            // p2
            xor = 0;
            for (int l = 2; l <= 8; l++) {
                if (l == 4 || l == 5) {
                    continue;
                }
                xor ^= Integer.parseInt(String.valueOf(buildByte[l]));

            }
            buildByte[2] = Character.forDigit(xor, 2);
            // p4
            xor = 0;
            for (int l = 4; l <= 8; l++) {
                xor ^= Integer.parseInt(String.valueOf(buildByte[l]));

            }
            buildByte[4] = Character.forDigit(xor, 2);
          

            String a = new String(buildByte);
           
            byteArray[m++] = (byte) Integer.parseInt(a.substring(1).toLowerCase(), 2);

        }
        return byteArray;
    }

    public static StringBuilder byteToStringArray(byte[] bytes) {
        StringBuilder binaryArray = new StringBuilder();
        for (byte b : bytes) {
            binaryArray.append(Tool.byteToBinary(b));
        }
        return binaryArray;
    }

}