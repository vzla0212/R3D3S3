package sensor;

import java.net.Socket;
import java.util.Date;

public class Sensor {

    private int velocidadMaxima;
    private String ubicacion;
    private String servidor;
    private int puerto;

    /**
     * Constructor de la clase. Inicializa valores por defecto
     */
    public Sensor() {
        velocidadMaxima = 60;
        ubicacion = "";
        servidor = "localhost";
        puerto = 1234;
    }

    /**
     * Espera por el paso de un automovil, simulado mediante la espera de un
     *  numero aleatorio en milisegundos. Calcula la velocidad del auto con
     *  un numero aleatorio entre 1 y 201 Kph
     * @return - velocidad del automovil
     */
    private int recibirSeñal() {
        long tiempo = Math.round(Math.random()*10000 + 1000);

        try { Thread.sleep(tiempo); }
        catch (Exception e) {}

        return (int)Math.round(Math.random()*200 + 1);
    }

    /**
     * Retorna la imagen del automovil infractor
     * @return - imagen del infractor
     */
    private String generarImagen() {
        int num = (int)Math.round(Math.random()*169);
        String nombre = "imagenes/img_" + num + ".jpg";
        return nombre;
    }

    /**
     * Envia la infraccion al servidor local
     * @param infraccion - Infraccion cometida
     */
    private void reportarInfraccion(Infraccion infraccion) throws Exception {
        System.out.println(infraccion);
        Socket server = new Socket(servidor, puerto);

        // Creando canal de conexion de entrada salida con el servidor
        infraccion.reportar(server.getOutputStream(), this);
        server.close();

    }

    /**
     * Realiza el trabajo del sensor. Cada vez que recibe una señal toma una
     * captura al vehiculo infractor, la hora de la infraccion y velocidad
     * excedida y la envia al servidor local.
     */
    public void trabajar() {
        int velocidad;
        while (true) {
            velocidad = recibirSeñal();

            // Si la velocidad recibida es mayor, generar la infraccion
            if (velocidad > velocidadMaxima) {
                String imagen = generarImagen();
                Date fecha = new Date();

                // Generando infraccion
                Infraccion infraccion =
                        new Infraccion(fecha, ubicacion, velocidad, imagen);

                try {
                    reportarInfraccion(infraccion);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    @Override
    public String toString() {
       return ubicacion + "-" + velocidadMaxima;
    }
}
