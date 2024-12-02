ChronoPostIF - Corentin JEANNE | Diego LARRAZ-MARTIN | Saad ELGHISSASSI | Harold MARTIN | Thomas LEVRARD

---

# Design Document

## Architecture

Notre projet est une application Web et se base de ce fait sur une architecture MVC à 5 couches: 
- Vue
- Contrôleur
- Services
- Modèle
- Données

Nous utilisons REACT avec next.js pour pouvoir communiquer avec le Modèle développé en Java.<br/>
Il faut préciser que REACT + next.js se comporte aussi comme contrôleur, requêtant le BACKEND et changeant le contenu de la VUE.<br/>
De ce fait, nous avons du côté du back un controlleur qui permet de faire le lien entre le controlleur FRONT et le BACK.


### Vue
REACT assimille le rôle de Vue avec une structure modulaire permettant d'ajouter des modules et fonctionalités facilement et rapidement. 
La vue communique avec le Controller BACK qui lui renvoie ce dont elle a besoin.


### Contrôleur
Le contrôleur BACK mappe les requêtes et interroge les services associés pour le calcul. Suite au résultat des services il le renvoie au contrôleur FRONT pour l'affichage.
Il existe un contrôleur par classe service.

<br/>

### Services
Le Controller communique avec le Modèle avec l'intermédaire de la classe Services qui offre les fonctionalités qui lui seraient nécessaires.<br/>
La classe Services s'en charge de réaliser des calculs sur les données.
Il existe une classe service par fonctionalité majeure.

### Modèle
Cette couche répresente le back-end qui va être intérrogé par le Controller pour mettre à jour la Vue.<br/>
Ici sont définies toutes les classes et données en Java. <br/>
Une classe Data contient les données chargées pour le traitement.


### Données
Les données, nottament des livraisons et le plan de la ville, sont stockées sous format XML et seront lues par la classe Services pour constituer le jeu de données (Data) sur lequel elle travaillera.

<div style="page-break-after: always"></div>

## Diagramme de Classes


![Diagramme de classes MVC](./ClassDiagramm.svg)

<div style="page-break-after: always"></div>




