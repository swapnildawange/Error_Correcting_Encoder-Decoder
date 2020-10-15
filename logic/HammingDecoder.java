package logic;

import tools.Tool;

public class HammingDecoder {

    public static byte[] stringMainipDecoder(byte[] bytes) {
        byte[] decodedByte = new byte[bytes.length / 2];
        StringBuilder decoded = new StringBuilder();
        int pointer = 0;

        int xor = 0;
        for (byte b : bytes) {

            StringBuilder x = new StringBuilder(Tool.byteToBinary(b));

            char[] buildByte = x.toString().toCharArray();
            int badParity = 0;

            // p1
            xor = 0;
            for (int l = 2; l < 8; l += 2) {

                xor ^= Integer.parseInt(String.valueOf(buildByte[l]));

            }

            if (buildByte[0] != Character.forDigit(xor, 10)) {

                badParity += 1;
            }

            // p2
            xor = 0;
            for (int l = 2; l < 7; l++) {

                if (l == 3 || l == 4) {
                    continue;
                } else {

                    xor ^= Integer.parseInt(String.valueOf(buildByte[l]));
                }

            }

            if (buildByte[1] != Character.forDigit(xor, 10)) {

                badParity += 2;
            }
            // p4
            xor = 0;
            for (int l = 4; l < 7; l++) {

                xor ^= Integer.parseInt(String.valueOf(buildByte[l]));

            }

            if (buildByte[3] != Character.forDigit(xor, 10)) {

                badParity += 4;

            }
            if (badParity > 0) {
                buildByte[badParity - 1] = buildByte[badParity - 1] == '0' ? '1' : '0';
            }
            for (int m = 2; m < 7; m++) {
                if (m == 3) {
                    continue;
                } else {
                    decoded.append(buildByte[m]);

                }
                if (decoded.length() == 8) {
                    decodedByte[pointer++] = (byte) Integer.parseInt(decoded.toString(), 2);
                    decoded.setLength(0);
                }
            }

        }

        return decodedByte;
    }
}
