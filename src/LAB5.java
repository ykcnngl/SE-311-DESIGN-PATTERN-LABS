import java.util.ArrayList;
import java.util.List;


interface HardwareProduct {
	void executeFunction();
}



class LogitechMouse implements HardwareProduct {
	private final int dpi;

	public LogitechMouse(int dpi) {
		this.dpi = dpi;
	}

	@Override
	public void executeFunction() {
		System.out.println("LogitechMouse clicked with " + dpi + " DPI sensitivity.");
	}
}

class LogitechKeyboard implements HardwareProduct {
	private final int hz;

	public LogitechKeyboard(int hz) {
		this.hz = hz;
	}

	@Override
	public void executeFunction() {
		System.out.println("LogitechKeyboard typed at " + hz + " Hz polling rate.");
	}
}

class LogitechHeadphone implements HardwareProduct {
	private final int frequency;

	public LogitechHeadphone(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public void executeFunction() {
		System.out.println("LogitechHeadphone played audio at " + frequency + " Hz frequency response.");
	}
}


class RazerMouse implements HardwareProduct {
	private final int dpi;

	public RazerMouse(int dpi) {
		this.dpi = dpi;
	}

	@Override
	public void executeFunction() {
		System.out.println("RazerMouse clicked with " + dpi + " DPI sensitivity.");
	}
}

class RazerKeyboard implements HardwareProduct {
	private final int hz;

	public RazerKeyboard(int hz) {
		this.hz = hz;
	}

	@Override
	public void executeFunction() {
		System.out.println("RazerKeyboard typed at " + hz + " Hz polling rate.");
	}
}

class RazerHeadphone implements HardwareProduct {
	private final int frequency;

	public RazerHeadphone(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public void executeFunction() {
		System.out.println("RazerHeadphone played audio at " + frequency + " Hz frequency response.");
	}
}


class SteelSeriesMouse implements HardwareProduct {
	private final int dpi;

	public SteelSeriesMouse(int dpi) {
		this.dpi = dpi;
	}

	@Override
	public void executeFunction() {
		System.out.println("SteelSeriesMouse clicked with " + dpi + " DPI sensitivity.");
	}
}

class SteelSeriesKeyboard implements HardwareProduct {
	private final int hz;

	public SteelSeriesKeyboard(int hz) {
		this.hz = hz;
	}

	@Override
	public void executeFunction() {
		System.out.println("SteelSeriesKeyboard typed at " + hz + " Hz polling rate.");
	}
}

class SteelSeriesHeadphone implements HardwareProduct {
	private final int frequency;

	public SteelSeriesHeadphone(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public void executeFunction() {
		System.out.println("SteelSeriesHeadphone played audio at " + frequency + " Hz frequency response.");
	}
}


interface HardwareFactory {
	HardwareProduct createProduct(String productType);
}


class LogitechFactory implements HardwareFactory {
	private static final LogitechFactory INSTANCE = new LogitechFactory();

	private LogitechFactory() {}

	public static LogitechFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public HardwareProduct createProduct(String productType) {
		return switch (productType.toUpperCase()) {
			case "MOUSE" -> new LogitechMouse(12000);
			case "KEYBOARD" -> new LogitechKeyboard(1000);
			case "HEADPHONE" -> new LogitechHeadphone(20000);
			default -> throw new IllegalArgumentException("Bilinmeyen ürün tipi: " + productType);
		};
	}
}

class RazerFactory implements HardwareFactory {
	private static final RazerFactory INSTANCE = new RazerFactory();

	private RazerFactory() {}

	public static RazerFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public HardwareProduct createProduct(String productType) {
		return switch (productType.toUpperCase()) {
			case "MOUSE" -> new RazerMouse(16000);
			case "KEYBOARD" -> new RazerKeyboard(8000);
			case "HEADPHONE" -> new RazerHeadphone(22000);
			default -> throw new IllegalArgumentException("Bilinmeyen ürün tipi: " + productType);
		};
	}
}

class SteelSeriesFactory implements HardwareFactory {
	private static final SteelSeriesFactory INSTANCE = new SteelSeriesFactory();

	private SteelSeriesFactory() {}

	public static SteelSeriesFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public HardwareProduct createProduct(String productType) {
		return switch (productType.toUpperCase()) {
			case "MOUSE" -> new SteelSeriesMouse(18000);
			case "KEYBOARD" -> new SteelSeriesKeyboard(2000);
			case "HEADPHONE" -> new SteelSeriesHeadphone(40000);
			default -> throw new IllegalArgumentException("Bilinmeyen ürün tipi: " + productType);
		};
	}
}


class HardwareSetupManager {
	private final List<HardwareProduct> products = new ArrayList<>();

	public void assembleHardwareSetup(HardwareFactory factory) {
		products.clear();
		products.add(factory.createProduct("MOUSE"));
		products.add(factory.createProduct("KEYBOARD"));
		products.add(factory.createProduct("HEADPHONE"));
	}

	public void runDiagnosticTest() {
		for (HardwareProduct product : products) {
			product.executeFunction();
		}
	}
}


public class LAB5 {
	public static void main(String[] args) {
		HardwareSetupManager setupManager = new HardwareSetupManager();

		System.out.println("----- Logitech Setup Diagnostic -----");
		setupManager.assembleHardwareSetup(LogitechFactory.getInstance());
		setupManager.runDiagnosticTest();

		System.out.println("\n----- Razer Setup Diagnostic -----");
		setupManager.assembleHardwareSetup(RazerFactory.getInstance());
		setupManager.runDiagnosticTest();

		System.out.println("\n----- SteelSeries Setup Diagnostic -----");
		setupManager.assembleHardwareSetup(SteelSeriesFactory.getInstance());
		setupManager.runDiagnosticTest();
	}
}