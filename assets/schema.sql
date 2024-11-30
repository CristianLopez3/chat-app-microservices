-- Message Table
CREATE TABLE message
(
    messageId      INT PRIMARY KEY,
    senderId       INT,
    recipientId    INT,
    content        TEXT,
    timestamp      DATETIME,
    conversationId INT,
    FOREIGN KEY (senderId) REFERENCES user (userId),
    FOREIGN KEY (recipientId) REFERENCES user (userId),
    FOREIGN KEY (conversationId) REFERENCES conversation (conversationId)
);

-- Conversation Table
CREATE TABLE conversation
(
    conversationId INT PRIMARY KEY,
    subject        VARCHAR(255)
);

-- User Table
CREATE TABLE user
(
    userId   INT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    email    VARCHAR(255)
);

-- Contact Table
CREATE TABLE contact
(
    contactId     INT PRIMARY KEY,
    userId        INT,
    contactUserId INT,
    FOREIGN KEY (userId) REFERENCES user (userId),
    FOREIGN KEY (contactUserId) REFERENCES user (userId)
);

-- Notification Table
CREATE TABLE notification
(
    notificationId INT PRIMARY KEY,
    userId         INT,
    content        TEXT,
    timestamp      DATETIME,
    FOREIGN KEY (userId) REFERENCES user (userId)
);