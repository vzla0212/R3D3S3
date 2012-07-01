/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import javax.mail.MessagingException;
/**
 *
 * @author Omar El Assaad
 */
public class ServidorCentral {

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

        while(true) {
            clientSocket = serverSocket.accept();
            Manejador manejador = new Manejador(clientSocket);
            manejador.start();

        }

    }
}
