package sensor;

import java.io.PrintWriter;
import java.util.Date;

public class Infraccion {

    private Date fecha;
    private String ubicacion;
    private int velocidad;
    private String imagen;

    /**
     * Constructor de la clase. Incializa los valores de la infraccion
     * @param fecha - fecha en que ocurre la infraccion
     * @param ubicacion - ubicacion del sensor que reporta la infraccion
     * @param velocidad - velocidad reportada por el sensor como excedida
     * @param imagen - imagen del vehiculo infractor
     */
    public Infraccion(Date fecha, String ubicacion, int velocidad,
            String imagen) {
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.velocidad = velocidad;
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getImagen() {
        return imagen;
    }


    public String getUbicacion() {
        return ubicacion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public String toString() {
        return fecha + "\n" + ubicacion + "\n" + velocidad;
    }

    /**
     * Crea el mensaje del protocolo necesario para reportar una infraccion
     * @param out - Canal de datos al servidor
     * @param sensor - sensor que reporta la infraccion
     */
    public void reportar(PrintWriter out, Sensor sensor) {
        out.println("SENSOR");
        out.println(sensor);
        out.println("INFRACCION");
        out.println(this);
    }

}
