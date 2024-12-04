import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.Bestanome.Model.Outils.ParseurXML;


public class MyClassTest {
    @Test
    void testParseXMLtoJSONLivraison() throws IOException {
        String xmlContent = new String(Files.readAllBytes(Paths.get("../fichiersXMLPickupDelivery/demandePetit1.xml")));
        JSONObject jsonCreated = ParseurXML.parseXMLFileContent(xmlContent);
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

        assertTrue(jsonObject.similar(jsonCreated), "Erreur de Parsing de la Livraison!");
    }

    
    @Test
    void testParseXMLtoJSONMap() throws IOException {
        String xmlContent = new String(Files.readAllBytes(Paths.get("../fichiersXMLPickupDelivery/mapTest.xml")));
        JSONObject jsonCreated = ParseurXML.parseXMLFileContent(xmlContent);
        JSONObject jsonObject = new JSONObject();
        JSONObject reseau = new JSONObject();
        JSONObject noeud = new JSONObject();
        JSONArray noeudArray = new JSONArray();
        JSONObject troncon = new JSONObject();
        JSONArray tronconArray = new JSONArray();

        noeud.put("id", 25175791);
        noeud.put("latitude", 45.75406);
        noeud.put("longitude", 4.857418);
        noeudArray.put(noeud);

        noeud = new JSONObject();
        noeud.put("id", 2129259178);
        noeud.put("latitude", 45.750404);
        noeud.put("longitude", 4.8744674);
        noeudArray.put(noeud);

        reseau.put("noeud", noeudArray);

        troncon.put("destination", 55475018);
        troncon.put("longueur", 96.57731);
        troncon.put("nomRue", "Rue Édouard Aynard");
        troncon.put("origine", 208769499);
        tronconArray.put(troncon);

        troncon = new JSONObject();
        troncon.put("destination", 26033277);
        troncon.put("longueur", 78.72686);
        troncon.put("nomRue", "Rue Danton");
        troncon.put("origine", 975886496);
        tronconArray.put(troncon);

        reseau.put("troncon", tronconArray);

        jsonObject.put("reseau", reseau);

        // System.out.println(jsonCreated.toString());
        // System.out.println(jsonObject.toString());

        assertTrue(jsonObject.similar(jsonCreated), "Erreur de Parsing de la Map!");

    }
}
