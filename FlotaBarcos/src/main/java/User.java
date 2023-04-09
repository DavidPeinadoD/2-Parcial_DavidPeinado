import java.awt.*;
import java.util.ArrayList;

public class User {
    private ArrayList<Ship> ships;
    private boolean isAlive;

    public User(ArrayList<Ship> ships) throws Exception {
        if (ships == null || ships.size() == 0) {
            throw new Exception("El usuario debe tener al menos un barco.");
        }
        for (Ship ship : ships) {
            if (ship == null) {
                throw new Exception("Los barcos no pueden ser nulos.");
            }
        }
        this.ships = ships;
        this.isAlive = true;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        isAlive = false;
    }

    public boolean attack(Point shotPoint, User user) {
        boolean hit = false;
        for (Ship ship : user.getShips()) {
            if (ship.getShot(shotPoint)) {
                hit = true;
                if (ship.isSunk()) {
                    System.out.println("Hundiste un barco!");
                } else {
                    System.out.println("Le diste a un barco!");
                }
                break;
            }
        }
        return hit;
    }

    public void getShot(Point shotPoint) {
        for (Ship ship : ships) {
            if (ship.getShot(shotPoint)) {
                if (ship.isSunk()) {
                    System.out.println("Tu barco ha sido hundido.");
                } else {
                    System.out.println("Tu barco ha sido golpeado.");
                }
                break;
            }
        }
    }
}
