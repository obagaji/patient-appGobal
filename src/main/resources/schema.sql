
create TABLE IF not EXISTS Patients
(
    id INTEGER NOT NULL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    middle_name VARCHAR,
    last_name VARCHAR,
    date_of_birth VARCHAR,
    gender VARCHAR,
    phone_no VARCHAR,
    email_address VARCHAR,
    home_address VARCHAR,
    next_of_kin bigint UNIQUE,
     FOREIGN KEY(next_of_kin) REFERENCES NextOfKin(next_of_kin_id)
);
create TABLE IF not EXISTS NextOfKin
(
     next_of_kin_id IDENTITY UNIQUE PRIMARY KEY,
     first_name_nof VARCHAR,
     midlle_name_nof VARCHAR,
     lastNameNof VARCHAR
)