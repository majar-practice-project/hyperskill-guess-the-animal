package animals.domain.decision;

public interface TreeStatistics {
    int getNumInternalNodes();

    int getNumLeafs();

    int getHeight();
    int getMinLeafDepth();

    double getAverageLeafDepth();
}
