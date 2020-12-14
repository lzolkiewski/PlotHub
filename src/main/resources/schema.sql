-- ************************************** `city`
CREATE TABLE city
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `street`
CREATE TABLE street
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `country`
CREATE TABLE country
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `plot_type`
CREATE TABLE plot_type
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `surrounding`
CREATE TABLE surrounding
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `User`
CREATE TABLE user
(
 id       integer NOT NULL AUTO_INCREMENT ,
 password varchar(45) NOT NULL ,
 email    varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `drive_type`
CREATE TABLE drive_type
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `address`
CREATE TABLE address
(
 id            integer NOT NULL AUTO_INCREMENT ,
 
 city_id       integer NOT NULL ,
 street_id     integer NOT NULL ,
 country_id    integer NOT NULL ,

 PRIMARY KEY (id),

 FOREIGN KEY (city_id) REFERENCES city (id),
 FOREIGN KEY (street_id) REFERENCES street (id),
 FOREIGN KEY (country_id) REFERENCES country (id)
);
-- ************************************** `Offer`
CREATE TABLE offer
(
 id          integer NOT NULL AUTO_INCREMENT ,
 
 length      integer NOT NULL ,
 width       integer NOT NULL ,
 area        integer NOT NULL ,
 price       integer NOT NULL ,
 
 title       varchar(45) NOT NULL ,
 description text NOT NULL ,
 
 fence       bit NOT NULL ,
 building    bit NOT NULL ,

 drive_type_id  integer NOT NULL ,
 plot_type_id   integer NOT NULL ,
 address_id  integer NOT NULL ,
 surrounding_id integer NOT NULL,

 PRIMARY KEY (id),

 FOREIGN KEY (address_id) REFERENCES address (id),
 FOREIGN KEY (drive_type_id) REFERENCES drive_type (id),
 FOREIGN KEY (plot_type_id) REFERENCES plot_type (id),
 FOREIGN KEY (surrounding_id) REFERENCES surrounding(id)
);

-- ************************************** `offerSurrounding`
-- CREATE TABLE offer_surrounding
-- (
--  id             integer NOT NULL AUTO_INCREMENT ,
--  offer_id       integer NOT NULL ,
--  surrounding_id integer NOT NULL ,

--  PRIMARY KEY (id),
--  FOREIGN KEY (offer_id) REFERENCES offer (id),
--  FOREIGN KEY (surrounding_id) REFERENCES surrounding (id)
-- );

-- ************************************** `userOffers`

CREATE TABLE user_offers
(
 id       integer NOT NULL AUTO_INCREMENT ,
 
 user_id  integer NOT NULL ,
 offer_id integer NOT NULL ,
 
 date     date NOT NULL ,

 PRIMARY KEY (id),
 
 FOREIGN KEY (offer_id) REFERENCES offer (id),
 FOREIGN KEY (user_id) REFERENCES user (id)
);