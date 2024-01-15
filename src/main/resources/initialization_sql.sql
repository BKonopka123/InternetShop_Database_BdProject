--Utworzenie Schamatu i tablic
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
     data_realizacji DATE DEFAULT NULL,
     CONSTRAINT dostawa_pk PRIMARY KEY (dostawa_id)
);

CREATE TABLE sklep.Zamowienie (
    zamowienie_id SERIAL,
    pracownik_id INTEGER NOT NULL DEFAULT 1,
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
    cena INTEGER NOT NULL,
    rabat INTEGER DEFAULT 0,
    wysokosc INTEGER NOT NULL,
    szerokosc INTEGER NOT NULL,
    ilosc_egzemplarzy INTEGER DEFAULT 100,
    ocena FLOAT DEFAULT 0,
    ilosc_ocen INTEGER DEFAULT 0,
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

--Utworzenie funkcji, tryggerów

CREATE OR REPLACE FUNCTION sklep.add_ocena_function() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE sklep.Produkt SET ilosc_ocen = ilosc_ocen + 1 WHERE produkt_id = NEW.produkt_id;
    UPDATE sklep.Produkt SET ocena =
        (((SELECT ocena FROM sklep.Produkt WHERE produkt_id = NEW.produkt_id) + NEW.ocena) /
        (SELECT ilosc_ocen FROM sklep.Produkt WHERE produkt_id = NEW.produkt_id)
    ) WHERE produkt_id = NEW.produkt_id;
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER add_ocena_trigger BEFORE INSERT OR UPDATE ON sklep.Ocena
    FOR EACH ROW EXECUTE PROCEDURE sklep.add_ocena_function();

CREATE OR REPLACE FUNCTION sklep.add_zamowienie_function() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE sklep.Produkt SET ilosc_egzemplarzy = ilosc_egzemplarzy - NEW.ilosc_egzemplarzy WHERE produkt_id = NEW.produkt_id;
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER add_zamowienie_trigger BEFORE INSERT OR UPDATE ON sklep.Zamowione_produkty
    FOR EACH ROW EXECUTE PROCEDURE sklep.add_zamowienie_function();

CREATE OR REPLACE FUNCTION sklep.add_dostawa_function() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE sklep.Produkt SET ilosc_egzemplarzy = ilosc_egzemplarzy + NEW.ilosc_egzemplarzy WHERE produkt_id = NEW.produkt_id;
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER add_dostawa_trigger BEFORE INSERT OR UPDATE ON sklep.Zamowiona_dostawa
    FOR EACH ROW EXECUTE PROCEDURE sklep.add_dostawa_function();

--Walidacja danych

CREATE OR REPLACE FUNCTION validate_klient()
    RETURNS TRIGGER AS $$
BEGIN
    IF CHAR_LENGTH(NEW.imie) < 2 OR CHAR_LENGTH(NEW.nazwisko) < 2 THEN
        RAISE EXCEPTION 'Imię i nazwisko muszą mieć co najmniej 2 znaki.';
    END IF;
    IF LENGTH(NEW.pesel) <> 11 OR NOT NEW.pesel ~ E'^\\d+$' THEN
        RAISE EXCEPTION 'Pesel musi mieć dokładnie 11 cyfr.';
    END IF;
    IF NEW.data_urodzenia > current_date THEN
        RAISE EXCEPTION 'Data urodzenia nie może być z przyszłości.';
    END IF;
    IF NEW.email NOT LIKE '%@%' THEN
        RAISE EXCEPTION 'Email musi zawierać znak @.';
    END IF;
    IF pg_typeof(NEW.imie) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Imię musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.nazwisko) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Nazwisko musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.pesel) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Pesel musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.data_urodzenia) <> pg_typeof(current_date::date) THEN
        RAISE EXCEPTION 'Data urodzenia musi być typu datowego.';
    END IF;
    IF pg_typeof(NEW.email) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Email musi być typu tekstowego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_klient_trigger BEFORE INSERT OR UPDATE
    ON sklep.Klient FOR EACH ROW EXECUTE FUNCTION validate_klient();

CREATE OR REPLACE FUNCTION validate_pracownik()
    RETURNS TRIGGER AS $$
BEGIN
    IF CHAR_LENGTH(NEW.imie) < 2 OR CHAR_LENGTH(NEW.nazwisko) < 2 THEN
        RAISE EXCEPTION 'Imię i nazwisko muszą mieć co najmniej 2 znaki.';
    END IF;
    IF LENGTH(NEW.pesel) <> 11 OR NOT NEW.pesel ~ E'^\\d+$' THEN
        RAISE EXCEPTION 'Pesel musi mieć dokładnie 11 cyfr.';
    END IF;
    IF NEW.data_urodzenia > current_date THEN
        RAISE EXCEPTION 'Data urodzenia nie może być z przyszłości.';
    END IF;
    IF pg_typeof(NEW.imie) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Imię musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.nazwisko) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Nazwisko musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.pesel) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Pesel musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.data_urodzenia) <> pg_typeof(current_date::date) THEN
        RAISE EXCEPTION 'Data urodzenia musi być typu datowego.';
    END IF;
    IF pg_typeof(NEW.pensja) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Pensja musi być typu całkowitego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_pracownik_trigger BEFORE INSERT OR UPDATE
    ON sklep.Pracownik FOR EACH ROW EXECUTE FUNCTION validate_pracownik();

CREATE OR REPLACE FUNCTION validate_dostawa()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.data_zlozenia > current_date THEN
        RAISE EXCEPTION 'Data złożenia nie może być z przyszłości.';
    END IF;
    IF NEW.data_realizacji > current_date THEN
        RAISE EXCEPTION 'Data realizacji nie może być z przyszłości.';
    END IF;
    IF pg_typeof(NEW.pracownik_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Pracownik_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.data_zlozenia) <> pg_typeof(current_date::date) THEN
        RAISE EXCEPTION 'Data złożenia musi być typu datowego.';
    END IF;
    IF pg_typeof(NEW.data_realizacji) <> pg_typeof(current_date::date) THEN
        RAISE EXCEPTION 'Data realizacji musi być typu datowego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_dostawa_trigger BEFORE INSERT OR UPDATE
    ON sklep.Dostawa FOR EACH ROW EXECUTE FUNCTION validate_dostawa();

CREATE OR REPLACE FUNCTION validate_zamowienie()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.data_zamowienia > current_date THEN
        RAISE EXCEPTION 'Data zamówienia nie może być z przyszłości.';
    END IF;
    IF NEW.data_zrealizowania > current_date THEN
        RAISE EXCEPTION 'Data zrealizowania nie może być z przyszłości.';
    END IF;
    IF pg_typeof(NEW.pracownik_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Pracownik_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.klient_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Klient_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.data_zamowienia) <> pg_typeof(current_date::date) THEN
        RAISE EXCEPTION 'Data zamówienia musi być typu datowego.';
    END IF;
    IF pg_typeof(NEW.data_zrealizowania) <> pg_typeof(current_date::date) THEN
        RAISE EXCEPTION 'Data zrealizowania musi być typu datowego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_zamowienie_trigger BEFORE INSERT OR UPDATE
    ON sklep.Zamowienie FOR EACH ROW EXECUTE FUNCTION validate_zamowienie();

CREATE OR REPLACE FUNCTION validate_producent()
    RETURNS TRIGGER AS $$
BEGIN
    IF CHAR_LENGTH(NEW.imie) < 2 OR CHAR_LENGTH(NEW.nazwisko) < 2 THEN
        RAISE EXCEPTION 'Imię i nazwisko muszą mieć co najmniej 2 znaki.';
    END IF;
    IF pg_typeof(NEW.imie) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Imię musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.nazwisko) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Nazwisko musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.firma) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Firma musi być typu tekstowego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_producent_trigger BEFORE INSERT OR UPDATE
    ON sklep.Producent FOR EACH ROW EXECUTE FUNCTION validate_producent();

CREATE OR REPLACE FUNCTION validate_typ_produktu()
    RETURNS TRIGGER AS $$
BEGIN
    IF CHAR_LENGTH(NEW.typ_produktu) < 2 THEN
        RAISE EXCEPTION 'Typ produktu musi mieć co najmniej 2 znaki.';
    END IF;
    IF pg_typeof(NEW.typ_produktu) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Typ produktu musi być typu tekstowego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_typ_produktu_trigger BEFORE INSERT OR UPDATE
    ON sklep.Typ_produktu FOR EACH ROW EXECUTE FUNCTION validate_typ_produktu();

CREATE OR REPLACE FUNCTION validate_produkt()
    RETURNS TRIGGER AS $$
BEGIN
    IF CHAR_LENGTH(NEW.tematyka) < 2 THEN
        RAISE EXCEPTION 'Tematyka musi mieć co najmniej 2 znaki.';
    END IF;
    IF CHAR_LENGTH(NEW.nazwa) < 2 THEN
        RAISE EXCEPTION 'Nazwa musi mieć co najmniej 2 znaki.';
    END IF;
    IF NEW.cena < 0 THEN
        RAISE EXCEPTION 'Cena nie może być ujemna.';
    END IF;
    IF NEW.rabat < 0 THEN
        RAISE EXCEPTION 'Rabat nie może być ujemny.';
    END IF;
    IF NEW.wysokosc < 0 THEN
        RAISE EXCEPTION 'Wysokość nie może być ujemna.';
    END IF;
    IF NEW.szerokosc < 0 THEN
        RAISE EXCEPTION 'Szerokość nie może być ujemna.';
    END IF;
    IF NEW.ilosc_egzemplarzy < 0 THEN
        RAISE EXCEPTION 'Ilość egzemplarzy nie może być ujemna.';
    END IF;
    IF NEW.ocena < 0 THEN
        RAISE EXCEPTION 'Ocena nie może być ujemna.';
    END IF;
    IF NEW.ilosc_ocen < 0 THEN
        RAISE EXCEPTION 'Ilość ocen nie może być ujemna.';
    END IF;
    IF pg_typeof(NEW.typ_produktu_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Typ produktu musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.producent_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Producent musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.tematyka) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Tematyka musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.nazwa) <> pg_typeof(''::varchar) THEN
        RAISE EXCEPTION 'Nazwa musi być typu tekstowego.';
    END IF;
    IF pg_typeof(NEW.cena) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Cena musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.rabat) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Rabat musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.wysokosc) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Wysokość musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.szerokosc) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Szerokość musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.ilosc_egzemplarzy) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Ilość egzemplarzy musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.ocena) <> pg_typeof(1.0::float) THEN
        RAISE EXCEPTION 'Ocena musi być typu zmiennoprzecinkowego.';
    END IF;
    IF pg_typeof(NEW.ilosc_ocen) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Ilość ocen musi być typu całkowitego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_produkt_trigger BEFORE INSERT OR UPDATE
    ON sklep.Produkt FOR EACH ROW EXECUTE FUNCTION validate_produkt();

CREATE OR REPLACE FUNCTION validate_zamowiona_dostawa()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.ilosc_egzemplarzy < 0 THEN
        RAISE EXCEPTION 'Ilość egzemplarzy nie może być ujemna.';
    END IF;
    IF pg_typeof(NEW.produkt_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Produkt_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.dostawa_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Dostawa_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.ilosc_egzemplarzy) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Ilość egzemplarzy musi być typu całkowitego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_zamowiona_dostawa_trigger BEFORE INSERT OR UPDATE
    ON sklep.Zamowiona_dostawa FOR EACH ROW EXECUTE FUNCTION validate_zamowiona_dostawa();

CREATE OR REPLACE FUNCTION validate_zamowione_produkty()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.ilosc_egzemplarzy < 0 THEN
        RAISE EXCEPTION 'Ilość egzemplarzy nie może być ujemna.';
    END IF;
    IF pg_typeof(NEW.produkt_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Produkt_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.zamowienie_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Zamowienie_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.ilosc_egzemplarzy) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Ilość egzemplarzy musi być typu całkowitego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_zamowione_produkty_trigger BEFORE INSERT OR UPDATE
    ON sklep.Zamowione_produkty FOR EACH ROW EXECUTE FUNCTION validate_zamowione_produkty();

CREATE OR REPLACE FUNCTION validate_ocena()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.ocena < 0 THEN
        RAISE EXCEPTION 'Ocena nie może być ujemna.';
    END IF;
    IF pg_typeof(NEW.klient_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Klient_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.produkt_id) <> pg_typeof(1::int) THEN
        RAISE EXCEPTION 'Produkt_id musi być typu całkowitego.';
    END IF;
    IF pg_typeof(NEW.ocena) <> pg_typeof(1.0::float) THEN
        RAISE EXCEPTION 'Ocena musi być typu zmiennoprzecinkowego.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER validate_ocena_trigger BEFORE INSERT OR UPDATE
    ON sklep.Ocena FOR EACH ROW EXECUTE FUNCTION validate_ocena();

--Utworzenie widoków

CREATE VIEW raport_1_view AS
    SELECT produkt_id, tematyka, nazwa, cena, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen FROM sklep.Produkt
    WHERE ilosc_ocen > 0
    ORDER BY produkt_id;

CREATE VIEW raport_2_view AS
    SELECT z.zamowienie_id, z.klient_id, z.data_zamowienia, z.data_zrealizowania, SUM(zp.ilosc_egzemplarzy * (p.cena - p.rabat)) AS koszt_zamowienie
    FROM sklep.Zamowienie z
    JOIN sklep.Zamowione_produkty zp USING(zamowienie_id)
    JOIN sklep.Produkt p USING(produkt_id)
    GROUP BY z.zamowienie_id
    ORDER BY z.zamowienie_id;

CREATE VIEW raport_3_view AS
    SELECT k.klient_id, k.imie, k.nazwisko, k.email, COUNT(DISTINCT z.zamowienie_id) AS ilosc_zamowien, SUM(zp.ilosc_egzemplarzy * (p.cena - p.rabat)) /  COUNT(DISTINCT z.zamowienie_id) AS sredni_koszt_zamowienia
    FROM sklep.Klient k
    JOIN sklep.Zamowienie z USING (klient_id)
    JOIN sklep.Zamowione_produkty zp USING (zamowienie_id)
    JOIN sklep.Produkt p USING (produkt_id)
    GROUP BY k.klient_id
    HAVING COUNT(DISTINCT z.zamowienie_id) > 1;

--Wypełnienie tabel danymi

INSERT INTO sklep.Typ_produktu (typ_produktu) VALUES ('plakat');     --1
INSERT INTO sklep.Typ_produktu (typ_produktu) VALUES ('obraz');      --2
INSERT INTO sklep.Typ_produktu (typ_produktu) VALUES ('antyrama');   --3

INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Jan', 'Kowalski', 'Kowal');         --1
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Adam', 'Nowak', 'Nowak');           --2
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Piotr', 'Marciniak', 'Marciniak');  --3
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Anna', 'Zwierciadło', 'Lustro');    --4
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Małgorzata', 'Kleks', 'Plama');     --5
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Katarzyna', 'Kowalska', 'Kowal');   --6
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Jan', 'Nowak', 'Nowak');            --7
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Adam', 'Złocień', 'Sprzedstwo');    --8
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Piotr', 'Krawczyk', 'Drzewa');      --9
INSERT INTO sklep.Producent (imie, nazwisko, firma) VALUES ('Elżbieta', 'Waciąg', 'Plama');      --10

INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 1, 'zwierzęta', 'Kot', 60, 0, 50, 50, 100, 0, 0);           --1
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 2, 'zwierzęta', 'Pies', 50, 0, 50, 50, 100, 0, 0);          --2
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 3, 'zwierzęta', 'Koń', 40, 0, 50, 50, 100, 0, 0);           --3
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 4, 'zwierzęta', 'Krowa', 55, 0, 50, 50, 100, 0, 0);         --4
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (2, 5, 'zwierzęta', 'Świnia', 100, 0, 50, 50, 100, 0, 0);        --5
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 6, 'film', 'Star Wars', 70, 0, 90, 90, 200, 0, 0);          --6
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 7, 'film', 'Piraci z Karaibów', 90, 10, 90, 90, 200, 0, 0); --7
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 8, 'film', 'Harry Potter', 80, 15, 90, 90, 200, 0, 0);      --8
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 9, 'film', 'Władca Pierścieni', 60, 10, 90, 90, 200, 0, 0); --9
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (2, 10, 'film', 'Pulp Fiction', 100, 0, 60, 60, 100, 0, 0);      --10
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 1, 'serial', 'Black Mirror', 40, 10, 60, 60, 200, 0, 0);    --11
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 2, 'serial', 'Breaking Bad', 50, 0, 60, 60, 300, 0, 0);     --12
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 3, 'serial', 'Gra o Tron', 55, 0, 60, 60, 400, 0, 0);       --13
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 4, 'serial', 'Stranger Things', 95, 0, 60, 60, 500, 0, 0);  --14
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (2, 5, 'serial', 'Breaking Bad 2', 120, 0, 50, 50, 100, 0, 0);   --15
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 6, 'krajobraz', 'Jezioro', 70, 10, 90, 50, 200, 0, 0);      --16
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 7, 'krajobraz', 'Góry', 80, 0, 90, 50, 200, 0, 0);          --17
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (1, 8, 'krajobraz', 'Morze', 40, 0, 90, 50, 200, 0, 0);         --18
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (2, 9, 'krajobraz', 'Pustynia', 30, 0, 90, 50, 200, 0, 0);      --19
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (2, 10, 'krajobraz', 'Rzeka', 20, 0, 90, 50, 300, 0, 0);        --20
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (3, 1, 'antyrama', 'Antyrama 1', 40, 0, 50, 50, 100, 0, 0);     --21
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (3, 2, 'antyrama', 'Antyrama 2', 70, 10, 90, 90, 200, 0, 0);     --22
INSERT INTO sklep.Produkt (typ_produktu_id, producent_id, tematyka, nazwa, cena, rabat, wysokosc, szerokosc, ilosc_egzemplarzy, ocena, ilosc_ocen) VALUES (3, 3, 'antyrama', 'Antyrama 3', 50, 0, 60, 60, 100, 0, 0);     --23

INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Maciej', 'Adamczyk', '12345678901', '1990-01-01', 'kowal@gmail.com');      --1
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Zbigniew', 'Nowak', '12345678902', '1990-01-02', 'nowak@gmail.com');       --2
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Piotr', 'Wałęsa', '12345678903', '1990-01-03', 'walesa@gmail.com');        --3
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Anna', 'Kowalska', '12345678904', '1990-01-04', 'kowalska@gmail.com');     --4
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Małgorzata', 'Kowal', '12345678905', '1990-01-05', 'gosia@gmail.com');     --5
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Katarzyna', 'Kowalska', '12345678906', '1990-01-06', 'kasia@gmail.com');   --6
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Maciej', 'Walkakik', '12345678907', '1990-01-07', 'walk@gmail.com');       --7
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Zbigniew', 'Nowak', '12345678908', '1990-01-08', 'nowakz@gmail.com');      --8
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('Piotr', 'Kaczyński', '12345678909', '1990-01-09', 'kaczor@gmail.com');     --9
INSERT INTO sklep.Klient (imie, nazwisko, pesel, data_urodzenia, email) VALUES ('MArta', 'Krawczyk', '12345678910', '1990-01-10', 'krawczyk@gmail.com');   --10

INSERT INTO sklep.Pracownik (imie, nazwisko, pesel, data_urodzenia, pensja) VALUES ('Jan', 'Zebrzydowski', '12345678911', '1990-01-01', 1000);     --1
INSERT INTO sklep.Pracownik (imie, nazwisko, pesel, data_urodzenia, pensja) VALUES ('Adam', 'Kalczyk', '12345678912', '1990-01-02', 2000);         --2
INSERT INTO sklep.Pracownik (imie, nazwisko, pesel, data_urodzenia, pensja) VALUES ('Piotr', 'Maciąg', '12345678913', '1990-01-03', 3000);         --3
INSERT INTO sklep.Pracownik (imie, nazwisko, pesel, data_urodzenia, pensja) VALUES ('Anna', 'Wleń', '12345678914', '1990-01-04', 4000);            --4
INSERT INTO sklep.Pracownik (imie, nazwisko, pesel, data_urodzenia, pensja) VALUES ('Małgorzata', 'Zbych', '12345678915', '1990-01-05', 5000);     --5

INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (1, 1, '2019-01-01', '2019-01-02');     --1
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (2, 2, '2019-01-02', '2019-01-03');     --2
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (3, 3, '2019-01-03', '2019-01-04');     --3
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (4, 4, '2019-01-04', '2019-01-05');     --4
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (5, 5, '2019-01-05', '2019-01-06');     --5
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (1, 6, '2019-01-06', '2019-01-07');     --6
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (2, 7, '2019-01-07', '2019-01-08');     --7
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (3, 8, '2019-01-08', '2019-01-09');     --8
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (4, 9, '2019-01-09', '2019-01-10');     --9
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (5, 10, '2019-01-10', '2019-01-11');    --10
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia, data_zrealizowania) VALUES (1, 1, '2019-01-11', '2019-01-12');     --11
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia) VALUES (2, 2, '2019-01-12');     --12
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia) VALUES (3, 3, '2019-01-13');     --13
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia) VALUES (4, 4, '2019-01-14');     --14
INSERT INTO sklep.Zamowienie (pracownik_id, klient_id, data_zamowienia) VALUES (5, 5, '2019-01-15');     --15

INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (1, 1, 3);     --1
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (1, 2, 2);     --2
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (1, 3, 1);     --3
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (2, 4, 3);     --4
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (2, 5, 2);     --5
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (3, 6, 1);     --6
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (3, 7, 3);     --7
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (4, 8, 2);     --8
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (5, 9, 1);     --9
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (6, 10, 3);    --10
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (7, 11, 2);    --11
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (8, 12, 1);    --12
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (8, 13, 3);    --13
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (9, 14, 2);    --14
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (10, 15, 1);   --15
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (10, 16, 3);   --16
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (10, 17, 2);   --17
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (11, 18, 1);   --18
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (12, 19, 3);   --19
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (13, 20, 2);   --20
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (14, 21, 1);   --21
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (15, 22, 1);   --22
INSERT INTO sklep.Zamowione_produkty (zamowienie_id, produkt_id, ilosc_egzemplarzy) VALUES (15, 23, 2);   --23

INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (1, 1, 5);     --1
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (1, 2, 4);     --2
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (1, 3, 3);     --3
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (2, 4, 2);     --4
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (2, 5, 1);     --5
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (3, 6, 5);     --6
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (3, 7, 4);     --7
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (4, 8, 3);     --8
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (4, 9, 2);     --9
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (5, 10, 1);    --10
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (5, 11, 5);    --11
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (6, 12, 4);    --12
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (6, 13, 3);    --13
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (7, 14, 2);    --14
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (7, 15, 1);    --15
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (8, 16, 5);    --16
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (8, 17, 4);    --17
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (9, 18, 3);    --18
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (9, 19, 2);    --19
INSERT INTO sklep.Ocena (klient_id, produkt_id, ocena) VALUES (10, 20, 1);   --20

INSERT INTO sklep.Dostawa (pracownik_id, data_zlozenia, data_realizacji) VALUES (1, '2019-01-01', '2019-01-02');     --1
INSERT INTO sklep.Dostawa (pracownik_id, data_zlozenia, data_realizacji) VALUES (2, '2019-01-02', '2019-01-03');     --2
INSERT INTO sklep.Dostawa (pracownik_id, data_zlozenia, data_realizacji) VALUES (3, '2019-01-03', '2019-01-04');     --3
INSERT INTO sklep.Dostawa (pracownik_id, data_zlozenia, data_realizacji) VALUES (4, '2019-01-04', '2019-01-05');     --4
INSERT INTO sklep.Dostawa (pracownik_id, data_zlozenia, data_realizacji) VALUES (5, '2019-01-05', '2019-01-06');     --5

INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (1, 1, 10);     --1
INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (2, 1, 13);     --2
INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (3, 1, 14);     --3
INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (4, 2, 10);     --4
INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (5, 2, 40);     --5
INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (6, 3, 30);     --6
INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (7, 3, 15);     --7
INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (8, 4, 10);     --8
INSERT INTO sklep.Zamowiona_dostawa (produkt_id, dostawa_id, ilosc_egzemplarzy) VALUES (9, 5, 60);     --9