interface Rentable {
    double calculateRent(int days);
}

class Car implements Rentable {

    static int totalVehiclesRented = 0;
    public static final double TAX_RATE = 0.05;


    public double calculateRent(int days) {
        totalVehiclesRented++;
        return 1500 * days;
    }
}

class Bike implements Rentable {
    public static final double TAX_RATE = 0.05;


    public double calculateRent(int days) {
        Car.totalVehiclesRented++;
        return 500 * days;
    }
}