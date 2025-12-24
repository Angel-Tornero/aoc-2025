package day_8_playground;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
    private final List<Point3D> points;

    public Circuit() {
        this.points = new ArrayList<>();
    }

    public void addPoint(Point3D point) {
        this.points.add(point);
    }

    public int size() {
        return this.points.size();
    }
}
