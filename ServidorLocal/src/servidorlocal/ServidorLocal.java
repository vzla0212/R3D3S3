package servidorlocal;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorLocal {
    private String ubicacion;
    private String servidor;
    private int puertoLocal;
    private int puertoServidor;

    /**
     * Espera por la peticion de un cliente. Una vez aceptado crea un hilo
     *  que lo atiende
     * @throws IOException
     */
    public void trabajar() throws IOException {
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
