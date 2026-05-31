
abstract class ASTNode {

    public abstract int eval();
    public abstract String display();

    public void add(ASTNode node) throws Exception {
        throw new Exception("Hata: Yaprak düğümlere alt eleman eklenemez!");
    }
}

class Node extends ASTNode {
    private int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return this.value;
    }

    @Override
    public String display() {
        return String.valueOf(value);
    }
}

abstract class OperatorNode extends ASTNode {
    public ASTNode firstOperandNode;
    public ASTNode secondOperandNode;

    @Override
    public void add(ASTNode node) {

        if (firstOperandNode == null) {
            firstOperandNode = node;
        } else if (secondOperandNode == null) {
            secondOperandNode = node;
        } else {
            throw new IllegalStateException("Hata: Binary operatörler en fazla 2 operand alabilir!");
        }
    }
}

class SummationNode extends OperatorNode {
    @Override
    public int eval() {
        return firstOperandNode.eval() + secondOperandNode.eval();
    }

    @Override
    public String display() {
        return "+ " + firstOperandNode.display() + " " + secondOperandNode.display();
    }
}

class SubtractionNode extends OperatorNode {
    @Override
    public int eval() {
        return firstOperandNode.eval() - secondOperandNode.eval();
    }

    @Override
    public String display() {
        return "- " + firstOperandNode.display() + " " + secondOperandNode.display();
    }
}

class MultiplicationNode extends OperatorNode {
    @Override
    public int eval() {
        return firstOperandNode.eval() * secondOperandNode.eval();
    }

    @Override
    public String display() {
        return "* " + firstOperandNode.display() + " " + secondOperandNode.display();
    }
}

class DivisionNode extends OperatorNode {
    @Override
    public int eval() {
        return firstOperandNode.eval() / secondOperandNode.eval();
    }

    @Override
    public String display() {
        return "/ " + firstOperandNode.display() + " " + secondOperandNode.display();
    }
}

public class LAB3{
    public static void main(String[] args) throws Exception {
        ASTNode root = new SummationNode();

        ASTNode leftSum = new Node(4);
        ASTNode rightSum = new SummationNode();

        root.add(leftSum);
        root.add(rightSum);

        ASTNode leftMul = new MultiplicationNode();
        ASTNode rightMul = new MultiplicationNode();

        rightSum.add(leftMul);
        rightSum.add(rightMul);

        leftMul.add(new Node(2));
        leftMul.add(new Node(10));

        rightMul.add(new Node(3));
        ASTNode innerSum = new SummationNode();
        rightMul.add(innerSum);

        innerSum.add(new Node(5));
        innerSum.add(new Node(1));


        System.out.println(root.eval());
        System.out.println(root.display());



    }
}
