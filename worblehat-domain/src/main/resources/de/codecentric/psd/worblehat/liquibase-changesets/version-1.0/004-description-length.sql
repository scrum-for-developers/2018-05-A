-- liquibase formatted sql

-- changeset action:description-length

alter Table book drop column description;

alter Table book add column description varchar(20000);