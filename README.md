# PLD Agile

## Install Dependencies

1. [`Install`](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm) **npm**

2. [`Install`](https://nodejs.org/fr/download/package-manager) **Node.js** 
 18.18 or later 

3.  [`Install`](https://www.oracle.com/java/technologies/downloads/#java21) **Java 21**

4. [`Download`](https://maven.apache.org/download.cgi) and [`Install`](https://maven.apache.org/install.html) **Maven**

## Web app (FrontEnd)

The project uses [react](https://react.dev/) and [Next.js](https://nextjs.org/) and is located in the `pickup-and-delivery-front/` directory.

This project uses [Ant-Design](https://ant.design/components/overview/) for its ui components

### Launch the FrontEnd

1. Go to the directory `pickup-and-delivery-front/`

2. Open a terminal and execute : 
    ```bash
    npm install
    ```

3. Then, in the same terminal, execute :
    ```bash
    npm run dev
    ```
The app will be accessible on http://localhost:3000.

## Backend

The project uses [java21](https://www.java.com), [Spring Boot](https://spring.io/projects/spring-boot) and [Maven](https://maven.apache.org/index.html) and is located in the `pickupAndDeliveryBack/` directory.

### Launch the BackEnd

1. Go to the directory `pickupAndDeliveryBack/`

2. Open a terminal and execute : 

    ```bash
    mvn spring-boot:run
    ```

The app will be accessible on http://localhost:8080.

## File system organization

- **"Documents"**: the collection of all project-related documents and reports.
   - ***"H4301_Rapport_PLD_AGILE.pdf"***: the project report, including the human assessment, design document, and unfinished but considered functionalities.```
   - **"Package_And_Class_Diagrams"**: the package and class diagrams.
   - **"Project Management"**: a link to the Excel file describing project workload distribution and task allocation.
   - **"UseCases"**: the use-case diagram, their descriptions, and the persona.
   - ***"glossaire.md"***: a glossary defining the terms used.
   - **"Maquettes_IHM"**: a link to the Figma mockups for the user interface.
   - **"Tests"**: all UI and Postman tests designed.
      - ***"How_To_Use_POSTMAN.md"***: a document describing how to use POSTMAN.
      - **"Data"**: all tests for the Data class.
      - **"Deliveries"**: all tests and files for deliveries and their import.
      - **"Map"**: all tests and files for maps and their import.
      - **"Tournees"**: all tests for the export, calculation, and display of tours, as well as modifications to deliveries.

- **"fichiersXMLPickupDelivery"**: the collection of request and plan XML files used.
- **"pickupAndDeliveryBack"**: the backend written in Java.
- **"pickup-and-delivery-front"**: the frontend written in React.
- **"Sujet"**: the subject description of the PLD AGILE project.
- ***"README"***: this document in English.
