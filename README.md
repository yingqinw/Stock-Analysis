
1. Prediction of future values of the portfolio
2. Ability to add or remove stocks from the portfolio
3. Track and visualize changes in value over time of user’s portfolio
4. View and compare the historical performance of a stock
5. Works on the Chrome web browser and mobile devices
6. The application must be secure and protect confidentiality of users’ data.
7. Users must be able to create an account in the system
8. User interfaces must look modern and be attractive.
9. Must be implemented as a client-server based web application

# Project Repo Information

Import this repository into Eclipse. This project provides everything needed to:

* Host the web application on a local web server
* Run unit tests with coverage
* Run acceptance tests

**To run JUnit tests:**

Right-click project -> Run As -> "Maven test"

**To generate coverage report for JUnit tests:**

Right-click "cobertura.launch" -> Run As -> "cobertura".

**To host your web application:**

Right-click "run.launch" -> Run As -> "run". It will be hosted on https://localhost:8080.

**To run Cucumber tests:**

Make sure the web server is running when you run the Cucumber tests. Right-click "cucumber.launch" -> Run As -> "cucumber".
1. Make sure the web server is running when you run the Cucumber tests. 
2. Make sure the React Web App is running when you run the Cucumber tests. Go to "/src/main/webapp/reactjs" and follow the README.md inside that repository. Ideally run `yarn install` and `yarn start` will start the website.
3. Right-click "cucumber.launch" -> Run As -> "cucumber".
