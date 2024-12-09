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

## Détails sur des choix de conception

### Classe Data
Afin d'accéder et stocker les données pertinentes (plan de livraison, carte, entrepot...) et pouvoir y accéder sur n'importe quel fichier du BACK, nous avons décidé de créer la classe Data. Elle est une classe à attributs statiques:

* ####  planVille: 
  C'est un Plan chargé d'un fichier XMl avec tous ses segments (routes avec un nom, une longueur et les IDs des points aux extremités) et ses points qui connectent les segments (avec des coordonnées un ID et un type). Le type d'un point indique son rôle: un entrepôt, un lieu de livraison ou destination ou une simple intersection de segments.
* #### livraisonsDues
  Une liste de Livraison, soit le plan de livraisons à réaliser. Chaque livraison décrit la durée d'enlèvement et livraison prevue tout comme les points correspondants (leurs ids plutôt).
* #### tourneesPrevues
  Une lsite des Tournee, soit des tournées réalisées par les livreurs. Une Tournee contient les livraisons à réaliser par le livreur et le circuit de segments le plus optimal pris.
* #### idEntrepot
  L'id du point qui correspond à l'Entrepot de la carte pour le plan de livraisons chargé.

### Factories
Certains objects sont générés dans Data à partir du parse d'un fichier XML. Ce parse est un objet JSON avec une structure différente selon le format du fichier lu. Leur création étant plus complexe que pour d'autres classes et afin de tenir en compte le format du fichier XML, nous avons décidé de créer des Factories pour eux.<br/>
De telles classes sont Plan et Livraison pour les livraisonsDues.

### DTOs: Data Transfer Objects
Nous transformons tout object envoyé vers le front en une DTO, exemple: PlanDTO lors de l'envoie de la carte chargée. <br/> Ces classes sont sérialisables et peuvent donc être transformées en JSON directement sans action suplémentaire de notre part lors de l'envoie.<br/> Ce choix permet de différencier ce qui est envoyé des données en soi et donc de le changer si jamais nous y aurions bésoin.

## Diagramme de Classes

![Diagramme de classes MVC](./ClassDiagramm.svg)

<div style="page-break-after: always"></div>



