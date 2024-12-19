# Documentation des Tests Postman pour ParseurXML dans le Cas du Chargement des Maps

Vous pouvez rejoindre l'environnement de test sur Postman via le lien suivant : 

https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-d4e74be3-a3af-416b-be76-336b09b474a6?action=share&creator=40136526&ctx=documentation

---
Pour l'affichage de la carte, nous avons au préalable testé que les trois cartes fournies par le client s'affichaient correctement, en un temps raisonnable. Nous procédons ensuite aux test unitaires.

Pour chaque cas, aller dans Body > form-data et sélectionner la clé du cas correspondant
Dans tous les cas, lorsqu'une réponse Json est correctement envoyée du Back au Front, elle s'affiche correctement dans le Front.

## **Cas 1 : XML Valide avec des Nœuds et des Tronçons**

### Description  
Tester le cas où le fichier XML contient des nœuds et des tronçons valides.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/map/upload-xml`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `valid_map.xml` avec le contenu suivant :  

```xml
<reseau>
    <noeud id="25175791" latitude="45.75406" longitude="4.857418"/>
    <noeud id="25175778" latitude="45.75343" longitude="4.8574653"/>
    <noeud id="195266" latitude="45.75" longitude="4.8644643"/>
    <noeud id="25321302" latitude="45.749954" longitude="4.8637676"/>
    <troncon destination="25175778" longueur="69.979805" nomRue="Rue Danton" origine="25175791"/>
    <troncon destination="195266" longueur="54.717278" nomRue="Rue du Dauphiné" origine="25321302"/>
</reseau>
```

### Réponse attendue :

Côté Back : 
```json
{
    "points": [
        {
            "id": 25175791,
            "longitude": 4.857418,
            "latitude": 45.75406,
            "type": "INTERSECTION"
        },
        {
            "id": 25175778,
            "longitude": 4.8574653,
            "latitude": 45.75343,
            "type": "INTERSECTION"
        },
        {
            "id": 195266,
            "longitude": 4.8644643,
            "latitude": 45.75,
            "type": "INTERSECTION"
        },
        {
            "id": 25321302,
            "longitude": 4.8637676,
            "latitude": 45.749954,
            "type": "INTERSECTION"
        }
    ],
    "segments": [
        {
            "nom": "Rue Danton",
            "longueur": 69.979805,
            "origine": 25175791,
            "destination": 25175778
        },
        {
            "nom": "Rue du Dauphiné",
            "longueur": 54.717278,
            "origine": 25321302,
            "destination": 195266
        }
    ]
}
```

Côté Front : 
```
Un segment entre deux points sur Rue Danton et un autre sur Rue du Dauphiné.
```

---

## **Cas 2 : Charger une demande de livraison au lieu d'une map.**

### Description  
L'utilisateur se trompe et charge une demande de livraison au lieu d'une map.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/map/upload-xml`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `demandeGrand9.xml` fourni par le client

### Réponse attendue :

Côté Back : 
```
500 Internal Server Error
The server has encountered a situation it does not know how to handle
```

Côté Front : 
```
Erreur lors de l'importation de la carte.
```
---

## **Cas 3 : XML Sans Nœuds**

### Description  
Tester le cas où le fichier XML ne contient pas de point.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/map/upload-xml`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `no_nodes.xml` avec le contenu suivant :  

```xml
<reseau>
    <troncon destination="2129259178" longueur="150.36613" nomRue="Rue Paul Bert" origine="25175791"/>
</reseau>
```

### Réponse attendue :

Côté Back : 
```
500 Internal Server Error
The server has encountered a situation it does not know how to handle
```

Côté Front : 
```
Erreur lors de l'importation de la carte.
```
---

## **Cas 4 : Pas de tronçon**

### Description  
Tester le cas où le fichier XML ne contient pas de segment.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/map/upload-xml`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `no_lines.xml` avec le contenu suivant :  

```xml
<reseau>
    <noeud id="25175791" latitude="45.75406" longitude="4.857418"/>
    <noeud id="2129259178" latitude="45.750404" longitude="4.8744674"/>
</reseau>
```

### Réponse attendue :

Côté Back : 
```
500 Internal Server Error
The server has encountered a situation it does not know how to handle
```

Côté Front : 
```
Erreur lors de l'importation de la carte.
```
---

## **Cas 5 :XML avec Syntaxe Incorrecte**

### Description  
Tester le cas où le fichier XML présente une erreur de syntaxe.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/map/upload-xml`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `syntax_error.xml` avec le contenu suivant :  

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<reseau>
    <noeud id="25175791" latitude="45.75406" longitude="4.857418">
    <troncon destination="2129259178" longueur="150.36613" nomRue="Rue Paul Bert" origine="25175791"/>
</reseau>
```

### Réponse attendue :

Côté Back : 
```
500 Internal Server Error
The server has encountered a situation it does not know how to handle
```

Côté Front : 
```
Erreur lors de l'importation de la carte.
```
---

## **Cas 6 :XML avec des noeuds en doublon**

### Description  
Tester le cas où le fichier XML présente une erreur de syntaxe.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/map/upload-xml`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `doublon_nodes.xml` avec le contenu suivant :  

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<reseau>
    <noeud id="25175791" latitude="45.75406" longitude="4.857418"/>
    <noeud id="25175791" latitude="45.75406" longitude="4.857418"/>
    <noeud id="26079801" latitude="45.754852" longitude="4.8574104"/>
    <troncon destination="25175791" longueur="88.53801" nomRue="Rue Danton" origine="26079801"/>
</reseau>
```

### Réponse attendue :

Côté back : 

Les doublons sont éliminés :

{
    "points": [
        {
            "id": 25175791,
            "longitude": 4.857418,
            "latitude": 45.75406,
            "type": "INTERSECTION"
        },
        {
            "id": 26079801,
            "longitude": 4.8574104,
            "latitude": 45.754852,
            "type": "INTERSECTION"
        }
    ],
    "segments": [
        {
            "nom": "Rue Danton",
            "longueur": 88.53801,
            "origine": 26079801,
            "destination": 25175791
        }
    ]
}

Côté front : Pas de  "double point"

## **Cas 7 :XML avec des segments en doublon**

### Description  
Tester le cas où le fichier XML présente une erreur de syntaxe.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/map/upload-xml`  
- **Paramètres de la requête** :  
  - **Type** : `form-data`  
  - **Clé** : `file`  
  - **Valeur** : Fichier `doublons_troncons.xml` avec le contenu suivant :  

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<reseau>
    <noeud id="25175791" latitude="45.75406" longitude="4.857418"/>
    <noeud id="26079801" latitude="45.754852" longitude="4.8574104"/>
    <troncon destination="25175791" longueur="88.53801" nomRue="Rue Danton" origine="26079801"/>
    <troncon destination="25175791" longueur="88.53801" nomRue="Rue Danton" origine="26079801"/>
</reseau>
```

### Réponse attendue :

Côté back : 

Les doublons sont éliminés :
```json
{
    "points": [
        {
            "id": 25175791,
            "longitude": 4.857418,
            "latitude": 45.75406,
            "type": "INTERSECTION"
        },
        {
            "id": 26079801,
            "longitude": 4.8574104,
            "latitude": 45.754852,
            "type": "INTERSECTION"
        }
    ],
    "segments": [
        {
            "nom": "Rue Danton",
            "longueur": 88.53801,
            "origine": 26079801,
            "destination": 25175791
        }
    ]
}
```
Côté front : Pas de "double ligne" Rue Danton