package logic;

import tools.Tool;

public class Decoder {
    public static byte[] stringMainipDecoder(byte[] bytes) {
        int decodedBytesLength = bytes.length * 3 / 8;
        StringBuilder buildByte = new StringBuilder();
        byte[] decodedBytes = new byte[decodedBytesLength];
        int buildCount = 0;
        int j = 0;
      
        for (byte b : bytes) {
            String currentByte = Tool.byteToBinary(b);
            int pairWithError = -1;
            for (int i = 0; i < 3; ++i) {
                if (currentByte.charAt(i * 2) != currentByte.charAt(i * 2 + 1)) {
                    pairWithError = i;
                    break;
                }
            }

            short xor = 0;
            if (pairWithError != -1) {
                for (int i = 0; i <= 3; ++i) {
                    if (pairWithError == i) {
                        continue;
                    }
                    xor += Short.parseShort(String.valueOf(currentByte.charAt(i * 2)));
                }
                xor %= 2;

            }

            for (int i = 0; i < 3; i++) {
                if (pairWithError == i) {
                    buildByte.append(xor == 0 ? '0' : '1');
                } else {
                    buildByte.append(currentByte.charAt(i * 2));
                }
                ++buildCount;

                if (buildCount == 8) {
                    decodedBytes[j++] = (byte) Integer.parseInt(buildByte.toString(), 2);
                    buildByte.setLength(0);
                    buildCount = 0;
                }
            }

        }
        return decodedBytes;

    }
}
