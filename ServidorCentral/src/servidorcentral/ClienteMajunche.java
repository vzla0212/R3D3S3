/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteMajunche {
    public static void main(String[] args) throws IOException {

        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            kkSocket = new Socket("localhost", 4445);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        out.println("DAD17Y");
        out.println("Prados del Este");
        out.println("23-06-12 16:08");
        out.println("145 Km/h");

        out.println("XEU376");
        out.println("Francisco Fajardo");
        out.println("23-06-09 16:08");
        out.println("121 Km/h");

        out.close();
        in.close();
        stdIn.close();
        kkSocket.close();
    }
}