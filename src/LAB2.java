import java.util.ArrayList;
import java.util.List;

// 1. Iterator Arayüzü (Custom)
interface MyIterator {
    boolean hasNext();
    Car next();
}

// 2. Car Sınıfı
class Car {
    private String model;
    private String engineType;
    private int price;

    public Car(String model, String engineType, int price) {
        this.model = model;
        this.engineType = engineType;
        this.price = price;
    }

    // İstenen: Sadece price için getter/setter
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    // Filtreleme için gerekli olan engineType getter
    public String getEngineType() { return engineType; }

    @Override
    public String toString() {
        // Format: model [tab] engineType [tab] price
        return model + "\t" + engineType + "\t" + price;
    }
}

// 3. Somut Iterator Uygulamaları (Filtering Logic)
class AllCarsIterator implements MyIterator {
    private List<Car> cars;
    private int position = 0;
    public AllCarsIterator(List<Car> cars) { this.cars = cars; }
    public boolean hasNext() { return position < cars.size(); }
    public Car next() { return cars.get(position++); }
}

class ElectricCarIterator implements MyIterator {
    private List<Car> cars;
    private int position = 0;
    public ElectricCarIterator(List<Car> cars) { this.cars = cars; }

    public boolean hasNext() {
        while (position < cars.size()) {
            if (cars.get(position).getEngineType().equalsIgnoreCase("Electric")) return true;
            position++;
        }
        return false;
    }
    public Car next() { return cars.get(position++); }
}

class AffordableCarIterator implements MyIterator {
    private List<Car> cars;
    private int position = 0;
    public AffordableCarIterator(List<Car> cars) { this.cars = cars; }

    public boolean hasNext() {
        while (position < cars.size()) {
            if (cars.get(position).getPrice() <= 30000) return true;
            position++;
        }
        return false;
    }
    public Car next() { return cars.get(position++); }
}

// 4. Koleksiyon Sınıfı
class CarCollection {
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    // Iterator oluşturma metodu
    public MyIterator createIterator(String type) {
        if (type.equalsIgnoreCase("ELECTRIC")) {
            return new ElectricCarIterator(cars);
        } else if (type.equalsIgnoreCase("AFFORDABLE")) {
            return new AffordableCarIterator(cars);
        } else {
            return new AllCarsIterator(cars);
        }
    }
}

// 5. Ana Test Sınıfı
public class LAB2 { //CarSystem
    public static void main(String[] args) {
        CarCollection showroom = new CarCollection();

        // İstenen Verilerin Eklenmesi
        showroom.addCar(new Car("Tesla Model 3", "Electric", 42000));
        showroom.addCar(new Car("Fiat 500", "Gasoline", 18000));
        showroom.addCar(new Car("Toyota Corolla", "Hybrid", 28000));
        showroom.addCar(new Car("Porsche Taycan", "Electric", 90000));
        showroom.addCar(new Car("Hyundai i20", "Gasoline", 15000));
        showroom.addCar(new Car("Honda Civic", "Hybrid", 30000));

        // Test 1: Tüm Arabalar
        System.out.println("--- TÜM ARABALAR ---");
        displayCars(showroom.createIterator("ALL"));

        // Test 2: Elektrikli Arabalar (Beklenen: 2 araç)
        System.out.println("\n--- ELEKTRİKLİ ARAÇLAR ---");
        displayCars(showroom.createIterator("ELECTRIC"));

        // Test 3: Ekonomik Arabalar (<= 30.000$) (Beklenen: 4 araç)
        System.out.println("\n--- EKONOMİK ARAÇLAR (<= 30,000$) ---");
        displayCars(showroom.createIterator("AFFORDABLE"));
    }

    private static void displayCars(MyIterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}