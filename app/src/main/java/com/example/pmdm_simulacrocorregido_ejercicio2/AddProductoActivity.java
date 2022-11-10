package com.example.pmdm_simulacrocorregido_ejercicio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pmdm_simulacrocorregido_ejercicio2.databinding.ActivityAddProductoBinding;
import com.example.pmdm_simulacrocorregido_ejercicio2.modelos.ProductoModel;

public class AddProductoActivity extends AppCompatActivity {

    //TODO: 3: Crear binding
    private ActivityAddProductoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: 3.01: Inicializar binding
        binding = ActivityAddProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO: 4: Configurar botón e instanciar las Views con el binding
        binding.btnAgregarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //La S en las variables es para indicarnos que son Strings
                String nombre = binding.txtNombreProductoAdd.getText().toString();
                String cantidadS = binding.txtCantidadProductoAdd.getText().toString();
                String precioS = binding.txtPrecioProductoAdd.getText().toString();
                 //TODO: 4.01: Comprobamos que los campos estén rellenos
                if (!nombre.isEmpty() && !cantidadS.isEmpty() && !precioS.isEmpty()) {
                    ProductoModel p = new ProductoModel(nombre, Integer.parseInt(cantidadS), Float.parseFloat(precioS));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PROD", p);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}