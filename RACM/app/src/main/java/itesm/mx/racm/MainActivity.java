package itesm.mx.racm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import itesm.mx.racm.datos.Categoria;
import itesm.mx.racm.datos.CategoriaOperations;
import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;
import itesm.mx.racm.datos.ListaContacto;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton imageSearch, imageContact, imagePhone, imageCreate;
    ListView lvLista;

    ////Nuevo
    ArrayList<Contacto> contactosCompletos;
    ArrayList<Categoria> categorias;
    ContactoOperations dao_Contactos;
    CategoriaOperations dao_Categorias;

    ////
    ListaContactoAdapter adapter;
    ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLista = (ListView) findViewById(R.id.list_categorias);

        ////Nuevooo
        dao_Contactos = new ContactoOperations(this);
        dao_Contactos.open();
        dao_Categorias = new CategoriaOperations(this);
        dao_Categorias.open();

       // crearCategoriasEstaticas();
        //crearContactosEstaticos();

        contactosCompletos= new ArrayList<Contacto>();
        contactosCompletos= dao_Contactos.obtenerContactos();
        categorias= new ArrayList<Categoria>();
        categorias= dao_Categorias.obtenerCategorias();

        ////

        adapter = new ListaContactoAdapter(getApplicationContext(),separarCategorias());
        lvLista.setAdapter(adapter);

        ////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Crear_Contacto);
        ImageButton ibSearch = (ImageButton) findViewById(R.id.ib_search);
        ibSearch.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_Crear_Contacto:
                Intent intent = new Intent(this, CrearContacto.class);
                startActivity(intent);
                break;

            case R.id.ib_search:

                break;
        }
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
        String[] nom= {"Yocelyn","Xinia","Shamir"};
        String[] cel= {"85262809","85189299","84401188"};
        String[] tel= {"27984959","27584959","27954959"};
        int [] cat= {1,1,2};
        int [] emer= {0,1,0};
        int [] fav= {0,1,1};
        String[] fotos= {"Foto1","Foto2","Foto3"};

        for(int i=0; i<cat.length;i++ ){
            dao_Contactos.añadirContacto(new Contacto(nom[i],cel[i],tel[i],cat[i],emer[i],fav[i],fotos[i]));
        }
    }
}