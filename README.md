### You have to implement console CRUD application that has next entities:
**ua.epam.crud.model.Developer
ua.epam.crud.model.Skill
Account
ua.epam.crud.model.AccountStatus **(enum ACTIVE, BANNED, DELETED)

**ua.epam.crud.model.Developer** -> Set<ua.epam.crud.model.Skill> skills + Account account
**Account** -> ua.epam.crud.model.AccountStatus

Use text files as a storage:
**developers.txt, skills.txt, accounts.txt**

User should be able to create, read, update and delete data.


Layers:
**ua.epam.crud.model** - POJO classes
**ua.epam.crud.repository** - classes that provide access to text files **ua.epam.crud.controller** - user’s requests handling
**ua.epam.crud.view** - all data that are required for user/console interaction.

Example: **ua.epam.crud.model.Developer, ua.epam.crud.repository.DeveloperRepository, ua.epam.crud.controller.DeveloperController, ua.epam.crud.view.DeveloperView** и т.д.


Try to use basic interface for ua.epam.crud.repository layer:
**interface GenericRepository<T,ID>**

**interface ua.epam.crud.repository.DeveloperRepository extends GenericRepository<ua.epam.crud.model.Developer, Long>

class JavaIODeveloperRepositoryImpl implements ua.epam.crud.repository.DeveloperRepository**

As a result of this task you should create new github ua.epam.crud.repository with *README.md* file, that contains task and project description and start up instructions.

Basic functionality should be covered with unit tests.

User travis (https://travis-ci.com/) to show project build status.**