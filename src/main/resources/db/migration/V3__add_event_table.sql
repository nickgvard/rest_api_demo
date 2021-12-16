create table events (
    id int primary key auto_increment,
    user_id int null,
    file_id int null,
    name varchar(30),
    constraint event_user_fkey foreign key (user_id) references li0kf6qceun7hant.users(id) on delete cascade on update cascade,
    constraint event_file_fkey foreign key (file_id) references li0kf6qceun7hant.files(id) on delete cascade on update cascade
)