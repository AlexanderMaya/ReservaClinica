package com.straccion.reservahotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.straccion.reservahotel.objetos.HorariosMedicos;
import com.straccion.reservahotel.objetos.MostarReservas;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdminBD extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "reservaBD";


    //creacion de tablas
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String TABLA_MEDICOS = "medicos";
    public static final String TABLA_RESERVACIONES = "reservaciones";

    public AdminBD(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

//    public static boolean databaseExists(Context context) {
//        File dbFile = context.getDatabasePath(DATABASE_NOMBRE);
//        return dbFile.exists();
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_USUARIOS + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBRE VARCHAR(100) NOT NULL, " +
                "CORREO VARCHAR(100) NOT NULL, " +
                "TELEFONO VARCHAR(100) NOT NULL, " +
                "CONTRASEÑA VARCHAR(100) NOT NULL, " +
                "NOMBREIMAGEN TEXT NOT NULL, " +
                "IMAGEN TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLA_MEDICOS + " (" +
                "IDMEDICO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NOMBREMEDICO VARCHAR(100) NOT NULL, " +
                "CLINICA_TRABAJA_MEDICO VARCHAR(100) NOT NULL, " +
                "CIUDAD VARCHAR(100) NOT NULL, " +
                "ESPECIALIDAD VARCHAR(100) NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLA_RESERVACIONES + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "IDMEDICO INTEGER NOT NULL, " +
                "HORA VARCHAR(100) NOT NULL, " +
                "FECHA VARCHAR(100) NOT NULL, " +
                "LUGAR VARCHAR(100) NOT NULL," +
                "FOREIGN KEY (IDMEDICO) REFERENCES medicos (IDMEDICO))");

    }

    public void llenarMedicos() {
        //abrir();
        String sentenciaSQL = "INSERT INTO " + TABLA_MEDICOS + " (NOMBREMEDICO, CLINICA_TRABAJA_MEDICO, CIUDAD, ESPECIALIDAD ) VALUES ('Juan Perez', 'Clinica Medellin S.A.S', 'Medellin', 'Dermatologia' ), " +
                "('Eduardo Gonzalez', 'Hospital de La Estrella', 'La Estrella', 'Ginecologia'), ('Roberto Sánchez', 'Hospital de La Estrella', 'La Estrella', 'Medicina Interna'), " +
                "('Carlos Rodriguez', 'Hospital San Rafael', 'Itagui', 'Oftalmologia'), (' Alejandro Lopez', 'Clinica Medellin S.A.S', 'Medellin', 'Ortopedia y traumatologia'), " +
                "(' Javier Martinez', 'Hospital San Juan de Dios', 'Rionegro', 'Pediatria'), ('Beatriz Perez', 'Hospital San Juan de Dios', 'Rionegro', 'Urologia'), " +
                "('Luis Ramirez', 'Clínica CES.', 'Itagui', 'Ginecologia'), ('Claudia Herrera', 'Clínica CES.', 'Itagui', 'Medicina Interna'), " +
                "('Jose Fernandez', 'Clínica Las Américas', 'Sabaneta', 'Ginecologia'), ('Susana Ramirez', 'Clinica Medellin S.A.S', 'Medellin', 'Dermatologia'), " +
                "('Pedro Garcia', 'Clinica Medellin S.A.S', 'Medellin', 'Ginecologia'), ('Isabel Fernández', 'Hospital San Vicente Fundación', 'Medellin', 'Medicina Interna'), " +
                "('Miguel Torres', 'Clínica Somer', 'Rionegro', 'Oftalmologia'), ('Natalia Torres', 'Clinica Medellin S.A.S', 'Medellin', 'Ortopedia y traumatologia'), " +
                "('Maria Lopez', 'Clínica las Vegas', 'Envigado', 'Pediatria'), ('Julia Sánchez', 'Hospital Marco Fidel Suárez.', 'Bello', 'Urologia'), " +
                "('Camila Mendez', 'Clínica Las Américas', 'Sabaneta', 'Ginecologia'), ('Isabel Mendoza', 'Clínica Las Américas', 'Sabaneta', 'Medicina Interna'), " +
                "('Valentina Rios', 'Clínica las Vegas', 'Envigado', 'Ginecologia'), ('Clara Paredes', 'Clínica las Vegas', 'Envigado', 'Medicina Interna'), " +
                "('Gabriela Silva', 'Clínica Somer', 'Rionegro', 'Oftalmologia'), ('Andres Rios', 'Clinica El Rosario', 'Medellin', 'Ortopedia y traumatologia'), " +
                "('Gabriel Gomez', 'Hospital Marco Fidel Suárez.', 'Bello', 'Pediatria'), ('Alvaro Delgado', 'Hospital Marco Fidel Suárez.', 'Bello', 'Urologia'), " +
                "('Laura Martinez', 'Hospital de La Estrella', 'La Estrella', 'Ginecologia'), ('Rafael Jimenez', 'Clinica El Rosario', 'Medellin', 'Medicina Interna'), " +
                "('Patricia Gonzalez', 'Hospital San Juan de Dios', 'Rionegro', 'Ginecologia'), ('Fernando Mendoza', 'Hospital de La Estrella', 'La Estrella', 'Dermatologia')";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sentenciaSQL);

        cerrarbd();
    }
    public int llenarHorarios(int medico, String hora, String fecha, String lugar ) {
        abrir();
        int valor = 0;
        String sql = "SELECT ID FROM " + TABLA_RESERVACIONES + " WHERE IDMEDICO = '" + medico + "' AND HORA = '" + hora + "' AND FECHA = '" + fecha + "' ";
        Cursor dato = this.getWritableDatabase().rawQuery(sql, null);

        if (dato.moveToFirst()) {

        }else {
            ContentValues insertar = new ContentValues();
            insertar.put("IDMEDICO", medico);
            insertar.put("HORA", hora);
            insertar.put("FECHA", fecha);
            insertar.put("LUGAR", lugar);
            this.getWritableDatabase().insert(TABLA_RESERVACIONES,null, insertar);
            cerrarbd();
            valor = 1;
            return valor;
        }
        cerrarbd();
        return valor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //valida si ya esta creada la base de datos
    private static AdminBD instance;

    public static synchronized AdminBD getInstance(Context context) {
        if (instance == null) {
            instance = new AdminBD(context.getApplicationContext());
        }
        return instance;
    }

    public void abrir() {
        this.getWritableDatabase();
    }

    public void cerrarbd() {
        this.close();
    }

    public void insertarDatosUsuario(String nombre, String correo, String numero, String contraseña, ContentValues values) {
        abrir();
        ContentValues insertar = new ContentValues();
        insertar.put("NOMBRE", nombre);
        insertar.put("CORREO", correo);
        insertar.put("TELEFONO", Integer.parseInt(numero));
        insertar.put("CONTRASEÑA", contraseña);
        insertar.put("NOMBREIMAGEN", values.get("nombre").toString());
        insertar.put("IMAGEN", values.get("imagen").toString());
        this.getWritableDatabase().insert(TABLA_USUARIOS, numero, insertar);
        cerrarbd();
    }

    public String mostrarImagen(int id) {
        String sql = "SELECT IMAGEN FROM usuarios WHERE ID = " + id;
        Cursor datos = this.getWritableDatabase().rawQuery(sql, null);
        String imagen = null;

        if (datos.moveToFirst()) {
            imagen = datos.getString(datos.getColumnIndex("IMAGEN"));
        }

        datos.close();
        cerrarbd();

        return imagen;
    }

    public int actualizarDatosUsuario(int idUsuario, String nombre, String correo, String telefono, ContentValues values){
        int resultado;

        ContentValues registro = new ContentValues();
        registro.put("NOMBRE", nombre);
        registro.put("CORREO", correo);
        registro.put("TELEFONO", telefono);
        registro.put("NOMBREIMAGEN", values.get("nombre").toString());
        registro.put("IMAGEN", values.get("imagen").toString());

        SQLiteDatabase db = this.getReadableDatabase();
        resultado = db.update(TABLA_USUARIOS, registro, "ID = " + idUsuario, null);
        return resultado;
    }

    public List<String> datosUsuario(int idUser){
        List<String> datos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT TELEFONO, NOMBRE, CORREO  FROM usuarios WHERE ID = " + idUser, null);

        if (cursor.moveToFirst()) {
            do {
                String resultado = cursor.getString(cursor.getColumnIndex("TELEFONO"));
                datos.add(resultado);
                resultado = cursor.getString(cursor.getColumnIndex("NOMBRE"));
                datos.add(resultado);
                resultado = cursor.getString(cursor.getColumnIndex("CORREO"));
                datos.add(resultado);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return datos;
    }

    public int saberID(String correo, String contraseña) {
        String sql = "SELECT ID FROM usuarios WHERE CORREO = '" + correo + "' AND CONTRASEÑA = '" + contraseña + "'";
        Cursor datos = this.getWritableDatabase().rawQuery(sql, null);
        int id = -1; // Valor predeterminado si no se encuentra ningún ID.

        if (datos.moveToFirst()) {
            id = datos.getInt(0);
        }

        datos.close();
        return id;
    }
    public int saberIDMedico(String nombre) {
        String sql = "SELECT IDMEDICO FROM "+ TABLA_MEDICOS + " WHERE NOMBREMEDICO = '" + nombre + "'" ;
        Cursor datos = this.getWritableDatabase().rawQuery(sql, null);
        int id = -1;

        if (datos.moveToFirst()) {
            id = datos.getInt(0);
        }

        datos.close();
        cerrarbd();
        return id;
    }

    public String saberNombre(int id) {
        String sql = "SELECT NOMBRE FROM usuarios WHERE ID = " + id;
        Cursor datos = this.getWritableDatabase().rawQuery(sql, null);
        String nombre = null;

        if (datos.moveToFirst()) {
            nombre = datos.getString(datos.getColumnIndex("NOMBRE"));
        }

        datos.close();
        cerrarbd();

        return nombre;
    }

    public List<String> medicosFiltroReserva(String ciudad, String especialidad) {
        List<String> nombres = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT NOMBREMEDICO FROM medicos WHERE CIUDAD = '" + ciudad + "' AND ESPECIALIDAD = '" + especialidad + "'", null);

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndex("NOMBREMEDICO"));
                nombres.add(nombre);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nombres;
    }

    public List<String> spinnerFiltroxCiudad(String ciudad) {
        List<String> especialidad = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT ESPECIALIDAD FROM medicos WHERE CIUDAD = '" + ciudad + "' ", null);

        if (cursor.moveToFirst()) {
            do {
                String especialidades = cursor.getString(cursor.getColumnIndex("ESPECIALIDAD"));
                especialidad.add(especialidades);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return especialidad;
    }

    public List<String> spinnerFiltroxEspecialidad(String especialidad) {
        List<String> ciudad = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT CIUDAD FROM medicos WHERE CIUDAD = '" + especialidad + "' ", null);

        if (cursor.moveToFirst()) {
            do {
                String ciudades = cursor.getString(cursor.getColumnIndex("ESPECIALIDAD"));
                ciudad.add(ciudades);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ciudad;
    }

    public String clinicaTrabajoMedico(String nombreMedico){
        String clinica="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor nombre = db.rawQuery("SELECT CLINICA_TRABAJA_MEDICO FROM medicos WHERE NOMBREMEDICO = '" + nombreMedico + "' ", null);

        if (nombre.moveToFirst()) {
            clinica = nombre.getString(0);
        }

        nombre.close();
        db.close();
        return clinica;
    }

    public List validarReservasAMostrar(){
        List<MostarReservas> citasMedicas = new ArrayList<MostarReservas>();
        String sql = "SELECT * from reservaciones";
        Cursor cursor = this.getWritableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("IDMEDICO"));
            String hora = cursor.getString(cursor.getColumnIndex("HORA"));
            String fecha = cursor.getString(cursor.getColumnIndex("FECHA"));
            String lugar = cursor.getString(cursor.getColumnIndex("LUGAR"));

            MostarReservas cita = new MostarReservas(id, hora, fecha, lugar);
            citasMedicas.add(cita);
        }
        cursor.close();
        cerrarbd();
        return citasMedicas;
    }

    public List<String> nombreMedicoyEspecialidad(int idMedico){
        List<String> respuesta = new ArrayList<>();
        String sql = "SELECT NOMBREMEDICO, ESPECIALIDAD from medicos where IDMEDICO = " +idMedico;
        Cursor cursor = this.getWritableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            respuesta.add(cursor.getString(cursor.getColumnIndex("NOMBREMEDICO")));
            respuesta.add(cursor.getString(cursor.getColumnIndex("ESPECIALIDAD")));
        }
        cursor.close();
        cerrarbd();
        return respuesta;
    }

    public int saberIDReserva(int idMedico, String hora, String fecha, String lugar){
        String sql = "SELECT ID FROM " + TABLA_RESERVACIONES + " WHERE IDMEDICO = '" + idMedico + "' AND HORA = '" + hora + "' AND FECHA = '" + fecha +"' AND LUGAR = '" + lugar + "'";
        Cursor datos = this.getWritableDatabase().rawQuery(sql, null);
        int id = -1; // Valor predeterminado si no se encuentra ningún ID.

        if (datos.moveToFirst()) {
            id = datos.getInt(0);
        }

        datos.close();
        return id;
    }

    public int borrarReservasRealizadas(int idBorrar){
        SQLiteDatabase BaseDatos = this.getWritableDatabase();
        int borrado = BaseDatos.delete(TABLA_RESERVACIONES, "ID= "+ idBorrar, null);
        if (borrado > 0) {
            return borrado;
        }else {
            return borrado;
        }
    }


}
