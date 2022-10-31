# Groupie

Tech: Java, ReactJS, Docker, Gherkin, HTML, CSS, SQLite, Cucumber

### Groupie is a web application that helps friends set a group date. Using the Groupie you can:
- Browse local concert events using the Ticketmaster API. 
- Visualize your friends availability.
- Propose a group date and send an invitation with multiple event options to your friends.
- Get response from friends regarding their excitement level and their response to yes/no/maybe.
- Get a recommendation from the system for the best overall group date choice based on your friend's preferences and availability.
### And more...
- Your friends can accept or decline finalized group date proposals.
- You and your friends can track and visualize your received proposals, responses, and time
commitments.
### Privacy Features
- Users are able to create an account in the system and manage their privacy
settings by adding blocked users.

- The application uses a SSL certificate for user authentication and protect confidentiality of users’ data by encrypting user's passwords in the data base.
### Responsiveness
The application works on the Chrome web browser and mobile devices.

Checkout a live demo here.

# Project Repo Information

### Frontend Notes

To run the React frontend during development and get hot module reloading, open a new terminal tab and run:
```
cd frontend
yarn start
```

You can access the frontend during development at `localhost:3000`.

For production and CI/CD, `jetty:run` will build a static production build of our app and serve it from `localhost:8080` (assuming Spring Boot is set up).

### Original Instructions

Import this repository into your IDE. This project provides everything needed to:

* Host the web application on a local web server
* Run unit tests with coverage
* Run acceptance tests
* Do the above within a container

**To run unit tests:**

Run the Maven lifecycle phase *test*.  This will run all unit tests and output the coverage file to "target/site/jacoco/index.html"

**To run your web application during development:**

Run the Maven goal *jetty:run*.  Your web application will launch and be hosted on http://localhost:8080.  The Jetty container scans for code updates every 5 seconds and will automatically update the web app with new changes. 

**To run acceptance tests:**

Run the Maven lifecycle phase *verify*. This phase will run unit tests, start the jetty server, run Cucumber features, and then stop the Jetty server.  Make sure you have stopped the Jetty server prior to verification, if you have been running the Jetty server during development as described above.  If you want to run just the Cucumber tests, then right-click on the RunCucumberTests file and run the file directly from the pop-up menu. You can customize the Cucumber features to be run by modifying the Cucumber options in the RunCucumberTests file. For example, @CucumberOptions(strict = true, features = {"src/test/resources/cucumber/x.feature", "src/test/resources/cucumber/y.feature"}) will run only feature files x.feature and y.feature.

**Doing the above within a container:**

Run `docker-compose up`. In a web browser navigate to `http://localhost:6080`. Use the above commands in the provided terminal window. A Chrome browser instance can be opened with the command `chrome-browser <url>`.

Note: If the container gets stuck on "Waiting X server..." make sure you remove any existing running containers (via `docker rm`) and try again.


