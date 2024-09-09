CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    taskcard_id BIGINT NOT NULL,
    user_id BINARY(16) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_comments_taskcards FOREIGN KEY (taskcard_id) REFERENCES taskcards(id) ON DELETE CASCADE,
    CONSTRAINT fk_comments_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE

);