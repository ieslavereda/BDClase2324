package es.ieslavereda.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionExample {

    public static void main(String[] args) {

        ConnectionExample ce = new ConnectionExample();

        Connection connection = ce.conectarMySQL();

    }

    public Connection conectarOracle() {
        Connection conexion = null;
        String host ="172.28.201.239";
        String puerto = "1521";
        String usuario = "C##Joaquin";
        String password = "root";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@" + host + ":" + puerto + ":XE";
            conexion = DriverManager.getConnection(BaseDeDatos, usuario, password);
            if (conexion != null)
                System.out.println("Conexión realizada con éxito");
        } catch (Exception e) {
            System.out.println("FALLOOOOO EXCEPCION!!!");
            e.printStackTrace();
        }
        return conexion;
    }

    public Connection conectarMySQL() {
        Connection conexion = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conexion =
                    DriverManager.getConnection(
        "jdbc:mysql://172.30.134.203:3306/java?user=jalonso&password=1111");

            if (conexion != null)
                System.out.println("Conexión realizada con éxito");
        } catch (Exception e) {
            System.out.println("FALLOOOOO EXCEPCION!!!");
            e.printStackTrace();
        }
        return conexion;
    }

}
