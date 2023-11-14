package code.strategy;

import code.Node;
import code.SearchStrategy;
import java.util.*;

public class DFStrategy<S> extends SearchStrategy<S> {
    Stack<Node<S>> st = new Stack<>();
    int depthSoFar = 0;
    Set<S> s = new HashSet<>();

    @Override
    public Node<S> removeFront() {
        return st.pop();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void addNewNodes(List<Node<S>> nodes) {
        List<Node<S>> filtered = nodes.stream()
                .filter(n -> !s.contains(n.state))
                .toList();
            st.addAll(filtered);
            s.addAll(filtered.stream().map(n -> n.state).toList());
    }
}
