package itesm.mx.racm.datos;

public class Contacto {
    private long id;
    private String nombre;
    private String celular;
    private String telefono;
    private int categoria;
    private int emergencia;
    private int favorito;
    private byte[] foto;

    public Contacto(long id, String nombre, String celular, String telefono, int categoria, int emergencia, int favorito, byte[] foto) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.telefono = telefono;
        this.categoria = categoria;
        this.emergencia = emergencia;
        this.favorito = favorito;
        this.foto = foto;
    }

    public Contacto(String nombre, String celular, String telefono, int categoria, int emergencia, int favorito, byte[] foto) {
        this.nombre = nombre;
        this.celular = celular;
        this.telefono = telefono;
        this.categoria = categoria;
        this.emergencia = emergencia;
        this.favorito = favorito;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(int emergencia) {
        this.emergencia = emergencia;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
