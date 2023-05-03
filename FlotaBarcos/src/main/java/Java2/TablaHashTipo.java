package Java2;


import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TablaHashTipo {
    private static final int TAMAÑO = 15;
    private LinkedList<Barco>[] tabla;

    public TablaHashTipo() {
        this.tabla = new LinkedList[TAMAÑO];
        for (int i = 0; i < TAMAÑO; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(String tipo) {
        int hash = 0;
        for (int i = 0; i < tipo.length(); i++) {
            hash = hash * 31 + tipo.charAt(i);
        }
        return Math.abs(hash) % TAMAÑO;
    }

    public void agregar(Barco barco) {
        String[] tipos = barco.getTipo().split(","); // Si el barco tiene más de un tipo, se separan por coma
        for (String tipo : tipos) {
            int indice = hash(tipo.trim()); // Se quita cualquier espacio en blanco al inicio o final del tipo
            LinkedList<Barco> lista = tabla[indice];
            lista.add(barco);
        }
    }

    public LinkedList<Barco> buscar(String tipo) {
        int indice = hash(tipo);
        LinkedList<Barco> lista = tabla[indice];
        if (lista == null) {
            throw new NoSuchElementException("No se encontraron barcos del tipo " + tipo);
        }
        LinkedList<Barco> resultado = new LinkedList<>();
        for (Barco barco : lista) {
            if (barco.getTipo().equals(tipo)) {
                resultado.add(barco);
            }
        }
        if (resultado.isEmpty()) {
            throw new NoSuchElementException("No se encontraron barcos del tipo " + tipo);
        }
        return resultado;
    }
}
