public class ShapeTest {
    public static void main(String[] args) {
        shape[] shapes = new shape[2];
        shapes[0] = new Circle(5.0);
        shapes[1] = new Rectangle(4.0, 6.0);

        for (shape s : shapes) {
            s.displayArea();   // must print correct area for each shape
        }
    }
}
