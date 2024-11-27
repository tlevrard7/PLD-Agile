import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.io.File;

public class XMLParsing {
    public static void main(String[] args) {
        try {
            // Load and parse XML file
            File inputFile = new File("../fichiersXMLPickupDelivery/myDeliverRequest.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            // Normalize XML structure
            document.getDocumentElement().normalize();

            // Print root element
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());

            // Get entrepot element
            Element entrepot = (Element) document.getElementsByTagName("entrepot").item(0);
            System.out.println("\nInfo " + entrepot.getNodeName() + " | adresse: " + entrepot.getAttribute("adresse") + " | heureDepart: " + entrepot.getAttribute("heureDepart"));

            // Get livraison elements
            NodeList nodeList = document.getElementsByTagName("livraison");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nCurrent Element: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("adresseEnlevement: " + element.getAttribute("adresseEnlevement"));
                    System.out.println("adresseLivraison: " + element.getAttribute("adresseLivraison"));
                    System.out.println("dureeEnlevement: " + element.getAttribute("dureeEnlevement"));
                    System.out.println("dureeLivraison: " + element.getAttribute("dureeLivraison"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
