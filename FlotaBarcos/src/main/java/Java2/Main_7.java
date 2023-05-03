package Java2;


import java.util.NoSuchElementException;

public class Main_7 {
    public static void main(String[] args) {

        // Crear tabla hash por tipo de barco
        TablaHashTipo tablaTipo = new TablaHashTipo();

        // Crear tabla hash por número de barco
        TablaHashNumero tablaNumero = new TablaHashNumero();

        // Crear tabla hash por nombre de barco
        TablaHashNombre tablaNombre = new TablaHashNombre();

        // Crear barcos y agregarlos a las tablas hash
        Barco barco1 = new Barco("123", "Barco1", "Tipo1", 1);
        tablaTipo.agregar(barco1);
        tablaNumero.agregar(barco1);
        tablaNombre.agregar(barco1);

        Barco barco2 = new Barco("456", "Barco2", "Tipo1", 2);
        tablaTipo.agregar(barco2);
        tablaNumero.agregar(barco2);
        tablaNombre.agregar(barco2);

        Barco barco3 = new Barco("789", "Barco3", "Tipo2", 3);
        tablaTipo.agregar(barco3);
        tablaNumero.agregar(barco3);
        tablaNombre.agregar(barco3);

        Barco barco4 = new Barco("101112", "Barco4", "Tipo2", 4);
        tablaTipo.agregar(barco4);
        tablaNumero.agregar(barco4);
        tablaNombre.agregar(barco4);

        Barco barco5 = new Barco("131415", "Barco5", "Tipo3", 5);
        tablaTipo.agregar(barco5);
        tablaNumero.agregar(barco5);
        tablaNombre.agregar(barco5);

        // Buscar barcos por tipo
        try {
            System.out.println("Buscar barcos por tipo:");
            for (Barco barco : tablaTipo.buscar("Tipo1")) {
                System.out.println(barco.getNombre() + " (" + barco.getNumero() + ")");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        try {
            for (Barco barco : tablaTipo.buscar("Tipo5")) {
                System.out.println(barco.getNombre() + " (" + barco.getNumero() + ")");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        // Buscar barcos por número
        System.out.println("\nBuscar barcos por número:");
        Barco barcoEncontrado = tablaNumero.buscar("123");
        if (barcoEncontrado != null) {
            System.out.println(barcoEncontrado.getNombre() + " (" + barcoEncontrado.getTipo() + ")");
        } else {
            System.out.println("Barco no encontrado");
        }
        barcoEncontrado = tablaNumero.buscar("12309765");
        if (barcoEncontrado != null) {
            System.out.println(barcoEncontrado.getNombre() + " (" + barcoEncontrado.getTipo() + ")");
        }

        // Buscar barcos por nombre
        System.out.println("\nBuscar barcos por nombre:");
        barcoEncontrado = tablaNombre.buscar("Barco5");
        if (barcoEncontrado != null) {
            System.out.println(barcoEncontrado.getNumero() + " (" + barcoEncontrado.getTipo() + ")");
        }

        barcoEncontrado = tablaNombre.buscar("Barco6");
        if (barcoEncontrado != null) {
            System.out.println(barcoEncontrado.getNumero() + " (" + barcoEncontrado.getTipo() + ")");
        }
    }
}

