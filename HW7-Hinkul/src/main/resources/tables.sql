CREATE TABLE books
(
  id         INT(11) PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL,
  isbn  VARCHAR(50) NOT NULL UNIQUE,
  author_id         INT(11) NOT NULL,
  CONSTRAINT fk_books_to_authors FOREIGN KEY (author_id) REFERENCES authors (id)

);

CREATE TABLE authors
(
  id     INT(11) PRIMARY KEY AUTO_INCREMENT,
  author_name VARCHAR(50) NOT NULL,
  author_surname VARCHAR(50) NOT NULL
);
