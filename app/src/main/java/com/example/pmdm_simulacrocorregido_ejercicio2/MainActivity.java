package com.example.pmdm_simulacrocorregido_ejercicio2;

import android.content.Intent;
import android.os.Bundle;

import com.example.pmdm_simulacrocorregido_ejercicio2.adapters.ProductosModelAdapters;
import com.example.pmdm_simulacrocorregido_ejercicio2.modelos.ProductoModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.example.pmdm_simulacrocorregido_ejercicio2.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //TODO: 1: Crear el modelo (ProductoModel) con todos los atributos
    //TODO: 2: Creamos AddProductoActivity y metemos las Views
    //TODO: 8: Cambiamos el id del constraint (creo que se llama así) del activitymain y añadimos el contenedor al contentmain
    //TODO: 9: Añadimos el nuevo LayoutResourceFile, tipo CardView, nombre product_view_holder y metemos las Views
    //TODO: 9.01: Al ImageButton se le cambia el gravity y otras 20 cosas para que quede bonito
    //TODO: 10: Creamos el paquete adapters y la clase java
    //TODO: 12: Hacemos las Views del ContentMain



    //TODO: 11: Variables para el adapter. Se inicializa DESPUÉS de la lista
    private ProductosModelAdapters adapter;
    private RecyclerView.LayoutManager layoutManager;

    //TODO: 5: Creamos la lista de lo que tiene el bundle de AddProductoActivity
    private ArrayList<ProductoModel> productoModelsList;
    private ActivityResultLauncher<Intent> launcherAddProducto;

    //TODO: NUMBERFORMAT
    NumberFormat nf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        //TODO: 5.01: Inicializamos el ArrayList
        productoModelsList = new ArrayList<>();

        //TODO: INSTANCIAR NUMBERFORMAT
        nf = NumberFormat.getCurrencyInstance();

        calculaValores();

        //TODO: 11.01: Inicializamos el adapter
        adapter = new ProductosModelAdapters(productoModelsList, R.layout.product_view_holder, this);
        //El número es para el número de columnas
        layoutManager = new GridLayoutManager(this, 1);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);
        binding.contentMain.contenedor.setAdapter(adapter);

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
                        adapter.notifyItemInserted(productoModelsList.size() - 1);
                        //TODO: 14: Llamar a la función
                        calculaValores();
                    }
                }
            }
        });
    }

    //TODO: 13:
    //HAY QUE CREAR EL NUMBERFORMAT, ESTÁ ARRIBA. TAMBIÉN HAY QUE INSTANCIARLO
    public void calculaValores() {
        int cantidad = 0;
        float precio = 0;
        for (ProductoModel p : productoModelsList) {
            cantidad += p.getCantidad();
            precio += p.getCantidad() * p.getPrecio();
        }
        binding.contentMain.lblCantidadTotalMain.setText(String.valueOf(cantidad));
        binding.contentMain.lblPrecioTotalMain.setText(nf.format(precio));
    }

    //TODO 18: Hacer que gire
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("LISTA", productoModelsList);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<ProductoModel> kk = (ArrayList<ProductoModel>) savedInstanceState.get("LISTA");
        productoModelsList.addAll(kk);
        adapter.notifyItemRangeInserted(0, productoModelsList.size());
        calculaValores();
    }
}