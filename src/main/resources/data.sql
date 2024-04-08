-- Create the ChatRoom table
CREATE TABLE IF NOT EXISTS ChatRoom (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

-- Create the ChatMessage table
CREATE TABLE IF NOT EXISTS ChatMessage (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           message VARCHAR(255),
    chat_room_id BIGINT,
    FOREIGN KEY (chat_room_id) REFERENCES ChatRoom(id)
    );

-- Create the User table
CREATE TABLE IF NOT EXISTS User (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    chat_room_id BIGINT,
    FOREIGN KEY (chat_room_id) REFERENCES ChatRoom(id)
    );


INSERT INTO chat_room (id) VALUES (1);