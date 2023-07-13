package com.alan.tool.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class GenerateClass {

    public void generateEntityFile(String json, String className) throws IOException {
        StringBuffer attributeBuilder = new StringBuffer();
        attributeBuilder.append(startClass(className));
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapParse = objectMapper.readValue(json, Map.class);
        mapParse.forEach((key, value) -> {
            attributeBuilder.append(checkValueType(value, key));
        });

        attributeBuilder.append(endClass());

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\ADMIN\\Downloads\\" + className + ".java"));
        bufferedWriter.append(attributeBuilder);

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public void generateEntityFile(Map<String, Object> map, String className) throws IOException {
        StringBuffer attributeBuilder = new StringBuffer();
        attributeBuilder.append(startClass(className));
        map.forEach((key, value) -> {
            attributeBuilder.append(checkValueType(value, key));
        });

        attributeBuilder.append(endClass());

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\ADMIN\\Downloads\\" + className + ".java"));
        bufferedWriter.append(attributeBuilder);

        bufferedWriter.flush();
        bufferedWriter.close();
    }


    public String startClass(String className) {
        StringBuffer classBuilder = new StringBuffer();

        classBuilder.append("import lombok.*;");

        classBuilder.append("\n");
        classBuilder.append("\n");

        classBuilder.append("@Getter").append("\n")
                .append("@Setter").append("\n")
                .append("@Builder").append("\n")
                .append("@AllArgsConstructor").append("\n")
                .append("@NoArgsConstructor").append("\n")
                .append("public class ").append(className).append("{");

        classBuilder.append("\n");
        classBuilder.append("\n");


        return classBuilder.toString();
    }

    public String checkValueType(Object value, String key) {
        StringBuilder variableBuilder = new StringBuilder();
        variableBuilder.append("private ");
        if (value instanceof Integer) {
            variableBuilder
                    .append("Integer ")
                    .append(key)
                    .append(";");
        } else if (value instanceof Double) {
            variableBuilder
                    .append("Double ")
                    .append(key)
                    .append(";");
        } else if (value instanceof String) {
            variableBuilder
                    .append("String ")
                    .append(key)
                    .append(";");
        }
        variableBuilder.append("\n");
        return variableBuilder.toString();
    }

    public String endClass() {
        return "}";
    }
}
