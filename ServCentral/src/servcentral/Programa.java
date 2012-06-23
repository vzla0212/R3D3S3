/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servcentral;

/**
 *
 * @author ali
 */
import java.sql.*;
import javax.mail.MessagingException;

public class Programa {

    public static void ReportarIncidente(String[] datos) throws ClassNotFoundException, SQLException {
        try {
            //Cargar clase de controlador de base de datos
            Class.forName("com.mysql.jdbc.Driver");
            //Crear el objeto de conexion a la base de datos
            Connection conexion = DriverManager.getConnection("jdbc:mysql://sql09.freemysql.net?user=vzla0212&password=186359");
            //Crear objeto Statement para realizar queries a la base de datos
            Statement instruccion = conexion.createStatement();
            //Un objeto ResultSet, almacena los datos de resultados de una <span id="IL_AD5" class="IL_AD">consulta</span>
            String aux = "INSERT INTO transito.reporte values ('" + datos[0] + "', '" + datos[1] + "', '" + datos[2] + "', '" + datos[3] + "')";
            System.out.println(aux);
            instruccion.executeUpdate(aux);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(e);
        }
    }

    public static void EnviarMail(String[] datos) throws MessagingException, SQLException, ClassNotFoundException {
        try {
            //Cargar clase de controlador de base de datos
            Class.forName("com.mysql.jdbc.Driver");
            //Crear el objeto de conexion a la base de datos
            Connection conexion = DriverManager.getConnection("jdbc:mysql://sql09.freemysql.net?user=vzla0212&password=186359");
            //Crear objeto Statement para realizar queries a la base de datos
            Statement instruccion = conexion.createStatement();
            //Un objeto ResultSet, almacena los datos de resultados de una <span id="IL_AD5" class="IL_AD">consulta</span>
            ResultSet tabla = instruccion.executeQuery("SELECT nombre,email FROM transito.baseplacas where placa = '" + datos[0] + "'");
            //System.out.println("Codigo\tNombre");
            tabla.next();

            //System.out.println(tabla.getString(1)+"\t"+tabla.getString(2));
            String[] aux = new String[1];
            aux[0] = tabla.getString(2);
            Mandarmail.postMail(aux, "PruebaMulta", "Cuidadano " + tabla.getString(1) + " su carro de placas " + datos[0] + " detectado en " + datos[1] + "a una velocidad de " + datos[3] + " en fecha y hora " + datos[2], "pruebatransito@vzla0212.com");
            System.out.println("Mensaje enviado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(e);
        }
    }
}
