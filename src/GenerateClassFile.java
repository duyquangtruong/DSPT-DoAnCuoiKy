import org.jsonschema2pojo.*;
import org.apache.commons.lang.*;
import com.fasterxml.*;
import com.sun.codemodel.*;

import java.net.URL;

public class GenerateClassFile {
    JCodeModel codeModel = new JCodeModel();

    URL source = ("classname.json");

    GenerationConfig config = new DefaultGenerationConfig() {
        @Override
        public boolean isGenerateBuilders() { // set config option by overriding method
            return true;
        }
    };

    SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
mapper.generate(codeModel, "ClassName", "com.example", source);

codeModel.build(Files.createTempDirectory("required").toFile());
}
