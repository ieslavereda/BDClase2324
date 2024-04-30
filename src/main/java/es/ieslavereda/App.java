package es.ieslavereda;

import es.ieslavereda.dbexamples.RepositorioUsuario;
import es.ieslavereda.dbexamples.RepositorioUsuarioImp;

public class App {
    public static void main(String[] args) {

        RepositorioUsuario repositorio = new RepositorioUsuarioImp();


        System.out.println(repositorio.autentificar("manolo","1' OR password!='1"));

    }
}
