### You have to implement console CRUD application that has next entities:
**model.Developer
model.Skill
Account
model.AccountStatus **(enum ACTIVE, BANNED, DELETED)

**model.Developer** -> Set<model.Skill> skills + Account account
**Account** -> model.AccountStatus

Use text files as a storage:
**developers.txt, skills.txt, accounts.txt**

User should be able to create, read, update and delete data.


Layers:
**model** - POJO classes
**repository** - classes that provide access to text files **controller** - user’s requests handling
**view** - all data that are required for user/console interaction.

Example: **model.Developer, repository.DeveloperRepository, controller.DeveloperController, view.DeveloperView** и т.д.


Try to use basic interface for repository layer:
**interface GenericRepository<T,ID>**

**interface repository.DeveloperRepository extends GenericRepository<model.Developer, Long>

class JavaIODeveloperRepositoryImpl implements repository.DeveloperRepository**

As a result of this task you should create new github repository with *README.md* file, that contains task and project description and start up instructions.

Basic functionality should be covered with unit tests.

User travis (https://travis-ci.com/) to show project build status.**