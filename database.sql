CREATE DATABASE IF NOT EXISTS library;
USE library;

CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    type VARCHAR(50)
);

CREATE TABLE books (
    item_id INT PRIMARY KEY,
    author VARCHAR(255),
    page_count INT,
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
);

CREATE TABLE magazine (
    item_id INT PRIMARY KEY,
    issue_number VARCHAR(50),
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
);

CREATE TABLE dvds (
    item_id INT PRIMARY KEY,
    duration INT,
    genre VARCHAR(100),
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
);

CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE loans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_id INT,
    member_id INT,
    lend_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    return_date DATETIME NULL,
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE
);