CREATE TABLE course (
    id uuid primary key,
    course_name varchar(255),
    instructor varchar(255),
    room varchar(255),
    course_begin date,
    course_end date
);

CREATE TABLE participant (
    id uuid primary key,
    first_name varchar(255),
    last_name varchar(255),
    email_address varchar(255),
    course_id uuid REFERENCES course(id)
);





