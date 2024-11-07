use schedule;


CREATE TABLE if not exists  Users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   varchar(255) NOT NULL UNIQUE,
    email      varchar(255) NOT NULL UNIQUE,
    password   varchar(255) NOT NULL,
    created_at timestamp     DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE if not exists  Categories
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE if not exists  Schedules
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    content     text         NOT NULL COMMENT 'Content of the post',
    user_id     BIGINT       NOT NULL,
    category_id BIGINT,
    due_date    DATE,
    priority    ENUM ('Low', 'Medium', 'High')               DEFAULT 'Medium',
    status      ENUM ('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
    created_at  timestamp                                    DEFAULT CURRENT_TIMESTAMP,
    updated_at  timestamp                                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES Categories (id) ON DELETE SET NULL
);

# Insert a new user
INSERT INTO Users (username, email, password)
VALUES ('youngjun', 'youngjun@gmil.com', 'password1234');

# Insert a new category
INSERT INTO Categories (name)
VALUES ('Work');

# Insert a new to-do list
INSERT INTO Schedules (content, user_id, category_id, due_date, status, priority)
VALUES ('content2', 1, 1,  '2024-11-05', 'Pending', 'High');

# Select all to-do lists for a specific user
SELECT *
FROM Schedules
WHERE user_id = 1;

# Select all task with a specific status and priority
SELECT *
FROM Schedules
WHERE status = 'Pending'
  AND priority = 'High';

# Update the content of a to-do list
UPDATE Schedules
SET content = 'Updated Task'
WHERE id = 1;

# Update a task status to "Completed"
UPDATE Schedules
SET status = 'Completed'
WHERE id = 1;

# Delete a task by ID
DELETE
FROM Schedules
WHERE id = 1;

# Select all categories 
SELECT *
FROM Categories;
