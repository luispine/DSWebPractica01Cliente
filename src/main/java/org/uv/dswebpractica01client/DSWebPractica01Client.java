package org.uv.dswebpractica01client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15-dy2xxLapDeLuis
 */
public class DSWebPractica01Client {

    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            scanner = new Scanner(System.in);

            Socket cliente = new Socket("localhost", 5000);

            BufferedReader reader = null;
            InputStreamReader isr = null;
            isr = new InputStreamReader(cliente.getInputStream());
            reader = new BufferedReader(isr);

            BufferedWriter writer = null;
            OutputStreamWriter osw = null;
            osw = new OutputStreamWriter(cliente.getOutputStream());
            writer = new BufferedWriter(osw);

            String operation;

            do {
                operation = menu();
                if (!operation.equals("exit")) {
                    sendData(writer, operation);
                    readData(reader);
                }

            } while (!operation.equals("exit"));

        } catch (IOException ex) {
            Logger.getLogger(DSWebPractica01Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static String menu() {
        System.out.println("""
                           \u00bfQu\u00e9 acci\u00f3n quieres realizar?
                           1 Suma
                           2 Resta
                           3 Multiplicaci\u00f3n
                           4 Divisi\u00f3n
                           5 Salir""");

        int option = scanner.nextInt();

        System.out.println("Ingresa un número");
        int a = scanner.nextInt();
        System.out.println("Ingresa un número");
        int b = scanner.nextInt();

        String operation = switch (option) {
            case 1 ->
                a + "+" + b;
            case 2 ->
                a + "-" + b;
            case 3 ->
                a + "*" + b;
            case 4 ->
                a + "*" + b;
            default ->
                "exit";
        };

        return operation;

    }

    private static void sendData(BufferedWriter writer, String operation) throws IOException {

        writer.write(operation + "\n");
        writer.flush();

    }

    private static void readData(BufferedReader reader) throws IOException {
        String msg = reader.readLine();
        System.out.println("MSG: " + msg);
    }
}
