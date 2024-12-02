import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.Bestanome.Model.Outils.ParseurXML;

public class MyClassTest {
    @Test
    void testParseXMLtoJSON() {
        JSONObject jsonCreated = ParseurXML.parseXMLFile("../fichiersXMLPickupDelivery/demandePetit1.xml");
        JSONObject jsonObject = new JSONObject();
        JSONObject demandeLivraison = new JSONObject();
        JSONObject entrepot = new JSONObject();
        JSONArray entrepotArray = new JSONArray();
        JSONObject livraison = new JSONObject();
        JSONArray livraisonArray = new JSONArray();

        entrepot.put("adresse", 342873658);
        entrepot.put("heureDepart", "8:0:0");
        entrepotArray.put(entrepot);
        demandeLivraison.put("entrepot", entrepotArray);

        livraison.put("dureeLivraison", 240);
        livraison.put("dureeEnlevement", 180);
        livraison.put("adresseEnlevement", 208769039);
        livraison.put("adresseLivraison", 25173820);
        livraisonArray.put(livraison);
        demandeLivraison.put("livraison", livraisonArray);

        jsonObject.put("demandeDeLivraisons", demandeLivraison);

        // System.out.println(jsonCreated.toString());
        // System.out.println(jsonObject.toString());

        assertTrue(jsonObject.similar(jsonCreated), "Erreur de Parsing!");
    }
}
