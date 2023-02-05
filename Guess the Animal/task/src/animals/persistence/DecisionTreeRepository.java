package animals.persistence;

import animals.domain.AnimalProperty;
import animals.domain.decision.DecisionTree;
import animals.domain.decision.DecisionTreeNode;
import animals.localization.ResourceBundleFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DecisionTreeRepository {
    private static final String DEFAULT_DATA_FILE_NAME = ResourceBundleFactory.getResource().getString("dataDefaultFileName");
    private String dataFormat;
    private Supplier<String> rootValueSupplier;
    private ObjectMapper mapper;

    private Path dataPath;

    public DecisionTreeRepository(String dataFormat, Supplier<String> rootValueSupplier) {
        this.dataFormat = dataFormat;
        this.rootValueSupplier = rootValueSupplier;
        this.mapper = ObjectMapperFactory.getInstance().getObjectMapper(dataFormat);
    }

    public DecisionTree<AnimalProperty, String> load() {
        Optional<Path> nullablePath;
        try (Stream<Path> stream = Files.list(Path.of("."))) {
            nullablePath = stream
                    .filter(path -> path.toString().endsWith(dataFormat))
                    .findFirst();

            dataPath = nullablePath.orElse(Path.of(String.format("%s.%s", DEFAULT_DATA_FILE_NAME, dataFormat)));
            if (nullablePath.isPresent()) {
                return new DecisionTree<>(mapper.readValue(nullablePath.get().toFile(), new TypeReference<>() {
                }));
            } else {
                return new DecisionTree<>(rootValueSupplier.get());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(DecisionTree<AnimalProperty, String> decisionTree) {
        try {
            Field headField = decisionTree.getClass().getDeclaredField("head");
            headField.setAccessible(true);
            DecisionTreeNode<AnimalProperty, String> head = (DecisionTreeNode<AnimalProperty, String>) headField.get(decisionTree);
            mapper.writeValue(dataPath.toFile(), head);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing data");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
