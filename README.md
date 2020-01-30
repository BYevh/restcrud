[![Build Status](https://api.travis-ci.com/BYevh/jdbccrud.svg?branch=master)](https://travis-ci.com/BYevh/jdbccrud)

**You have to implement console CRUD application that has next entities:**
Developer
Skill
Account
AccountStatus (enum ACTIVE, BANNED, DELETED)

Developer -> Set<Skill> skills + Account account
Account -> AccountStatus

Use MySQL database as a storage.
User should be able to create, read, update and delete data.


**Layers:**
model - POJO classes
repository - classes that provide access to database
service - classes with business logic
controller - user’s requests handling
view - all data that are required for user/console interaction.

Example: Developer, DeveloperRepository, DeveloperController, DeveloperView и т.д.


Try to use basic interface for repository layer:
interface GenericRepository<T,ID>

interface DeveloperRepository extends GenericRepository<Developer, Long>

class JdbcDeveloperRepositoryImpl implements DeveloperRepository

As a result of this task you should create new github repository with README.md file, that contains task and project description and start up instructions.

Basic functionality should be covered with unit tests.
For tests use H2 in-memory database

User travis (https://travis-ci.com/) to show project build status.