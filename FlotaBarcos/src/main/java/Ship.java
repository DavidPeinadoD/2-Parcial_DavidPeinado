import java.awt.*;

public class Ship {
    private int size;
    protected int hits;
    private Point startPoint;
    private Point endPoint;

    public Ship(int size, Point startPoint, CardinalPoints direction) {
        this.size = size;
        this.startPoint = startPoint;
        this.endPoint = calculateEndPoint(startPoint, direction, size);
        this.hits = 0;
    }

    public Ship(int size, Point startPoint, Point endPoint) throws Exception {
        this.size = size;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        if (!checkIfPointsFormLine(startPoint, endPoint) || calculateLength(startPoint, endPoint) != size - 1) {
            throw new Exception("Invalid ship coordinates");
        }
        this.hits = 0;
    }

    public boolean isSunk() {
        return hits == size;
    }

    public boolean getShot(Point shotPoint) {
        if (checkIfPointsFormLine(startPoint, endPoint)) {
            if (calculateLength(startPoint, endPoint) == 0) {
                if (startPoint.equals(shotPoint)) {
                    hits++;
                    return true;
                }
            } else if (checkIfPointsFormLine(startPoint, shotPoint) && checkIfPointsFormLine(shotPoint, endPoint)) {
                hits++;
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public int getHits() {
        return hits;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    private Point calculateEndPoint(Point startPoint, CardinalPoints direction, int size) {
        int x = (int) Math.round(startPoint.getX());
        int y = (int) Math.round(startPoint.getY());
        switch (direction) {
            case NORTH:
                y -= size - 1;
                break;
            case EAST:
                x += size - 1;
                break;
            case SOUTH:
                y += size - 1;
                break;
            case WEST:
                x -= size - 1;
                break;
        }
        return new Point(x, y);
    }

    protected boolean checkIfPointsFormLine(Point startPoint, Point endPoint) {
        return startPoint.getX() == endPoint.getX() || startPoint.getY() == endPoint.getY();
    }

    protected int calculateLength(Point startPoint, Point endPoint) {
        if (startPoint.getX() == endPoint.getX()) {
            return (int) Math.round(Math.abs(endPoint.getY() - startPoint.getY())) + 1;
        } else {
            return (int) Math.round(Math.abs(endPoint.getX() - startPoint.getX())) + 1;
        }
    }

}