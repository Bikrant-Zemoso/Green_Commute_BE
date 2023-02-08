CREATE TABLE IF NOT EXISTS Company_Details (
   id UUID PRIMARY KEY,
   company_name VARCHAR(255) UNIQUE NOT NULL,
   icon VARCHAR(255),
   location_id UUID NOT NULL,
   is_metro_available BOOLEAN DEFAULT FALSE,
   is_bus_available BOOLEAN DEFAULT FALSE,
   is_cab_available BOOLEAN DEFAULT FALSE,
   is_cycle_available BOOLEAN DEFAULT FALSE,

   FOREIGN KEY (location_id)
             REFERENCES Location_Details (id)
);

INSERT INTO
Company_Details (id,company_name,icon,location_id,is_metro_available,is_bus_available,is_cab_available,is_cycle_available)
VALUES
('123e4567-e89b-12d3-a456-426614174031','Hp','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlzaPN0F2uedzxzPk-09Ta-4USZvnUVb4QpA&usqp=CAU',
        '123e4567-e89b-12d3-a456-426614174013',true,true,true,true),
('123e4567-e89b-12d3-a456-426614174032','BMW','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlzaPN0F2uedzxzPk-09Ta-4USZvnUVb4QpA&usqp=CAU',
        '123e4567-e89b-12d3-a456-426614174013',true,true,true,false),
('123e4567-e89b-12d3-a456-426614174033','Microchip','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlzaPN0F2uedzxzPk-09Ta-4USZvnUVb4QpA&usqp=CAU',
        '123e4567-e89b-12d3-a456-426614174013',true,true,false,false);