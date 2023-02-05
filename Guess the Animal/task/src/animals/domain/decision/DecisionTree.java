package animals.domain.decision;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class DecisionTree<InternalT, LeafT> {
    private final DecisionTreeNode<InternalT, LeafT> head;
    private DecisionTreeNode<InternalT, LeafT> node;

    public DecisionTree(DecisionTreeNode<InternalT, LeafT> head){
        this.node = this.head = head;
    }
    public DecisionTree(LeafT headVal) {
        this.node = this.head = new DecisionTreeNode<>(headVal);
    }

    /**
     * get value of current node
     */
    public LeafT getLeafValue() {
        return node.getLeafValue();
    }

    public InternalT getInternalValue() {
        return node.getInternalNodeValue();
    }

    /**
     * get value of a child
     */
    public void move(boolean isTrueChild) {
        if (node.isLeaf()) throw new IllegalArgumentException("Reach the leaf");
        node = isTrueChild ? node.getTrueChild() : node.getFalseChild();
    }

    /**
     * add condition to the current node
     */
    public void addCondition(InternalT condition, LeafT anotherChild, boolean isTrueChild) {
        node.changeToDecision(condition, anotherChild, isTrueChild);
    }

    public void resetToRoot() {
        node = head;
    }

    public List<LeafT> getLeafs(){
        List<LeafT> leafs = new ArrayList<>();
        Deque<DecisionTreeNode<InternalT, LeafT>> stack = new ArrayDeque<>();
        stack.add(head);
        while(!stack.isEmpty()){
            DecisionTreeNode<InternalT, LeafT> tempNode = stack.poll();
            if(tempNode.isLeaf()) {
                leafs.add(tempNode.getLeafValue());
                continue;
            }
            stack.add(tempNode.getTrueChild());
            stack.add(tempNode.getFalseChild());
        }
        return leafs;
    }

    public Map<InternalT, Boolean> getConditions(LeafT value){
        Map<InternalT, Boolean> result = new LinkedHashMap<>();
        dfsGetConditions(result, head, value);
        return result;
    }

    private boolean dfsGetConditions(Map<InternalT, Boolean> path, DecisionTreeNode<InternalT, LeafT> tempNode, LeafT value){
        if(tempNode.isLeaf()) return value.equals(tempNode.getLeafValue());

        path.put(tempNode.getInternalNodeValue(), true);
        if(dfsGetConditions(path, tempNode.getTrueChild(), value)) return true;

        path.put(tempNode.getInternalNodeValue(), false);
        if(dfsGetConditions(path, tempNode.getFalseChild(), value)) return true;
        path.remove(tempNode.getInternalNodeValue());

        return false;
    }

    public boolean reachTheEnd() {
        return node.isLeaf();
    }

    @JsonIgnore
    DecisionTreeNode<InternalT, LeafT> getHead() {
        return head;
    }
}