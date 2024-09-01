ALTER TABLE taskcards
    ADD CONSTRAINT fk_taskcards_users
    FOREIGN KEY (creator_id) REFERENCES users(id)
    ON DELETE CASCADE;