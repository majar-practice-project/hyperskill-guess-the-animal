package animals.domain.decision;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DecisionTreeNode<InternalT, LeafT> {
    private LeafT leafValue;
    private InternalT internalNodeValue;
    private DecisionTreeNode<InternalT, LeafT> trueChild;
    private DecisionTreeNode<InternalT, LeafT> falseChild;

    DecisionTreeNode(){}

    public DecisionTreeNode(LeafT val) {
        this.leafValue = val;
    }

    public LeafT getLeafValue() {
        return leafValue;
    }

    public InternalT getInternalNodeValue() {
        return internalNodeValue;
    }

    public DecisionTreeNode<InternalT, LeafT> getTrueChild() {
        return trueChild;
    }

    public DecisionTreeNode<InternalT, LeafT> getFalseChild() {
        return falseChild;
    }

    public void changeToDecision(InternalT condition, LeafT anotherChild, boolean isTrueChild){
        if(!isLeaf()) throw new IllegalArgumentException("Cannot change a leaf node to a decision statement!");

        if(isTrueChild){
            falseChild = new DecisionTreeNode<>(leafValue);
            trueChild = new DecisionTreeNode<>(anotherChild);
        } else {
            trueChild = new DecisionTreeNode<>(leafValue);
            falseChild = new DecisionTreeNode<>(anotherChild);
        }

        internalNodeValue = condition;
    }

    @JsonIgnore
    boolean isLeaf(){
        return trueChild==null && falseChild==null;
    }
}