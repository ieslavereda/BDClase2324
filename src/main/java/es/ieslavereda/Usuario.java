package es.ieslavereda;

public class Usuario {

    private Integer idUsuario;
    private String nombre;
    private String apellidos;
    private int idOficio;

    public Usuario(String nombre, String apellidos, int idOficio) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.idOficio = idOficio;
    }

    public Usuario(int idUsuario, String nombre, String apellidos, int idOficio) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.idOficio = idOficio;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(int idOficio) {
        this.idOficio = idOficio;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", idOficio=" + idOficio +
                '}';
    }
}
