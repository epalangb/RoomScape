package src.Presentacion.Vistas;

import src.Negocio.TVideojuego;

import javax.swing.*;
import java.util.ArrayList;

public class ModeloListaVideojuego extends AbstractListModel<String> {

    private final ArrayList<TVideojuego> model;

    public ModeloListaVideojuego(ArrayList<TVideojuego> tVideojuegos) {
        this.model = tVideojuegos;
    }

    @Override
    public int getSize() {
        return model.size();
    }

    @Override
    public String getElementAt(int index) {
        return model.get(index).getNombre();
    }
}
