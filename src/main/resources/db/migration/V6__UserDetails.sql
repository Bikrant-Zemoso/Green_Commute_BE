CREATE TABLE IF NOT EXISTS User_Details (
   id UUID PRIMARY KEY,
   username VARCHAR(255) UNIQUE NOT NULL,
   password VARCHAR(255) UNIQUE NOT NULL,
   profile_pic VARCHAR(255)
);
INSERT INTO User_Details (id, username, password, profile_pic) VALUES
('132e4567-e89b-12d3-a456-426614174000','Ram', 'zemoso123', '');