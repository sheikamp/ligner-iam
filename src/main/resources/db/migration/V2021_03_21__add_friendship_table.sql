CREATE TABLE friendship (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    status ENUM ('REQUESTED', 'ACCEPTED', 'DENIED') NOT NULL,
    UNIQUE KEY uni_user_friend_id (user_id, friend_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_friend FOREIGN KEY (friend_id) REFERENCES user(id) ON DELETE CASCADE
);