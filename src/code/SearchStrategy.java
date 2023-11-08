package code;

import java.util.List;

public abstract class SearchStrategy<S> {

    public abstract Node<S> removeFront();
    public abstract void addNewNodes(List<Node<S>> nodes);
    public abstract boolean isEmpty();

}
