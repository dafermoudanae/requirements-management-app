# requirements-management-app

A web application for requirements analysis and management, built with Java and Spring Boot. Users can manage projects, actors, use cases and CRC cards, generate UML-style diagrams from the project data, and collaborate on projects.

Software Engineering course project, University of Ioannina, 2026.


## Features
- User registration and sign-in
- Project creation and management, with project members
- Actors and use cases
- CRC cards
- Diagram generation (PlantUML and Nomnoml)

## Technologies
- Java 17
- Spring Boot 3.2.2
- Thymeleaf
- MySQL
- Maven

## Requirements
- JDK 17
- MySQL server running on `localhost:3306`

## How to Run
1. Clone the repository:
     `git clone https://github.com/dafermoudanae/requirements-management-app.git`
2. Set the database password as an environment variable:
   - Windows (PowerShell): `$env:DB_PASSWORD="your_password"`
   - Linux / macOS: `export DB_PASSWORD=your_password`
3. Start the application:
   - Windows: `mvnw.cmd spring-boot:run`
   - Linux / macOS: `./mvnw spring-boot:run`
4. Open `http://localhost:8080` in your browser.

The database `analysisApp` is created automatically on first run.

## Project Structure
- `src/main/java/analysis/app/AM5298_5224_5206/`: application code (controllers, services, repositories, domain classes, diagram generators)
- `src/main/resources/templates/`: Thymeleaf HTML pages
- `src/main/resources/application.properties`: configuration
- `src/test/`: tests

## Use Case Diagram
![Use case diagram](Use%20case%20diagram.png)
