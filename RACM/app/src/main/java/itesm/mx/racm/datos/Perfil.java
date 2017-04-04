package itesm.mx.racm.datos;

/**
 * Created by sergiosanchez on 04/04/17.
 */

public class Perfil {
    private long id;
    private String nombre;
    private String celular;
    private String telefonoFIjo;
    private String direccion;
    private String ubicacion;
    private byte[] foto;

    public Perfil(String nombre, String telefonoFIjo, String direccion) {
        this.nombre = nombre;
        this.telefonoFIjo = telefonoFIjo;
        this.direccion = direccion;
    }

    public Perfil(long id, String nombre, String celular, String telefonoFIjo, String direccion, String ubicacion, byte[] foto) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.telefonoFIjo = telefonoFIjo;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.foto = foto;
    }

    public Perfil(String nombre, String celular, String telefonoFIjo, String direccion, String ubicacion, byte[] foto) {
        this.nombre = nombre;
        this.celular = celular;
        this.telefonoFIjo = telefonoFIjo;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefonoFIjo() {
        return telefonoFIjo;
    }

    public void setTelefonoFIjo(String telefonoFIjo) {
        this.telefonoFIjo = telefonoFIjo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
