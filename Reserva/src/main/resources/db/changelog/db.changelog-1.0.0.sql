create table users(
    id serial primary key not null,
    email varchar(100) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    password varchar(100) not null,
    birthDate date not null,
    natioality varchar (100)
);
--rollback drop table users;
--changeset develop:1

create table rooms(
    id serial primary key not null,
    name varchar(255) not null,
    price float not null,
    occupancy int not null,
    availabitlity int not null, 
    facilities varchar(255) not null
);
--rollback drop table rooms;
--changeset develop:2

create table booking(
    id serial primary key not null,
    check_in  date not null,
    check_out date,
    breakfast_included boolean not null,
    parking boolean not null,
    free_cancelation boolean not null,
    cost float not null,
    user_id bigint not null,
    room_id bigint not null
);

--rollback drop table booking;
--changeset develop:3
alter table booking add constraint fk_user foreign key (user_id) references users(id);
alter table booking add constraint fk_room foreign key (room_id) references rooms(id);
--rollback drop constraint fk_user;
--changeset develop:4
--rollback drop constraint fk_room;
--changeset develop:5

create table cancellations(
    id serial primary key not null,
    created_at date not null,
    booking_id bigint not null
);
--rollbanck drop cancellations
--changeset develop:6
alter table cancellations add constraint fk_booking foreign key (booking_id) references booking(id);
--rollback drop table cancellation;
--changeset develop:7
--rollback drop constraint fk_booking;
--changeset develop:8

create table payments(
    id serial primary key not null,
    created_at date not null,
    booking_id bigint not null,
    card varchar(255) not null,
    card_number varchar(255) not null
);
--rollback drop table payments;
--changeset develop:9
alter table payments add constraint fk_booking_payments foreign key (booking_id) references booking(id);
--rollback drop constraint fk_booking_payments;
--changeset develop:10


