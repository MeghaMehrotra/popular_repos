## Github API Popular Repositories

This project is a Java Spring Boot application that uses the Github API to fetch the most popular repositories. The application allows the user to filter the results based on language, creation date, sorting and ordering parameters.
###Requirements
To run this project, you need:

* Java 8 or later
* Maven 3.2 or later
* Git

## Getting Started

### Clone the repository
```bash
 git clone https://github.com/MeghaMehrotra/popular_repos.git
```

### Navigate to project directory

```bash
cd popular_repos
```

### Build the application using Maven

```bash
mvn clean install
```
### Run the application 

Either run 
```bash
mvn spring-boot:run
```
OR 

```bash
java -jar target/popular_repos-0.0.1-SNAPSHOT.jar
```
## Usage

Once the application is running, you can access the following endpoint http://localhost:8080/repos/popular
to fetch the most popular repositories:


The endpoint accepts the following query parameters:

* **perPage** (optional, default: 10) - the number of repositories to return per page 
*  **sort** (optional, default: "stars") - the field to sort the repositories by ("stars", "forks", "updated")
*  **order** (optional, default: "desc") - the order to sort the repositories by ("asc", "desc")
* **language** (optional) - the programming language to filter the repositories by
* **createdSince** (optional) - the creation date to filter the repositories by (in ISO 8601 format, e.g. "2022-01-01")

## Example Request

```bash
http://localhost:8080/repos/popular?perPage=1&sort=stars&order=desc&language=java&createdSince=2023-01-01
```
## Example Response
```bash
[
    {
        "name": "huntly",
        "html_url": "https://github.com/lcomplete/huntly"
    }
]
```

## Testing
To run the tests, execute the following command in the project root directory:

```bash
mvn test
```

This will run the unit tests for the GithubRepoController, GithubRepoService, and UrlBuilderService classes.

### Built With
Spring Boot
Spring Web
Spring Validation
Jackson
JUnit 4
Mockito

