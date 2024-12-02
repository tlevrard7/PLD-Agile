# Unit tests

<ins>Request:</ins>
```console
X:~$ mvn test
```


## X tests

### Parsing XML test
><ins>Description:</ins><br>
> Test de la méthode static JSONObject parseXMLFile(String filePath) de la class ParseurXML.
> Ce test utilise un fichier nommé "demandePetit1.xml".

<ins>Pre-condition:</ins>
- On suppose que le fichier "demandePetit1.xml" se situe dans le dossier "fichiersXMLPickupDelivery" dans le root.
- On suppose que le fichier XML est sous la forme :<br>
&lt;demandeDeLivraisons&gt;<br>
&lt;entrepot adresse="342873658" heureDepart="8:0:0"/&gt;<br>
&lt;livraison adresseEnlevement="208769039" adresseLivraison="25173820" dureeEnlevement="180" dureeLivraison="240"/&gt;<br>
...<br>
&lt;/demandeDeLivraisons&gt;
- Tout changement apporté au fichier "demandePetit1.xml" nécessite un changement du test.


<ins>Output:</ins>
Un test Booléen.

### Parsing XML test
><ins>Description:</ins><br>
> Test de la méthode static JSONObject parseXMLFile(String filePath) de la class ParseurXML.
> Ce test utilise un fichier nommé "demandePetit1.xml".

<ins>Pre-condition:</ins>
- On suppose que 

<ins>Post-condition:</ins>
- squil squik


<ins>Output:</ins>
```console
boom!
```
