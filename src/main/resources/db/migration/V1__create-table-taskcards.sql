CREATE TABLE taskcards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP,
    due_date TIMESTAMP,
    creator_id INT NOT NULL,
    priority VARCHAR(50),
    status VARCHAR(50) NOT NULL
);
