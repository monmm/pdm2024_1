package com.example.tarea2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tarea2.modelos.ElementoLista;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<ElementoLista> {

    private OnIconClickListener iconClickListener; // Interfaz para manejar clics en los íconos

    CustomListAdapter(Context context, List<ElementoLista> items) {
        super(context, 0, items);
    }

    // Interfaz para manejar clics en los íconos
    public interface OnIconClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    // Método para establecer el listener
    public void setOnIconClickListener(OnIconClickListener listener) {
        this.iconClickListener = listener;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            item = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item, parent, false);
        }

        TextView textView = item.findViewById(R.id.text1);
        ImageView imageView1 = item.findViewById(R.id.edit);
        ImageView imageView2 = item.findViewById(R.id.del);
        textView.setText(getItem(position).getMascota().toString());
        imageView1.setImageResource(getItem(position).getEdit());
        imageView2.setImageResource(getItem(position).getDelete());

        // Asignar clic al ícono de edición
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iconClickListener != null) {
                    iconClickListener.onEditClick(position);
                }
            }
        });

        // Asignar clic al ícono de eliminación
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iconClickListener != null) {
                    iconClickListener.onDeleteClick(position);
                }
            }
        });

        return item;
    }
}
