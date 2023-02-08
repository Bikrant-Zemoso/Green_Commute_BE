CREATE TABLE IF NOT EXISTS City(
   id UUID PRIMARY KEY,
   name VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO City (id,name) VALUES
('123e4567-e89b-12d3-a456-426614174000','Mumbai'),
('123e4567-e89b-12d3-a456-426614174001','Delhi'),
('123e4567-e89b-12d3-a456-426614174002','Hyderabad'),
('123e4567-e89b-12d3-a456-426614174003','Bangaluru'),
('123e4567-e89b-12d3-a456-426614174004','Chennai'),
('123e4567-e89b-12d3-a456-426614174005','Pune'),
('123e4567-e89b-12d3-a456-426614174006','Kolkata');