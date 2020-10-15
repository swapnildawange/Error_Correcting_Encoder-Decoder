package ui;

import java.io.File;
import java.util.Scanner;

import logic.Decoder;
import logic.Encoder;
import logic.ErrorSimulator;
import logic.HammingDecoder;
import logic.HammingEncoder;
import tools.Tool;

public class Operation {
    static Scanner scanner = new Scanner(System.in);
    static String mode;
    static File SEND_FILE = new File("send.txt");
    static File RECEIVED_FILE = new File("received.txt");
    static File ENCODED_FILE = new File("encoded.txt");
    static File DECODED_FILE = new File("decoded.txt");

    public static void Run() {
        System.out.println("Enter a mode");
        mode = scanner.nextLine();

        switch (mode) {
            case "encode":
                Encode();
                break;
            case "send":
                Send();
                break;
            case "decode":
                Decode();
                break;
            default:
                break;

        }
    }

    public static void Encode() {
        System.out.println("Hello world");
        byte[] sendBytes = Tool.getFileAsBytes(SEND_FILE);
        StringBuilder expanded = HammingEncoder.byteToStringArray(sendBytes);
        byte[] encoded = HammingEncoder.biteWiseEncoder(expanded);
      
        Tool.writeToFile(ENCODED_FILE, encoded);

    }

    public static void Send() {
        byte[] encodedBytes = Tool.getFileAsBytes(ENCODED_FILE);
    
        byte[] errorBytes = ErrorSimulator.stringManipulatorError(encodedBytes);
        Tool.writeToFile(RECEIVED_FILE, errorBytes);
    }

    public static void Decode() {
        byte[] receivedBytes = Tool.getFileAsBytes(RECEIVED_FILE);
        byte[] deocedBytes = HammingDecoder.stringMainipDecoder(receivedBytes);
        System.out.println(deocedBytes[0]);
        Tool.writeToFile(DECODED_FILE, deocedBytes);
    }
}
