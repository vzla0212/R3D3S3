package servidorlocal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        Reporte reporte;
        try {
            // Creacion de canales E/S
            InputStreamReader isr = new InputStreamReader(
                    cliente.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            OutputStream out = cliente.getOutputStream();

            String linea = in.readLine();
            if (!linea.equals("SENSOR")) return;

            // Leyendo nombre del sensor
            String sensor = in.readLine();
            linea = in.readLine();
            if (!linea.equals("INFRACCION")) return;

            // Leyendo datos de la infraccion
            String fecha = in.readLine();
            String ubicacion = in.readLine();
            int velocidad = Integer.parseInt(in.readLine());

            reporte = new Reporte(sensor, fecha, ubicacion, velocidad);
            reportarInfraccion(reporte);
            
        } catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void reportarInfraccion(Reporte reporte) throws Exception{
        System.out.println(reporte);
        Socket server = new Socket(local.getServidor(),
                local.getPuertoServidor());

        // Creando canal de conexion de entrada salida con el servidor
        InputStreamReader isr = new InputStreamReader(server.getInputStream());
        BufferedReader in = new BufferedReader(isr);
        PrintWriter out = new PrintWriter(server.getOutputStream(), true);

        reporte.reportar(out);
    }

    /**
     * Procesa la imagen de la infraccion
     */
    private void procesarImagen() {

    }
}
