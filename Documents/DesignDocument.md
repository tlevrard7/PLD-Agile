Pickup And Delivery - Corentin JEANNE | Diego LARRAZ-MARTIN | Saad ELGHISSASSI | Harold MARTIN | Thomas LEVRARD

---

# Design Document

## Architecture

Notre projet est une application Web et se base de ce fait sur une architecture MVC. <br/>
Nous utilisons REACT avec node.js pour pouvoir communiquer avec le Modèle développé en Java.<br/>


### Vue et Controlleur
REACT assimille le rôle de Vue et Controlleur avec une structure modulaire permettant d'ajouter des modules et fonctionalités facilement et rapidement.

### Modèle
Cette couche répresente le back-end qui va être intérrogé par le Controlleur pour mettre à jour la Vue.<br/>
Ici sont définies toutes les classes et données en Java. <br/>

- #### Services
Le Controlleur communique avec le Modèle avec l'intermédaire de la classe Services qui offre les fonctionalités qui lui seraient nécessaires.<br/>
La classe Services s'en charge de réaliser des calculs sur les données et d'envoyer ce résultat au Controlleur pour l'affichage.

- #### Données
Les données, nottament des livraisons et le plan de la ville, sont stockées sous format XML et seront lues par la classe Services pour constituer le jeu de données sur lequel elle travaillera.

<div style="page-break-after: always"></div>

## Diagramme de Classes


![Diagramme de classes MVC](./ClassDiagramm.svg)

<div style="page-break-after: always"></div>


## Unit tests

### Application tests

#### example test
><ins>Description:</ins><br>
> miau miau miau

<ins>Pre-condition:</ins>
- woof woof

<ins>Post-condition:</ins>
- squil squik

<ins>Request:</ins>
```console
X:~$ skadooosh!
```

<ins>Output:</ins>
```console
boom!
```


