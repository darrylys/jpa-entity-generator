
create table Users (
    id integer identity,
    username varchar(50) not null,
    email varchar(50) not null,
    passwordhash varchar(128) not null,
    primary key (id)
);

create table Users_Mod (
    id integer,
    mod_key varchar(128) not null,
    mod_val varchar(512) not null,
    primary key (id, mod_key),
    constraint fk_usersmod_user foreign key (id) references Users(id)
);

create table Address (
    id integer identity,
    user_id integer not null,
    addressLine1 varchar(128) not null,
    addressLine2 varchar(128),
    city varchar(128) not null,
    postalcode varchar(10) not null,
    primary key (id),
    constraint fk_address_user foreign key (user_id) references Users(id)
);

create table UserLogin (
    id integer identity,
    session_id varchar(32) not null,
    last_login timestamp with time zone,
    primary key(id),
    constraint fk_userlogin_user foreign key (id) references Users(id)
);

create table Book (
    id integer identity,
    title varchar(128) not null,
    blurb varchar(128),
    isbn varchar(128),
    numcode bigint,
    primary key (id)
);

create table Author (
    id integer identity,
    author_name varchar(128) not null,
    registration_ts timestamp with time zone,
    primary key (id)
);

create table Book_Author (
    book_id integer not null,
    author_id integer not null,
    primary key (book_id, author_id),
    constraint fk_book_author_book foreign key (book_id) references Book(id),
    constraint fk_book_author_author foreign key (author_id) references Author(id)
);
