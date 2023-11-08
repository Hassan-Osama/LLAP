package code.strategy;

import code.Node;
import code.SearchStrategy;

import java.util.*;

public class UCStrategy<S> extends SearchStrategy<S> {

    PriorityQueue<Node<S>> queue = new PriorityQueue<>(
            Comparator.comparingInt(o -> o.pathCost)
    );

    Set<S> s = new HashSet<>();

    @Override
    public Node<S> removeFront() {
        return queue.poll();
    }

    @Override
    public void addNewNodes(List<Node<S>> nodes) {
        List<Node<S>> filteredList = nodes.stream()
                .filter(n -> !s.contains(n.state))
                .toList();
        queue.addAll(filteredList);
        s.addAll(filteredList.stream().map(n -> n.state).toList());
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
