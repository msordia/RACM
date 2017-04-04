package itesm.mx.racm.datos;

import android.provider.BaseColumns;

public final class DataBaseSchema {

    private DataBaseSchema(){}

    public static class ContactoTable implements BaseColumns {
        public static final String TABLE_NAME= "Contacto";
        public static final String COLUMN_NAME_NOMBRE= "nombre";
        public static final String COLUMN_NAME_CELULAR= "celular";
        public static final String COLUMN_NAME_TELEFONO= "telefonoFijo";
        public static final String COLUMN_NAME_CATEGORIA= "idCategoria";
        public static final String COLUMN_NAME_EMERGENCIA= "emergencia";
        public static final String COLUMN_NAME_FAVORITO= "favorito";
        public static final String COLUMN_NAME_IMAGEN= "imagenContacto";
    }

    public static class PerfilTable implements BaseColumns {
        public static final String TABLE_NAME= "Perfil";
        public static final String COLUMN_NAME_NOMBRE= "nombre";
        public static final String COLUMN_NAME_CELULAR= "celular";
        public static final String COLUMN_NAME_TELEFONO= "telefonoFijo";
        public static final String COLUMN_NAME_DIRECCION= "direccion";
        public static final String COLUMN_NAME_UBICACION= "ubicacion";
        public static final String COLUMN_NAME_IMAGEN= "imagenPerfil";
    }

    public static class CategoriaTable implements BaseColumns {
        public static final String TABLE_NAME= "idCategoria";
        public static final String COLUMN_NAME_NOMBRE= "nombreCategoria";
    }
}

