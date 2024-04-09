# OrbEscape
<p align="center">
  <img src="readme-assets/game_image.png" width="500">
</p>

## About
Project for SOFTENG 206 (Software Engineering Design 1), a compulsory course for Part II Software Engineering students at the University of Auckland.

OrbEscape is an escape room game designed to enhance critical thinking skills among younger demographics. It features:

* Riddles that leverage the OpenAI API
* An intelligent "Game Master" to assist users through the OpenAI API
* A carefully crafted pixel art interface
* Various difficulty levels with different time limits
* Elements of randomness for replayability

This project ultimately led to a first-in-course award in SOFTENG 206.

## Video Demonstration

https://github.com/vqiu25/orb-escape/assets/109129209/503a9517-ad28-4f35-82e3-3e1b8612249f

## Acknowledgements
This project would not have been possible without the hard work and dedication of our lecturers:
* Nasser Giacaman, and
* Valerio Teragni

Furthermore, I would like to express my deepest gratitude towards my group members:
* Liam Parker, and
* William Chong

for their passion and commitment to this project.

## To setup OpenAI's API

- add in the root of the project (i.e., the same level where `pom.xml` is located) a file named `apiproxy.config`
- put inside the credentials that you received from no-reply@digitaledu.ac.nz (put the quotes "")
  `    email: “UPI@aucklanduni.ac.nz"
    apiKey: “YOUR_KEY”
   `
  these are your credentials to invoke the OpenAI GPT APIs

## To setup codestyle's API

- add in the root of the project (i.e., the same level where `pom.xml` is located) a file named `codestyle.config`
- put inside the credentials that you received from gradestyle@digitaledu.ac.nz (put the quotes "")
  `    email: “UPI@aucklanduni.ac.nz"
    accessToken: “YOUR_KEY”
   `
  these are your credentials to invoke gradestyle

## To run the game

`./mvnw clean javafx:run`

## To debug the game

`./mvnw clean javafx:run@debug` then in VS Code "Run & Debug", then run "Debug JavaFX"

## To run codestyle

`./mvnw clean compile exec:java@style`
