# Documentation des Tests Postman avec IHM pour les tournées

Vous pouvez rejoindre l'environnement de test sur Postman via les liens suivants : 

[Lien 1 - Tests pour obtenir les livreurs](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-74977bce-63a7-4313-b3b4-b942d1114403?action=share&creator=40453978&ctx=documentation)

[Lien 2 - Tests par livreur](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-a8e553f0-d455-4f26-b01b-5de3b886cdf2?action=share&creator=40453978&ctx=documentation)

[Lien 3 - Test pour l'ensemble des livreurs](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-5a3ed576-74a1-4659-bad7-30e8b4a7e062?action=share&creator=40453978&ctx=documentation)

[Lien 4 - Test pour affecter un livreur à une livraison](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-339b05fe-e0e9-4689-99fc-c61ce75845f6?action=share&creator=40455199&ctx=documentation)

[Lien 5 - Test pour désaffecter un livreur à une livraison](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-9ed198ac-79bd-45a6-9549-ea750636a5ea?action=share&creator=40455199&ctx=documentation)

---
Pour l'affichage de la carte, nous avons au préalable testé que les trois cartes fournies par le client s'affichaient correctement, en un temps raisonnable. Nous procédons ensuite aux test unitaires.

Pour chaque cas, aller dans Body > form-data et sélectionner la clé du cas correspondant
Dans tous les cas, lorsqu'une réponse Json est correctement envoyée du Back au Front, elle s'affiche correctement dans le Front.

## **Test 1 : Récupérer les informations de tous les livreurs.**

### Interfaces utilisées:
- Postman: [Lien 1](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-74977bce-63a7-4313-b3b4-b942d1114403?action=share&creator=40453978&ctx=documentation) à utiliser pour ce test.

### Description
Obtenir la liste de tous les livreurs.

- **Méthode** : `GET`  
- **URL** : `http://localhost:8080/api/livreurs`


### Réponse attendue:

Côté Back : 
```
[
    {
        "id": 1,
        "nom": "Corentin",
        "prenom": "Jeanne",
        "nombreDeLivraisons": 0,
        "livraisons": []
    },
    {
        "id": 2,
        "nom": "Harold",
        "prenom": "Martin",
        "nombreDeLivraisons": 0,
        "livraisons": []
    },
    {
        "id": 3,
        "nom": "Saad",
        "prenom": "Elghissassi",
        "nombreDeLivraisons": 0,
        "livraisons": []
    },
    {
        "id": 4,
        "nom": "Thomas",
        "prenom": "Levrard",
        "nombreDeLivraisons": 0,
        "livraisons": []
    }
]
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


### Réponse attendue:

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


### Réponse attendue:

Côté Back : 
```
Aucune livraison assignée pour ce livreur.
```

---

## **Test 4 : Exportation de toutes les tournées**

### Interfaces utilisées:
- Postman: [Lien 3](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-5a3ed576-74a1-4659-bad7-30e8b4a7e062?action=share&creator=40453978&ctx=documentation) à utiliser pour ce test.
- IHM (pour l'importation)

### Pré-Requis
Avoir importé une carte et une livraison.

### Description
Exporter les tournées de tous les livreurs (quel que soit le nombre de livreur ayant une livraison)

- **Méthode** : `POST`  
- **URL** : `http://localhost:8080/api/livreurs/export-all-tournees`

### Réponse attendue:

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

## **Test 5 : Déplacer un point de delivery non assigné sur un point de la carte vide (ne contient ni pickup ni delivery)**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on "drag and drop" un point de delivery sur la carte vers un point de la carte vide (ne contient ni pickup ni delivery). Si l'on drop à un endroit qui n'est pas un point de la carte, le point sera automatiquement déplacé à l'emplacement du point le plus proche du point de drop.

### Pré-requis: 
Importer une carte et une demande de livraisons. 

### Post-condition: 

Le point de delivery a bien été déplacé sur le point de la carte le plus proche du point de drop.

### Réponse attendue:
La verification peut se faire sur l'IHM à la fois en regardant la carte mais aussi en regardant que le nouvel ID assigné correspond à celui du nouveau point, et en regardant que lorsqu'elle est assignée à un livreur celui-ci le parcours.

---


## **Test 6 : Déplacer un point de delivery non assigné sur un point contenant déjà une ou des deliveries et/ou une ou des pickups**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description:  
Tester le cas où l'on "drag and drop" un point de delivery (non assigné) sur la carte vers un nouveau point de la carte qui contient déjà un ou plusieurs points de deliveries ou pickups (correspondant à d'autres livraisons)

### Pré-requis:
Importer une carte et une demande de livraisons. 

### Post-condition:
Dans le cas où nous deplaçons le point de delivery sur un autre emplacement qui contenait déjà au moins un point de delivery ou pickup, les points de deliveries/pickups qui étaient déjà là ne sont pas modifiés et notre point glissé est correctement ajouté au tas de points.

### Réponse attendue:
La verification peut se faire sur l'IHM à la fois en regardant la carte mais aussi le nouvel ID assigné correspond à celui du nouveau point, et en regardant qu'après qu'elle a été assignée à un livreur, celui-ci la parcours. Sur la carte, dans le cas où il y a plusieurs deliviries/pickups sur le même point, on le voit clairement quand on zoome.

---

## **Test 7 : Déplacer un point de pickup non assigné sur un point de la carte vide (ne contient ni pickup ni delivery)**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on "drag and drop" un point de pickup sur la carte vers un point de la carte vide (ne contient ni pickup ni delivery). Si l'on drop à un endroit qui n'est pas un point de la carte, le point sera automatiquement déplacé à l'emplacement du point de la carte le plus proche du point de drop.

### Pré-requis: 
Importer une carte et une demande de livraisons. 

### Post-condition: 
Le point de pickup a bien été déplacé sur le point de la carte le plus proche du point de drop.

### Réponse attendue:
La verification peut se faire sur l'IHM à la fois en regardant la carte mais aussi en regardant que le nouvel ID assigné correspond à celui du nouveau point, et en regardant que lorsqu'il est assigné à un livreur celui-ci le parcours.

---

## **Test 8 : Déplacer un point de pickup non assigné sur un point contenant déjà une ou des deliveries et/ou une ou des pickups**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on "drag and drop" un point de pickup (non assigné) sur la carte vers un nouveau point de la carte qui contient déjà un ou plusieurs points de deliveries ou pickups (correspondant à d'autres livraisons)

### Pré-requis: 
Importer une carte et une demande de livraisons. 

### Post-condition: 
Dans le cas où nous deplaçons le point de pickup sur un autre emplacement qui contenait déjà au moins un point de delivery ou pickup, les points de deliveries/pickups qui étaient déjà là ne sont pas modifiés et notre point glissé est correctement ajouté au tas de points.

### Réponse attendue:
La verification peut se faire sur l'IHM à la fois en regardant la carte mais aussi le nouvel ID assigné correspond à celui du nouveau point, et en regardant qu'après qu'il a été assignée à un livreur, celui-ci le parcours. Sur la carte, dans le cas où il y a plusieurs deliviries/pickups sur le même point, on le voit clairement quand on zoome.

---

## **Test 9 : Déplacer un point de pickup ou delivery déjà assigné**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on essaie de "drag and drop" un point de pickup ou delivery d'une livraison déjà assignée.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner à un livreur la livraison des points à déplacer.

### Post-condition: 
Le point n'a pas été déplacé.

### Réponse attendue:
Le point ne peut pas être déplacé donc il n'est pas déplacé sur la carte et son id dans la liste de droite ne change pas.

---

## **Test 10 : Déplacer l'entrepôt**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester le cas où l'on essaie de "drag and drop" l'entrepôt sur la carte.

### Pré-requis: 
Importer une carte et une demande de livraisons.

### Post-condition: 
Le point n'a pas été déplacé.

### Réponse attendue:
Le point ne peut pas être déplacé donc il n'est pas déplacé sur la carte et son id ne change pas.

---

## **Test 11 : Afficher sur la carte la tournée d'un livreur à qui on n'a pas assigné de livraisons**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester l'affichage de la tournée d'un livreur à qui on n'a pas assigné de livraisons

### Pré-requis: 
Importer une carte et une demande de livraisons. Ne pas avoir assigné de livraisons au livreur pour lequel on calcule la tournée.

### Post-condition: 
/

### Réponse attendue:
Message indiquant : "Aucune livraison assignée à ce livreur"

---

## **Test 12 : Afficher sur la carte la tournée d'un livreur à qui on a assigné des livraisons**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester l'affichage de la tournée d'un livreur.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur dont on affiche sa tournée.

### Post-condition: 
Les livraisons ne sont pas modifiées. La tournée du livreur a été modifiée avec le circuit calculé au plus optimal possible.
La tournée commence bien à l'entrepôt, tous les points pickup & delivery des livraisons assignées à ce livreur sont bien parcourus sachant que pour une livraison donnée, le point pickup est bien parcouru avant le point delivery.
La tournée finit à l'entrepôt.

La durée estimée prend bien en compte la vitesse de 15 km/h du cycliste et les temps de livraisons et d'enlèvement.
On a notamment, durée estimée > (distance / vitesse) 

### Réponse attendue:
Affiche la tournée sur la carte, la distance totale parcourue ainsi que la durée estimée du parcours.

---

## **Test 13 : Afficher une tournée sur la carte après avoir affiché une autre tournée**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Tester l'affichage de la tournée d'un livreur après avoir affiché une autre tournée.
Cette autre tournée peut être juste le fait d'avoir assigné ou déssasigné une livraison au livreur.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur et afficher sa tournée. Puis, afficher une autre tournée.

### Réponse attendue:
Affiche la nouvelle tournée sur la carte et efface toute trace de l'ancienne tournée.

---

## **Test 14 : Afficher l'animation d'une tournée sur la carte en cliquant sur le bouton 'play' **

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Afficher l'animation d'une tournée sur la carte en cliquant sur le bouton 'play'

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur et afficher sa tournée. Cliquer sur play.

### Réponse attendue:
Affiche l'animation de la tournée sur la carte (animation du chemin parcouru par le livreur au cours de sa tournée)

---

## **Test 15 : "Appuyer sur le bouton 'play' pour afficher l'animation d'une tournée alors qu'aucune tournée n'a été affichée**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Cliquer sur "play" sans avoir affiché une tournée.
Deux variantes:
- sans avoir importé de demande de livraisons
- après avoir importé une demande de livraisons

### Pré-requis: 

Ne pas avoir affiché de tournée.

### Réponse attendue:

Message indiquant "Aucun circuit disponible pour jouer"

---

## **Test 16 : Déplacer un point pickup ou delivery  (assigné ou non assigné) sur la carte pendant l'animation d'une tournée ('play') **

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Déplacer un point pickup ou delivery (assigné ou non assigné) sur la carte pendant l'animation d'une tournée ('play')

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons au livreur et afficher sa tournée. Cliquer sur play.

### Post-condition: 
Le point n'a pas été déplacé.

### Réponse attendue:
Affiche l'animation de la tournée sur la carte (animation du chemin parcouru par le livreur au cours de sa tournée).
Le point ne peut pas être déplacé donc il n'est pas déplacé sur la carte et son id ne change pas.

---

## **Test 17 : Déplacer un point de delivery non assigné sur un point de la carte vide (ne contient ni pickup ni delivery), après avoir affiché une tournée **

Idem que test 5 mais après avoir affiché une tournée ou après la fin de l'animation du play

---

## **Test 18 : Déplacer un point de delivery non assigné sur un point contenant déjà une ou des deliveries et/ou une ou des pickups, après avoir affiché une tournée ****

Idem que test 6 mais après avoir affiché une tournée ou après la fin de l'animation du play

---

## **Test 19 : Déplacer un point de pickup non assigné sur un point de la carte vide (ne contient ni pickup ni delivery), après avoir affiché une tournée **

Idem que test 7 mais après avoir affiché une tournée ou après la fin de l'animation du play

---

## **Test 20 : Déplacer un point de pickup non assigné sur un point contenant déjà une ou des deliveries et/ou une ou des pickups, après avoir affiché une tournée **

Idem que test 8 mais après avoir affiché une tournée ou après la fin de l'animation du play

---

## **Test 21 : Déplacer un point de pickup ou delivery déjà assigné, après avoir affiché une tournée **

Idem que test 9 mais après avoir affiché une tournée ou après la fin de l'animation du play

---

## **Test 22 : Tenter d'importer une demande de livraison sans avoir importé de carte**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)

### Description  
Importer une demande de livraison sans avoir importé de carte préalablement

### Pré-requis: 
Ne pas avoir déjà importé une carte.

### Réponse attendue:
Le bouton d'import de demande est grisé et non cliquable donc il est impossible d'importer une demande de livraison sans avoir importé de carte (permet d'éviter de nombreux problèmes)

---

## **Test 23 : Importer une nouvelle carte**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)
- Postman: [Lien 3 - Test pour l'ensemble des livreurs](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-5a3ed576-74a1-4659-bad7-30e8b4a7e062?action=share&creator=40453978&ctx=documentation) pour tester si le BACK a été réinitialisé.

### Description  
Importer une nouvelle carte alors qu'il y a déjà des livraisons assignées.

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons et en laisser d'autres non-assignées.

### Réponse attendue:
La nouvelle carte se charge et tout est remis à zero (demande à re-importer, livraisons à re-assigner et tournées à re-calculer).
```
{
SAAD
}
```
---

## **Test 24 :Importer de nouvelles demandes de livraisons**

### Interfaces utilisées:
- IHM (pour l'importation et déplacement)
- Postman: [Lien 3 - Test pour l'ensemble des livreurs](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-5a3ed576-74a1-4659-bad7-30e8b4a7e062?action=share&creator=40453978&ctx=documentation) pour tester si le BACK a été réinitialisé.

### Description  
Importer une nouvelle demande de livraisons alors qu'il y a déjà des livraisons assignées.
Trois variantes:
- sans avoir affiché de tournée
- Après avoir affiché une tournée
- Après avoir cliqué sur "play" pour une tournée 

### Pré-requis: 
Importer une carte et une demande de livraisons. Assigner des livraisons et en laisser d'autres non-assignées.

### Réponse attendue:
La demande se charge et tout est remis à zero (livraisons à re-assigner et la carte affiche seulement les nouveaux points de pickup et delivery).
```
{
SAAD
}
```
---

## **Test 25 : Assigner un livreur à une livraison**

### Interfaces utilisées:
- IHM (pour l'importation)
- Postman: [Lien 4](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-339b05fe-e0e9-4689-99fc-c61ce75845f6?action=share&creator=40455199&ctx=documentation) à utiliser pour ce test.

### Description  
Test d'assignation d'un livreur à une livraison

### Pré-requis: 
Avoir chargé une carte et avoir chargé la livraison "demandePetit1.xml"

### Réponse attendue:
'''
Livraison assignée avec succès
'''

---

## **Test 26 : Désassigner un livreur à sa livraison**

### Interfaces utilisées:
- Postman: [Lien 5](https://pldagile.postman.co/workspace/PLD_AGILE-Workspace~3bd4dbed-30d6-4a78-840f-6fb5cfd48a2b/request/40136526-9ed198ac-79bd-45a6-9549-ea750636a5ea?action=share&creator=40455199&ctx=documentation) à utiliser pour ce test.

### Description  
Test de désassignation d'un livreur pour une de ses livraisons.

### Pré-requis: 
Avoir effectué le Test précédent : Assigner un livreur à une livraison

### Réponse attendue:
'''
Livraison désassignée avec succès
'''

---

## **Test 27 : Modifier les livraisons assignées à un livreur puis réafficher sa tournée **

### Interfaces utilisées:
- IHM 

### Description  
Test de recalcule et de réaffichage du trajet sur la carte.

Plusieurs variantes :
- avoir désassigné une livraison
- avoir assigné une nouvelle livraison

Puis réaffichage de la tournée

### Pré-requis:
Avoir chargé une carte et des livraisons.
Avoir assigné des livraisons à un livreur puis avoir calculé son trajet et avoir visualisé son chemin avec le bouton "Play".

### Réponse attendue:

La tournée affichée correspond bien aux nouvelles modifications effectuées.

---

## **Test 28 : Actualiser/Recharger la page**

### Interfaces utilisées:
- IHM 

### Description  
Test de la réinitialisation totale de la page (carte, demande de livraisons, etc.) lors de son rafraichissement (f5)

### Pré-requis:
Avoir chargé une carte et des livraisons.<br/>
Avoir assigné des livraisons à un livreur.

### Réponse attendue:
La page est totalement réinitialisée (carte, demande de livraisons, etc.)

---
