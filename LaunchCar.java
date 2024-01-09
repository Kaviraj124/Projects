import java.util.*;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean availability;

    Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.availability = true;
    }

    String getCarId() {
        return carId;
    }

    String getBrand() {
        return brand;
    }

    String getModel() {
        return model;
    }

    double getTotalRent(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    boolean getAvailability() {
        return availability;
    }

    void carGone() {
        availability = false;
    }

    void carReturned() {
        availability = true;
    }
}

class Customer {
    private String customerId;
    private String name;

    Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    String getCustomerId() {
        return customerId;
    }

    String getName() {
        return name;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    Car getCar() {
        return car;
    }

    Customer getCustomer() {
        return customer;
    }

    int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> carList;
    private List<Customer> customerList;
    private List<Rental> rentalList;

    CarRentalSystem() {
        carList = new ArrayList<>();
        customerList = new ArrayList<>();
        rentalList = new ArrayList<>();
    }

    void addCar(Car car) {
        carList.add(car);
    }

    void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    void rentCar(Car car, Customer customer, int days) {
        if (car.getAvailability()) {
            car.carGone();
            rentalList.add(new Rental(car, customer, days));
        } else {
            System.out.println("Sorry, Car is not available for rent");
        }
    }

    void returnCar(Car car) {
        Rental rentalRemover = null;
        for (Rental rental : rentalList) {
            if (rental.getCar() == car) {
                rentalRemover = rental;
                break;
            }
        }
        if (rentalRemover != null) {
            rentalList.remove(rentalRemover);
            System.out.println("Car returned Successfully");
        } else {
            System.out.println("Car was not rented");
        }
    }

    void menu() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("***** Welcome To Car Rental System *****");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Exit system");
            System.out.print("Enter Your Choice: ");

            int choice = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (choice == 1) {
                System.out.println("== Rent a car ==");
                System.out.print("Enter your name: ");
                String name = scan.nextLine();

                System.out.println("\nAvailable Cars");
                for (Car car : carList) {
                    if (car.getAvailability()) {
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " - " + car.getModel() +
                                " - $" + car.getTotalRent(1));
                    }
                }

                System.out.print("\nEnter the car ID: ");
                String carId = scan.nextLine();

                System.out.print("\nEnter the days for rental: ");
                int rentDays = scan.nextInt();
                scan.nextLine();

                Customer newCustomer = new Customer("CUS" + (customerList.size() + 1), name);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : carList) {
                    if (car.getCarId().equals(carId) && car.getAvailability()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalRent = selectedCar.getTotalRent(rentDays);
                    System.out.println("\n== Rental Information ==");
                    System.out.println("Customer Id: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentDays);
                    System.out.printf("Total price: $%.2f%n", totalRent);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scan.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentDays);
                        System.out.println("Car Rented Successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid Car Selection or car not available for rent");
                }
            } else if (choice == 2) {
                System.out.println("== Return a car ==");
                System.out.print("Enter a car ID: ");
                String carId = scan.nextLine();

                Car returnCar = null;
                for (Car car : carList) {
                    if (car.getCarId().equals(carId) && !car.getAvailability()) {
                        returnCar = car;
                        break;
                    }
                }

                if (returnCar != null) {
                    Customer customer = null;
                    for (Rental rental : rentalList) {
                        if (rental.getCar() == returnCar) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(returnCar);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or information is missing");
                    }
                } else {
                    System.out.println("Invalid Car ID or car was not rented");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option");
            }
        }
        System.out.println("\nThank you for using Car Rental System!");
    }
}

class LaunchCar {
    public static void main(String[] args) {
        CarRentalSystem crs = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Fortuner", 175.0);
        Car car2 = new Car("C002", "Honda", "Amaze", 85.0);
        Car car3 = new Car("C003", "Mahindra", "Scorpio", 150.0);

        crs.addCar(car1);
        crs.addCar(car2);
        crs.addCar(car3);

        crs.menu();
    }
}
