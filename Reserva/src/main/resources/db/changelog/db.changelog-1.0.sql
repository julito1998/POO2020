create table users(
    id int primary key not null,
    email varchar(100) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    password varchar(100) not null,
    birthDate date not null,
    natioality varchar (100) 
);
--rollback drop table users;


create table booking(
    id int primary key not null,
    check_in  date not null,
    check_out date,
    breakfast_included boolean not null,
    parking boolean not null,
    free_cancelation boolean not null,
    cost float not null,
    user_id integer not null 
);

--rollback drop table booking;
alter table booking add constraint fk_user foreign key (user_id) references users(id);
--rollback drop constraint fk_user;


create table cancellations(
    id int primary key not null,
    created_at date not null,
    booking_id int not null
);

alter table cancellations add constraint fk_booking foreign key (booking_id) references booking(id);
--rollback drop table cancellation;
--rollback drop constraint fk_booking;
--changeset borre_cancellations

create table payments(
    id int primary key not null,
    created_at date not null,
    booking_id int not null,
    card varchar(255) not null,
    card_number varchar(255) not null
);



alter table payments add constraint fk_booking_payments foreign key (booking_id) references booking(id);
--rollback drop table payments;
--rollback drop constraint fk_booking_payments;

--changeset borre_payments

create table rooms(
    id int primary key not null,
    name varchar(255) not null,
    price float not null,
    occupancy int not null,
    availabitlity int not null, 
    facilities varchar(255) not null
);

--rollback drop table rooms;
--changeset borre_rooms

