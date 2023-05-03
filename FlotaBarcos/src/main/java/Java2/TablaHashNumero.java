package Java2;

import java.util.LinkedList;

public class TablaHashNumero {
    private static final int TAMAÑO = 15;
    private LinkedList<Barco>[] tabla;

    public TablaHashNumero() {
        this.tabla = new LinkedList[TAMAÑO];
        for (int i = 0; i < TAMAÑO; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(String numero) {
        return Integer.parseInt(numero) % TAMAÑO;
    }

    public void agregar(Barco barco) {
        int indice = hash(barco.getNumero());
        LinkedList<Barco> lista = tabla[indice];
        lista.add(barco);
    }

    public Barco buscar(String numero) {
        int indice = hash(numero);
        LinkedList<Barco> lista = tabla[indice];
        if (lista == null || lista.isEmpty()) {
            System.out.println("No se encontró ningún barco con el número " + numero);
            return null;
        }
        for (Barco barco : lista) {
            if (barco.getNumero().equals(numero)) {
                return barco;
            }
        }
        System.out.println("No se encontró ningún barco con el número " + numero);
        return null;
    }
}

