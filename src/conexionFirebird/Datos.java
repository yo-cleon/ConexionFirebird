/**
 * Clase para la gestión de la conexión y las ocnsultas con la BBDD.
 * BBDD: Firebird 2.5
 * Driver: JDBC Driver jaybird-full-3.0.3 
 */
package conexionFirebird;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Carlos León Remedios
 * 2º DAM - Acceso a Datos
 * Ejercicio 12/03/2018
 */
public class Datos {
    
    private Connection conexion;
    private String path = System.getProperty("user.dir");
    private String user = "SYSDBA";
    private String pass = "masterkey";
    private ResultSet resultado = null;
    
    public void conectar(){
        try{
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conexion = DriverManager.getConnection("jdbc:firebirdsql:localhost/3055:"
                    +path+"\\data\\EJERCICIO02.fdb", user, pass);
        } catch (SQLException sqle){
            System.out.println("Error en la conexión:\n"+sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error registrando el driver:\n"+cnfe.getMessage());
        }
    }
    
    public ResultSet consultarClientes(){
        try {
            Statement st = conexion.createStatement();
            resultado = st.executeQuery("select nif, nombre from Clientes");
        } catch (SQLException sqlex){
            System.out.println("Error en la consulta:\n"+sqlex.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consultarCliente(String nif){
        ResultSet cliente = null;
        try {
            Statement st = conexion.createStatement();
            cliente = st.executeQuery("select nif, nombre, direccion, telefono from Clientes "
                    + "where nif = \'"+nif+"\'");
        } catch (SQLException sqlex){
            System.out.println("Error en la consulta:\n"+sqlex.getMessage());
        }
        return cliente;
    }
    
    public void comprobacion(){
        System.out.println(path);
    }
    
}
