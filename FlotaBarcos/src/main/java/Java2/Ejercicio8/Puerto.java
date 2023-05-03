package Java2.Ejercicio8;

public class Puerto {
    private String nombre;

    public Puerto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

