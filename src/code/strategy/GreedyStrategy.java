package code.strategy;

import code.Constants;
import code.Node;
import code.SearchStrategy;
import code.State;

import java.util.*;

public class GreedyStrategy extends SearchStrategy<State> {

    Queue<Node<State>> queue;

    Set<State> s = new HashSet<>();

    Constants c;

    public GreedyStrategy(Constants c, int h) {
        this.c = c;
        if (h == 1)
            queue = new PriorityQueue<>(
                    Comparator.comparingInt(n -> firstHeuristic(n.state))
            );
        if (h == 2)
            queue = new PriorityQueue<>(
                    Comparator.comparingDouble(n -> secondHeuristic(n.state))
            );
    }

    @Override
    public Node<State> removeFront() {
        return queue.poll();
    }

    @Override
    public void addNewNodes(List<Node<State>> nodes) {
        List<Node<State>> filteredList = nodes.stream()
                .filter(n -> !s.contains(n.state))
                .toList();
        queue.addAll(filteredList);
        s.addAll(filteredList.stream().map(n -> n.state).toList());
    }

    int firstHeuristic(State s) {
        // The higher the prosperity the more important the node is
        return -s.prosperity;
    }

    float secondHeuristic(State s) {
        // The closer we are to build the better.
        int build1Shortage =
                Math.max((c.foodUseBuild1 - s.food), 0) * c.unitPriceFood
                        + Math.max((c.materialUseBuild1 - s.material), 0) * c.unitPriceMaterial
                        + Math.max((c.energyUseBuild1 - s.energy), 0) * c.unitPriceEnergy;

        int build2Shortage =
                Math.max((c.foodUseBuild2 - s.food), 0) * c.unitPriceFood
                        + Math.max((c.materialUseBuild2 - s.material), 0) * c.unitPriceMaterial
                        + Math.max((c.energyUseBuild2 - s.energy), 0) * c.unitPriceEnergy;

        return Math.min(build2Shortage , build1Shortage);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
