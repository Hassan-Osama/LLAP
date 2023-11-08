package code;

public class Node<S> {
    public S state;
    public Node<S> parent = null;
    public String operator;
    public int depth;
    public int pathCost;
}
