CREATE DATABASE green_commute_db;
\c green_commute_db;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
SET timezone TO 'UTC';

CREATE USER greencommute_user WITH PASSWORD 'greencommute_password';
