import java.util.ArrayList;
import java.util.List;

interface BountyHunter {
    void onBountyPosted(String bountyInfo);
    String getName();
}

class StreetSamurai implements BountyHunter {          // Observer
    private final String name;

    StreetSamurai(String name) {
        this.name = name;
    }

    @Override
    public void onBountyPosted(String bountyInfo) {
        System.out.println("[Street Samurai " + name + "] Yeni is: " + bountyInfo + " -> katanayi hazirla.");
    }

    @Override
    public String getName() {
        return name;
    }
}

class Netrunner implements BountyHunter {         // Observer
    private final String name;

    Netrunner(String name) {
        this.name = name;
    }

    @Override
    public void onBountyPosted(String bountyInfo) {
        System.out.println("[Netrunner " + name + "] Yeni is: " + bountyInfo + " -> sistemleri hackle.");
    }

    @Override
    public String getName() {
        return name;
    }
}

class BountyDispatcher {                 // Subject
    private final List<BountyHunter> hunters = new ArrayList<>();

    public void subscribe(BountyHunter hunter) {
        hunters.add(hunter);
        System.out.println("Baglandi: " + hunter.getName());
    }

    public void unsubscribe(BountyHunter hunter) {
        hunters.remove(hunter);
        System.out.println("Ayrildi: " + hunter.getName());
    }

    public void postBounty(String bountyInfo) {
        System.out.println("\n[Dispatcher] Yeni bounty: " + bountyInfo);
        notifyHunters(bountyInfo);
    }

    public void syncFromSatellite() {

        System.out.println("\n[Dispatcher] Uydu verisi senkronlandi (avcilar bilgilendirilmedi).");
    }

    private void notifyHunters(String bountyInfo) {
        for (BountyHunter hunter : hunters) {
            hunter.onBountyPosted(bountyInfo);
        }
    }
}

    public class LAB9 {   // OBSERVER PATTERN
    public static void main(String[] args) {
        BountyDispatcher dispatcher = new BountyDispatcher();

        BountyHunter samurai = new StreetSamurai("Raiden");
        BountyHunter netrunner = new Netrunner("Sonic");

        dispatcher.subscribe(samurai);
        dispatcher.subscribe(netrunner);

        dispatcher.postBounty("Drone kacagi");


        dispatcher.unsubscribe(netrunner);

        dispatcher.postBounty("Cyber");


        dispatcher.syncFromSatellite();

        dispatcher.postBounty("Yuksek profilli hedef - Neon Plaza");
    }
}