USE [CSC-CSD-S212_10407557]
GO

INSERT INTO [City] (Zipcode,CityName,Country)
VALUES
  ('628357','Barranca','Ukraine'),
  ('F4F 8NC','Balclutha','Norway'),
  ('93154','La Paz','Singapore'),
  ('33751','Galway','Netherlands'),
  ('4193','Hunan','Costa Rica'),
  ('647792','Tuxtla Gutiérrez','Netherlands'),
  ('41713','Hereford','Chile'),
  ('36C 2T8','Zaria','Netherlands'),
  ('61-190','Morpeth','Norway'),
  ('558462','Kech','Spain'),
  ('3257','Zielona Góra','Spain'),
  ('4991','Lambayeque','Sweden'),
  ('2530','Deventer','Austria'),
  ('7624','Ipswich','Austria'),
  ('31761-79520','Primavera','Costa Rica'),
  ('4544','Kidapawan','Sweden'),
  ('84655','Westport','United States'),
  ('0342','Osasco','China'),
  ('12253','Auxerre','Russian Federation'),
  ('252589','Canberra','Canada'),
  ('64154','Changi Bay','Ukraine'),
  ('3908 NW','Barranquilla','United States'),
  ('33423','Atlanta','Canada'),
  ('74-231','Peumo','Ukraine'),
  ('R3 4VX','Seletar','Spain'),
  ('8368','Montluçon','United States'),
  ('31071','Aizwal','Singapore'),
  ('658837','Caucaia','Peru'),
  ('70905','Kavaratti','Canada'),
  ('8472','Jeonju','Russian Federation'),
  ('423114','Kitsman','India'),
  ('6478','Port Pirie','Peru'),
  ('22188','Potchefstroom','China'),
  ('9482-6295','Da Lat','United States'),
  ('XG5 4ET','Rechnitz','Turkey'),
  ('55835','Tomsk','Brazil'),
  ('3976375','Ryazan','Colombia'),
  ('25763-33997','Minna','India'),
  ('50705','Emalahleni','Nigeria'),
  ('5367','Montpelier','United States'),
  ('335547','Yogyakarta','Germany'),
  ('31523','Shimanovsk','Mexico'),
  ('83960','Chungju','Sweden'),
  ('1571-1886','Wallasey','Philippines'),
  ('56302','Bourges','Belgium'),
  ('11707','Wellington','Canada'),
  ('81223','Kimberley','Netherlands'),
  ('527358','Saint Paul','Austria'),
  ('1115','Salon-de-Provence','Peru'),
  ('H6H 8C7','Bremen','Russian Federation');
INSERT INTO [City] (Zipcode,CityName,Country)
VALUES
  ('5939','Goyang','Colombia'),
  ('1374 OM','Liberia','Nigeria'),
  ('7449','Zelzate','India'),
  ('7364','Los Patios','Mexico'),
  ('72223','Chimbote','Turkey'),
  ('2331','Meppel','Brazil'),
  ('721544','Mérida','Colombia'),
  ('3254','Hildesheim','Peru'),
  ('714858','Calapan','Belgium'),
  ('86517','Maidstone','United Kingdom'),
  ('3156','Ceuta','Pakistan'),
  ('424834','Villarrica','Poland'),
  ('12476','Sale','Australia'),
  ('85422','Lüneburg','Indonesia'),
  ('57587','Skövde','Germany'),
  ('307788','Gore','Singapore'),
  ('242388','Kimberley','Colombia'),
  ('2547','Tromsø','Ukraine'),
  ('3677','Cebu City','Pakistan'),
  ('3870','Padre las Casas','France'),
  ('QV7V 8QN','Montigny-lès-Metz','Italy'),
  ('31021','Nelson','United States'),
  ('442832','Tunja','Australia'),
  ('9234','Juliaca','Sweden'),
  ('551229','Huara','Ukraine'),
  ('6184','Isabela City','Spain'),
  ('5718','Lansing','Russian Federation'),
  ('13256-836','Almere','Ukraine'),
  ('43502','Rechnitz','Ireland'),
  ('7241','Hong Kong','Belgium'),
  ('96346','Carmen','Singapore'),
  ('H7M 2M5','Tampico','United Kingdom'),
  ('244784','Nizhyn','Norway'),
  ('363287','Schönebeck','India'),
  ('66719-64322','Tandag','South Korea'),
  ('553161','Jedburgh','Sweden'),
  ('137547','Bydgoszcz','Turkey'),
  ('32-54','Larvik','Austria'),
  ('552237','Medellín','Austria'),
  ('549125','Arviat','Ukraine'),
  ('13264','Yeoju','Nigeria'),
  ('15127','Bedok','South Africa'),
  ('38-487','Kon Tum','Philippines'),
  ('VU0 5EM','San Felipe','Ukraine'),
  ('138872','Carmen','Philippines'),
  ('651867','Vetlanda','Pakistan'),
  ('718028','Bethlehem','Spain'),
  ('481495','Queenstown','United States'),
  ('78746-32838','Tarakan','Sweden'),
  ('08361','Pagadian','Indonesia');



  INSERT INTO [StoreLocation] (LocationName,Address,CityID)
VALUES
  ('StoreLocation1','Ap #845-7449 Tristique St.',3),
  ('StoreLocation2','731-1904 Aliquet, Street',54),
  ('StoreLocation3','5846 Montes, Road',84),
  ('StoreLocation4','Ap #302-1815 Amet Av.',99),
  ('StoreLocation5','Ap #589-3405 Lorem Rd.',6);






  INSERT INTO [Person] (Name,Address,PhoneNumber,Email,PersonType,CityID)
VALUES
  ('Lars Hill','447-4906 Neque Road','055 5125 2526','ridiculus@hotmail.ca','Customer',6),
  ('Basia Merrill','183-2974 A Rd.','(01939) 817637','ligula.elit@protonmail.couk','Customer',2),
  ('Remedios Underwood','9739 Volutpat. Av.','0500 555422','vel.pede@icloud.org','Customer',5),
  ('Ryder Lee','P.O. Box 792, 4254 Eu, Street','0845 46 42','massa.non.ante@protonmail.couk','Customer',19),
  ('Owen Strong','546 Massa St.','(01334) 84264','id@aol.couk','Customer',10),
  ('Reed Page','Ap #485-4637 Augue Avenue','(01335) 75811','velit.quisque.varius@hotmail.org','Customer',15),
  ('Quintessa Kim','Ap #307-5221 Vitae Avenue','0500 156239','diam@outlook.org','Customer',5),
  ('Hilel Daniels','Ap #158-2505 Purus, St.','(0141) 630 5388','bibendum@outlook.ca','Customer',20),
  ('Warren Merritt','Ap #455-3325 Diam. Road','055 2894 2332','habitant.morbi@google.edu','Customer',4),
  ('Libby Mcbride','3112 Tincidunt Road','(0171) 984 7231','sed.nulla@protonmail.edu','Customer',20),
  ('Ronan Buckley','Ap #338-3515 Arcu. Rd.','(012414) 42241','facilisis.vitae@outlook.ca','Employee',5),
  ('Harrison Suarez','Ap #457-7632 Ornare Avenue','07367 874411','eget.massa@protonmail.org','Employee',11),
  ('Claudia Mcdaniel','Ap #119-5746 Tincidunt Street','(01595) 25434','metus.vitae@outlook.com','Employee',5),
  ('Hasad Sanders','133-4324 Mus. Ave','(0116) 632 3928','cursus.nunc@yahoo.com','Employee',17),
  ('Declan Herrera','Ap #900-8171 Eros Ave','0988 125 1927','interdum@hotmail.org','Employee',1),
  ('Leigh Harmon','353-1711 Quam Rd.','070 5591 3041','non.quam@outlook.couk','Employee',17),
  ('Hillary Sims','488-5656 Faucibus St.','(01537) 642578','nibh@aol.net','Employee',14),
  ('Hammett Willis','331-9001 Neque Rd.','0847 532 8646','aliquam.gravida@aol.org','Employee',16),
  ('Rudyard Ayala','283-5635 Est, Rd.','0500 659516','arcu.vestibulum@google.net','Employee',13),
  ('Selma Cruz','Ap #306-6040 Ut St.','070 5561 8746','proin@outlook.couk','Employee',2),
  ('Aline Caldwell','112-845 Nec St.','(021) 8707 3179','diam@outlook.ca','Supplier',16),
  ('Asher Morin','Ap #313-6893 Vehicula. St.','0800 1111','lorem.ipsum@google.couk','Supplier',8),
  ('Leila Paul','448-7140 Molestie St.','07114 557538','sed.et@outlook.com','Supplier',8),
  ('Martena Sellers','Ap #651-4201 Tellus St.','(0121) 872 1933','tellus.non@google.com','Supplier',7),
  ('Neve Duffy','2543 Nulla. Rd.','(01333) 282785','donec@aol.com','Supplier',19),
  ('Elaine Clayton','9051 Nonummy. St.','(01701) 21261','orci.ut@hotmail.edu','Supplier',13),
  ('Natalie White','3078 Nullam Av.','0800 427 6968','lorem.auctor@hotmail.ca','Supplier',15),
  ('Noel Morris','Ap #611-6198 Adipiscing Ave','0871 035 2509','scelerisque.neque@outlook.edu','Supplier',9),
  ('Adrian Anthony','Ap #139-9405 Integer Ave','(01615) 13805','vivamus.sit.amet@aol.ca','Supplier',5),
  ('Jocelyn Vazquez','532-1466 Mauris Av.','(025) 1083 4294','senectus.et@icloud.edu','Supplier',6);



  INSERT INTO [Customer] (discount,IsPremium,IsCompany,totalSpends,OrdersCount,CustomerID)
VALUES
  (141,0,0,0,0,0),
  (2855,1,0,0,0,1),
  (2009,1,1,0,0,2),
  (419,1,1,0,0,3),
  (2522,1,0,0,0,4),
  (84,0,0,0,0,5),
  (1594,1,0,0,0,6),
  (2678,0,0,0,0,7),
  (2748,0,1,0,0,8),
  (626,0,1,0,0,9);



  INSERT INTO [Employee] (Position,Salary,StartDate,EmployeeID,LocationID)
VALUES
  ('Manager',14975,'2017-01-08',10,1),
  ('Manager',14810,'2018-02-23',11,1),
  ('Worker',10748,'2016-03-25',12,2),
  ('Worker',11581,'2020-08-24',13,2),
  ('Manager',12957,'2018-11-28',14,3),
  ('Manager',10389,'2019-10-16',15,3),
  ('Worker',13442,'2014-05-21',16,4),
  ('Worker',11798,'2014-09-04',17,4),
  ('Manager',14558,'2017-11-08',18,0),
  ('Manager',13329,'2020-12-24',19,0);



  INSERT INTO [Supplier] (SupplierID)
VALUES
  (20),
  (21),
  (22),
  (23),
  (24),
  (25),
  (26),
  (27),
  (28),
  (29);



GO


