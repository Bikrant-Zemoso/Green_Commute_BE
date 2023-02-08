CREATE TABLE IF NOT EXISTS Job_Role (
   id UUID PRIMARY KEY,
   role VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO
    Job_Role (id, role)
VALUES
    ('92fbd158-78bc-4017-866d-27a05767fc5e','User Experience Designer'),
    ('dce8304c-25ae-471a-9203-16f0f5fa1d7e','Product Designer'),
    ('7dfb0d96-df85-4016-ac92-3d3482a5995e','User Interface Designer'),
    ('95f48793-851b-4afd-bb2b-480175b953f6','Lead Product Designer'),
    ('91823605-9064-4ccc-a113-7ce49b12bab8','UI/UX Designer'),
    ('978574a0-fba1-49bf-a9bf-7b499064eeb6','Visual Designer');