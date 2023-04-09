import java.awt.*;

public class Battleship extends Ship {
    private int isolatedContainers;

    public Battleship(Point startPoint, CardinalPoints direction) {
        super(5, startPoint, direction);
        this.isolatedContainers = 2;
    }

    public Battleship(Point startPoint, Point endPoint) throws Exception {
        super(5, startPoint, endPoint);
        this.isolatedContainers = 2;
    }

    @Override
    public boolean getShot(Point shotPoint) {
        if (checkIfPointsFormLine(getStartPoint(), getEndPoint())) {
            if (calculateLength(getStartPoint(), getEndPoint()) == 0) {
                if (getStartPoint().equals(shotPoint)) {
                    hits++;
                    isolatedContainers--;
                    return true;
                }
            } else if (checkIfPointsFormLine(getStartPoint(), shotPoint) && checkIfPointsFormLine(shotPoint, getEndPoint())) {
                hits++;
                if (isolatedContainers == 0) {
                    return true;
                } else {
                    isolatedContainers--;
                }
            }
        }
        return false;
    }
}