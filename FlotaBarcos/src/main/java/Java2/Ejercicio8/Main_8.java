package Java2.Ejercicio8;

import java.util.List;

public class Main_8 {

    public static void main(String[] args) {
        // Creamos los puertos
        Puerto buenosAires = new Puerto("Buenos Aires");
        Puerto santos = new Puerto("Santos");
        Puerto miami = new Puerto("Miami");
        Puerto rotterdam = new Puerto("Rotterdam");
        Puerto shanghai = new Puerto("Shanghai");
        Puerto rodas = new Puerto("Rodas");
        Puerto hongKong = new Puerto("Hong Kong");
        Puerto madero = new Puerto("Madero");

        // Creamos el grafo
        Grafo grafo = new Grafo();

        // Agregamos los puertos al grafo
        grafo.agregarPuerto(buenosAires);
        grafo.agregarPuerto(santos);
        grafo.agregarPuerto(miami);
        grafo.agregarPuerto(rotterdam);
        grafo.agregarPuerto(shanghai);
        grafo.agregarPuerto(rodas);
        grafo.agregarPuerto(hongKong);
        grafo.agregarPuerto(madero);

        // Agregamos las aristas al grafo
        grafo.agregarArista(buenosAires, santos, 2000);
        grafo.agregarArista(buenosAires, miami, 5000);
        grafo.agregarArista(buenosAires, rotterdam, 11000);
        grafo.agregarArista(buenosAires, shanghai, 17000);
        grafo.agregarArista(santos, madero, 1000);
        grafo.agregarArista(miami, rotterdam, 7000);
        grafo.agregarArista(rotterdam, shanghai, 10000);
        grafo.agregarArista(shanghai, hongKong, 1500);
        grafo.agregarArista(shanghai, rodas, 8000);
        grafo.agregarArista(hongKong, rodas, 20000);
        grafo.agregarArista(madero, rodas, 3000);

        // Barrido en profundidad
        System.out.println("Barrido en profundidad:");
        grafo.barridoEnProfundidad(buenosAires);

        // Camino más corto
        System.out.println("Camino más corto desde Puerto Madero a Puerto Rodas:");
        List<Puerto> camino = grafo.caminoMasCorto(madero, rodas);
        for (Puerto puerto : camino) {
            System.out.print(puerto + " ");
        }
        System.out.println();

        // Eliminar puerto con más aristas
        Puerto puertoMaxAristas = grafo.eliminarPuertoConMayorAristas();
        System.out.println("Se eliminó el puerto con más aristas: " + puertoMaxAristas);
    }

}
