abstract class shape{
     double area;

    void displayArea(){
        System.out.println("Area ==" + area);
    }

}

class Circle extends shape{
    double radius;
    Circle(double radius){
        this.radius = radius;
    }

    double area(){
        return Math.PI * radius * radius;
    }
}

class Rectangle extends shape {
    double length;
    double width;

    Rectangle(double l, double w) {
        this.length = 1;
        this.width = w;
    }

    double area() {
        return length * width;
    }

}

