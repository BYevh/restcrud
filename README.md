[![Build Status](https://api.travis-ci.com/BYevh/jdbccrud.svg?branch=master)](https://travis-ci.com/BYevh/jdbccrud)


**DeveloperCRUD**
This application allow to communicate with database via POST requests and provide CRUD interface.


It has next entities: *Developer, Skill, Account.*

*Developer*: Long id; String Name; Set skills; Account account.\
*Skill*: Long id; String name.\
*Account*: Long id; AccountStatus (enum: ACTIVE, BANNED, DELETED) status.

**Layers:**
model - POJO classes;
repository - classes that provide access to database;
service - classes with business logic;
rest - user’s requests handling.


It uses MySQL/hiroku database as a storage.
User is able to create, read (get by ID or get all), update and delete data.

Technology stack: Java, MySQL, H2 DB, JDBC, Servlets, Liquibase, JUnit, Mockito, Log4j, Tomcat.

Also this api is deployed to Heroku cloud service.\
https://restapiepam-developers.herokuapp.com

There are a few endpoints:

api/v1/skills\
api/v1/accounts\
api/v1/developers

To show the build status of the project used Trevis
