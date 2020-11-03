public class Balloon {
    private double x, y;

    public Balloon(double x, double y) {
        this.x = x;
        this.y = y;
    }    

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

	public void move() {
        if (x < 400) {
            x += 5;
        } else {
            x = 10;
        }
	}


}
