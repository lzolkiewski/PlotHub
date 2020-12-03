insert into User
    (password, email)
    values
        ('admin', 'admin@admin.pl'),
        ('lucas', 'lucas@inter.pl'),
        ('kin', 'kin@boi.com');

insert into City
    (name)
    values
        ('Tomaszów Lubelski'),
        ('Warszawa'),
        ('Szczecin'),
        ('Majdan Sopocki');

insert into Street
    (name)
    values
        ('Lwowska'),
        ('Bohaterów Warszawy'),
        ('Króla Zygmunta'),
        ('Tomaszowska');
insert into Country
    (name)
    values
        ('Polska'),
        ('Niemcy'),
        ('Ukraina'),
        ('Czechy');
insert into PlotType
    (name)
    values
        ('Budowlana'),
        ('Leśna'),
        ('Rekreacyjna'),
        ('Rolna');
insert into Surrounding
    (name)
    values
        ('las'),
        ('jezioro'),
        ('osiedle'),
        ('góry'),
        ('rzeka'),
        ('morze');
insert into DriveType
    (name)
    values
        ('gruntowa'),
        ('asfaltowa'),
        ('utwardzana');
insert into Address
    (street_number, city, street, country)
    values
        (15, 1, 3, 1),
        (30, 1, 1 ,1),
        (45, 2, 2, 1),
        (60, 3, 3, 1);
insert into Offer
    (title, length, width, area, description, price, drive_type, plot_type, fence, building, address)
    values
        ('Super oferta', 20, 20, 400, 'Budynek super świetny', 40000, 1, 1, 1, 1, 1),
        ('Nie ma lepszej', 15, 10, 150, 'No ładna działeczka na sprzedaż', 15000, 3, 1, 0, 0, 2),
        ('Jedyna w swoim rodzaju', 50, 50, 2500, 'Duża i ładna', 250000, 3, 1, 0, 0, 3);
insert into OfferSurrounding
    (offer_id, surrounding_id)
    values
        (1, 1),(1, 6),
        (2, 2),(2, 5),
        (3, 3),(3, 4);
insert into UserOffers
    (user_id, offer_id, date)
    values
        (2, 1, '2020-12-01'),
        (2, 2, '2020-12-02'),
        (3, 3, '2020-12-03');

