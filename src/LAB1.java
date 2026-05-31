import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. Interface Segregation: Yetenekleri ayırdık
interface Powerable {
    void turnOn();
    void turnOff();
}

interface Recordable {
    void recordVideo();
}

// 2. Base Class: Ortak özellikleri topladık
abstract class SmartDevice implements Powerable {
    private String name;
    private int energyRating;

    public SmartDevice(String name, int energyRating) {
        this.name = name;
        this.energyRating = energyRating;
    }

    public String getName() { return name; }
    public int getEnergyRating() { return energyRating; }

    // OCP: Her cihaz kendi maliyetini hesaplar
    public double calculateCost(double costPerWatt) {
        return energyRating * costPerWatt;
    }
}

// 3. Somut Cihazlar
class SmartLight extends SmartDevice {
    public SmartLight() { super("Smart Light", 10); }

    @Override
    public void turnOn() { System.out.println(getName() + " ON: Brightness 100%."); }
    @Override
    public void turnOff() { System.out.println(getName() + " OFF."); }
}

class SecurityCamera extends SmartDevice implements Recordable {
    public SecurityCamera() { super("Security Camera", 50); }

    @Override
    public void turnOn() { System.out.println(getName() + " is active."); }
    @Override
    public void turnOff() { System.out.println(getName() + " is off."); }

    @Override
    public void recordVideo() {
        System.out.println(getName() + " recording... SAVED.");
    }
}

// HINT: Yeni cihaz eklemek artık çok kolay
class SmartThermostat extends SmartDevice {
    public SmartThermostat() { super("Smart Thermostat", 25); }

    @Override
    public void turnOn() { System.out.println(getName() + " regulating temperature."); }
    @Override
    public void turnOff() { System.out.println(getName() + " in eco-mode."); }
}

// 4. Central Hub: Sadece yönetir, detayları bilmez
class CentralHub {
    private List<SmartDevice> deviceList = new ArrayList<>();

    public void addDevice(SmartDevice device) {
        deviceList.add(device);
    }

    public void activateSystem(String command) {
        System.out.println("\n--- Hub Action: " + command + " ---");
        System.out.println("Connecting to WiFi...");

        double totalCost = 0;
        for (SmartDevice device : deviceList) {
            device.turnOn();
            totalCost += device.calculateCost(0.15);
        }

        System.out.println("Current Energy Cost: $" + totalCost);
        if (totalCost > 5.0) {
            System.out.println("WARNING: High consumption!");
        }
    }
}

// 5. Test Sınıfı
public class LAB1 { //SMARTHOME
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter command (e.g., START): ");
        String cmd = scanner.next();

        CentralHub hub = new CentralHub();

        // İstediğimiz kadar cihaz ekleyebiliriz
        hub.addDevice(new SmartLight());
        hub.addDevice(new SecurityCamera());
        hub.addDevice(new SmartThermostat());

        hub.activateSystem(cmd);

        scanner.close();
    }
}