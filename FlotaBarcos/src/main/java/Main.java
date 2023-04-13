import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Obtener la configuración de los barcos del primer jugador
        System.out.println("Configuración de barcos del Primer Jugador:");
        ArrayList<Ship> player1Ships = configureShips(scanner);

        // Obtener la configuración de los barcos del segundo jugador
        System.out.println("\nConfiguración de barcos del Segundo Jugador:");
        ArrayList<Ship> player2Ships = configureShips(scanner);

        // Crear los objetos de los jugadores
        User player1 = null;
        User player2 = null;
        try {
            player1 = new User(player1Ships);
            player2 = new User(player2Ships);
        } catch (Exception e) {
            System.out.println("Error al crear jugadores: " + e.getMessage());
            System.exit(1);
        }

        // Iniciar el juego
        System.out.println("\nComienza el juego de Hundir la Flota!");
        boolean gameOver = false;
        while (!gameOver) {
            // Turno del Primer Jugador
            System.out.println("\nTurno del Primer Jugador:");
            Point shotPoint1 = getRandomShotPoint();
            boolean hit1 = player1.attack(shotPoint1, player2);
            if (player2.isAlive()) {
                System.out.println("Barcos restantes del Segundo Jugador: " + player2.getShips().size());
            } else {
                System.out.println("¡El Primer Jugador ha ganado!");
                gameOver = true;
                break;
            }

            // Turno del Segundo Jugador
            System.out.println("\nTurno del Segundo Jugador:");
            Point shotPoint2 = getRandomShotPoint();
            boolean hit2 = player2.attack(shotPoint2, player1);
            if (player1.isAlive()) {
                System.out.println("Barcos restantes del Primer Jugador: " + player1.getShips().size());
            } else {
                System.out.println("¡El Segundo Jugador ha ganado!");
                gameOver = true;
                break;
            }

            // Si ambos jugadores no tienen barcos, es empate
            if (!player1.isAlive() && !player2.isAlive()) {
                System.out.println("¡Es un empate!");
                gameOver = true;
            }
        }

        scanner.close();
    }


    // Declaración de arreglos para almacenar los barcos
    Ship[] ships = new Ship[3];

    public static ArrayList<Ship> configureShips(Scanner input) {
        ArrayList<Ship> ships = new ArrayList<>();

        System.out.print("Ingrese la posición de inicio del Battleship (tamaño 5): ");
        Point battleshipStart = getValidPoint(input);
        System.out.print("Ingrese la dirección del Battleship (NORTH, EAST, SOUTH, WEST): ");
        CardinalPoints battleshipDirection = CardinalPoints.valueOf(input.next().toUpperCase());
        ships.add(new Ship(5, battleshipStart, battleshipDirection));

        System.out.print("Ingrese la posición de inicio del Frigate (tamaño 3): ");
        Point frigateStart = getValidPoint(input);
        System.out.print("Ingrese la dirección del Frigate (NORTH, EAST, SOUTH, WEST): ");
        CardinalPoints frigateDirection = CardinalPoints.valueOf(input.next().toUpperCase());
        ships.add(new Ship(3, frigateStart, frigateDirection));

        System.out.print("Ingrese la posición de inicio del Canoe (tamaño 1): ");
        Point canoeStart = getValidPoint(input);
        ships.add(new Ship(1, canoeStart, (CardinalPoints) null)); // Utilizar el constructor sin dirección para el Canoe

        return ships;
    }

    // Método para obtener un punto de coordenada válida dentro del rango del tablero de juego
    public static Point getValidPoint(Scanner input) {
        int x, y;
        do {
            System.out.print("Ingrese la coordenada X (letra A-J): ");
            char letter = input.next().toUpperCase().charAt(0);
            x = letter - 'A';
        } while (x < 0 || x > 9); // Validar que la coordenada X esté dentro del rango A-J

        do {
            System.out.print("Ingrese la coordenada Y (número 1-10): ");
            y = input.nextInt() - 1;
        } while (y < 0 || y > 9); // Validar que la coordenada Y esté dentro del rango 1-10

        return new Point(x, y);
    }
    // Método para obtener la configuración de los barcos de un jugador por consola (En este caso el tablero es infinito)
  /*
    public static ArrayList<Ship> configureShips(Scanner input) {
        ArrayList<Ship> ships = new ArrayList<>();

        System.out.print("Ingrese la posición de inicio del Battleship (tamaño 5): ");
        Point battleshipStart = new Point(input.nextInt(), input.nextInt());
        System.out.print("Ingrese la dirección del Battleship (NORTH, EAST, SOUTH, WEST): ");
        CardinalPoints battleshipDirection = CardinalPoints.valueOf(input.next().toUpperCase());
        ships.add(new Ship(5, battleshipStart, battleshipDirection));

        System.out.print("Ingrese la posición de inicio del Frigate (tamaño 3): ");
        Point frigateStart = new Point(input.nextInt(), input.nextInt());
        System.out.print("Ingrese la dirección del Frigate (N/S/E/W): ");
        CardinalPoints frigateDirection = CardinalPoints.valueOf(input.next().toUpperCase());
        ships.add(new Ship(3, frigateStart, frigateDirection));

        System.out.print("Ingrese la posición de inicio del Canoe (tamaño 1): ");
        Point canoeStart = new Point(input.nextInt(), input.nextInt());
        ships.add(new Ship(1, canoeStart, (CardinalPoints) null)); // Utilizar el constructor sin dirección para el Canoe


        return ships;
    }
*/

    // Método para convertir la entrada del usuario en un objeto Point
    public static Point convertInputToPoint(String input) {
        char[] chars = input.toCharArray();
        char letter = Character.toUpperCase(chars[0]);
        int number = Character.getNumericValue(chars[1]);
        int x = letter - 'A';
        int y = number - 1;
        return new Point(x, y);
    }

    // Método para obtener un punto de disparo aleatorio
    public static Point getRandomShotPoint() {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        return new Point(x, y);
    }
}
