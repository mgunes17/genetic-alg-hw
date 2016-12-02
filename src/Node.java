/**
 * Created by mgunes on 30.11.2016.
 *
 * Bir düğüme ait yapıyı tutar
 * Algoritmik bir işlemi yok
 */
public class Node {
    private int id;
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private NodeType type;
    private int value;
    private char operator;
    private int generation;

    public enum NodeType {
        ROOT,
        BRANCH,
        LEAF
    }

    //constructors
    public Node(int id){
        super();
        this.id = id;
    }

    public Node(NodeType type, int id){
        super();
        this.id = id;
        this.type = type;
    }

    //getter-setter
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
