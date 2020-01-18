<h2>Telna Code Task</h2>

A coding assignment by Telna, in which a system	 is designed to	collect users information and allow	us to	pull up	and	enter information	about	them.
<br>A Java application using SpringBoot framework is created to perform following functionalities :
<ul>
<li>Save a new user</li>
<li>Save usage of user for particular time</li>
<li>Fetch all the information about user and it's usages for a time range</li>
</ul>

<h3>Requirements</h3>
For building and running the application you would require:<br>

<ul>
<li>JDK 11</li>
<li>Maven</li>
</ul>

<h3>Running the application</h3>
Run following command in to build and run the application <b>mvn clean spring-boot:run</b>

<h3>Documentation</h3>
<b>Swagger</b> software is used to have a detailed view of all rest endpoints. It is configured within the springboot java application to automate the generation of rest endpoints details.
When application is up and running, hitting this URL would display the details, <b><i>http://localhost:8080/swagger-ui.html</b></i>

<h3>End Points</h3>
<ol><li>First end point of type POST, is exposed to	collect	and	store	some basic information about the user like name,	email,	country,	phone	number as a request body.
Following is the url, hitting which would return a user id as a response.</li>
<h4>POST : http://localhost:8080/user</h4>
Request body is in JSON format with following parameters with defined validations :
<p>{<br/>"name": "testUser",<br/> "email": "test_user@gmail.com",<br/>"phoneNumber": "998-124-2222",<br/>"country": "India"<br/>}</p>
 </li>
<li>Second end point of type POST, is exposed to store information	about	the service	a	user used	at a particular
time.	
Following is the url, hitting which would return a string message as a response, stating the success or error message accordingly.</li>
<h4>POST : http://localhost:8080/usage</h4>
Request body is in JSON format with following parameters with defined validations :
<p>{<br/>"user":{<br/>"userId": 1<br/>},<br/> "usageType": "data",<br/>"startDate": "2018-05-20"<br/>}</p>
 </li>
<li>Third end point of type POST, is exposed to retrieve	all	information	about	 the	 user’s	 usage	 history based		
on	a	time	range.	
Following is the url, hitting which would return a response in JSON format containing all	information	about	 the	 user’s	 usage	 history based		
on	a	time	range.</li>
<h4>POST : http://localhost:8080/usage/history</h4>
Request body is in JSON format with following parameters with defined validations :
<p>{<br/>"user":{<br/>"userId": 1<br/>},<br/> "usageType": "data",<br/>"startDate": "2018-05-20"<br/>}</p>
Response : 
<p>[<br>{<br/>"usageId": 3,<br/>    "usageType": "data",<br/>"startDate": "2018-05-20",<br/>"user": {<br>"userId": 1,<br>"name": "testUser",<br/> "email": "test_user@gmail.com",<br/>"phoneNumber": "998-124-2222",<br/>"country": "India"<br>}<br>}<br>]</p>
</li>
</ol>
<h3>Technologies and Frameworks used</h3>
Following technologies, tools and frameworks are used to develop a system with end to end flow of collecting user's information, it's usage types, thus assuring a good code quality standards.<br>
<ul>
<li>Language: Java 8</li>
<li>Build and Packaging: Maven</li>
<li>Application Framework: Spring Boot</li>
<li>Persistence Framework: Spring JPA and Hibernate</li>
<li>Application Documentation: Swagger</li>
<li>Database Change Management: Flyway</li>
<li>Database: H2</li>
<li>Unit Testing: Junit, Mockito</li>
<li>Integration Testing: Spring Test</li>
<li>Coverage Tool: Jacoco</li>
</ul>

<h3>In memory database</h3>
H2 Database is used for storing and retrieving data saved and fetched from various endpoints.
When application is up, following URL <b><i>http://localhost:8080/h2-console</i></b> gives a view of database layout.<br>
Schema name : telna
Tables : User, Usage


<h3>Test cases</h3>
<ul>
 Application has following test cases more than 80% of application
  <li>Unit test cases</li>
  <li>Controller test cases</li>
  <li>Integration test cases</li>
</ul>

<h3>Running test cases and checking coverage</h3>
<ul>
<li>Run following command to run unit and integration test cases <b>./mvnw clean test</b></li>
<li>Run following command to generate test coverage in html format <b>./mvnw clean test</b></li>
</ul>

<h3>Layers and packaging of application</h3>
<ul>
<li><b>config</b> — contains appropriate configuration required application wide</li>
<li><b>validation</b> — contains validation for rest endpoints</li>
<li><b>constants</b> — contains all constants used application wide</li>
<li><b>entity</b> — to hold our entities</li>
<li><b>repository</b> — to communicate with the database</li>
<li><b>service</b> — to hold our business logic</li>
<li><b>controller</b> — to listen to the client</li>
<li><b>request</b> — to create required request object to be transfered</li>
<li><b>enums</b> — to define fixed set of constants to be used application wide</li>
<li><b>resources/</b> - contains all the static resources and property files.</li>
<li><b>src/main/resources/application.properties</b> - contains application-wide properties. Spring reads the properties defined in this file to configure your application. We can define server’s default port, server’s context path, database URLs etc, in this file.</li>
<li><b>test/</b> - contains unit and integration tests</li>
<li><b>src/test/resources/application-test.properties</b> - contains test specific application properties</li>



