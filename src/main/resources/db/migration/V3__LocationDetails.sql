CREATE TABLE IF NOT EXISTS Location_Details (
    id UUID PRIMARY KEY,
    city_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    address TEXT,
    lat NUMERIC,
    long NUMERIC,

    FOREIGN KEY (city_id)
          REFERENCES City (id)
);

INSERT INTO
    Location_Details (id,city_id,name,address,lat,long)
VALUES
('123e4567-e89b-12d3-a456-426614174011','123e4567-e89b-12d3-a456-426614174000','Mumbai','Maharastra, India',19.0760,72.8777),
('123e4567-e89b-12d3-a456-426614174012','123e4567-e89b-12d3-a456-426614174001','Delhi','Delhi, India',28.7041,77.1025),
('123e4567-e89b-12d3-a456-426614174013','123e4567-e89b-12d3-a456-426614174002','Hyderabad','Hitech City, Telangana',17.3850,78.4867),
('123e4567-e89b-12d3-a456-426614174014','123e4567-e89b-12d3-a456-426614174003','Bengaluru','Karnataka, India',12.9716,77.5946),
('123e4567-e89b-12d3-a456-426614174015','123e4567-e89b-12d3-a456-426614174004','Chennai','Tamil Nadu, India',22.5726,88.3639);
