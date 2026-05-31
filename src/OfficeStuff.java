import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// eager halini de yaz bu lazy

public class OfficeStuff {
    public static void main(String[] args) {
        String developerName = "Sisyphos";
        OfficeAssistantSystem.getInstance().startDeepWork("IntelliJ IDEA");
        System.out.println("Productivity mode engaged for " + developerName + "!");
    }
}

class OfficeAssistantSystem {
    private static OfficeAssistantSystem instanceA;
    private static OfficeAssistantSystem instanceB;
    private static volatile int getInstanceCallCount = 0;
    private static final Lock instanceLock = new ReentrantLock();


    private final Workstation pc = new Workstation();
    private final ClimateControl ac = new ClimateControl();
    private final CoffeeMachine coffee = new CoffeeMachine();
    private final SmartBlinds blinds = new SmartBlinds();
    private final StatusIndicator status = new StatusIndicator();

    private OfficeAssistantSystem() {}

    public static OfficeAssistantSystem getInstance() {
        instanceLock.lock();
        try {
            getInstanceCallCount++;

            if (getInstanceCallCount == 1) {
                return getOrCreateA();
            }

            if (getInstanceCallCount == 2) {
                return getOrCreateB();
            }

            return ThreadLocalRandom.current().nextInt(2) == 0 ? getOrCreateA() : getOrCreateB();
        } finally {
            instanceLock.unlock();
        }
    }

    private static synchronized OfficeAssistantSystem getOrCreateA() {
        if (instanceA == null) {
            instanceA = new OfficeAssistantSystem();
        }
        return instanceA;
    }

    private static synchronized OfficeAssistantSystem getOrCreateB() {
        if (instanceB == null) {
            instanceB = new OfficeAssistantSystem();
        }
        return instanceB;
    }

    public void startDeepWork(String ideName) {
        pc.powerOn();
        pc.launchIDE(ideName);
        ac.setTemp(23);
        coffee.brew("Double Espresso");
        blinds.open();
        status.setDND(true);
    }
}

// --- Subsystem classes ---


class Workstation {

    public void powerOn() {
        System.out.println("PC is booting up...");
    }

    public void launchIDE(String ide) {

        System.out.println("Launching " + ide + "...");
    }
}


class ClimateControl {
    public void setTemp(int temp) {
        System.out.println("AC set to " + temp + " C.");
    }
}

class CoffeeMachine {
    public void brew(String type) {
        System.out.println("Brewing a hot " + type + "...");
    }
}

class SmartBlinds {
    public void open() {
        System.out.println("Blinds are opening...");
    }
}

class StatusIndicator {
    public void setDND(boolean active) {
        System.out.println("DND Light is " + (active ? "ON" : "OFF"));
    }
}

