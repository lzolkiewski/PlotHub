insert into user
    (password, email)
    values
        ('admin', 'admin@admin.pl'),
        ('lucas', 'lucas@inter.pl'),
        ('kings', 'kin@boi.com');

insert into city
    (name)
    values
        ('Tomaszów Lubelski'),
        ('Warszawa'),
        ('Szczecin'),
        ('Majdan Sopocki');

insert into street
    (name)
    values
        ('Lwowska'),
        ('Bohaterów Warszawy'),
        ('Króla Zygmunta'),
        ('Tomaszowska');
insert into country
    (name)
    values
        ('Polska'),
        ('Niemcy'),
        ('Ukraina'),
        ('Czechy');
insert into plot_type
    (name)
    values
        ('budowlana'),
        ('leśna'),
        ('rekreacyjna'),
        ('inwestycyjna'),
        ('rolna');
insert into surrounding
    (name)
    values
        ('las'),
        ('jezioro'),
        ('osiedle'),
        ('góry'),
        ('rzeka'),
        ('morze');
insert into drive_type
    (name)
    values
        ('gruntowy'),
        ('asfaltowy'),
        ('utwardzony');
insert into address
    (city_id, street_id, country_id)
    values
        (1, 3, 1),
        (1, 1 ,1),
        (2, 2, 1),
        (3, 3, 1);
insert into offer
    (title, length, width, area, description, price, drive_type_id, plot_type_id, fence, building, address_id, surrounding_id)
    values
        ('Działka z budynkiem pod lasem', 28, 20, 560, 'Sprzedam ładną działkę pod lasem.', 40000, 1, 1, 1, 1, 1, 1),
        ('Nie ma lepszej', 40, 40, 1600, 'Działka nad jeziorem o dużej powierzchni. Wybudujesz tu niemal wszystko co chcesz. Pisać proszę na maila.', 15000, 3, 1, 1, 0, 2, 2),
        ('Jedyna w swoim rodzaju', 50, 50, 2500, 'Duża i ładna działka na nowym osiedlu. Idealna dla ludzi szukających miłej okolicy.', 250000, 3, 1, 0, 0, 3, 3);
insert into user_offers
    (user_id, offer_id, date)
    values
        (2, 1, '2020-12-01'),
        (2, 2, '2020-12-02'),
        (3, 3, '2020-12-03');

