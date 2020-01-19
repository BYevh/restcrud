USE crud;


INSERT INTO skills (name_skill)
VALUES ('Java'),
       ('PHP'),
       ('C#'),
       ('C++'),
       ('Kotlin');


INSERT INTO developers (first_name, second_name, account_id)
VALUES ('Sergey', 'Ivanov', 1),
       ('Pavel', 'Petrov', 2),
       ('Yuriy', 'Luzhkov', 3);


INSERT INTO developer_skills(developer_id, skill_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1);

INSERT INTO accounts (id, name,  status)
VALUES (1, 'Sergey Ivanov', 'ACTIVE'),
       (2, 'Pavel Petrov', 'DELETE'),
       (3, 'Yuriy Luzhkov', 'BANNED');