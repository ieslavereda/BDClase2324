package es.ieslavereda.dbexamples;

import es.ieslavereda.Usuario;
import es.ieslavereda.db.MyDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarioImp implements RepositorioUsuario {

    DataSource ds = MyDataSource.getMySQLDataSource();

    @Override
    public List<Usuario> getUsuarios() {


        List<Usuario> usuarios = new ArrayList<Usuario>();

        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario")){

            int id;
            String nombre;
            String apellidos;
            int idOficio;

            while (rs.next()) {
                id = rs.getInt("idUsuario");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                idOficio = rs.getInt("Oficio_idOficio");

                usuarios.add(new Usuario(id, nombre, apellidos, idOficio));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


        return usuarios;
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {

        String sql = "INSERT INTO Usuario " +
                "(nombre, apellidos, Oficio_idOficio) " +
                "VALUES (\""+ usuario.getNombre()+"\","+
                "\""+ usuario.getApellidos()+"\","+
                usuario.getIdOficio()+")";


        try(Connection con = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = con.createStatement()){

            stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                usuario.setIdUsuario(id);
            }

            return usuario;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//    @Override
//    public int addUsuario(Usuario usuario) {
//
//        String sql = "INSERT INTO Usuario " +
//                "(idUsuario, nombre, apellidos, Oficio_idOficio) " +
//                "VALUES ("+usuario.getIdUsuario()+","+
//                "\""+ usuario.getNombre()+"\","+
//                "\""+ usuario.getApellidos()+"\","+
//                usuario.getIdOficio()+")";
//
//        return executeUpdate(sql);
//
//    }

    @Override
    public int removeUsuario(Usuario usuario) {
        return removeUsuario(usuario.getIdUsuario());
    }

    @Override
    public int removeUsuario(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = "+idUsuario;

        return executeUpdate(sql);

    }

    @Override
    public int updateUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET "+
                "nombre=?, apellidos=?, Oficio_idOficio=? " +
                "WHERE idUsuario = ?";

        try(Connection con = MyDataSource.getMySQLDataSource().getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)){

            int pos=0;

            pstmt.setString(++pos, usuario.getNombre());
            pstmt.setString(++pos, usuario.getApellidos());
            pstmt.setInt(++pos, usuario.getIdOficio());
            pstmt.setInt(++pos, usuario.getIdUsuario());


            return pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario getUsuario(int idUsuario) {

        String sql = "SELECT * FROM Usuario WHERE idUsuario="+idUsuario;

        try(Connection con = MyDataSource.getMySQLDataSource().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){

            if(rs.next()){
                int id = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                int Oficio_idOficio = rs.getInt("Oficio_idOficio");
                return new Usuario(id, nombre, apellidos, Oficio_idOficio);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    public boolean autentificar(String username, String password) {

        String sql = "SELECT COUNT(*) " +
                "FROM Usuario " +
                "WHERE username=? AND password=?";

        System.out.println(sql);

        try(Connection con = MyDataSource.getMySQLDataSource().getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
        ){
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            rs.next();

            return rs.getInt(1)!=0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

//    @Override
//    public Usuario getUsuario(int idUsuario) {
//
//        Optional<Usuario> optional = getUsuarios().stream()
//                .filter(u -> u.getIdUsuario() == idUsuario)
//                .findFirst();
//
//        if(optional.isPresent())
//            return optional.get();
//        else return null;
//
//    }


    public int executeUpdate(String sql) {
        try(Connection con = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = con.createStatement()){

            return stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
