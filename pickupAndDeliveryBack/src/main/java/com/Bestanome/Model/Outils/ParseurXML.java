package com.Bestanome.Model.Outils;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.multipart.MultipartFile;

public class ParseurXML {

    public static JSONObject parseXMLFileContent(MultipartFile file) {
        try {
            // Lire le contenu du fichier XML
            String xmlContent = new String(file.getBytes(), StandardCharsets.UTF_8);
            JSONObject jsonObject = XML.toJSONObject(xmlContent);
            String rootName = jsonObject.keys().next();
            JSONObject rootObject = jsonObject.getJSONObject(rootName);

            // Transforme les enfants en JSONArray si nécessaire tout en supprimant les
            // doublons
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
        Set<String> uniqueItems = new HashSet<>();

        for (String key : object.keySet()) {
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                JSONArray filteredArray = new JSONArray();
                for (int i = 0; i < ((JSONArray) value).length(); i++) {
                    JSONObject item = ((JSONArray) value).getJSONObject(i);
                    String uniqueKey = generateUniqueKey(item);

                    if (!uniqueItems.contains(uniqueKey)) {
                        uniqueItems.add(uniqueKey);
                        filteredArray.put(item);
                    }
                }
                transformedObject.put(key, filteredArray);
            } else if (value instanceof JSONObject) {
                JSONObject item = (JSONObject) value;
                String uniqueKey = generateUniqueKey(item);

                if (!uniqueItems.contains(uniqueKey)) {
                    uniqueItems.add(uniqueKey);
                    JSONArray array = new JSONArray();
                    array.put(item);
                    transformedObject.put(key, array);
                }
            } else {
                JSONArray array = new JSONArray();
                array.put(value);
                transformedObject.put(key, array);
            }
        }

        return transformedObject;
    }

    private static String generateUniqueKey(JSONObject item) {
        return item.toString();
    }
}
