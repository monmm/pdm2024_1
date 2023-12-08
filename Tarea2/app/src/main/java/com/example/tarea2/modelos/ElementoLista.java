package com.example.tarea2.modelos;

import com.example.tarea2.R;

public class ElementoLista {

    private Mascota mascota;
    private String edit;
    private String delete;

    public ElementoLista() {}

    public ElementoLista(Mascota mascota) {
        this.mascota = mascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    @Override
    public String toString() {
        return mascota.toString();
    }

    public int getEdit() { return R.drawable.edit_icon; }
    public int getDelete() { return R.drawable.delete_icon; }
}
