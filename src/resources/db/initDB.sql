CREATE SCHEMA IF NOT EXISTS crud;
USE crud;

CREATE TABLE IF NOT EXISTS skills
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name_skill varchar(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS developers
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name  varchar(255) NOT NULL

);

CREATE TABLE IF NOT EXISTS accounts
(
    developer_id     INT NOT NULL,
    id_status INT NOT NULL,
    UNIQUE (developer_id, id_status),
    FOREIGN KEY (developer_id) REFERENCES developers (id)
);

CREATE TABLE IF NOT EXISTS developer_skills
(
    developer_id INT(255) NOT NULL,
    skill_id     INT(255),
    UNIQUE (developer_id, skill_id),
    FOREIGN KEY (developer_id) REFERENCES developers (id),
    FOREIGN KEY (skill_id) REFERENCES skills (id)
);
