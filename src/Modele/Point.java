package Modele;

import java.util.ArrayList;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point p) {
        return (x == p.getX() && y == p.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean In(ArrayList<Point> points) {
        for (Point p : points) {
            if (p.equals(this)) {
                return true;
            }
        }
        return false;
    }


}
