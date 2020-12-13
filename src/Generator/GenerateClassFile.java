package Generator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;
import com.sun.codemodel.JCodeModel;

import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;

public class GenerateClassFile {

    @SuppressWarnings("unchecked")
    public void Generate(String path,String className, String packageName){
        JCodeModel codeModel = new JCodeModel();

        JSONParser parser = new JSONParser();
        String source = "";

        try {
            Object obj = parser.parse(new FileReader(path));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;
            source = jsonObject.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                // set config option by overriding method
                return true;
            }
        };

        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        try {
            // Problem with className and package
            // Need help
            mapper.generate(codeModel, className, packageName, source);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            codeModel.build(Files.createTempDirectory("required").toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
