package Java2.Ejercicio8;

import java.util.*;

public class Grafo {
    private Map<Puerto, List<Arista>> grafo;

    public Grafo() {
        grafo = new HashMap<>();
    }

    public void agregarPuerto(Puerto puerto) {
        grafo.putIfAbsent(puerto, new ArrayList<>());
    }

    public void agregarArista(Puerto origen, Puerto destino, int distancia) {
        grafo.get(origen).add(new Arista(origen, destino, distancia));
        grafo.get(destino).add(new Arista(destino, origen, distancia)); // Para hacer el grafo no dirigido
    }

    public void barridoEnProfundidad(Puerto inicio) {
        Set<Puerto> visitados = new HashSet<>();
        Stack<Puerto> pila = new Stack<>();
        pila.push(inicio);
        while (!pila.isEmpty()) {
            Puerto actual = pila.pop();
            if (!visitados.contains(actual)) {
                visitados.add(actual);
                System.out.println("Visitando puerto " + actual);
                for (Arista arista : grafo.get(actual)) {
                    pila.push(arista.getDestino());
                }
            }
        }
    }

    public List<Puerto> caminoMasCorto(Puerto origen, Puerto destino) {
        Map<Puerto, Puerto> predecesor = new HashMap<>();
        Map<Puerto, Integer> distancia = new HashMap<>();
        PriorityQueue<Puerto> cola = new PriorityQueue<>(Comparator.comparingInt(distancia::get));
        predecesor.put(origen, null);
        distancia.put(origen, 0);
        cola.offer(origen);
        while (!cola.isEmpty()) {
            Puerto actual = cola.poll();
            if (actual.equals(destino)) {
                break;
            }
            for (Arista arista : grafo.get(actual)) {
                Puerto vecino = arista.getDestino();
                int peso = arista.getDistancia();
                int distanciaActual = distancia.get(actual);
                int distanciaNueva = distanciaActual + peso;
                if (!distancia.containsKey(vecino) || distanciaNueva < distancia.get(vecino)) {
                    predecesor.put(vecino, actual);
                    distancia.put(vecino, distanciaNueva);
                    cola.offer(vecino);
                }
            }
        }
        List<Puerto> camino = new ArrayList<>();
        Puerto actual = destino;
        while (actual != null) {
            camino.add(0, actual);
            actual = predecesor.get(actual);
        }
        return camino;
    }

    public Puerto eliminarPuertoConMayorAristas() {
        int maxAristas = 0;
        Puerto puertoMaxAristas = null;
        for (Puerto puerto : grafo.keySet()) {
            int cantidadAristas = grafo.get(puerto).size();
            if (cantidadAristas > maxAristas) {
                maxAristas = cantidadAristas;
                puertoMaxAristas = puerto;
            }
        }
        if (puertoMaxAristas != null) {
            // Eliminar todas las aristas relacionadas con el puerto a eliminar
            for (Arista arista : grafo.get(puertoMaxAristas)) {
                Puerto destino = arista.getDestino();
                Puerto finalPuertoMaxAristas = puertoMaxAristas;
                grafo.get(destino).removeIf(a -> a.getDestino().equals(finalPuertoMaxAristas));
            }
            // Eliminar el puerto del grafo
            grafo.remove(puertoMaxAristas);
        }
        return puertoMaxAristas;
    }


}
