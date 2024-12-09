# Documentation : Comment Utiliser Postman pour Tester les Requêtes API

---

## **1. Introduction à Postman**

**Postman** est un outil permettant de tester les API en envoyant des requêtes HTTP et en inspectant les réponses. Il facilite le développement et le débogage des applications en simulant des requêtes complexes et en automatisant les tests.

---

## **2. Installation de Postman**

1. **Télécharger Postman :**  
   Rendez-vous sur [https://www.postman.com/downloads/](https://www.postman.com/downloads/) et téléchargez la version adaptée à votre système d'exploitation (Windows, MacOS ou Linux).

2. **Installer Postman :**  
   Suivez les instructions d’installation pour votre système.

3. **Lancer Postman :**  
   Une fois installé, ouvrez Postman pour commencer à l'utiliser.

---

## **3. Interface de Postman**

### **Principaux Éléments de l’Interface**

- **Collections :**  
  Permettent d’organiser vos requêtes dans des dossiers.

- **Nouvelle Requête :**  
  Bouton **"New"** pour créer une nouvelle requête HTTP.

- **Paramètres de la Requête :**  
  - **URL :** La barre où vous entrez l’URL de l’API.  
  - **Méthode :** GET, POST, PUT, DELETE, etc.  
  - **Headers :** Pour ajouter des en-têtes à la requête.  
  - **Body :** Pour ajouter des données à la requête (par exemple, un fichier pour les tests).  

- **Bouton Send :**  
  Envoie la requête au serveur.

- **Section de Résultat :**  
  Affiche la réponse du serveur (statut HTTP, contenu de la réponse, etc.).

---

## **4. Créer et Envoyer une Requête**

### **Étapes pour Envoyer une Requête POST**

1. **Créer une Nouvelle Requête :**  
   - Cliquez sur le bouton **"New"** puis choisissez **"Request"**.  
   - Donnez un nom à la requête et enregistrez-la dans une collection.

2. **Définir la Méthode et l’URL :**  
   - Sélectionnez la méthode `POST` dans le menu déroulant.  
   - Entrez l’URL de l'API, par exemple :  
     ```
     http://localhost:8080/api/map/upload-xml
     ```

3. **Configurer le Body :**  
   - Allez dans l’onglet **"Body"**.  
   - Sélectionnez **"form-data"**.  
   - Ajoutez une clé **"file"** et choisissez le fichier à télécharger via le bouton **"Select File"**.

4. **Ajouter des Headers (si nécessaire) :**  
   - Par exemple, si l’API nécessite le type `multipart/form-data`, il est souvent ajouté automatiquement. Sinon, ajoutez le header :  
     ```
     Content-Type : multipart/form-data
     ```

5. **Envoyer la Requête :**  
   - Cliquez sur le bouton **"Send"** pour envoyer la requête.

6. **Analyser la Réponse :**  
   - La réponse apparaîtra dans la section inférieure. Vous verrez le **statut HTTP** et le **contenu de la réponse**.  
   - Exemple de réponse JSON :  
     ```json
     {
       "error": "JSONObject[\"entrepot\"] not found"
     }
     ```

---

## **5. Exemples de Requêtes**

### **Requête POST avec Fichier XML**

- **Méthode :** `POST`  
- **URL :** `http://localhost:8080/parseXML`  
- **Body :**  
  - Type : `form-data`  
  - Clé : `file`  
  - Valeur : Sélectionner un fichier `.xml` à partir de votre système.

---

## **6. Sauvegarder et Organiser les Requêtes**

1. **Créer une Collection :**  
   - Cliquez sur **"New"** → **"Collection"**.  
   - Donnez un nom à votre collection, par exemple **"ParseurXML Tests"**.

2. **Ajouter des Requêtes à une Collection :**  
   - Lorsque vous créez ou éditez une requête, cliquez sur **"Save"** et choisissez la collection où vous souhaitez l'enregistrer.

---

## **7. Exécuter les Tests Automatiquement**

1. **Configurer des Tests :**  
   - Allez dans l’onglet **"Tests"** de votre requête.  
   - Ajoutez du code JavaScript pour vérifier les réponses. Exemple :  
     ```javascript
     pm.test("Statut HTTP est 400", function () {
       pm.response.to.have.status(400);
     });

     pm.test("La réponse contient une erreur", function () {
       pm.response.to.have.jsonBody("error");
     });
     ```

2. **Exécuter une Collection :**  
   - Cliquez sur le bouton **"Run"** pour exécuter toutes les requêtes d'une collection automatiquement.

---

## **8. Exporter et Importer des Collections**

### **Exporter une Collection**

1. Cliquez sur les trois points (`...`) à côté de votre collection.  
2. Sélectionnez **"Export"**.  
3. Choisissez le format **JSON** et enregistrez le fichier.

### **Importer une Collection**

1. Cliquez sur **"Import"** en haut à gauche de l’écran.  
2. Sélectionnez le fichier JSON exporté précédemment.  
3. La collection apparaîtra dans votre interface Postman.

---

## **9. Bonnes Pratiques**

1. **Nommer clairement vos requêtes et collections.**  
2. **Ajouter des descriptions détaillées** pour chaque requête.  
3. **Utiliser des variables d’environnement** pour des URLs dynamiques (ex. `{{base_url}}/parseXML`).  
4. **Automatiser les tests** avec des scripts pour éviter des vérifications manuelles.  
5. **Sauvegarder régulièrement** et exporter vos collections pour éviter de perdre vos tests.

---

Avec cette documentation, vous êtes prêt à utiliser Postman pour tester votre API `ParseurXML` efficacement !
