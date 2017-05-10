package itesm.mx.racm;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import itesm.mx.racm.datos.Categoria;
import itesm.mx.racm.datos.CategoriaOperations;
import itesm.mx.racm.datos.Contacto;
import itesm.mx.racm.datos.ContactoOperations;
import itesm.mx.racm.datos.ListaContacto;
import android.widget.SearchView;
import android.app.SearchManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,  SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    ImageButton imageSearch, imageContact, imagePhone, imageCreate;
    ArrayList<Contacto> contactosCompletos;
    ArrayList<Categoria> categorias;
    ContactoOperations dao_Contactos;
    CategoriaOperations dao_Categorias;
    List<String> titulos;
    HashMap<String,List<Contacto>> listaContactos;
    MenuFragment fragmentoMenu;
    SearchView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentoMenu = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_menu);
        expListView = (ExpandableListView) findViewById(R.id.list_categorias);
        dao_Contactos = new ContactoOperations(this);
        dao_Contactos.open();
        dao_Categorias = new CategoriaOperations(this);
        dao_Categorias.open();

        crearCategoriasEstaticas();
        //crearContactosEstaticos();

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);

        contactosCompletos= new ArrayList<Contacto>();
        contactosCompletos= dao_Contactos.obtenerContactos();
        categorias= new ArrayList<Categoria>();
        categorias= dao_Categorias.obtenerCategorias();
        titulos= new ArrayList<String>();
        listaContactos= new HashMap<String,List<Contacto>>();
        separarCategorias();
        listAdapter = new ExpandableListAdapter(this, titulos,listaContactos);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Contacto contacto= (Contacto) listaContactos.get(titulos.get(groupPosition)).get(childPosition);
                Intent intent= new Intent(getApplicationContext(),ContactoDetalle.class);
                intent.putExtra("idContacto",contacto.getId());
                startActivity(intent);
                return false;
            }
        });

        ////BOTON FLOTANTE
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Crear_Contacto);
        //ImageButton ibSearch = (ImageButton) findViewById(R.id.ib_search);
        //ibSearch.setOnClickListener(this);
        fab.setOnClickListener(this);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        contactosCompletos= dao_Contactos.obtenerContactos();
        categorias= dao_Categorias.obtenerCategorias();
        titulos.clear();
        listaContactos.clear();
        separarCategorias();
        listAdapter.setTitles(titulos);
        listAdapter.setContent(listaContactos);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_Crear_Contacto:
                Intent intent = new Intent(this, CrearContacto.class);
                startActivity(intent);
                break;

          //  case R.id.ib_search:

           //     break;
        }
    }

    public void separarCategorias(){
        ArrayList<Contacto> arregloLista= new ArrayList<Contacto>();
        ArrayList<Contacto> arregloFavoritos= new ArrayList<Contacto>();

        if(contactosCompletos.size()!=0) {

            int categoriaActual = contactosCompletos.get(0).getCategoria();
            arregloLista.add(contactosCompletos.get(0));
            titulos.add("Favoritos");
            titulos.add(obtenerNombreCategoria(categoriaActual));
            if(contactosCompletos.get(0).getFavorito()==1){
                arregloFavoritos.add(contactosCompletos.get(0));
            }
            listaContactos.put("Favoritos",arregloFavoritos);

            for (int i = 1; i <= contactosCompletos.size(); i++) {

                if (i == contactosCompletos.size()) {
                    listaContactos.put(obtenerNombreCategoria(arregloLista.get(0).getCategoria()), arregloLista);//enviar categoria y arreglo de contacto

                    if(arregloFavoritos.size()>0) {
                        listaContactos.put("Favoritos", arregloFavoritos);
                    }else{
                        listaContactos.remove("Favoritos");
                        titulos.remove("Favoritos");
                    }
                } else {

                    if(contactosCompletos.get(i).getFavorito()==1){
                        arregloFavoritos.add(contactosCompletos.get(i));
                    }

                    if (categoriaActual == contactosCompletos.get(i).getCategoria()) {
                        arregloLista.add(contactosCompletos.get(i));
                    } else {
                        listaContactos.put(obtenerNombreCategoria(arregloLista.get(0).getCategoria()), arregloLista);//enviar categoria y arreglo de contactos
                        arregloLista = new ArrayList<Contacto>();
                        categoriaActual = contactosCompletos.get(i).getCategoria();
                        titulos.add(obtenerNombreCategoria(categoriaActual));
                        arregloLista.add(contactosCompletos.get(i));
                    }
                }
            }
        }
    }

    public void crearCategoriasEstaticas(){
        String[] cat= {"Familia","Amigos","Salud","Proveedores","Servicios"};

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
            dao_Contactos.añadirContacto(new Contacto(nom[i],cel[i],tel[i],cat[i],emer[i],fav[i],new byte[1]));
        }
    }

    public String obtenerNombreCategoria(int id){
        String categoria= "";
                for(int i=0; i<categorias.size();i++){
                 if(categorias.get(i).getIdCategoria()==id){
                     categoria= categorias.get(i).getNombreCategoria();
                 }
                }
        return categoria;
    }

    @Override
    public boolean onClose() {
        contactosCompletos= new ArrayList<Contacto>();
        contactosCompletos= dao_Contactos.obtenerContactos();
        categorias= new ArrayList<Categoria>();
        categorias= dao_Categorias.obtenerCategorias();
        titulos= new ArrayList<String>();
        listaContactos= new HashMap<String,List<Contacto>>();
        separarCategorias();
        listAdapter = new ExpandableListAdapter(this, titulos,listaContactos);
        expListView.setAdapter(listAdapter);

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        contactosCompletos= new ArrayList<Contacto>();
        contactosCompletos= dao_Contactos.obtenerContacto(query);
        categorias= new ArrayList<Categoria>();
        categorias= dao_Categorias.obtenerCategorias();
        titulos= new ArrayList<String>();
        listaContactos= new HashMap<String,List<Contacto>>();
        separarCategorias();
        listAdapter = new ExpandableListAdapter(this, titulos,listaContactos);
        expListView.setAdapter(listAdapter);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if(newText.isEmpty()){
            contactosCompletos= new ArrayList<Contacto>();
            contactosCompletos= dao_Contactos.obtenerContactos();
            categorias= new ArrayList<Categoria>();
            categorias= dao_Categorias.obtenerCategorias();
            titulos= new ArrayList<String>();
            listaContactos= new HashMap<String,List<Contacto>>();
            separarCategorias();
            listAdapter = new ExpandableListAdapter(this, titulos,listaContactos);
            expListView.setAdapter(listAdapter);
        }

        return false;
    }
}