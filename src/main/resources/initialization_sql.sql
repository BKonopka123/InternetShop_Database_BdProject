CREATE SCHEMA IF NOT EXISTS sklep;

CREATE TABLE sklep.Klient (
    klient_id SERIAL,
    imie VARCHAR NOT NULL,
    nazwisko VARCHAR NOT NULL,
    pesel VARCHAR NOT NULL,
    data_urodzenia DATE NOT NULL,
    email VARCHAR NOT NULL,
    CONSTRAINT klient_pk PRIMARY KEY (klient_id)
);

CREATE TABLE sklep.Pracownik (
    pracownik_id SERIAL,
    imie VARCHAR NOT NULL,
    nazwisko VARCHAR NOT NULL,
    pesel VARCHAR NOT NULL,
    data_urodzenia DATE NOT NULL,
    pensja INTEGER NOT NULL,
    CONSTRAINT pracownik_pk PRIMARY KEY (pracownik_id)
);

CREATE TABLE sklep.Dostawa (
     dostawa_id SERIAL,
     pracownik_id INTEGER NOT NULL,
     data_zlozenia DATE NOT NULL,
     data_realizacji DATE,
     CONSTRAINT dostawa_pk PRIMARY KEY (dostawa_id)
);

CREATE TABLE sklep.Zamowienie (
    zamowienie_id SERIAL,
    pracownik_id INTEGER NOT NULL,
    klient_id INTEGER NOT NULL,
    data_zamowienia DATE NOT NULL,
    data_zrealizowania DATE DEFAULT NULL,
    CONSTRAINT zamowienie_pk PRIMARY KEY (zamowienie_id)
);

CREATE TABLE sklep.Producent (
    producent_id SERIAL,
    imie VARCHAR NOT NULL,
    nazwisko VARCHAR NOT NULL,
    firma VARCHAR,
    CONSTRAINT producent_pk PRIMARY KEY (producent_id)
);

CREATE TABLE sklep.Typ_produktu (
    typ_produktu_id SERIAL,
    typ_produktu VARCHAR DEFAULT 'plakat' NOT NULL,
    CONSTRAINT typ_produktu_pk PRIMARY KEY (typ_produktu_id)
);

CREATE TABLE sklep.Produkt (
    produkt_id SERIAL,
    typ_produktu_id INTEGER NOT NULL,
    producent_id INTEGER NOT NULL,
    tematyka VARCHAR NOT NULL,
    nazwa VARCHAR NOT NULL,
    nazwa_1 FLOAT NOT NULL,
    rabat INTEGER DEFAULT NULL,
    wysokosc INTEGER NOT NULL,
    szerokosc INTEGER NOT NULL,
    ilosc_egzemplarzy INTEGER,
    CONSTRAINT produkt_pk PRIMARY KEY (produkt_id)
);

CREATE TABLE sklep.Zamowiona_dostawa (
    zamowiona_dostawa_id SERIAL,
    produkt_id INTEGER NOT NULL,
    dostawa_id INTEGER NOT NULL,
    ilosc_egzemplarzy INTEGER NOT NULL,
    CONSTRAINT zamowiona_dostawa_pk PRIMARY KEY (zamowiona_dostawa_id)
);

CREATE TABLE sklep.Ocena (
    ocena_id SERIAL,
    klient_id INTEGER NOT NULL,
    produkt_id INTEGER NOT NULL,
    ocena FLOAT NOT NULL,
    CONSTRAINT ocena_pk PRIMARY KEY (ocena_id)
);

CREATE TABLE sklep.Zamowione_produkty (
    zamowione_produkty_id SERIAL,
    zamowienie_id INTEGER NOT NULL,
    produkt_id INTEGER NOT NULL,
    ilosc_egzemplarzy INTEGER NOT NULL,
    CONSTRAINT Zamowione_produkty_pk PRIMARY KEY (zamowione_produkty_id)
);

ALTER TABLE sklep.Zamowienie ADD CONSTRAINT Klient_Zamowienie_fk
    FOREIGN KEY (klient_id)
        REFERENCES sklep.Klient (klient_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Ocena ADD CONSTRAINT Klient_Ocena_fk
    FOREIGN KEY (klient_id)
        REFERENCES sklep.Klient (klient_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Zamowienie ADD CONSTRAINT Pracownik_Zamowienie_fk
    FOREIGN KEY (pracownik_id)
        REFERENCES sklep.Pracownik (pracownik_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Dostawa ADD CONSTRAINT Pracownik_Dostawa_fk
    FOREIGN KEY (pracownik_id)
        REFERENCES sklep.Pracownik (pracownik_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Zamowiona_dostawa ADD CONSTRAINT Dostawa_Zamowiona_dostawa_fk
    FOREIGN KEY (dostawa_id)
        REFERENCES sklep.Dostawa (dostawa_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Zamowione_produkty ADD CONSTRAINT Zamowienie_Zamowione_produkty_fk
    FOREIGN KEY (zamowienie_id)
        REFERENCES sklep.Zamowienie (zamowienie_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Produkt ADD CONSTRAINT Producent_Produkt_fk
    FOREIGN KEY (producent_id)
        REFERENCES sklep.Producent (producent_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Produkt ADD CONSTRAINT Typ_produktu_Produkt_fk
    FOREIGN KEY (typ_produktu_id)
        REFERENCES sklep.Typ_produktu (typ_produktu_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Zamowione_produkty ADD CONSTRAINT produkt_Zamowione_produkty_fk
    FOREIGN KEY (produkt_id)
        REFERENCES sklep.Produkt (produkt_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Ocena ADD CONSTRAINT produkt_Ocena_fk
    FOREIGN KEY (produkt_id)
        REFERENCES sklep.Produkt (produkt_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE sklep.Zamowiona_dostawa ADD CONSTRAINT produkt_Zamowiona_dostawa_fk
    FOREIGN KEY (produkt_id)
        REFERENCES sklep.Produkt (produkt_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;