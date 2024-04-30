package es.ieslavereda.dbexamples;

import es.ieslavereda.Usuario;

import java.util.List;

public interface RepositorioUsuario {

    List<Usuario> getUsuarios();
//    int addUsuario(Usuario usuario);
    Usuario addUsuario(Usuario usuario);
    int removeUsuario(Usuario usuario);
    int removeUsuario(int idUsuario);
    int updateUsuario(Usuario usuario);
    Usuario getUsuario(int idUsuario);
    boolean autentificar(String username, String password);

}
