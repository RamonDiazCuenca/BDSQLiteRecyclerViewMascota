package com.rdc.bdsqliterecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rdc.bdsqliterecyclerview.adaptadores.MyRecyclerViewAdapter;
import com.rdc.bdsqliterecyclerview.databinding.ActivityMainBinding;
import com.rdc.bdsqliterecyclerview.entidades.Mascota;
import com.rdc.bdsqliterecyclerview.utilidades.BBDD_Helper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnregistrar, btnconsultar;
    EditText etdueño, etnombre, etraza;

    ArrayList<Mascota> listaMascotas;
    RecyclerView recyclerView;

    MyRecyclerViewAdapter adapter;

    BBDD_Helper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanciaViews();

        btnregistrar.setOnClickListener(view -> {registrarMascota();});

        btnconsultar.setOnClickListener(view -> {consultarDatosMascotas();});

        conn = new BBDD_Helper(getApplicationContext(),"bd_mascotas.db",null,1);

    }

    private void instanciaViews() {

        btnregistrar = findViewById(R.id.btnregistrardatos);
        btnconsultar = findViewById(R.id.btnconsultadatos);
        etdueño = findViewById(R.id.etdueño);
        etnombre = findViewById(R.id.etnombre);
        etraza = findViewById(R.id.etraza);
    }

    private void registrarMascota() {

        String dueño = etdueño.getText().toString();
        String nombre = etnombre.getText().toString();
        String raza = etraza.getText().toString();


        if (!dueño.isEmpty() && !nombre.isEmpty() && !raza.isEmpty()) { //sí el campo dueño, nombre y raza no estan vacios, primero me buscas este dueño a ver si esta en la bd

            SQLiteDatabase db = conn.getReadableDatabase();
            String[] parametros = {nombre};// buscamos por este parámetro

            try {
                Cursor cursor = db.rawQuery("SELECT * FROM mascotas WHERE nombre=?", parametros);

                if(cursor.moveToFirst()){//sí mi consulta contiene valores

                    Toast.makeText(this.getApplicationContext(), "Este perro ya está registrado", Toast.LENGTH_SHORT).show();

                    cursor.close();
                    limpiar();

                }else{//sino me registras este perro

                    ContentValues values = new ContentValues();

                    values.put("dueño", dueño);
                    values.put("nombre", nombre);
                    values.put("raza", raza);

                    db.insert("mascotas", null, values);

                    db.close();

                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }catch(Exception e){Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();}

        }else{Toast.makeText(this, "Rellene los campos", Toast.LENGTH_SHORT).show();}
    }

    private void consultarDatosMascotas() {

        recyclerView = findViewById(R.id.recyclerMascotas);
        listaMascotas = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //Esto es para que la lista salga en dos columnas
        //recycler.setLayoutManager(new GridLayoutManager(this,2));

        SQLiteDatabase db = conn.getReadableDatabase();
        Mascota mascota = null;

        Cursor cursor = db.rawQuery("SELECT * FROM mascotas",null);

        while(cursor.moveToNext()){

            mascota = new Mascota();
            mascota.setId(cursor.getInt(0));
            mascota.setDueño(cursor.getString(1));
            mascota.setNombre(cursor.getString(2));
            mascota.setRaza(cursor.getString(3));

            listaMascotas.add(mascota);
        }

        adapter = new MyRecyclerViewAdapter(listaMascotas);
        recyclerView.setAdapter(adapter);
    }

    private void limpiar() {

      etdueño.setText("");
      etnombre.setText("");
      etraza.setText("");
    }
}