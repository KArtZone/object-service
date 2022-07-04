create table resources
(
    id          uuid,
    title       varchar,
    description varchar,
    state       varchar,
    created_by  uuid,
    constraint resources_pk primary key (id)
)