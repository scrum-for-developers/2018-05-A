-- liquibase formatted sql

-- changeset action:add_description

alter Table book add column description varchar(2048);