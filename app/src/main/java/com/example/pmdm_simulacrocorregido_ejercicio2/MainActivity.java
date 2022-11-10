package com.example.pmdm_simulacrocorregido_ejercicio2;

import android.content.Intent;
import android.os.Bundle;

import com.example.pmdm_simulacrocorregido_ejercicio2.modelos.ProductoModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.example.pmdm_simulacrocorregido_ejercicio2.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //TODO: 1: Crear el modelo (ProductoModel) con todos los atributos
    //TODO: 2: Creamos AddProductoActivity y metemos las Views
    //TODO: 8: Cambiamos el id del constraint (creo que se llama así) del activitymain y añadimos el contenedor al contentmain
    //TODO: 9: Añadimos el nuevo LayoutResourceFile, tipo CardView, nombre product_view_holder y metemos las Views
    //TODO: 9.01: Al ImageButton se le cambia el gravity y otras 20 cosas para que quede bonito
    //TODO: 10: Creamos el paquete adapters y la clase java

    //TODO: 5: Creamos la lista de lo que tiene el bundle de AddProductoActivity
    private ArrayList<ProductoModel> productoModelsList;
    private ActivityResultLauncher<Intent> launcherAddProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        //TODO: 5.01: Inicializamos el ArrayList
        productoModelsList = new ArrayList<>();
        //TODO: 6: Creamos la función para inicializar los launchers
        inicializaLaunchers();

        //TODO: 7: Configuramos el botón para que lance el Launcher
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherAddProducto.launch(new Intent(MainActivity.this, AddProductoActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        //TODO: 6.01
        launcherAddProducto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null && result.getData().getExtras() != null) {
                        ProductoModel p = (ProductoModel) result.getData().getExtras().getSerializable("PROD");
                        productoModelsList.add(p);
                        Toast.makeText(MainActivity.this, p.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}