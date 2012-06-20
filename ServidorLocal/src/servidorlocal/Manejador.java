package servidorlocal;

import java.net.Socket;

public class Manejador extends Thread {
    private ServidorLocal local;
    private Socket cliente;

    /**
     * Constructor de la clase. Requiere los datos del servidor local y el
     *  socket del cliente para establecer comunicacion
     * @param local - ServidorLocal
     * @param cliente - cliente aceptado
     */
    public Manejador(ServidorLocal local, Socket cliente) {
        this.local = local;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            // Obtener infraccion
        } catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Procesa la imagen de la infraccion
     */
    private void procesarImagen() {

    }
}
