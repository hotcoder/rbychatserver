-- Create the ChatRoom table
CREATE TABLE IF NOT EXISTS chat_room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

-- Create the ChatMessage table
CREATE TABLE IF NOT EXISTS chat_message (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           message VARCHAR(255),
    chat_room_id BIGINT,
    FOREIGN KEY (chat_room_id) REFERENCES chat_room(id)
    );

-- Create the User table
CREATE TABLE IF NOT EXISTS "chat_user" (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    chat_room_id BIGINT,
    FOREIGN KEY (chat_room_id) REFERENCES chat_room(id)
    );