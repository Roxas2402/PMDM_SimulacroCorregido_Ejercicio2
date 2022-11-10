package com.example.pmdm_simulacrocorregido_ejercicio2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_simulacrocorregido_ejercicio2.R;
import com.example.pmdm_simulacrocorregido_ejercicio2.modelos.ProductoModel;

import java.util.List;


public class ProductosModelAdapters extends RecyclerView.Adapter<ProductosModelAdapters.ProductoVH> {

    //10.05:
    private List<ProductoModel> objects;
    private int resource;
    private Context context;

    //10.06: Constructor de las variables de arriba
    public ProductosModelAdapters(List<ProductoModel> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
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

    @Override
    public void onBindViewHolder(@NonNull ProductoVH holder, int position) {

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
