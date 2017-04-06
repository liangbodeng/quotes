# Spring Boot + Angular 4 Demo

## What's this project?

This is a multi-module project using Spring Boot and Angular 4.

## How to build it?

    ./gradlew clean build

The final artifact is `quotes-app/build/libs/quotes-app-*.jar`, which contains both the server and front-end.

## How to run it?

    (cd quotes-app; gradle bootRun)

or

    (cd quotes-app; java -jar build/libs/quotes-app-*.jar)

or run it using IDE.

## How to enable the front-end hot loading?

* First start the server as indicated above

* Start the angular development server in another terminal

        (cd quotes-web; gradle ngServe)

* Edit the angular files, and watch it's automatically re-loaded
