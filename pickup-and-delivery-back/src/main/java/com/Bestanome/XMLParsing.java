import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLParsing {

    public static void main() {
        try {
            String filePath = "../fichiersXMLPickupDelivery/myDeliverRequest.xml";
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

            // Print the final JSON structure
            System.out.println(resultObject.toString(4)); // Pretty print with indentation
        } catch (Exception e) {
            e.printStackTrace();
        }
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
