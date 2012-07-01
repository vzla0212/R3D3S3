package servidorlocal;

import java.io.PrintWriter;

public class Reporte {
    private String sensor;
    private String fecha;
    private String ubicacion;
    private int velocidad;
    private String placa;

    public Reporte(String sensor, String fecha, String ubicacion, int velocidad)
    {
        this.sensor = sensor;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.velocidad = velocidad;
        this.placa = "DAD17Y";
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return placa + "\n" + ubicacion + "\n" + fecha + "\n" + velocidad;
    }

    /**
     * Crea el mensaje del protocolo necesario para reportar una infraccion
     * @param out - Canal de datos al servidor
     * @param sensor - sensor que reporta la infraccion
     */
    public void reportar(PrintWriter out) {
        out.println(this);
    }
}
