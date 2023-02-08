CREATE TABLE IF NOT EXISTS Job_Details (
   id UUID PRIMARY KEY,
   job_role_id UUID NOT NULL,
   company_id UUID NOT NULL,
   experience NUMERIC NOT NULL,
   job_type VARCHAR(255) NOT NULL,
   description TEXT,
   qualifications jsonb,
   posted_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,

   FOREIGN KEY (job_role_id)
      REFERENCES Job_Role (id),
   FOREIGN KEY (company_id)
      REFERENCES Company_Details (id)

);

INSERT INTO Job_Details (id, job_role_id, company_id, experience, job_type, description, qualifications) VALUES
('123e4567-e89b-12d3-a456-426614174041','92fbd158-78bc-4017-866d-27a05767fc5e','123e4567-e89b-12d3-a456-426614174031',2,'Full Time','Sample',
'[{"content": "High level of proficiency with leading UX Design software packages, such as Axure,Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "High level of proficiency with leading UX Design software packages, such as Axure Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "Excellent written and oral communication and presentation skills"},
  {"content": "Excellent written and oral communication and presentation skills"}]'),
('123e4567-e89b-12d3-a456-426614174042','dce8304c-25ae-471a-9203-16f0f5fa1d7e','123e4567-e89b-12d3-a456-426614174031',4,'Full Time','Sample',
'[{"content": "High level of proficiency with leading UX Design software packages, such as Axure,Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "High level of proficiency with leading UX Design software packages, such as Axure Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "Excellent written and oral communication and presentation skills"},
  {"content": "Excellent written and oral communication and presentation skills"}]'),
('123e4567-e89b-12d3-a456-426614174043','978574a0-fba1-49bf-a9bf-7b499064eeb6','123e4567-e89b-12d3-a456-426614174032',0,'Internship','Sample',
'[{"content": "High level of proficiency with leading UX Design software packages, such as Axure,Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "High level of proficiency with leading UX Design software packages, such as Axure Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "Excellent written and oral communication and presentation skills"},
  {"content": "Excellent written and oral communication and presentation skills"}]'),
('123e4567-e89b-12d3-a456-426614174044','95f48793-851b-4afd-bb2b-480175b953f6','123e4567-e89b-12d3-a456-426614174032',3,'Contract','Sample',
'[{"content": "High level of proficiency with leading UX Design software packages, such as Axure,Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "High level of proficiency with leading UX Design software packages, such as Axure Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "Excellent written and oral communication and presentation skills"},
  {"content": "Excellent written and oral communication and presentation skills"}]'),
('123e4567-e89b-12d3-a456-426614174045','7dfb0d96-df85-4016-ac92-3d3482a5995e','123e4567-e89b-12d3-a456-426614174033',2,'Remote','Sample',
'[{"content": "High level of proficiency with leading UX Design software packages, such as Axure,Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "High level of proficiency with leading UX Design software packages, such as Axure Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "Excellent written and oral communication and presentation skills"},
  {"content": "Excellent written and oral communication and presentation skills"}]'),
('123e4567-e89b-12d3-a456-426614174046','91823605-9064-4ccc-a113-7ce49b12bab8','123e4567-e89b-12d3-a456-426614174033',5,'Full Time','Sample',
'[{"content": "High level of proficiency with leading UX Design software packages, such as Axure,Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "High level of proficiency with leading UX Design software packages, such as Axure Sketch, InVision, or Experience Design including the core Adobe Creative Suite products."},
  {"content": "Excellent written and oral communication and presentation skills"},
  {"content": "Excellent written and oral communication and presentation skills"}]');