# Movie Management App

## Functionalities
- Display Movie Schedule (In Text and JSON format) like below
```
<TODAY'S DATE>
===========================================================
1: <TODAY'S DATE AND TIME> <MOVIE NAME> <DURATION> <PRICE>
...
===========================================================
```
- Apply special discounts for the movie ticket based on different conditions
- Customers can make new reservations and price will be calaculated after applying applicable discounts. If the theatre has reached its maximum seating capacity, the transcations shoud be declined.


## How to run the App
- The application is built using maven and generate the jar using `mvn clean install`.
- The app produces both "Thin jars"(With only compliled class of the source code) and "Fat jars"(With compiled classes of both source code and its dependencies).
