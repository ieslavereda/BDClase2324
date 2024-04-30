package es.ieslavereda.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyDataSource {

    public static DataSource getMySQLDataSource() {

// Propiedades donde tenemos los datos de acceso a la BD
        Properties props = new Properties();

// Objeto DataSource que devolveremos
        MysqlDataSource mysqlDS = null;


        try (FileInputStream fis = new FileInputStream("db.properties");) {
// Cargamos las propiedades
            props.load(fis);
            // Generamos el DataSource con los datos URL, user y passwd necesarios
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }

}
