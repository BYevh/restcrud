USE crud;


INSERT INTO skills (name_skill)
VALUES ('Java'),
       ('PHP'),
       ('C#'),
       ('C++'),
       ('Kotlin');


INSERT INTO developers (id, name)
VALUES (1, 'Sergey Ivanov'),
       (2, 'Pavel Petrov'),
       (3, 'Yuriy Luzhkov');


INSERT INTO developer_skills(developer_id, skill_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1);

INSERT INTO accounts (developer_id, status)
VALUES (1, 'ACTIVE'),
       (2, 'DELETE'),
       (3, 'BANNED');