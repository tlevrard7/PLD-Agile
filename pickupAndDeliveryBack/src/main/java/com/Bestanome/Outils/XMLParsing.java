package com.Bestanome.Outils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLParsing {

    public static JSONObject parseXMLFile(String filePath) {
        try {
            // Read the XML file as a string
            String xmlContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Convert XML to JSONObject
            JSONObject jsonObject = XML.toJSONObject(xmlContent);

            // Get the root element
            String rootName = jsonObject.keys().next();
            JSONObject rootObject = jsonObject.getJSONObject(rootName);

            // Transform child elements into JSONArrays
            JSONObject resultObject = new JSONObject();
            resultObject.put(rootName, convertToJSONArray(rootObject));

            return resultObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static JSONObject convertToJSONArray(JSONObject object) {
        JSONObject transformedObject = new JSONObject();

        // Iterate through the keys of the original object
        for (String key : object.keySet()) {
            Object value = object.get(key);

            // Wrap the value in a JSONArray if not already an array
            if (value instanceof JSONArray) {
                transformedObject.put(key, value); // Retain as-is if already an array
            }
            else {
                JSONArray array = new JSONArray();
                array.put(value);
                transformedObject.put(key, array);
            }
        }

        return transformedObject;
    }
}
