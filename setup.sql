CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

CREATE TABLE IF NOT EXISTS users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    role     VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS students (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    email      VARCHAR(100),
    course     VARCHAR(100),
    year       INT
);

INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'admin');

INSERT INTO students (first_name, last_name, email, course, year) VALUES
('Alice',   'Johnson', 'alice.johnson@uni.ac.uk', 'Computer Science',        2),
('Bob',     'Smith',   'bob.smith@uni.ac.uk',     'Software Engineering',    1),
('Charlie', 'Brown',   'charlie.brown@uni.ac.uk', 'Data Science',            3);
