import java.util.Scanner;
import java.util.Stack;

// 1. Command Arayüzü
interface Command {
    void execute();
    void undo();
}

// 2. Receiver (Alıcı) Sınıfı
class GameCharacter {
    private int x;
    private int y;

    public GameCharacter(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp() { y++; }
    public void moveDown() { y--; }
    public void moveLeft() { x--; }
    public void moveRight() { x++; }

    public String position() {
        return "(" + x + ", " + y + ")";
    }
}

// 3. Concrete Command (Somut Komut) Sınıfları
class MoveUpCommand implements Command {
    private final GameCharacter character;
    public MoveUpCommand(GameCharacter character) { this.character = character; }
    @Override public void execute() { System.out.println("Moving Up!"); character.moveUp(); }
    @Override public void undo() { character.moveDown(); }
}

class MoveDownCommand implements Command {
    private final GameCharacter character;
    public MoveDownCommand(GameCharacter character) { this.character = character; }
    @Override public void execute() { System.out.println("Moving Down!"); character.moveDown(); }
    @Override public void undo() { character.moveUp(); }
}

class MoveLeftCommand implements Command {
    private final GameCharacter character;
    public MoveLeftCommand(GameCharacter character) { this.character = character; }
    @Override public void execute() { System.out.println("Moving Left!"); character.moveLeft(); }
    @Override public void undo() { character.moveRight(); }
}

class MoveRightCommand implements Command {
    private final GameCharacter character;
    public MoveRightCommand(GameCharacter character) { this.character = character; }
    @Override public void execute() { System.out.println("Moving Right!"); character.moveRight(); }
    @Override public void undo() { character.moveLeft(); }
}

// Macro Command (Komutları birleştiren özel komut)
class LShapeMoveCommand implements Command {
    private final Command moveUpCommand;
    private final Command moveRightCommand;

    public LShapeMoveCommand(Command moveUpCommand, Command moveRightCommand) {
        this.moveUpCommand = moveUpCommand;
        this.moveRightCommand = moveRightCommand;
    }

    @Override
    public void execute() {
        System.out.println("Executing L-Shape Move!");
        moveUpCommand.execute();
        moveUpCommand.execute();
        moveRightCommand.execute();
    }

    @Override
    public void undo() {
        moveRightCommand.undo();
        moveUpCommand.undo();
        moveUpCommand.undo();
    }
}

// 4. Invoker (Çağırıcı) Sınıfı
class Keyboard {
    private final Command moveUp, moveDown, moveLeft, moveRight, lShape;
    private final Stack<Command> history = new Stack<>();

    public Keyboard(Command up, Command down, Command left, Command right, Command l) {
        this.moveUp = up; this.moveDown = down; this.moveLeft = left;
        this.moveRight = right; this.lShape = l;
    }

    public void pressUp() { executeAndStore(moveUp); }
    public void pressDown() { executeAndStore(moveDown); }
    public void pressLeft() { executeAndStore(moveLeft); }
    public void pressRight() { executeAndStore(moveRight); }
    public void pressLShape() { executeAndStore(lShape); }

    public void pressUndo() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        System.out.println("Undoing last action...");
        history.pop().undo();
    }

    private void executeAndStore(Command command) {
        command.execute();
        history.push(command);
    }
}

// 5. Client (Ana Uygulama)
public class LAB4 {
    public static void main(String[] args) {
        GameCharacter character = new GameCharacter(0, 0);

        Command up = new MoveUpCommand(character);
        Command down = new MoveDownCommand(character);
        Command left = new MoveLeftCommand(character);
        Command right = new MoveRightCommand(character);
        Command lShape = new LShapeMoveCommand(up, right);

        Keyboard keyboard = new Keyboard(up, down, left, right, lShape);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Commands: up, down, left, right, lshape, undo, exit");

        while (true) {
            System.out.print("Current: " + character.position() + " > ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) break;

            switch (input) {
                case "up" -> keyboard.pressUp();
                case "down" -> keyboard.pressDown();
                case "left" -> keyboard.pressLeft();
                case "right" -> keyboard.pressRight();
                case "lshape" -> keyboard.pressLShape();
                case "undo" -> keyboard.pressUndo();
                default -> System.out.println("Unknown command!");
            }
        }
        scanner.close();
    }
}

