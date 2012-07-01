package servidorcentral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;
import javax.mail.MessagingException;


public class Manejador extends Thread {
    private Socket clientSocket;

    public Manejador(Socket cliente) {
        this.clientSocket = cliente;
    }

    @Override
    public void run() {
        try{
            InputStreamReader isr = new InputStreamReader(
                    clientSocket.getInputStream());
            try (BufferedReader in = new BufferedReader(isr)) {
                String inputLine;
                String[] aux= new String[4];
                int i=0;

                while ((inputLine=in.readLine()) != null){
                    aux[i] = inputLine;
                    i++;

                    if(i==4){
                        i=0;
                        Programa.ReportarIncidente(aux);
                        Programa.EnviarMail(aux);
                    }
                }
            }

            clientSocket.close();
        }catch (IOException | ClassNotFoundException | SQLException |
                MessagingException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
