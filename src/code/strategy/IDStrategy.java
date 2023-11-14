package code.strategy;

import code.Node;
import code.SearchStrategy;
import java.util.*;

public class IDStrategy<S> extends SearchStrategy<S> {
    Stack<Node<S>> st = new Stack<>();
    int depthSoFar = 0;
    Set<S> s = new HashSet<>();

    @Override
    public Node<S> removeFront() {
        //System.out.println(st.peek().depth);
        if(st.size()==1){
            depthSoFar+=1;
            s=new HashSet<>();
            return st.peek();
        }
        else return st.pop();
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
        int depth=0;
        if(filtered.size()>0) depth = filtered.get(0).depth;
        //System.out.println(filtered);
        if(depth <= depthSoFar){
            st.addAll(filtered);
            s.addAll(filtered.stream().map(n -> n.state).toList());
        }
    }
}
