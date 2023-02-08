CREATE TABLE IF NOT EXISTS User_Preference (
   id UUID PRIMARY KEY,
   user_details_id UUID NOT NULL,
   user_location_id UUID NOT NULL,
   CONSTRAINT uk_userPreference UNIQUE (user_details_id),
   FOREIGN KEY (user_details_id) REFERENCES User_Details(id),
   FOREIGN KEY (user_location_id) REFERENCES Location_Details(id)
);
INSERT INTO User_Preference (id, user_details_id, user_location_id) VALUES
('132e4567-e89b-12d3-a456-426614174001','132e4567-e89b-12d3-a456-426614174000', '123e4567-e89b-12d3-a456-426614174011');