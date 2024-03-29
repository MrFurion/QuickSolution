/*
 add test user for use all people
 */
insert into users (name, email, password, type)
VALUES ('test_1', 'test1@mail.com', '$2a$10$3g5oUt/kVom9Iubi7Aj5FeeMf0v2R2Fe9pYxRtXXRLgJCrzyapLX2', 'ROLE_USER');
insert into users (name, email, password, type)
VALUES ('test_2', 'test2@mail.com', '$2a$10$1IySCyfwjoQcNwcQS2aIK.d3wlboiihFBOlUCJEMOj40sNs1U10vq', 'ROLE_USER');

CREATE EXTENSION postgis;
/*
 change name and type column coordinates
 */
ALTER TABLE delivery ADD COLUMN finish_coordinates geometry(Point, 4326);
ALTER TABLE delivery RENAME COLUMN coordinates TO start_coordinates;
ALTER TABLE delivery ALTER COLUMN start_coordinates TYPE geometry(Point, 4326) USING ST_SetSRID(start_coordinates::geometry, 4326);

/*
 add new table for type order and delivery
 */
ALTER TABLE Orders DROP COLUMN name;

create table Item(
                     id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                     order_id int references orders(id) on delete cascade,
                     type_orders varchar(100),
                     type_delivery varchar(100)
);