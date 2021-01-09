package Generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Writer {

    private String attribute;

    public static void writeClass(String className, HashMap<String,String> atttributes){
        try {
            FileWriter classFile =  new FileWriter(className+".java");

            classFile.write("public class "+className+ " {\n");

            // Tao cac thuoc tinh cho class
            for (String attributeName:atttributes.keySet()) {
                classFile.write("private "+atttributes.get(attributeName)+" "+attributeName+";\n");
            }

            // Them create options ben duoi



            classFile.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
