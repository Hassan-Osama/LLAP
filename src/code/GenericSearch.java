package code;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericSearch<S> {

    public SearchStrategy<S> strategy;

    public Node<S> makeNode(S s, Node<S> parent, String operator, int depth, int pathCost) {
        Node<S> node = new Node<S>();
        node.depth = depth;
        node.parent = parent;
        node.pathCost = pathCost;
        node.operator = operator;
        node.state = s;

        return node;
    }

    public GenericSearch(S initialState, SearchStrategy<S> strategy) {
        this.strategy = strategy;
        List<Node<S>> initialNodes = new ArrayList<>();
        initialNodes.add(makeNode(initialState, null, null, 0, 0));
        strategy.addNewNodes(initialNodes);
    }

    /**
     * Returns the end goal node if exists or null
     */
    public Node<S> solve() {
        while(!strategy.isEmpty()) {
            Node<S> node = strategy.removeFront();
            if (isGoalState(node.state)) {
                return node;
            }
            List<Node<S>> generatedNodes = expandNode(node);
            strategy.addNewNodes(generatedNodes);
        }
        return null;
    }

    public abstract List<Node<S>> expandNode(Node<S> node);
    public abstract boolean isGoalState(S state);
}
