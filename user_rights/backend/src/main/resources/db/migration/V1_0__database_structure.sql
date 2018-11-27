    create table bt_sequences (
        sequencename varchar(255) not null,
        sequencevalue int8,
        primary key (sequencename)
    );

    create table person (
       id int8 not null,
        first_name varchar(255),
        last_name varchar(255),
        version int4 not null,
        primary key (id)
    );
    
     create table mandant (
        id int8 not null,
        deleted boolean not null,
        name varchar(256) not null,
        version int4 not null,
        primary key (id)
    );
    
    create table removed_permissions (
        id int8 not null,
        permission int8 not null,
        version int4 not null,
        user_id int8,
        primary key (id)
    );
    
    create table users (
       id int8 not null,
        deleted boolean not null,
        version int4 not null,
        display_name varchar(256) not null,
        password varchar(256) not null,
        rolle varchar(100) not null,
        username varchar(256) not null,
        mandant_id int8 not null,
        primary key (id)
    );
    
     create table role (
       id int8 not null,
        deleted boolean not null,
        authority_name varchar(256) not null,
        version int4 not null,
        primary key (id)
    );
    
    create table blz (
       id int8 not null,
        bankname varchar(60),
        bic varchar(11),
        blz varchar(8) not null,
        deleted boolean DEFAULT FALSE NOT NULL,
        deleted_at timestamp,
        deletedbyid int8,
        version int4 not null,
        primary key (id)
    );
    
alter table users 
       add constraint FK1wvpetretfmrq1rss3hyc5802 
       foreign key (mandant_id) 
       references mandant;

alter table removed_permissions 
       add constraint FKa5b3ngk6a69hhy8cp120idyn 
       foreign key (user_id) 
       references users;

alter table blz 
       add constraint FK6y5k59s7jd3gd7vwnnbp3tgy5 
       foreign key (deletedbyid) 
       references users;       
       
       
insert into bt_sequences (SEQUENCENAME, SEQUENCEVALUE)  values ('mandant',2);
insert into bt_sequences (SEQUENCENAME, SEQUENCEVALUE)  values ('removed_permissions',0);
insert into bt_sequences (SEQUENCENAME, SEQUENCEVALUE)  values ('users',2);
insert into bt_sequences (sequencename, sequencevalue)  values ('person',14);


insert into mandant (name, deleted, version, id) values ('HOMEDEV', false, 0, 1);
insert into users (deleted, password, username, version, id, mandant_id, rolle, display_name) values (false, '$2a$12$M07JzOtBuXe3EnyzYBMKSeB8m.OP/avK9m8JgpaeLY15iARenXSB2', 'user', 0, 1, 1, 'ROLE_USER','M. Musterman (user)');
insert into users (deleted, password, username, version, id, mandant_id, rolle, display_name) values (false, '$2a$12$RNhi365o0Pb9ylREvLAmeeDEDUbtxB8uce2uG6n.J1MQRfOewY056', 'admin', 0, 1, 1, 'ROLE_ADMIN','M. Musterman (admin)');

insert into person (id, version, first_name, last_name)  values (1, 0, 'Bill', 'Gates');
insert into person (id, version, first_name, last_name)  values (2, 0, 'Steve', 'Jobs');
insert into person (id, version, first_name, last_name)  values (3, 0, 'Robert', 'Müller');
insert into person (id, version, first_name, last_name)  values (4, 0, 'Anna', 'Krüger');
insert into person (id, version, first_name, last_name)  values (5, 0, 'Markus', 'Albrecht');
insert into person (id, version, first_name, last_name)  values (6, 0, 'Robin', 'Schulz');
insert into person (id, version, first_name, last_name)  values (7, 0, 'Thomas', 'Courieres');
insert into person (id, version, first_name, last_name)  values (8, 0, 'Markus', 'Pöschl');
insert into person (id, version, first_name, last_name)  values (9, 0, 'Heike', 'Bisschop');
insert into person (id, version, first_name, last_name)  values (10, 0, 'Alina', 'Wettengel');
insert into person (id, version, first_name, last_name)  values (11, 0, 'Sara', 'Adams');
insert into person (id, version, first_name, last_name)  values (12, 0, 'Tatjana', 'Müller');
