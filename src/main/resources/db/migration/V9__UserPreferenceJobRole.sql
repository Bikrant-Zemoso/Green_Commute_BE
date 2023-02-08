CREATE TABLE IF NOT EXISTS User_Preference_Job_Role (
   id UUID PRIMARY KEY,
   user_preference_id UUID NOT NULL,
   job_role_id UUID NOT NULL,
   FOREIGN KEY (user_preference_id) REFERENCES User_Preference(id),
   FOREIGN KEY (job_role_id) REFERENCES Job_Role(id),
   CONSTRAINT uk_user_preference_job_role UNIQUE (user_preference_id, job_role_id)
);
INSERT INTO User_Preference_Job_Role (id, user_preference_id, job_role_id) VALUES
('132e4567-e89b-12d3-a456-426614174003','132e4567-e89b-12d3-a456-426614174001', '92fbd158-78bc-4017-866d-27a05767fc5e');