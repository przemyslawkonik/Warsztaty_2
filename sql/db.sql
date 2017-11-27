CREATE TABLE user_group(
	id INT AUTO_INCREMENT,
	name VARCHAR(255),
	PRIMARY KEY(id)
);

CREATE TABLE users(
	id BIGINT AUTO_INCREMENT,
	username VARCHAR(255),
	email VARCHAR(255) UNIQUE,
	password VARCHAR(245),
	user_group_id INT,
	PRIMARY KEY(id),
	FOREIGN KEY(user_group_id) REFERENCES user_group(id) ON DELETE CASCADE
);

CREATE TABLE excercise(
	id INT AUTO_INCREMENT,
	title VARCHAR(255),
	description TEXT,
	PRIMARY KEY(id)
);

CREATE TABLE solution(
	id INT AUTO_INCREMENT,
	created DATETIME DEFAULT NOW(),
	updated DATETIME,
	description TEXT,
	excercise_id INT,
	users_id BIGINT,
	PRIMARY KEY(id),
	FOREIGN KEY(excercise_id) REFERENCES excercise(id) ON DELETE CASCADE,
	FOREIGN KEY(users_id) REFERENCES users(id) ON DELETE CASCADE
);