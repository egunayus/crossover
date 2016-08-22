# crossover tech trial project
An air ticket reservation system by Erdem Günay.
 
Software design and solution is explained in detail in Software Design Document.pdf

Maven is used as the build manager for the project.

## Project sctructure
The project is configured to be a multi module maven project. At the root level techtrial/ is the parent pom module. Below there are three modules. 

+ techtrial/
	+ techtrial-domain/
	+ techtrial-api/
	+ tectrial-admin/

Important configuration files and folders are presented in the following picture
![GitHub Logo](/docs/folder_structure.png)
 

## Installation
The project has two executable package 

- techtrial-api-0.0.1-SNAPSHOT.jar
- techtrial-admin-0.0.1-SNAPSHOT.jar


In order to generate the packages following comment should be executed in techtrial/ folder where the parent pom.xml file exists. 

mvn clean package
