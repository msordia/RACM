package itesm.mx.racm.datos;


import java.util.ArrayList;

public class ListaContacto {

    String titulo;
    ArrayList<Contacto> lista;

    public ListaContacto(String titulo, ArrayList<Contacto> lista) {
        this.titulo = titulo;
        this.lista = lista;
    }

    public ArrayList<Contacto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Contacto> lista) {
        this.lista = lista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
