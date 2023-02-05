package animals.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class ObjectMapperFactory {
    private static final ObjectMapperFactory INSTANCE = new ObjectMapperFactory();
    public ObjectMapper getObjectMapper(String mapper){
        return switch (mapper.toLowerCase()){
            case "json" -> new JsonMapper();
            case "xml" -> new XmlMapper();
            case "yaml" -> new YAMLMapper();
            default -> throw new IllegalArgumentException("Data format not supported");
        };
    }

    public static ObjectMapperFactory getInstance(){
        return INSTANCE;
    }
}
