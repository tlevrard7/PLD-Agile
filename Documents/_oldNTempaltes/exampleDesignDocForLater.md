Pickup And Delivery - Corentin JEANNE | Diego LARRAZ-MARTIN | Saad ELGHISSASSI | Harold MARTIN | Thomas LEVRARD

---

# Design Document

## Architecture

Notre projet est une application Web et se base de ce fait sur une architecture MVC. <br/>
Nous utilisons REACT avec node.js pour pouvoir communiquer avec le Modèle développé en Java.<br/>


### Vue et Controlleur
REACT assimille le rôle de Vue et Controlleur avec une structure modulaire permettant d'ajouter des modules et fonctionalités facilement et rapidement..

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


![Diagramme de classes MVC](./ClassDiagrams/ClassDiagramm.svg)

<div style="page-break-after: always"></div>

## Sequence diagrams

**to come...**
![alt text]()

<div style="page-break-after: always"></div>



## Description et pseudo-code des algorithmes principaux

### **to come...**

The `getMeasurements` algorithm takes as input several optional filtering criteria such as sensor, provider, time range, geographical location, and radius around that location. It then iterates through each sensor and measurements of these sensors and applies the specified filters, retaining only those that satisfy all given criteria.

**Inputs:**

- `latitude` (Decimal number): The latitude of the central geographical position for location-based filtering (optional).
- `longitude` (Decimal number): The longitude of the central geographical position for location-based filtering (optional).
- `radius` (Decimal number): The radius around the central geographical position for location-based filtering (optional).
- `from` (Date): The start date of the time range of measurements to include (optional).
- `to` (Date): The end date of the time range of measurements to include (optional).
- `sensor` (Sensor): The sensor whose measurements only need to be included (optional).

**Output:**

- `results` (List of measurements): The filtered list of measurements that satisfy all specified criteria.
  
**Note:**

- Several signature variants are available to facilitate the use of filters in all situations.
- It doesn't take into account measurements from a blacklisted provider. 
- It includes the ability to reward a user when one or more measurements from his sensors appear in the final list of measurements. A blacklisted provider cannot earn points.
- If latitude, longitude and radius are entered, the measurements of the sensors in the zone are kept.
- If only latitude and longitude are entered, sensor measurements at the specified location only are retained. 
- If a time interval is specified, only measurements taken during this interval are retained.
- If only a start date is specified, only measurements taken at that time are retained.


This algorithm is useful in many of the application's functions. It centralizes the filtering and retrieval of measurements in a single function. It must be the only query point to ensure data consistency and user reward.

**Complexity:** The algorithm has a time complexity of O(n + p) where n is the number of sensors in the database and p is the number of measurements in the database. The algorithm iterates through all measurements and applies the specified filters to each one. Generally, there are many more measurements than sensors, thus taking a mean complexity of O(p).

![alt text](images/getMeasurements.png)

<div style="page-break-after: always"></div>

### Get Ranking

This algorithm is used in the  `rank <id> [-l <limit>]`.

The `getRanking` algorithm computes a ranking of sensors based on their similarity to a base sensor, using a specified limit if provided. The similarity between the sensors is calcultated with a Pearson Correlation. If a limit is provided, sensors with a similarity score greater than or equal to the limit are added to the ranking. Finally, the ranking is sorted by similarity and distance to the base sensor and returned.

**Inputs:**

- `base_sensor` (Sensor): The reference sensor for comparison.
- `limit` (Decimal number): The similarity limit for including sensors in the ranking (optional).

**Output:**

- `ranking` (List of Rank): The ranked list of sensors and their similarity scores and distance to the base sensor.

**Note:**

- It utilizes the getMeasurements function to retrieve measurements for each sensor.
- The computeSimilarity function calculates the similarity between the base sensor and each other sensor based on the retrieved measurements. It calculates the Pearson Correlation between the two sensors' measurements for each attribute.
-  The ranking is sorted by similarity in descending order.
- The similarity score is calculated based on the mean of correlation coefficient of each attribute.

**Extract from Requirements Specifications :**

Let $M1_{A}$ and $M2_{A}$ be the random variables of the measures of a $sensor_1$ and $sensor_2$ respectively, and of an attribute A,
**similarity** is calculted by comparing the mean correlation between each set of measures $M1_{A}$ and $M2_{A}$,  $∀A ∈ \{O_{2}, SO_{2}, NO_{2}, PM10,...\}$  using Bravais-Pearson's linear correlation cohefficient:
$$r_A = \frac{COV(M1_{A},M2_{A})}{σ_{1A}σ_{2A}}$$
With $σ_{1A}$ and $σ_{2A}$ being the standard deviation of variables $M1_{A}$ and $M2_{A}$ respectively.
- Let $n_{iA}$ be the number of measures of attribute A for $sensor_i$ and $mi_{kA}$ his $k^{th}$ measure of attribute A. 
- Let also $\overline{m_{iA}}$ be the mean value of all measures of attribute A from $sensor_i$<br>

$$ COV(M1_{A},M2_{A}) = \frac{\sum_{j=1}^{n_{1A}}\sum_{k=1}^{n_{2A}} (m1_{jA} - \overline{m_{1A}})(m2_{kA} - \overline{m_{2A}})  - \left( \sum_{k=1}^{n_{1A}} (m1_{kA} - \overline{m_{1A}}) \right) \left( \sum_{k=1}^{n_{2A}} (m2_{kA} - \overline{m_{2A}}) \right)}{n_{1A}n_{2A}} $$<br>

$σ_{iA}= \sqrt{\frac{1}{n_{iA}}\sum_{k=1}^{n_{iA}} (mi_{kA}  - \overline{m_{iA}})^2}$ <br>

And the mean correlation
$$r = \frac{1}{|Attributes|}\sum_{A ∈ Attributes} r_A = \frac{r_{O_2} + r_{SO_2} + r_{NO_2} + r_{PM10}}{4}$$  

>**interpretation**  <br>
>Our hypothesis is: when 2 sensors are similar they posses similar or equal tendencies, because their area has the same air quality or because they are close to each other. As such, if a sensor gives values in a certain range, the other should also give them in said range. And in case of the measures of the first sensor evolving (either incrementing or decrementing) then the measures of the second sensor should do the same if they really are similar.
>
>The linear correlation cohefficient $r_A ∈ [-1, 1]$ determines if $M1_{A}$ and $M2_{A}$ have a linear relation of the type $M1_{A}$ = $aM2_{A} + b$ with $(a,b) ∈ \real^2$. Or in other terms, it determines how $M1_{A}$ evolves in relation to $M2_{A}$. <br> There are 3 general cases: 
> - $r_A$ is close to -1 => $M1_{A}$ and $M2_{A}$ have an inverse linear realtion with a < 0. When $M1_{A}$ increments, $M2_{A}$ decrements. The 2 sensors give completely different measures and aren't similar at all.
>
> - $r_A$ is close to 0 => $M1_{A}$ and $M2_{A}$ have no linear relation, they aren't opposed, but they are also not very similar.
>
> - $r_A$ is close to 1 => $M1_{A}$ and $M2_{A}$ have a direct linear realtion with a > 0. When $M1_{A}$ increments, $M2_{A}$ increments. The 2 sensors are very similar.

In order to be interpretable, similarity is scored from 0 (very different) to 10 (nearly identical) as such:
$$ S = 10*\frac{1+r}{2} $$

With $S$ being the similarity score (no unit) 

**Complexity:** The algorithm has a general time complexity of O(p) where p is the number of measurements in the database. The computeSimilarity function calculates the similarity between the base sensor and each other sensor based on the retrieved measurements.

![alt text](images/getRanking.png)

<div style="page-break-after: always"></div>

### Show Impact of a Cleaner

This algorithm is used in the `cleaner [<id>]`

The `getCleanerImpact` algorithm estimates the effectiveness of a cleaner device's operation at a given location and time range. It iterates over all sensors while keeping track of the unaffected (maximum) and affected radius. It then retrieves past, current, and future measurements within the estimated radius and time frame of the cleaner's operation. The Air Quality Index (ATMO) is computed for each set of measurements, and a score is calculated based on the difference in ATMO values between the current and past measurements and the radius.

**Inputs:**

- `sensors` (List of sensors): List of all sensors.
- `cleaner` (Cleaner): The cleaner device.

**Output:**

- `estimatedRadius` (int) : The estimated radius in kilometers of cleaner impact.
- `score` (int) : The score represents the improvement in air quality achieved by the cleaner, normalized to a range between -10 and 10.
- `pastATMO` (int) : ATMO score before the action of the cleaner (for a period equal to its operating time)
- `currentATMO` (int) : ATMO score during the action of the cleaner (for a period equal to its operating time)
- `postATMO` (int) : ATMO score after the action of the cleaner (for a period equal to its operating time)

**Extract from Requirements Specifications :**

<ins>Definition :</ins> The **impact** of an air cleaner is measured by calculating the Air Quality in the area of effect of the air cleaner. Every cleaner has a radius, which is the furthest point in which the air cleaner impacts the air quality. This air quality is then compared to the air quality in the area before its launch. We determine this area using the estimated radius of impact. This radius corresponds to the largest area in which air cleaner has improved the ATMO score. It starts at 0 and increases when we found a sensor affected by the cleaner. It will also always be less than the distance to the closest unaffected sensor.

$$ I = (A_1-At_2) * radius $$
$I$ : impact of the air cleaner (no unit)\
$A_1$ : air quality of an area during its launch (no unit)\
$A_2$ : air quality of an area before its launch (no unit)

**Note:**

- It utilizes the getMeasurements function to retrieve measurements within specified parameters.
- The computeATMO function calculates the Air Quality Index (ATMO) based on the retrieved measurements. It calculates the average of each indicator with the given measurements and establishes the ATMO score with these averages as defined in the Requirements specification document.

**Complexity:** The algorithm has a time complexity of O(n) where n is the number of measurements in the database. The getMeasurements function retrieves measurements within the specified parameters. It's possible to iterate over all measurements in the database.

![alt text](images/getCleanerImpact.png)

**Here are a few equivalents to simplify notation.**

![alt text](images/getCleanerImpact2.png)

<div style="page-break-after: always"></div>

## Unit tests

### Application tests

#### The datapath is incorrect
><ins>Description:</ins><br>
> We shall launch the application with a wrong datapath argument.

<ins>Pre-condition:</ins>
- The datapath argument is incorrect (does not lead to a valid directory).

<ins>Post-condition:</ins>
- The application should return an error message and exit.

<ins>Request:</ins>
```console
X:~$ airWatcher ./fakedata agency password
```

<ins>Output:</ins>
```console
An error occured : No file was found at ./fakedata/*.csv
Exiting...
```

#### Username or password is incorrect
><ins>Description:</ins><br>
> We shall launch the application with a wrong username or password.

<ins>Pre-condition:</ins>
- The username or password argument is incorrect (does not match with a valid user).

<ins>Post-condition:</ins>
- The application should return an error message and exit.

<ins>Request:</ins>
```console
X:~$ airWatcher ../data fakeusername fakepassword
```

<ins>Output:</ins>
```console
An error occured : Invalid username or password.
Exiting...
```

#### Data provided does not match the expected format
><ins>Description:</ins><br>
> We shall launch the application with a mistaken data set.

<ins>Pre-condition:</ins>
> The data set provided does not match the expected format. There is an unexpected character in the data set.
> Exemple : 2019-01-03 fake12:00:00;Sensor0;O3;43.75;

<ins>Post-condition:</ins>
> The application should return an error message and exit.

<ins>Request:</ins>
```console
X:~$ airWatcher ./data agency password
```

<ins>Output:</ins><br>
```console
An error occured : string doesn't match with the regex
AW >> Exiting...
```

#### Load of the data set must be quick
><ins>Description:</ins><br>
> We shall launch the application with the provided data set and the time of loading must be quick.

<ins>Pre-condition:</ins>
- The data set used corresponds to the one provided.

<ins>Post-condition:</ins>
- The loading time of the data set must be less than 3 seconds.
- If the loading time is greater than 3 seconds, the test is considered failed.

<ins>Request:</ins>
```console
X:~$ airWatcher ./data agency password
```

<ins>Output:</ins>
```console
Application ready to use.
```

#### No parameters given
><ins>Description:</ins><br>
> We shall launch the application without any parameters.

<ins>Pre-condition:</ins>
- No parameters are given.

<ins>Post-condition:</ins>
- The application should return an error message and exit.

<ins>Request:</ins>
```console
X:~$ airWatcher
```

<ins>Output:</ins>
```console
Invalid command usage
```

#### Classic load and quit
><ins>Description:</ins><br>
> We shall launch the application with the provided data set and quit it.

<ins>Pre-condition:</ins>
- The data set used corresponds to the one provided.

<ins>Post-condition:</ins>
- The application should return a message and exit.

<ins>Request:</ins>
```console
X:~$ airWatcher ./data agency password
AW >> quit
```

<ins>Output:</ins>
```console
Welcome to Air Watcher. Type "help" to display available commands
AW >> Exiting...
```

#### A sensor doesn't exist
><ins>Description:</ins><br>
> We shall try to load a data set in which a sensor doesn't exist.

<ins>Pre-condition:</ins>
- The data set used contains a user linked to a sensor that doesn't exist.

<ins>Post-condition:</ins>
- The application should return a message and exit.

<ins>Request:</ins>
```console
X:~$ airWatcher ./data agency password
```

<ins>Output:</ins>
```console
An error occured : the sensor does not exist
AW >> Exiting...
```

### Data set

#### <ins>Attributes:</ins><br> 
> ID | Unit | Description
> --- | --- | ---
>O3 | µg/m3 | concentration d'ozone
>SO2 | µg/m3 | concentration de dioxyde de soufre
>NO2 | µg/m3 | concentration de dioxyde d'azote
>PM10 | µg/m3 | concentration de particules fines 

####  <ins>Cleaners:</ins><br> 
> ID | Latitude | Longitude | Start | End
> --- | --- | --- | --- | --- 
> Cleaner0 | 45.3 | 1.3 | 2019-02-01 12:00:00:00 | 2019-03-01 00:00:00 
> Cleaner1 | 46.6 | 3.6 | 2019-02-01 12:00:00:00 | 2019-03-01 00:00:00 
> Cleaner2 | 47.9 | 5.9 | 2019-02-01 12:00:00:00 | 2019-03-01 00:00:00
> Cleaner3 | 48.2 | 6.2 | 2019-02-01 12:00:00:00 | 2019-03-01 00:00:00

#### <ins>Measurements:</ins><br> 
> Timestamp | Sensor | Attribute | Value
> --- | --- | --- | ---
> 01/01/2019 12:00:00	| Sensor0 | O3 |	10.25
> 01/01/2019 12:00:00	| Sensor0 | SO2 |	32.15
> 01/01/2019 12:00:00	| Sensor0 | NO2 |	42.12
> 01/01/2019 12:00:00	| Sensor0 | PM10 |	13.10
> 17/01/2019 12:00:00	| Sensor2 | O3 |	75.5
> 17/01/2019 12:00:00	| Sensor2 | SO2 |	80.16
> 17/01/2019 12:00:00	| Sensor2 | NO2 |	27.5
> 17/01/2019 12:00:00	| Sensor2 | PM10 |	73.2
> 10/02/2019 12:00:00	| Sensor0	| O3	| 30.26
> 10/02/2019 12:00:00	| Sensor0	| SO2	| 40.35
> 10/02/2019 12:00:00	| Sensor0	| NO2	| 52.15
> 10/02/2019 12:00:00	| Sensor0	| PM10	| 60.97
> 12/02/2019 12:00:00	| Sensor3	| O3	| 70.5
> 12/02/2019 12:00:00	| Sensor3	| SO2	| 75.42
> 12/02/2019 12:00:00	| Sensor3	| NO2	| 27.3
> 12/02/2019 12:00:00	| Sensor3	| PM10	| 56.2
> 15/02/2019 12:00:00	| Sensor4 | O3 |	259.12
> 15/02/2019 12:00:00	| Sensor4 | SO2 |	12.5
> 15/02/2019 12:00:00	| Sensor4 | NO2 |	13.1
> 15/02/2019 23:30:00	| Sensor4 | PM10 |	21.8
> 13/03/2019 12:00:00	| Sensor1	| O3	| 51.2
> 13/03/2019 12:00:00	| Sensor1	| SO2	| 24.6
> 13/03/2019 12:00:00	| Sensor1	| NO2	| 73.4
> 13/03/2019 12:00:00	| Sensor1	| PM10	| 45.75

<ins>Providers:</ins><br>
> ID | Cleaner 
> --- | ---
> Provider0 | Cleaner0
> Provider1 | Cleaner1
> Provider2 | Cleaner2
> Provider2 | Cleaner3

#### <ins>Sensors:</ins><br> 
> ID | Latitude | Longitude
> --- | --- | --- 
> Sensor0 | 44 | -1 
> Sensor1 | 44 | 0.1
> Sensor2 | 44 | 0.3 
> Sensor3 | 44 | 5.1 
> Sensor4 | 44 | 10.8
> Sensor5 | 44 | 22.5

#### <ins>Users:</ins><br> 
> ID | Sensor | Blacklisted
> --- | --- | ---
> User0 | Sensor0 | false
> User0 | Sensor1 | false
> User1 | Sensor2 | false
> User2 | Sensor3 | true
> User3 | Sensor4 | false
> User4 | Sensor5 | true

### Tests for getMeasurements

These are all the unit tests to assure the method getMeasurements functions correctly and to know if the application's behavior is expected.

#### (1) The measurements given are correct for a given timestap
><ins>Description:</ins><br> 
We shall try to obtain all measurements saved during the period of time from `10/02/2019 at 00:00:00` to `15/02/2019 at 22:00:00`

<ins>Pre-condition:</ins><br>
The time stamp must be correct (represents a past period of time, from < to) and there must be measures during that period to test.

<ins>Post-conditions:</ins><br>
No measures from blacklisted sources are provided.

<ins>Request:</ins><br>
```js
AW >> stats -t 2019-02-10_00:00:00 2019-02-15_22:00:00
```

<ins>Output:</ins><br>
> Timestamp | Sensor | Attribute | Value
> --- | --- | --- | ---
> 10/02/2019 12:00:00	| Sensor0	| O3	| 30.26
> 10/02/2019 12:00:00	| Sensor0	| SO2	| 40.35 
> 10/02/2019 12:00:00	| Sensor0	| NO2	| 52.15 
> 10/02/2019 12:00:00	| Sensor0	| PM10	| 60.97 
> 15/02/2019 12:00:00	| Sensor4 | O3 |	259.12 
> 15/02/2019 12:00:00	| Sensor4 | SO2 |	12.5   
> 15/02/2019 12:00:00	| Sensor4 | NO2 |	13.1   

#### (2) sensor doesn't exist and radius is undefined
><ins>Description:</ins><br> We shall try to obtain the measurements of a sensor at the coordinates `(44.0, 0.2)`. There is however no sensor in that area and no radius was given for exploration. We should obtain no measurements.

<ins>Pre-conditions:</ins><br>
There is no sensor in the data base with the exact given coordinates.

<ins>Post-conditions:</ins><br>
No measures from blacklisted sources are provided.

<ins>Request:</ins><br>
```js
AW >>  stats 44.0 0.2
```

<ins>Output:</ins><br>
> Timestamp | Sensor | Attribute | Value
> --- | --- | --- | ---

#### (3) radius > 0
><ins>Description:</ins><br> We shall try to obtain the measurements of the sensors near coordinates `(44.0, 0.2)`, at a radius of 140km from it. 

<ins>Pre-conditions:</ins><br>
There are sensors near the coordinates at the given radius (at least 2).

<ins>Post-conditions:</ins><br>
No measures from blacklisted sources are provided.

<ins>Request:</ins><br>
```js
AW >>  stats 44.0 0.2 -r 140
```

<ins>Output:</ins><br>
> Timestamp | Sensor | Attribute | Value
> --- | --- | --- | ---
> 01/01/2019 12:00:00	| Sensor0 | O3 |	10.25 
> 01/01/2019 12:00:00	| Sensor0 | SO2 |	32.15   
> 01/01/2019 12:00:00	| Sensor0 | NO2 |	42.12 
> 01/01/2019 12:00:00	| Sensor0 | PM10 |	13.10   
> 10/02/2019 12:00:00	| Sensor0	| O3	| 30.26 
> 10/02/2019 12:00:00	| Sensor0	| SO2	| 40.35 
> 10/02/2019 12:00:00	| Sensor0	| NO2	| 52.15 
> 10/02/2019 12:00:00	| Sensor0	| PM10	| 60.97 
> 13/03/2019 12:00:00	| Sensor1	| O3	| 51.2  
> 13/03/2019 12:00:00	| Sensor1	| SO2	| 24.6  
> 13/03/2019 12:00:00	| Sensor1	| NO2	| 73.4  
> 13/03/2019 12:00:00	| Sensor1	| PM10	| 45.75 
> 17/01/2019 12:00:00	| Sensor2 | O3 |	75.5    
> 17/01/2019 12:00:00	| Sensor2 | SO2 |	80.16   
> 17/01/2019 12:00:00	| Sensor2 | NO2 |	27.5    
> 17/01/2019 12:00:00	| Sensor2 | PM10 |	73.2   

#### (4) radius > 0 and a timestamp was given
><ins>Description:</ins><br> We shall try to obtain the measurements of the sensors near coordinates `(44.0, 0.2)`, at a radius of 140km from it, saved during the period of time from `10/01/2019 at 11:47:02` to `15/02/2019 at 22:00:00`. 

<ins>Pre-conditions:</ins><br>
- The time stamp must be correct (represents a past period of time, from < to) and there must be measures during that period to test.
- There are sensors near the coordinates at the given radius (at least 1) and said sensors must have measurements during the given period of time.

<ins>Post-conditions:</ins><br>
- No measures from blacklisted sources are provided.
The results are given quickly. 
- The duration of the test is limited to 0.5 seconds.

<ins>Request:</ins><br>
```js
AW >>  stats 44.0 0.2 -r 140 -t 2019-01-10_11:47:02 2019-02-15_22:00:00
```

<ins>Output:</ins><br>
> Timestamp | Sensor | Attribute | Value
> --- | --- | --- | ---
> 10/02/2019 12:00:00	| Sensor0	| O3	| 30.26 
> 10/02/2019 12:00:00	| Sensor0	| SO2	| 40.35 
> 10/02/2019 12:00:00	| Sensor0	| NO2	| 52.15 
> 10/02/2019 12:00:00	| Sensor0	| PM10	| 60.97     
> 17/01/2019 12:00:00	| Sensor2 | O3 |	75.5    
> 17/01/2019 12:00:00	| Sensor2 | SO2 |	80.16   
> 17/01/2019 12:00:00	| Sensor2 | NO2 |	27.5    
> 17/01/2019 12:00:00	| Sensor2 | PM10 |	73.2 

#### (5) sensor exists and radius is undefined
><ins>Description:</ins><br> We shall try to obtain all the measurements of the sensor at coordinates `(44.0, -1)`. 

<ins>Pre-condition:</ins><br>
- from = to = 0.<br> There exists a sensor in the data base with the exact given coordinates.<br>

<ins>Post-conditions:</ins><br>
- No measures from blacklsited sources are provided.
- The results are given quickly. The duration of the test is limited to 0.5 seconds.

<ins>Request:</ins><br>
```js
AW >>  stats 44.0 -1.0
```

<ins>Output:</ins><br>
> Timestamp | Sensor | Attribute | Value
> --- | --- | --- | ---
> 01/01/2019 12:00:00	| Sensor0 | O3 |	10.25   
> 01/01/2019 12:00:00	| Sensor0 | SO2 |	32.15   
> 01/01/2019 12:00:00	| Sensor0 | NO2 |	42.12   
> 01/01/2019 12:00:00	| Sensor0 | PM10 |	13.10   
> 10/02/2019 12:00:00	| Sensor0	| O3	| 30.26 
> 10/02/2019 12:00:00	| Sensor0	| SO2	| 40.35 
> 10/02/2019 12:00:00	| Sensor0	| NO2	| 52.15
> 10/02/2019 12:00:00	| Sensor0	| PM10	| 60.97 


#### (6) sensor exists and radius is undefined and a timestamp was given
><ins>Description:</ins><br> We shall try to obtain all the measurements of the sensor at coordinates `(44.0, -1)` saved during the period of time from `10/01/2019 at 11:47:02` to `15/02/2019 at 22:00:00`. . 

<ins>Pre-condition:</ins><br>
- from = to = 0.<br> There exists a sensor in the data base with the exact given coordinates.<br>

<ins>Post-conditions:</ins><br>
- No measures from blacklisted sources are provided.
- The results are given quickly. The duration of the test is limited to 0.5 seconds.

<ins>Request:</ins><br>
```js
AW >>  stats 44.0 -1.0 -t 2019-01-10_11:47:02 2019-02-15_22:00:00
```

<ins>Output:</ins><br>
> Timestamp | Sensor | Attribute | Value
> --- | --- | --- | ---
> 10/02/2019 12:00:00	| Sensor0	| O3	| 30.26 
> 10/02/2019 12:00:00	| Sensor0	| SO2	| 40.35 
> 10/02/2019 12:00:00	| Sensor0	| NO2	| 52.15
> 10/02/2019 12:00:00	| Sensor0	| PM10	| 60.97 

#### (7) only one timestamp was given
><ins>Description:</ins>
> We shall try to obtain the measurements saved at `15/02/2019 at 12:00:00`.

<ins>Pre-conditions:</ins>
- The time stamp must be correct (represents a past period of time) and there must be measures during that period to test.

<ins>Post-condition:</ins>
- No measures from blacklisted sources are provided.
- The results are given quickly. The duration of the test is limited to 0.5 seconds.

<ins>Request:</ins>
```js
AW >> stats -t 2019-02-15_12:00:00
```

<ins>Output:</ins>
> Timestamp | Sensor | Attribute | Value
> --- | --- | --- | ---
> 2019-02-15 12:00:00 | Sensor4 | O3 | 259.12
> 2019-02-15 12:00:00 | Sensor4 | SO2 | 12.5
> 2019-02-15 12:00:00 | Sensor4 | NO2 | 13.1

### Tests GetRanking

These are all the unit tests to assure the method getRanking functions correctly and to know if the application's behavior is expected.

For the following tests, we will consider another data set. You can see the data set in the correct test folder (TestGRX).

#### (1) The id doesn't identify an existing sensor
><ins>Description:</ins><br> We shall try to obtain a ranking of similarity to a non-existent sensor from a given id.

<ins>Pre-condition:</ins><br>
- There is no sensor in the database identified by the given id.

<ins>Post-condition:</ins>
- The results are given quickly. The duration of the test is limited to 0.5 seconds.


<ins>Request:</ins><br>
```js
AW >> rank Sensor6
```

<ins>Output:</ins><br>
> sensorId | similarity(0-10) 
> --- | ---  


#### (2) id identifies an existing sensor and no limit was given
><ins>Description:</ins><br> We shall try to obtain a ranking of similarity to an existing sensor from a given id, all sensors near it will be shown, even those those who are far too different since there is no limit to the similarity.

<ins>Pre-condition:</ins><br>
- limit = 0
- The given id identifies an existing sensor in the database that posses measures.

<ins>Post-conditions:</ins><br>
- Output: returns a list of all existing sensors in the database, paired with their similarity to the indicated sensor.
- The results are given quickly. The duration of the test is limited to 0.5 seconds.

<ins>Request:</ins><br>
```js
AW >> rank Sensor2
```

<ins>Output:</ins><br>
> sensorId | similarity(0-10) 
> --- | --- | 
> Sensor1 | 6
> Sensor0 | 3
> Sensor4 | 4


#### (3) id identifies an existing sensor and a limit was given
><ins>Description:</ins><br> We shall try to obtain a ranking of similarity to an existing sensor from a given id, only sufficiently similar sensors to it will be shown, those being with a similarity superior or equal to the limit.

<ins>Pre-condition:</ins><br>
-  0 < limit <= 10
- The given id identifies an existing sensor in the database that posses measures.

<ins>Post-conditions:</ins><br>
- Output: returns a list of all existing sensors in the database, paired with their similarity to the indicated sensor.
- The results are given quickly. The duration of the test is limited to 0.5 seconds.

<ins>Request:</ins><br>
```js
AW >> rank Sensor2 -l 5
```

<ins>Output:</ins><br>
> sensorId | similarity(0-10) 
> --- | --- | 
> Sensor1 | 6

### Tests GetCleanerImpact

These are all the unit tests to assure the method getCleanerImpact functions correctly and to know if the application's behavior is expected.

#### (1) standard case
><ins>Description:</ins><br> 
>We shall try to obtain the statistics of a cleaner such as: the estimated radius up to which it has impacted the ATMO score, by how much and the current and past measures of all sensors near it at the estimated radius..

<ins>Pre-condition:</ins><br>
- the id identifies an existing cleaner
- There are sensors near the studied cleaner for it to be measured.

<ins>Post-conditions:</ins><br>
- The results are given quickly. The duration of the test is limited to 0.5 seconds.

<ins>Request:</ins><br>
```js
AW >> cleaner Cleaner0
```

<ins>Output:</ins><br>
> Cleaner | Estimated Radius | Impact | Pre ATMO | Active ATMO | Post ATMO
> ---| --- | --- | --- | --- | ---
> Cleaner0 | 165 | 1485 | 9 | 0 | 0

#### (2) the cleaner doesn't exist

>We shall try to obtain the statistics of a cleaner that doesn't exist

<ins>Pre-condition:</ins><br>
- the id doesn't identify an existing cleaner

<ins>Post-conditions:</ins><br>
- The results are given quickly. The duration of the test is limited to 0.5 seconds.

<ins>Request:</ins><br>
```js
AW >> cleaner Cleaner10
```

<ins>Output:</ins><br>
> Cleaner | Estimated Radius | Score | Pre ATMO | Active ATMO | Post ATMO
> ---| --- | --- | --- | --- | ---
> Cleaner10 | 0 | 0 | 0 | 0 | 0