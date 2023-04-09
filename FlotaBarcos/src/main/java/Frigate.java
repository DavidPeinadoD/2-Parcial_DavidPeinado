import java.awt.*;

public class Frigate extends Ship {
    public Frigate(Point startPoint, CardinalPoints direction) {
        super(3, startPoint, direction);
    }

    public Frigate(Point startPoint, Point endPoint) throws Exception {
        super(3, startPoint, endPoint);
    }
}
