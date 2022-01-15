create table blog_post (
   id  bigserial not null,
    attribute text,
    created_by varchar(255),
    created_on timestamp,
    is_active boolean,
    updated_by varchar(255),
    updated_on timestamp,
    author_id int8 not null,
    content oid,
    title varchar(100) not null,
    view_count int8 not null,
    primary key (id)
)
