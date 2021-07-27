# spring-batch-rest
- Spring Batch and Spring REST example
- Batch controlled and monitored through REST API's exposed
- Resources loaded from Batch are exposed through Spring REST

## Requirements
For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)
- Use `mvn --version` for verifying maven and `java --version` for verifying Java installations.

## Building and Running Application using Command Prompt

Clone from git repository `git clone URL`.
Navigate to project root and `mvn clean install`, alternativey use `mvn clean install -DskipTests=true` to skip tests

## Running the Application on IDE

Clone from git repository `git clone URL`.
File >> Import as `Existing Maven Project`.
Execute the `main` method in the `com.chaitanya.LibraryApplication` class from your IDE.
Application Built on STS IDE, can work on eclipse and Intellij IDE

## Note
- If `java.net.BindException: Address already in use` is caused while launching application modify `server.port` in `application.properties`
- This project uses in-memory database `H2`. To access database please navigate to `http://localhost:<configured_port>/h2-lib-console`
- Swagger UI using open-api is available at `http://localhost:<configured_port>/swagger-ui-lib.html`

## Given Problem Statement and Expectations

Functional Features:
-	CRUD interface to access the books by ISBN
-	Search API by any of the attributes of the books
-	Rest API to Import books from CSV (or any other format you prefer)
 
Technical Features:
-	DB Storage (any in memory approach will do)
-	Swagger on the REST API

Not Required:
-	User interface
-	API Authentication

## Assumptions
- Data in CSV is structured
- Skip Policy, Retry Policy and Restart Policy dosent corresponds to any business case for the given implmentation
- ...colud be more

## Pending
- Validation to allow only CSV file in file upload API `TO DO`
- Pending verification of `Database lock` scenrio `TO DO`
- TaskExecutor Integration to step; found some spring issue `JobExecutionDao Synchronization issue`. `TO BE RESOLVED`
- Test cases covered to business logic (Services, ItemReader, ItemWriter, ItemProcessor), `TO DO` for Controllers and Batch configuration
- Integration with some `Observability` platform for monitoring runtime.

## Basic Features Achieved
- API to Upload CSV file
- API to Start, Stop, Restart batch execution and Read Processing Information of current execution
- API to expose Resources which are loaded using Batch

## References
- [spring.docs](https://docs.spring.io/spring-batch/docs/current/reference/html/job.html#configureJob)
- [baeldung.com](https://www.baeldung.com/spring-rest-api-query-search-language-tutorial)
