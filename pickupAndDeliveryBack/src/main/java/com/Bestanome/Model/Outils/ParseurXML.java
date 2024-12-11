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

            // Transforme les enfants en JSONArray si nécessaire tout en supprimant les doublons
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