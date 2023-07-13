package com.alan.tool.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

import static com.alan.tool.common.constant.Constant.JSON_BODY;

public class JsonUtils {

    public String generateJson(Map<String, Object> map, String objectName) {
        StringBuffer jsonBuilder = new StringBuffer();
        jsonBuilder.append(this.jsonStartWith(objectName));
        jsonBuilder.append(this.jsonBody(map));
        jsonBuilder.append(this.jsonEndWith(objectName));

        return jsonBuilder.toString();
    }

    private String jsonStartWith(String objectName) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        if (!StringUtils.isEmpty(objectName)) {
            jsonBuilder.append("\"").append(objectName).append("\"").append(":{");
        }

        return jsonBuilder.toString();
    }

    private String jsonEndWith(String objectName) {
        StringBuilder jsonBuilder = new StringBuilder();
        if (!StringUtils.isEmpty(objectName)) {
            jsonBuilder.append("}");
        }
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    private String jsonBody(Map<String, Object> map) {
        StringBuffer jsonBuilder = new StringBuffer();
        map.forEach((key, value) -> {

            String valueStr = JSON_BODY.replace("{key}", key)
                    .replace("{value}", value.toString());
            if (value instanceof String) {
                if (!value.toString().startsWith("{") && !value.toString().startsWith("[")) {
                    valueStr = JSON_BODY.replace("{key}", key)
                            .replace("{value}", "\"" + value + "\"");
                }
            }

            jsonBuilder.append(valueStr);
            jsonBuilder.append(",");
        });
        jsonBuilder.replace(jsonBuilder.length() - 1, jsonBuilder.length(), "");

        return jsonBuilder.toString();
    }


    public String GenerateJsonArrayBody(List<String> jsonArr) {
        StringBuffer jsonBuilder = new StringBuffer();
        jsonBuilder.append("[");
        jsonArr.forEach(j -> {
            jsonBuilder.append(j).append(",");
        });
        jsonBuilder.replace(jsonBuilder.length() - 1, jsonBuilder.length(), "");
        jsonBuilder.append("]");

        return jsonBuilder.toString();
    }
}
