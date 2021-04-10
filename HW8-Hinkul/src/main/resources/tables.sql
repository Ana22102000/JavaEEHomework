CREATE TABLE books
(
  id         INT(11) PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(50) NOT NULL,
  isbn  VARCHAR(50) NOT NULL UNIQUE,
  author_id         INT(11) NOT NULL,
  CONSTRAINT fk_books_to_authors FOREIGN KEY (author_id) REFERENCES authors (id),
  unique uniq_isbn (isbn)

);

CREATE TABLE authors
(
  id     INT(11) PRIMARY KEY AUTO_INCREMENT,
  author_name VARCHAR(50) NOT NULL,
  author_surname VARCHAR(50) NOT NULL
);

CREATE TABLE users
(
  id     INT(11) PRIMARY KEY AUTO_INCREMENT,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  unique uniq_login (login)
);

create table permissions
(
  id         int primary key auto_increment,
  permission varchar(30) not null,
  unique uniq_permission (permission)
);

create table user_to_permissions (
                                   user_id int not null,
                                   permission_id int not null,
                                   constraint fk_user_to_permission_user foreign key (user_id) references users(id),
                                   constraint fk_user_to_permission_permission foreign key (permission_id) references permissions(id)
);

create table selected_books (
                                   user_id int not null,
                                   book_id int not null,
                                   constraint fk_selected_books_user foreign key (user_id) references users(id),
                                   constraint fk_selected_books_book foreign key (book_id) references books(id)
);



insert into users (login, password, custom_auth_field) values
('user', 'password', 'custom user field');

insert into permissions (permission) values
('VIEW_SELECTED');

insert into user_to_permissions (user_id, permission_id) values
((select id from users where login = 'user'), (select id from permissions where permission = 'VIEW_SELECTED'));

