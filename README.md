# PLD Agile

<!--TODO : PRECISER LES CHEMINS SVP-->

## Web app

The project uses [react](https://react.dev/) and [Next.js](https://nextjs.org/) and is located in the `pickup-and-delivery-front` directory.

You need to have [`npm`](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm) installed tu run it. 

This project uses [Ant-Design](https://ant.design/components/overview/) for its ui components

### Launching the project

Before launching the project for the 1st time, run
```bash
npm install
npm install uuid
```

To launch the app, run
```bash
npm run dev
```

Alternatively, you can build and deploy the app with the following commands:
```bash
npm run build
npm run start
```

The app will be accessible on http://localhost:3000.

## Backend

The project uses [java](https://www.java.com) and [Spring Boot](https://spring.io/projects/spring-boot) and is located in the `pickupAndDeliveryBack` directory.

You need to have [`maven`](https://maven.apache.org/install.html) and [`java 21`](https://www.oracle.com/java/technologies/downloads/) installed to run it.

### Launching the project

To launch the app, run
```bash
mvn spring-boot:run
```

The app will be accessible on http://localhost:8080.