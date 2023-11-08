package code.strategy;

import code.Node;
import code.SearchStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class BFStrategy<S> extends SearchStrategy<S> {

    Queue<Node<S>> queue = new ArrayDeque<>();

    Set<S> s = new HashSet<>();

    @Override
    public Node<S> removeFront() {
        return queue.poll();
    }

    @Override
    public void addNewNodes(List<Node<S>> nodes) {
        List<Node<S>> filtered = nodes.stream()
                .filter(n -> !s.contains(n.state))
                .toList();
        queue.addAll(filtered);
        s.addAll(filtered.stream().map(n -> n.state).toList());
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
