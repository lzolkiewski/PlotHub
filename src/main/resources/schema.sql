-- ************************************** `city`
CREATE TABLE City
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `street`
CREATE TABLE Street
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `country`
CREATE TABLE Country
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `plot_type`
CREATE TABLE PlotType
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);

-- ************************************** `surrounding`
CREATE TABLE Surrounding
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `User`
CREATE TABLE User
(
 id       integer NOT NULL AUTO_INCREMENT ,
 password varchar(45) NOT NULL ,
 email    varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `drive_type`
CREATE TABLE DriveType
(
 id   integer NOT NULL AUTO_INCREMENT ,
 name varchar(45) NOT NULL ,

 PRIMARY KEY (id)
);
-- ************************************** `address`
CREATE TABLE Address
(
 id            integer NOT NULL AUTO_INCREMENT ,
 street_number integer NOT NULL ,
 city_id       integer NOT NULL ,
 street_id     integer NOT NULL ,
 country_id    integer NOT NULL ,

 PRIMARY KEY (id),
 FOREIGN KEY (city_id) REFERENCES City (id),
 FOREIGN KEY (street_id) REFERENCES Street (id),
 FOREIGN KEY (country_id) REFERENCES Country (id)
);
-- ************************************** `Offer`
CREATE TABLE Offer
(
 id          integer NOT NULL AUTO_INCREMENT ,
 title       varchar(45) NOT NULL ,
 length      double NOT NULL ,
 width       double NOT NULL ,
 area        double NOT NULL ,
 description text NOT NULL ,
 price       double NOT NULL ,
 drive_type_id  integer NOT NULL ,
 plot_type_id   integer NOT NULL ,
 fence       bit NOT NULL ,
 building    bit NOT NULL ,
 address_id  integer NOT NULL ,

 PRIMARY KEY (id),
 FOREIGN KEY (address_id) REFERENCES Address (id),
 FOREIGN KEY (drive_type_id) REFERENCES DriveType (id),
 FOREIGN KEY (plot_type_id) REFERENCES PlotType (id)
);
-- ************************************** `offerSurrounding`
CREATE TABLE OfferSurrounding
(
 id             integer NOT NULL AUTO_INCREMENT ,
 offer_id       integer NOT NULL ,
 surrounding_id integer NOT NULL ,

 PRIMARY KEY (id),
 FOREIGN KEY (offer_id) REFERENCES Offer (id),
 FOREIGN KEY (surrounding_id) REFERENCES Surrounding (id)
);

-- ************************************** `userOffers`
CREATE TABLE UserOffers
(
 id       integer NOT NULL AUTO_INCREMENT ,
 user_id  integer NOT NULL ,
 offer_id integer NOT NULL ,
 date     date NOT NULL ,

 PRIMARY KEY (id),
 FOREIGN KEY (offer_id) REFERENCES Offer (id),
 FOREIGN KEY (user_id) REFERENCES User (id)
);