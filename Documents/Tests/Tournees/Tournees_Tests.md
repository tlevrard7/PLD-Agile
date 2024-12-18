# Documentation des Tests Postman avec IHM pour les tournées

Vous pouvez rejoindre l'environnement de test sur Postman via les liens suivants : 

[Lien 1 - Tests pour obtenir les livreurs](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-74977bce-63a7-4313-b3b4-b942d1114403?action=share&creator=40453978&ctx=documentation)

[Lien 2 - Tests par livreur](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-a8e553f0-d455-4f26-b01b-5de3b886cdf2?action=share&creator=40453978&ctx=documentation)


[Lien 3 - Test pour l'ensemble des livreurs](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-5a3ed576-74a1-4659-bad7-30e8b4a7e062?action=share&creator=40453978&ctx=documentation)

---
Pour l'affichage de la carte, nous avons au préalable testé que les trois cartes fournies par le client s'affichaient correctement, en un temps raisonnable. Nous procédons ensuite aux test unitaires.

Pour chaque cas, aller dans Body > form-data et sélectionner la clé du cas correspondant
Dans tous les cas, lorsqu'une réponse Json est correctement envoyée du Back au Front, elle s'affiche correctement dans le Front.

## **Test 1 : Récupérer la les informations de tous les livreurs.**

### Interfaces utilisées:
- Postman: [Lien 1](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-74977bce-63a7-4313-b3b4-b942d1114403?action=share&creator=40453978&ctx=documentation) à utiliser pour ce test.

### Description
Obtenir la liste de tous les livreurs.

- **Méthode** : `GET`  
- **URL** : `http://localhost:8080/api/livreurs`


### Réponse attendue :

Côté Back : 
```
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
```

---

## **Test 2 : Récupérer la tournée d'un livreur.**

### Interfaces utilisées:
- Postman: [Lien 2](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-a8e553f0-d455-4f26-b01b-5de3b886cdf2?action=share&creator=40453978&ctx=documentation) à utiliser pour ce test.
- IHM (pour l'importation)

### Pré-Requis
Avoir importé une carte et une livraison, ainsi que d'avoir affecter au moins une livraison au livreur testé.

### Description
Obtenir la tournée d'un livreur spécifique quel que soit le nombre de livraisons assignées à ce livreur.<br/>
Changer la valeur du '1' dans l'URL pour sélectionner un autre livreur.

- **Méthode** : `GET`  
- **URL** : `http://localhost:8080/api/livreurs/1/tournee`


### Réponse attendue :

Côté Back : 
```
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
```

---

## **Test 3 : Gestion de l'erreur d'appel sans livraisons.**

### interfaces utilisées:
- Postman: [Lien 2](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-a8e553f0-d455-4f26-b01b-5de3b886cdf2?action=share&creator=40453978&ctx=documentation) à utiliser pour ce test.
- IHM (pour l'importation)

### Pré-Requis
Avoir importé une carte et une livraison. Et aucune livraison assignée au livreur à tester.

### Description
Tentative d'obtenir la tournée d'un livreur qui n'a pas de livraison assignée.<br/>
Changer la valeur du '1' dans l'URL pour sélectionner un autre livreur.

- **Méthode** : `GET`  
- **URL** : `http://localhost:8080/api/livreurs/1/tournee`


### Réponse attendue :

Côté Back : 
```
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
```

---

## **Test 4 : Obtention de toutes les tournées**

### Interfaces utilisées:
- Postman: [Lien 3](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-5a3ed576-74a1-4659-bad7-30e8b4a7e062?action=share&creator=40453978&ctx=documentation) à utiliser pour ce test.
- IHM (pour l'importation)

### Pré-Requis
Avoir importé une carte et une livraison.

### Description
Obtenir les tournées de tous les livreurs quel que soit le nombre de livreur ayant une livraison.

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/livreurs/export-all-tournees`

### Réponse attendue :

Côté Back : 
```
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD SAAD
```
---

## **Test 5 : Modifier un point de delivery non assigné**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on "drag and drop" un point de delivery sur la carte vers un nouveau emplacement.
Cet emplacement peut être vide ou contenir déjà un point de delivery d'une autre livraison.


### Pré-requis: 
Importer une carte et une demande de livraisons. 

### Post-condition: 
Dans le cas où nous deployons le point de delivery sur un autre emplacement qui contenait déjà au moins un point de delivery, tous ces points de delivery 
ne sont pas modifiés et notre point glissé est correctement ajouté au tas de points de delivery.

### Réponse attendue :
La verification peut se faire sur l'IHM à la fois en regardant que le nouvel ID assigné correspond à celui du nouveau point et en regardant que 
lors qu'elle est assignée à un livreur celui le parcours.

---

## **Test 6 : Modifier un point de pickup non assigné**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on "drag and drop" un point de pickup sur la carte vers un nouveau emplacement.
Cet emplacement peut être vide ou contenir déjà un point de pickup d'une autre livraison.


### Pré-requis: 
Importer une carte et une demande de livraisons. 

### Post-condition: 
Dans le cas où nous deployons le point de pickup sur un autre emplacement qui contenait déjà au moins un point de pickup, tous ces points de pickup 
ne sont pas modifiés et notre point glissé est correctement ajouté au tas de points de pickup.

### Réponse attendue :
La verification peut se faire sur l'IHM à la fois en regardant que le nouvel ID assigné correspond à celui du nouveau point et en regardant que 
lors qu'elle est assignée à un livreur celui le parcours.

---

## **Test 7 : Modifier un point de pickup ou delviery assigné**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on essaie de "drag and drop" un point de pickup ou delivery d'une livraison assignée.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner à un livreur la livraison des points à déplacer.

### Post-condition: 
Les points n'ont pas été modifiés.

### Réponse attendue :
Les points ne peuvent pas être déplacés.

---

## **Test 8 : Afficher une tournée avec une carte**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester l'affichage de la tournée d'un livreur.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur dont on affiche sa tournée.

### Post-condition: 
Les livraisons ne sont pas modifiées. La tournée du livreur a été modifiée avec le circuit calculé au plus optimal possible.

### Réponse attendue :
Affiche la tournée sur la carte.

---

## **Test 9 : Afficher une tournée avec une carte après avoir affiché une autre tournée**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester l'affichage de la tournée d'un livreur après avoir affiché une autre tournée.
Cette autre tournée peut être juste le fait d'avoir assigné ou déssasigné une livraison au livreur.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur et afficher sa tournée. Puis, afficher une autre tournée.

### Réponse attendue :
Affiche la nouvelle tournée sur la carte et efface toute trace de l'ancienne tournée.

---

## **Test 10 : Afficher une tournée avec une carte après avoir cliqué sur le bouton "play"**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester l'affichage de la tournée d'un livreur après avoir affiché une autre tournée.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur et afficher sa tournée. Cliquer sur play et afficher une autre tournée.

### Réponse attendue :
Affiche la nouvelle tournée sur la carte et efface toute trace de l'ancienne tournée.

---

## **Test 11 : Modifier un point de pickup non assigné après avoir affiché une tournée**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on "drag and drop" un point de pickup sur la carte vers un nouveau emplacement.
Cet emplacement peut être vide ou contenir déjà un point de pickup d'une autre livraison.
Cela lorsque nous avons affiché une tournée sur la carte.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur et afficher sa tournée.

### Post-condition: 
Dans le cas où nous deployons le point de pickup sur un autre emplacement qui contenait déjà au moins un point de pickup, tous ces points de pickup 
ne sont pas modifiés et notre point glissé est correctement ajouté au tas de points de pickup.<br>
Pas de bug d'affichage.

### Réponse attendue :
La verification peut se faire sur l'IHM à la fois en regardant que le nouvel ID assigné correspond à celui du nouveau point et en regardant que 
lors qu'elle est assignée à un livreur celui le parcours.

---

## **Test 12 : Modifier un point de pickup non assigné après avoir affiché une tournée et cliqué sur play**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on "drag and drop" un point de pickup sur la carte vers un nouveau emplacement.
Cet emplacement peut être vide ou contenir déjà un point de pickup d'une autre livraison.
Cela lorsque nous avons affiché une tournée sur la carte et on a cliqué sur play.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur, afficher sa tournée et cliquer sur play.

### Post-condition: 
Dans le cas où nous deployons le point de pickup sur un autre emplacement qui contenait déjà au moins un point de pickup, tous ces points de pickup 
ne sont pas modifiés et notre point glissé est correctement ajouté au tas de points de pickup.<br>
Pas de bug d'affichage.

### Réponse attendue :
La verification peut se faire sur l'IHM à la fois en regardant que le nouvel ID assigné correspond à celui du nouveau point et en regardant que 
lors qu'elle est assignée à un livreur celui le parcours.

---

## **Test 11 : Modifier un point de pickup non assigné après avoir affiché une tournée**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on "drag and drop" un point de pickup sur la carte vers un nouveau emplacement.
Cet emplacement peut être vide ou contenir déjà un point de pickup d'une autre livraison.
Cela lorsque nous avons affiché une tournée sur la carte.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur et afficher sa tournée.

---

## **Test 13 : Modifier un point de pickup ou delviery assigné**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on essaie de "drag and drop" un point de pickup ou delivery d'une livraison assignée et dont on a affiché la tournée du livreur assigné.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner à un livreur la livraison des points à déplacer et afficher sa tournée.

### Post-condition: 
Les points n'ont pas été modifiés.<br>
Pas de bug d'affichage.

### Réponse attendue :
Les points ne peuvent pas être déplacés.

---

## **Test 14 : Tenter d'importer une demande de livraison sans importer de carte**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Importer une demande de livraison sans importer de carte. On essaye de rendre impossible de charger une demande sans carte afin d'éviter de nombreux problèmes.

### Pré-requis: 
Ne pas improter une carte.

### Réponse attendue :
Le bouton d'import de demande est grisé et ne permet pas d'importer.

---

## **Test 15 : Changer la carte**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)
- Postman: [Lien 3 - Test pour l'ensemble des livreurs](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-5a3ed576-74a1-4659-bad7-30e8b4a7e062?action=share&creator=40453978&ctx=documentation) pour tester si le BACK a été réinitialisé.

### Description  
Importer une nouvelle carte alors qu'il y a déjà des livraisons assignées.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons et en laisser d'autres non-assignées.

### Réponse attendue :
La carte se charge et tout est remis à zero (demande à re-importer et livraisons à re-assigner).
```
{
SAAD
}
```
---

## **Test 16 : Changer la demande de livraisons**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)
- Postman: [Lien 3 - Test pour l'ensemble des livreurs](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-5a3ed576-74a1-4659-bad7-30e8b4a7e062?action=share&creator=40453978&ctx=documentation) pour tester si le BACK a été réinitialisé.

### Description  
Importer une nouvelle demande de livraisons alors qu'il y a déjà des livraisons assignées.
Trois variantes:
- sans affichage de tournée
- Après avoir affiché une tournée
- Après avoir cliqué sur "play" pour uen tournée 

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons et en laisser d'autres non-assignées.

### Réponse attendue :
La demande se charge et tout est remis à zero (livraisons à re-assigner et la carte affiche seulement les novueaux points).
```
{
SAAD
}
```
---

## **Test 16 : Changer la demande de livraisons**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Cliquer sur "play" sans avoir affiché uen tournée.
Deux variantes:
- sans carte
- avec carte

### Pré-requis: 
Ne pas avoir affiché une tournée.

### Réponse attendue :
Rien ne se passe
---
