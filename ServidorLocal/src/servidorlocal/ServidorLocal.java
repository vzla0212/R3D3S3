package servidorlocal;
//Comentario intencional

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorLocal {
    private String ubicacion;
    private String servidor;
    private int puertoLocal;
    private int puertoServidor;


    private void trabajar() throws IOException {
        ServerSocket server;
        Socket cliente;

        // Conectando Servidor local
        server = new ServerSocket(puertoLocal);
        System.out.println(this);

        while(true) {
            cliente = server.accept();
            Manejador manejador = new Manejador(this, cliente);
            manejador.start();
        }

    }

    public static void main(String[] args) {

        // Verificando numero de argumentos
        if (args.length != 6) {
            System.err.println("Error en los argumentos de entrada");
        }

        ServidorLocal local = new ServidorLocal();

        // Extrayendo datos de los argumentos de entrada
        for (int i = 0; i < 6; i = i + 2) {
            // Ubicacion del servidor local
            if (args[i].equals("-u"))
                local.setUbicacion(args[i + 1]);

            // Ubicacion del servidor central
            else if (args[i].equals("-s"))
                local.setServidor(args[i + 1]);

            // Numero de puerto
            else if (args[i].equals("-l"))
                local.setPuertoLocal(Integer.parseInt(args[i + 1]));

            else if (args[i].equals("-p"))
                local.setPuertoServidor(Integer.parseInt(args[i + 1]));

            // Argumento desconocido
            else {
                System.err.println("Error: argumento " + args[i] + " no valido");
                return;
            }
        }

        // Iniciando servidor local
        try { local.trabajar(); }
        catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void setPuertoLocal(int puerto) {
        this.puertoLocal = puerto;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setPuertoServidor(int puertoServidor) {
        this.puertoServidor = puertoServidor;
    }

    @Override
    public String toString() {
        return "Servidor local \"" + ubicacion + "\" en puerto " + puertoLocal;
    }

}
