import java.util.*;

class DrawShapes {
    public static void main(String[] args) {
        var shapes = new ArrayList<Shape>();
        shapes.add(new Circle(5, 20, "red", 10));
        shapes.add(new Rectangle(10, 15, "blue", 11, 6));

        for (Shape s : shapes) {
            s.draw();
        }
    }
}

class Shape {
    protected int x, y;
    protected String color;

    public Shape(int initX, int initY, String initColor) {
        x = initX;
        y = initY;
        color = initColor;
    }

    public void draw() {
        System.out.format("\nAt %d, %d color %s:", x, y, color);
    }

    public void moveTo(int newX, int newY) {
        x = newX;
        y = newY;
    }
}

class Circle extends Shape {
    protected int radius;

    public Circle(int initX, int initY, String initColor, int initRadius) {
        super(initX, initY, initColor);
        radius = initRadius;
    }

    @Override
    public void draw() {
        super.draw();
        System.out.format("  circle radius: %d", radius);
    }
}

class Rectangle extends Shape {
    protected int width, height;

    public Rectangle(int initX, int initY, String initColor, int initWidth, int initHeight) {
        super(initX, initY, initColor);
        width = initWidth;
        height = initHeight;
    }

    @Override
    public void draw() {
        super.draw();
        System.out.format("  width %d height %d", width, height);
    }
}
