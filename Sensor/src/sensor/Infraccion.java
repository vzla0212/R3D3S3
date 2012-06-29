package sensor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return format.format(fecha) + "\n" + ubicacion + "\n" + velocidad;
    }

    /**
     * Crea el mensaje del protocolo necesario para reportar una infraccion
     * @param out - Canal de datos al servidor
     * @param sensor - sensor que reporta la infraccion
     */
    public void reportar(OutputStream out, Sensor sensor)
            throws Exception {

        byte[] byteArray;
        int in;

        PrintWriter pw = new PrintWriter(out, true);
        BufferedOutputStream bs = new BufferedOutputStream(out);
        BufferedInputStream archivo;

        pw.println("SENSOR");
        pw.println(sensor);
        pw.println("INFRACCION");
        pw.println(this);
        pw.println("IMAGEN");

        byteArray = new byte[8192];
        archivo = new BufferedInputStream(new FileInputStream(imagen));

        while((in = archivo.read(byteArray)) != -1)
            out.write(byteArray, 0, in);

        archivo.close();
        bs.close();
        pw.close();
    }

}
