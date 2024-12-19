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