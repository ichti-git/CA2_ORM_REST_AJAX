INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0555', 'Scanning');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0800', 'Høje Taastrup');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0877', 'København C');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0892', 'Sjælland USF P');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0893', 'Sjælland USF B');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0894', 'Udbetaling');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0897', 'eBrevsprækken');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0899', 'Kommuneservice');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0900', 'København C');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0910', 'København C');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0917', 'Københavns Pakkecenter');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0918', 'Københavns Pakke BRC');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0919', 'Returprint BRC');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0929', 'København C');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('0960', 'Internationalt Postcenter');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('999', 'København C');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('1259', 'København K');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3360', 'Liseleje');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3370', 'Melby');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3390', 'Hundested');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3400', 'Hillerød');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3450', 'Allerød');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3460', 'Birkerød');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3480', 'Fredensborg');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3490', 'Kvistgård');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3500', 'Værløse');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3520', 'Farum');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3540', 'Lynge');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3550', 'Slangerup');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3600', 'Frederikssund');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3630', 'Jægerspris');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3650', 'Ølstykke');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3660', 'Stenløse');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3670', 'Veksø Sjælland');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3700', 'Rønne');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3720', 'Aakirkeby');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3730', 'Nexø');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3740', 'Svaneke');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3770', 'Allinge');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3782', 'Klemensker');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('3790', 'Hasle');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('4000', 'Roskilde');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('4030', 'Tune');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('4040', 'Jyllinge');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('4050', 'Skibby');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('4894', 'Øster Ulslev');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('4895', 'Errindlev');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('5932', 'Humble');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('5935', 'Bagenkop');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('5953', 'Tranekær');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('5960', 'Marstal');
INSERT INTO CA2Db_TEST.City (zipCode, city) VALUES ('7730', 'Hanstholm');
INSERT INTO CA2Db_TEST.Address (street, streetNumber, zipCode) VALUES ('Hansensvej', '44', '3600'), ('Hansensvej', '44', '3600'), ('TinyRoad', '2', '4000');
INSERT INTO CA2Db_TEST.InfoGeneral (email, addressId) VALUES ('j@smith.org', 1), ('a@smith.org', 2), ('bob@last.em', 3);
INSERT INTO CA2Db_TEST.Person (infoId, firstName, lastName) VALUES (1, 'John', 'Smith'), (2, 'Alice', 'Smith'), (3, 'Bob', 'Gordard');
INSERT INTO CA2Db_TEST.Phone (phoneNumber, Description, infoId) VALUES ('+4567883249', 'Home phone', 1), ('+4586224591', 'Mobile', 1);
INSERT INTO CA2Db_TEST.Hobby (name, description) VALUES ('Swimming', 'In the water'), ('Dancing', 'On the floor');
INSERT INTO CA2Db_TEST.PersonHobby (hobbyId, personId) VALUES (1, 2), (2, 2);