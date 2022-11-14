package com.example.pmdm_simulacrocorregido_ejercicio2.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_simulacrocorregido_ejercicio2.MainActivity;
import com.example.pmdm_simulacrocorregido_ejercicio2.R;
import com.example.pmdm_simulacrocorregido_ejercicio2.modelos.ProductoModel;

import java.text.NumberFormat;
import java.util.List;


public class ProductosModelAdapters extends RecyclerView.Adapter<ProductosModelAdapters.ProductoVH> {

    //TODO: 15: LAS FUNCIONES CONFIRMDELETE Y EDIT PRODUCTO HAN DE LLAMAR A CALCULAVALORES. MIRAR TODO 16

    //10.05:
    private List<ProductoModel> objects;
    private int resource;
    private Context context;

    //10.10.01: Al ser un float y un precio a la vez, hay que hacer el numberformat
    //10.10.02: Hay que crear el constructor, pero se crea donde ya están todos
    private NumberFormat nf;

    //TODO: 16.01
    private MainActivity main;

    //10.06: Constructor de las variables de arriba
    public ProductosModelAdapters(List<ProductoModel> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
        nf = NumberFormat.getCurrencyInstance();
        //TODO: 16: Creamos para llamar a la función calculaValores del main (mirar 16.01 / 02 / 03)
        main = (MainActivity) context;
    }

    //10.07: En el error hay que implementar los métodos
    @NonNull
    @Override
    public ProductoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //10.09:
        View productoView = LayoutInflater.from(context).inflate(resource, null);
        productoView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ProductoVH(productoView);
    }

    //10.10
    //VER 10.10.01
    @Override
    public void onBindViewHolder(@NonNull ProductoVH holder, int position) {
        ProductoModel p = objects.get(position);
        holder.lblNombre.setText(p.getNombre());
        holder.lblCantidad.setText(String.valueOf(p.getCantidad()));
        holder.lblPrecio.setText(nf.format(p.getPrecio()));

        //TODO: 10.11: Crear botón eliminar
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete(p, holder.getAdapterPosition()).show();
            }
        });

        //TODO: 12: Botón editar
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //12.02:
                editProducto(p, holder.getAdapterPosition()).show();
            }
        });
    }

    //LA VISTA DE EDITAR VAMOS A HACERLA EN EL VIEW DEL PRODECT VIEW HOLDER, NO VAMOS A CREAR UN VIEW NUEVO
    //12.01: AlertDialog
    private AlertDialog editProducto(ProductoModel p, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(p.getNombre());
        View productoView = LayoutInflater.from(context).inflate(R.layout.activity_add_producto, null);

        //LO DEL VISIBILITY ES PARA QUE LO QUE ESTAMOS CREANDO NI SE VEA NI OCUPE LUGAR EN EL VIEW
        EditText txtNombre = productoView.findViewById(R.id.txtNombreProductoAdd);
        txtNombre.setVisibility(View.GONE);
        Button btn = productoView.findViewById(R.id.btnAgregarAdd);
        btn.setVisibility(View.GONE);
        EditText txtCantidad = productoView.findViewById(R.id.txtCantidadProductoAdd);
        txtCantidad.setText(String.valueOf(p.getCantidad()));
        EditText txtPrecio = productoView.findViewById(R.id.txtPrecioProductoAdd);
        txtPrecio.setText(String.valueOf(p.getPrecio()));

        builder.setView(productoView);

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("MODIFICAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!txtCantidad.getText().toString().isEmpty() && !txtPrecio.getText().toString().isEmpty()) {
                    p.setCantidad(Integer.parseInt(txtCantidad.getText().toString()));
                    p.setPrecio(Float.parseFloat(txtPrecio.getText().toString()));
                    notifyItemChanged(position);
                    //16.02
                    main.calculaValores();
                }
            }
        });


        return builder.create();
    }

    //10.11.01: Diálogo de confirmación de eliminar
    private AlertDialog confirmDelete(ProductoModel p, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Estás seguro???");
        builder.setCancelable(false);
        builder.setNegativeButton("Nope", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objects.remove(p);
                notifyItemRemoved(position);
                //16.03
                main.calculaValores();
            }
        });
        return builder.create();
    }

    //10.08:
    @Override
    public int getItemCount() {
        return objects.size();
    }

    //TODO: 10.01:
    public class ProductoVH extends RecyclerView.ViewHolder {
        //10.03:
        TextView lblNombre, lblCantidad, lblPrecio;
        ImageButton btnEliminar;

        //10.02:
        public ProductoVH(@NonNull View itemView) {
            super(itemView);
            //10.04:
            lblNombre = itemView.findViewById(R.id.lblNombreProductoCard);
            lblCantidad = itemView.findViewById(R.id.lblCantidadProductoCard);
            lblPrecio = itemView.findViewById(R.id.lblPrecioProductoCard);
            btnEliminar = itemView.findViewById(R.id.btnEliminarProductoCard);
        }
    }


}
