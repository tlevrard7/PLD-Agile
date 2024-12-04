package com.Bestanome.Model.Outils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class ParseurXML {

    public static JSONObject parseXMLFileContent(String xmlContent) {
        try {
            JSONObject jsonObject = XML.toJSONObject(xmlContent);
            String rootName = jsonObject.keys().next();
            JSONObject rootObject = jsonObject.getJSONObject(rootName);

            // Transforme les enfants en JSONArray si nécessaire
            JSONObject resultObject = new JSONObject();
            resultObject.put(rootName, convertToJSONArray(rootObject));

            return resultObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject convertToJSONArray(JSONObject object) {
        JSONObject transformedObject = new JSONObject();

        for (String key : object.keySet()) {
            Object value = object.get(key);
            if (value instanceof JSONArray) {
                transformedObject.put(key, value);
            } else {
                JSONArray array = new JSONArray();
                array.put(value);
                transformedObject.put(key, array);
            }
        }

        return transformedObject;
    }
}
