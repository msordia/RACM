package itesm.mx.racm.prueba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import itesm.mx.racm.ContactoAdapter;
import itesm.mx.racm.ListaContactoAdapter;
import itesm.mx.racm.R;
import itesm.mx.racm.datos.Categoria;
import itesm.mx.racm.datos.CategoriaOperations;
import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;
import itesm.mx.racm.datos.ListaContacto;

public class AdapterListaContactosActivity extends AppCompatActivity {

    ContactoAdapter adapter;
    TextView tvNombre;
    ListView lvLista;

    ////Nuevo
    ArrayList<Contacto> contactosCompletos;
    ArrayList<Categoria> categorias;
    ContactoOperations dao_Contactos;
    CategoriaOperations dao_Categorias;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        lvLista = (ListView) findViewById(R.id.list_contactos);
        tvNombre = (TextView) findViewById(R.id.text_nombre_categoria);

        ////Nuevooo
        dao_Contactos = new ContactoOperations(this);
        dao_Contactos.open();
        dao_Categorias = new CategoriaOperations(this);
        dao_Categorias.open();

     //   crearCategoriasEstaticas();
       // crearContactosEstaticos();

        contactosCompletos= new ArrayList<Contacto>();
        contactosCompletos= dao_Contactos.obtenerContactos();
        categorias= new ArrayList<Categoria>();
        categorias= dao_Categorias.obtenerCategorias();

        ////

        ListaContacto contactosPrueba= separarCategorias().get(1);

        adapter = new ContactoAdapter(getApplicationContext(),contactosPrueba.getLista());
        lvLista.setAdapter(adapter);

        tvNombre.setText(contactosPrueba.getTitulo());
    }

    public ArrayList<ListaContacto> separarCategorias(){
        ArrayList<ListaContacto> arregloFinal= new ArrayList<ListaContacto>();
        ArrayList<Contacto> arregloLista= new ArrayList<Contacto>();

        int categoriaActual= contactosCompletos.get(0).getCategoria();
        arregloLista.add(contactosCompletos.get(0));

        for(int i=1; i<=contactosCompletos.size(); i++){

            if(i==contactosCompletos.size()){
                ListaContacto temporal= new ListaContacto("otro",arregloLista);//enviar categoria y arreglo de contactos
                arregloFinal.add(temporal);
            }else {
                if (categoriaActual == contactosCompletos.get(i).getCategoria()) {
                    arregloLista.add(contactosCompletos.get(i));
                } else {
                    ListaContacto temporal = new ListaContacto("fam", arregloLista);//enviar categoria y arreglo de contactos
                    arregloFinal.add(temporal);
                    arregloLista = new ArrayList<Contacto>();
                    categoriaActual = contactosCompletos.get(i).getCategoria();
                    arregloLista.add(contactosCompletos.get(i));
                }
            }
        }
        return arregloFinal;
    }

    public void crearCategoriasEstaticas(){
        String[] cat= {"Familia","Amigos","Proveedores","Salud","Servicios"};

        for(int i=0; i<cat.length;i++ ){
            dao_Categorias.añadirCategoria(new Categoria(cat[i]));
        }
    }

    public void crearContactosEstaticos(){
        String[] nom= {"Yocelyn","Ricardo","Sergio"};
        String[] cel= {"85262809","85189299","84401188"};
        String[] tel= {"27984959","27584959","27954959"};
        int [] cat= {2,1,1};
        int [] emer= {0,1,0};
        int [] fav= {0,1,1};
        String[] fotos= {"Foto1","Foto2","Foto3"};

        for(int i=0; i<cat.length;i++ ){
            dao_Contactos.añadirContacto(new Contacto(nom[i],cel[i],tel[i],cat[i],emer[i],fav[i],fotos[i]));
        }
    }
}
