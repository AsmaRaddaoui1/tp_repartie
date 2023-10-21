package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            int rep, choice = 0;

            System.out.println("I am a client not yet connected ... ");
            InetAddress serverInetAddress = InetAddress.getByName("192.168.1.30");
            InetSocketAddress serverSocketAddress = new InetSocketAddress(serverInetAddress, 1234);
            Socket socket = new Socket();

            try (socket) {
                socket.connect(serverSocketAddress);
                System.out.println("Establish a connection to the server");

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                // Reading a number
                Scanner scanner = new Scanner(System.in);
                System.out.println("Give a number:");
                int nb = scanner.nextInt();
                outputStream.write(nb);
                System.out.println("I send the number " + nb + " to the server");

                // Operation menu
                do {
                    System.out.println("Menu:");
                    System.out.println("1. Addition");
                    System.out.println("2. Subtraction");
                    System.out.println("3. Multiplication");
                    System.out.println("4. Division");
                    System.out.print("Enter your choice: ");
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice < 1 || choice > 4) {
                            System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                        }
                    }
                } while (choice < 1 || choice > 4);

                outputStream.write(choice);
                System.out.println("I'm waiting for the response from the server");
                rep = inputStream.read();

                switch (choice) {
                    case 1:
                        System.out.println("You selected Addition operation");
                        System.out.println("Response = " + nb + " + 5 = " + rep);
                        break;
                    case 2:
                        System.out.println("You selected Subtraction operation");
                        System.out.println("Response = " + nb + " - 5 = " + rep);
                        break;
                    case 3:
                        System.out.println("You selected Multiplication operation");
                        System.out.println("Response = " + nb + " * 5 = " + rep);
                        break;
                    case 4:
                        System.out.println("You selected Division operation");
                        System.out.println("Response = " + nb + " / 5 = " + rep);
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Closing the client socket...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
