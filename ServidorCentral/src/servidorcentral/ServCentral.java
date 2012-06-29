/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import javax.mail.MessagingException;
/**
 *
 * @author Omar El Assaad
 */
public class ServCentral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, MessagingException {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(4445);
        } catch(IOException e){
            System.err.println("No se pudo escuchar del puerto: 4445.");
            System. exit(1);
        }

        Socket clientSocket = null;
        try{
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                clientSocket.getInputStream()));
        String inputLine, outputLine;

        out.println("Servidor Central Arriba");

        String[] aux= new String[4];
        int i=0;
        while ((inputLine=in.readLine()) != null){
            aux[i] = inputLine;
            i++;
            if(i==4){
                i=0;
                Programa.ReportarIncidente(aux);
                Programa.EnviarMail(aux);
                System.out.println(aux[0]+aux[1]+aux[2]+aux[3]);

            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
