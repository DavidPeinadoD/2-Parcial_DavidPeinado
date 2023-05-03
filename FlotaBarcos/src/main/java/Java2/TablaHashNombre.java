package Java2;

import java.util.LinkedList;

public class TablaHashNombre {
    private static final int TAMAÑO = 15;
    private LinkedList<Barco>[] tabla;

    public TablaHashNombre() {
        this.tabla = new LinkedList[TAMAÑO];
        for (int i = 0; i < TAMAÑO; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(String nombre) {
        int hash = 0;
        for (int i = 0; i < nombre.length(); i++) {
            hash = hash * 31 + nombre.charAt(i);
        }
        return Math.abs(hash) % TAMAÑO;
    }

    public void agregar(Barco barco) {
        int indice = hash(barco.getNombre());
        LinkedList<Barco> lista = tabla[indice];
        lista.add(barco);
    }

    public Barco buscar(String nombre) {
        int indice = hash(nombre);
        LinkedList<Barco> lista = tabla[indice];
        if (lista == null || lista.isEmpty()) {
            System.out.println("No se encontró ningún barco con el nombre " + nombre);
            return null;
        }
        for (Barco barco : lista) {
            if (barco.getNombre().equals(nombre)) {
                return barco;
            }
        }
        System.out.println("No se encontró ningún barco con el nombre " + nombre);
        return null;
    }
}
