# Documentation des Tests Postman pour ParseurXML dans le Cas du Chargement des Demandes de Livraison

Vous pouvez rejoindre l'environnement de test sur Postman via le lien suivant : 

https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-97848ce6-37b5-4747-90d4-459125132fab?action=share&creator=40136526&ctx=documentation

---
Pour chaque cas, aller dans Body > form-data et sélectionner la clé du cas correspondant
Dans tous les cas, lorsqu'une réponse Json est correctement envoyée du Back au Front, elle s'affiche correctement dans le Front.

## **Cas 1 : Pas d’Entrepôt**

### Description  
Tester le cas où le fichier XML ne contient pas d’entrepôt.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/delivery/upload-deliveries`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `Pas_entrepot.xml` avec le contenu suivant :  

```xml
<demandeDeLivraisons>
    <livraison>
        <pickup>21992645</pickup>
        <destination>55444215</destination>
        <dureeEnlevement>360</dureeEnlevement>
        <dureeLivraison>480</dureeLivraison>
    </livraison>
</demandeDeLivraisons>
```

### Réponse attendue :

Côté Back : 
```
500 Internal Server Error
The server has encountered a situation it does not know how to handle
```

Côté Front : 
```
Erreur lors de l'importation des livraisons.
```

---

## **Cas 2 : Doublons de Livraison**

### Description  
Tester le cas où le fichier XML contient des livraisons en doublon.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/delivery/upload-deliveries`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `Doublon_livraison.xml` avec le contenu suivant :  

```xml
<demandeDeLivraisons>
  <entrepot adresse="25327124" heureDepart="8:0:0"/>
  <livraison adresseEnlevement="26464256" adresseLivraison="239603465" dureeEnlevement="0" dureeLivraison="600"/>
  <livraison adresseEnlevement="26464256" adresseLivraison="239603465" dureeEnlevement="0" dureeLivraison="600"/>
  </demandeDeLivraisons>
```

### Réponse attendue :

```json
{
    "livraisons": [
        {
            "pickup": 26464256,
            "destination": 239603465,
            "dureeEnlevement": 0,
            "dureeLivraison": 600
        }
    ],
    "idEntrepot": 25327124
}
```

Le doublon est éliminé

---

## **Cas 3 : Syntaxe XML Incorrecte**

### Description  
Tester le cas où le fichier XML contient une erreur de syntaxe.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/delivery/upload-deliveries`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `Syntaxe_erreur.xml` avec le contenu suivant :  

```xml
<demandeDeLivraisons>
    <entrepot>12345</entrepot>
    <livrason>
        <pickup>21992645</pickup>
        <destination>55444215</destination>
        <dureeEnlevement>360</dureeEnlevement>
</demandeDeLivraisons>
```

### Réponse attendue :

Côté Back : 
```
500 Internal Server Error
The server has encountered a situation it does not know how to handle
```

Côté Front : 
```
Erreur lors de l'importation des livraisons.
```

---

## **Cas 4 : Fichier .txt au Lieu de .xml (mais le contenu est le même que si ça avait été un .xml)**

### Description  
Tester le cas où le fichier envoyé est un `.txt` au lieu d'un `.xml`.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/delivery/upload-deliveries`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `Fichier_txt.txt` avec le contenu suivant :  

```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<demandeDeLivraisons>
<entrepot adresse="25327124" heureDepart="8:0:0"/>
<livraison adresseEnlevement="26464256" adresseLivraison="239603465" dureeEnlevement="0" dureeLivraison="600"/>
<livraison adresseEnlevement="25319255" adresseLivraison="1370403192" dureeEnlevement="600" dureeLivraison="120"/>
<livraison adresseEnlevement="984553611" adresseLivraison="1368674802" dureeEnlevement="60" dureeLivraison="480"/>
<livraison adresseEnlevement="1678996781" adresseLivraison="26084216" dureeEnlevement="420" dureeLivraison="600"/>
<livraison adresseEnlevement="1301805013" adresseLivraison="25310896" dureeEnlevement="420" dureeLivraison="240"/>
<livraison adresseEnlevement="59654812" adresseLivraison="25316399" dureeEnlevement="120" dureeLivraison="60"/>
<livraison adresseEnlevement="130144280" adresseLivraison="25499154" dureeEnlevement="240" dureeLivraison="120"/>
<livraison adresseEnlevement="26035105" adresseLivraison="25624158" dureeEnlevement="480" dureeLivraison="300"/>
<livraison adresseEnlevement="1362204817" adresseLivraison="843129906" dureeEnlevement="180" dureeLivraison="540"/>
</demandeDeLivraisons>
```

### Réponse attendue :

```json
{
    "livraisons": [
        {
            "pickup": 26464256,
            "destination": 239603465,
            "dureeEnlevement": 0,
            "dureeLivraison": 600
        },
        {
            "pickup": 25319255,
            "destination": 1370403192,
            "dureeEnlevement": 600,
            "dureeLivraison": 120
        },
        {
            "pickup": 984553611,
            "destination": 1368674802,
            "dureeEnlevement": 60,
            "dureeLivraison": 480
        },
        {
            "pickup": 1678996781,
            "destination": 26084216,
            "dureeEnlevement": 420,
            "dureeLivraison": 600
        },
        {
            "pickup": 1301805013,
            "destination": 25310896,
            "dureeEnlevement": 420,
            "dureeLivraison": 240
        },
        {
            "pickup": 59654812,
            "destination": 25316399,
            "dureeEnlevement": 120,
            "dureeLivraison": 60
        },
        {
            "pickup": 130144280,
            "destination": 25499154,
            "dureeEnlevement": 240,
            "dureeLivraison": 120
        },
        {
            "pickup": 26035105,
            "destination": 25624158,
            "dureeEnlevement": 480,
            "dureeLivraison": 300
        },
        {
            "pickup": 1362204817,
            "destination": 843129906,
            "dureeEnlevement": 180,
            "dureeLivraison": 540
        }
    ],
    "idEntrepot": 25327124
}
```

---

## **Cas 5 : Balise Fermante Manquante**

### Description  
Tester le cas où une balise fermante est manquante dans le fichier XML.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/delivery/upload-deliveries`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `Balise_manquante.xml` avec le contenu suivant :  

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<demandeDeLivraisons>
<entrepot adresse="25327124" heureDepart="8:0:0"/>
<livraison adresseEnlevement="26464256" adresseLivraison="239603465" dureeEnlevement="0" dureeLivraison="600"/>
<livraison adresseEnlevement="25319255" adresseLivraison="1370403192" dureeEnlevement="600" dureeLivraison="120"/>
<livraison adresseEnlevement="984553611" adresseLivraison="1368674802" dureeEnlevement="60" dureeLivraison="480"/>
<livraison adresseEnlevement="1678996781" adresseLivraison="26084216" dureeEnlevement="420" dureeLivraison="600"/>
<livraison adresseEnlevement="1301805013" adresseLivraison="25310896" dureeEnlevement="420" dureeLivraison="240"/>
<livraison adresseEnlevement="59654812" adresseLivraison="25316399" dureeEnlevement="120" dureeLivraison="60"/>
<livraison adresseEnlevement="130144280" adresseLivraison="25499154" dureeEnlevement="240" dureeLivraison="120"/>
<livraison adresseEnlevement="26035105" adresseLivraison="25624158" dureeEnlevement="480" dureeLivraison="300"/>
<livraison adresseEnlevement="1362204817" adresseLivraison="843129906" dureeEnlevement="180" dureeLivraison="540"/>
```

### Réponse attendue :

Côté Back : 
```
500 Internal Server Error
The server has encountered a situation it does not know how to handle
```

Côté Front : 
```
Erreur lors de l'importation des livraisons.
```

---

## **Cas 6 : Pas de Saut de Ligne Entre les Balises**

### Description  
Tester le cas où le fichier XML n'a pas de sauts de ligne entre les balises.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/parseXML`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `Pas_de_saut_de_lignes.xml` avec le contenu suivant :  

```xml
<demandeDeLivraisons><entrepot adresse="25327124" heureDepart="8:0:0"/>
<livraison adresseEnlevement="26464256" adresseLivraison="239603465" dureeEnlevement="0" dureeLivraison="600"/><livraison adresseEnlevement="26464256" adresseLivraison="239603465" dureeEnlevement="0" dureeLivraison="600"/></demandeDeLivraisons>
```

### Réponse attendue :

```json
{
    "livraisons": [
        {
            "pickup": 26464256,
            "destination": 239603465,
            "dureeEnlevement": 0,
            "dureeLivraison": 600
        }
    ],
    "idEntrepot": 25327124
}
```


