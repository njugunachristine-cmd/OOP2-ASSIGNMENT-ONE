
import java.util.ArrayList;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean available;

    public Car(String carId, String brand, String model, double pricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    public String getCarId() { return carId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return available; }

    public void rentCar() { available = false; }
    public void returnCar() { available = true; }

    @Override
    public String toString() {
        return carId + " - " + brand + " " + model +
                " | Price: $" + pricePerDay + " per day | " +
                (available ? "Available" : "Rented");
    }
}

class Customer {
    private String customerId;
    private String name;
    private String licenseNumber;
    private Car rentedCar;

    public Customer(String customerId, String name, String licenseNumber) {
        this.customerId = customerId;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getLicenseNumber() { return licenseNumber; }
    public Car getRentedCar() { return rentedCar; }

    public void rentCar(Car car) {
        this.rentedCar = car;
        car.rentCar();
    }

    public void returnCar() {
        if (rentedCar != null) {
            rentedCar.returnCar();
            rentedCar = null;
        }
    }

    @Override
    public String toString() {
        return customerId + " - " + name +
                " | License: " + licenseNumber +
                (rentedCar != null ? " | Rented: " + rentedCar.getModel() : " | No car rented");
    }
}

class RentalAgency {
    private ArrayList<Car> cars;
    private ArrayList<Customer> customers;

    public RentalAgency() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void addCar(Car car) { cars.add(car); }
    public void addCustomer(Customer customer) { customers.add(customer); }

    public void showAvailableCars() {
        System.out.println("\nAvailable Cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car);
            }
        }
    }

    public void showAllCustomers() {
        System.out.println("\nCustomers:");
        for (Customer cust : customers) {
            System.out.println(cust);
        }
    }

    public void rentCar(String customerId, String carId) {
        Customer customer = findCustomer(customerId);
        Car car = findCar(carId);

        if (customer != null && car != null && car.isAvailable()) {
            customer.rentCar(car);
            System.out.println(customer.getName() + " rented " + car.getModel());
        } else {
            System.out.println("Car not available or invalid input.");
        }
    }

    public void returnCar(String customerId) {
        Customer customer = findCustomer(customerId);
        if (customer != null && customer.getRentedCar() != null) {
            String carModel = customer.getRentedCar().getModel();
            customer.returnCar();
            System.out.println(customer.getName() + " returned " + carModel);
        } else {
            System.out.println("No car to return.");
        }
    }

    private Customer findCustomer(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) return c;
        }
        return null;
    }

    private Car findCar(String id) {
        for (Car c : cars) {
            if (c.getCarId().equals(id)) return c;
        }
        return null;
    }
}

public class CarRentalSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        // Add cars
        agency.addCar(new Car("C1", "Toyota", "Corolla", 50));
        agency.addCar(new Car("C2", "Honda", "Civic", 60));
        agency.addCar(new Car("C3", "Tesla", "Model 3", 120));

        // Add customers
        agency.addCustomer(new Customer("U1", "Alice", "L123"));
        agency.addCustomer(new Customer("U2", "Bob", "L456"));

        // Show initial state
        agency.showAvailableCars();
        agency.showAllCustomers();

        // Rent a car
        agency.rentCar("U1", "C2");
        agency.showAvailableCars();
        agency.showAllCustomers();

        // Return a car
        agency.returnCar("U1");
        agency.showAvailableCars();
        agency.showAllCustomers();
    }
}