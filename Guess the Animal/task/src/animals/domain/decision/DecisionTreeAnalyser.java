package animals.domain.decision;

public class DecisionTreeAnalyser implements TreeStatistics{
    private int numInternalNodes = 0;
    private int height = 0;
    private int numLeafs = 0;
    private int minLeafDepth = Integer.MAX_VALUE;
    private int depthCount = 0;

    public void resetParameters(){
        numInternalNodes = 0;
        numLeafs = 0;
        height = 0;
        minLeafDepth = Integer.MAX_VALUE;
        depthCount = 0;
    }
    public void anaylse(DecisionTree<?, ?> tree){
        resetParameters();
        DecisionTreeNode<?, ?> root = tree.getHead();

        dfs(root, 0);
    }

    private void dfs(DecisionTreeNode<?, ?> node, int depth){
        if(node.isLeaf()) {
            numLeafs++;
            depthCount+=depth;
            minLeafDepth = Math.min(minLeafDepth, depth);
            height = Math.max(height, depth);
            return;
        }

        numInternalNodes++;
        depth++;

        dfs(node.getTrueChild(), depth);
        dfs(node.getFalseChild(), depth);
    }

    @Override
    public int getNumInternalNodes() {
        return numInternalNodes;
    }

    @Override
    public int getNumLeafs() {
        return numLeafs;
    }

    @Override
    public int getMinLeafDepth() {
        return minLeafDepth;
    }

    @Override
    public double getAverageLeafDepth(){
        return (double)depthCount / numLeafs;
    }

    @Override
    public int getHeight() {
        return height;
    }
}