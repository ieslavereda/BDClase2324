package es.ieslavereda.db;

import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionDSExample {

    public static void main(String[] args) {
        conectarMySQL();
    }

    public static Connection conectarMySQL() {
        Connection conexion = null;
        try {
            DataSource dataSource = MyDataSource.getMySQLDataSource();
            conexion = dataSource.getConnection();
            if (conexion != null)
                System.out.println("Conexión realizada con éxito");
        } catch (Exception e) {
            System.out.println("FALLOOOOO EXCEPCION!!!");
            e.printStackTrace();
        }
        return conexion;
    }
}
