package code;

import java.util.Objects;

public class Node<S> {
    public S state;
    public Node<S> parent = null;
    public String operator;
    public int depth;
    public int pathCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return depth == node.depth && pathCost == node.pathCost && Objects.equals(state, node.state) && Objects.equals(parent, node.parent) && Objects.equals(operator, node.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, parent, operator, depth, pathCost);
    }
}
