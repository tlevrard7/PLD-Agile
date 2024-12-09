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
        Set<String> uniqueDeliveries = new HashSet<>();

        for (String key : object.keySet()) {
            Object value = object.get(key);
            JSONArray array;

            if (value instanceof JSONArray) {
                array = (JSONArray) value;
            } else {
                array = new JSONArray();
                array.put(value);
            }

            JSONArray uniqueArray = new JSONArray();

            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);

                // Créez une clé unique pour chaque livraison en combinant pickup, destination, dureeEnlevement et dureeLivraison
                String uniqueKey = item.optString("pickup") + "|" +
                                   item.optString("destination") + "|" +
                                   item.optString("dureeEnlevement") + "|" +
                                   item.optString("dureeLivraison");

                if (!uniqueDeliveries.contains(uniqueKey)) {
                    uniqueDeliveries.add(uniqueKey);
                    uniqueArray.put(item);
                }
            }

            transformedObject.put(key, uniqueArray);
        }

        return transformedObject;
    }
}