# Unit tests

JUnit5 est utilisé pour faire les Unit tests.<br>
Pour pouvoir lancer les Unit tests, veuillez lancer la commande:<br>
```console
X:~$ mvn test
```

## 2 tests

### Parsing XML d'un fichier XML de livraison
><ins>Description:</ins><br>
> Test de la méthode static JSONObject parseXMLFile(String filePath) de la class ParseurXML.<br>
> Ce test vérifie l'objet JSON retourné par la méthode.<br>
> Cette vérification utilise un fichier nommé "demandePetit1.xml" présent dans le dossier "fichiersXMLPickupDelivery" du root.

<ins>Pre-condition:</ins>
- On suppose que le fichier "demandePetit1.xml" se situe dans le dossier "fichiersXMLPickupDelivery" du root.
- On suppose que le fichier XML est sous la forme :<br>
&lt;demandeDeLivraisons&gt;<br>
&lt;entrepot adresse="342873658" heureDepart="8:0:0"/&gt;<br>
&lt;livraison adresseEnlevement="208769039" adresseLivraison="25173820" dureeEnlevement="180" dureeLivraison="240"/&gt;<br>
...<br>
&lt;/demandeDeLivraisons&gt;
- Tout changement apporté au fichier "demandePetit1.xml" nécessite un changement du test.


<ins>Output:</ins>
Booléen


### Parsing XML d'un fichier XML de la map
><ins>Description:</ins><br>
> Test de la méthode static JSONObject parseXMLFile(String filePath) de la class ParseurXML.<br>
> Ce test vérifie l'objet JSON retourné par la méthode.<br>
> Cette vérification utilise un fichier nommé "mapTest.xml" présent dans le dossier "fichiersXMLPickupDelivery" du root.

<ins>Pre-condition:</ins>
- On suppose que le fichier "mapTest.xml" se situe dans le dossier "fichiersXMLPickupDelivery" du root.
- On suppose que le fichier XML est sous la forme :<br>
&lt;reseau&gt;<br>
&lt;noeud id="25175791" latitude="45.75406" longitude="4.857418"/&gt;<br>
...<br>
&lt;troncon destination="55475018" longueur="96.57731" nomRue="Rue Édouard Aynard" origine="208769499"/&gt;<br>
...<br>
&lt;/reseau&gt;
- Tout changement apporté au fichier "mapTest.xml" nécessite un changement du test.


<ins>Output:</ins>
Booléen
