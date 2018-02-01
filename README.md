# tehara

the flights data come from: [https://openflights.org/data.html](https://openflights.org/data.html)

Create MySql db with these parameters:

	url: jdbc:mysql://localhost:3306/tehara
	username: tehara
	password: tehara

prepare gradle

	$> gradle wrapper
 
 
To build wildfly war

	$> ./gradlew -Pprod war
 
To test with springboot

	$> ./gradlew bootRun
