package animals.domain.decision;

import animals.domain.AnimalProperty;
import animals.language.processor.IndefiniteArticlePrepender;
import animals.language.processor.StringCapitalizer;

public class DecisionTreePrinter {
    private final StringCapitalizer capitalizer = new StringCapitalizer();
    private final IndefiniteArticlePrepender articlePrepender = new IndefiniteArticlePrepender();
    public void print(DecisionTree<AnimalProperty, String> tree){
        DecisionTreeNode<AnimalProperty, String> root = tree.getHead();
        dfs(root, 0);
    }

    private void dfs(DecisionTreeNode<AnimalProperty, String> node, int depth){
        if(node.isLeaf()){
            System.out.println(" ".repeat(depth)+"> "+articlePrepender.process(node.getLeafValue()));
            return;
        }
        System.out.println(" ".repeat(depth)+"> "+capitalizer.process(node.getInternalNodeValue().toInterrogativeString()));
        depth++;

        dfs(node.getTrueChild(), depth);
        dfs(node.getFalseChild(), depth);
    }
}
