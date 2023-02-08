CREATE TABLE IF NOT EXISTS User_Preference_Job_Location (
   id UUID PRIMARY KEY,
   user_preference_id UUID NOT NULL,
   location_id UUID NOT NULL,
   FOREIGN KEY (user_preference_id) REFERENCES User_Preference(id),
   FOREIGN KEY (location_id) REFERENCES Location_Details(id),
   CONSTRAINT uk_user_preference_job_location UNIQUE (user_preference_id, location_id)
);
INSERT INTO User_Preference_Job_Location (id, user_preference_id, location_id) VALUES
('132e4567-e89b-12d3-a456-426614174002','132e4567-e89b-12d3-a456-426614174001', '123e4567-e89b-12d3-a456-426614174012');