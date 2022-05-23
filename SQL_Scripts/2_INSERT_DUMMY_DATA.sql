USE [CSC-CSD-S212_10407557]
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
  ('5939','yang','Colombia'),
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
  ('307788','re','Singapore'),
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
  ('137547','Bydszcz','Turkey'),
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
  ('Warren Merritt','Ap #455-3325 Diam. Road','055 2894 2332','habitant.morbi@ogle.edu','Customer',4),
  ('Libby Mcbride','3112 Tincidunt Road','(0171) 984 7231','sed.nulla@protonmail.edu','Customer',20),
  ('Ronan Buckley','Ap #338-3515 Arcu. Rd.','(012414) 42241','facilisis.vitae@outlook.ca','Employee',5),
  ('Harrison Suarez','Ap #457-7632 Ornare Avenue','07367 874411','eget.massa@protonmail.org','Employee',11),
  ('Claudia Mcdaniel','Ap #119-5746 Tincidunt Street','(01595) 25434','metus.vitae@outlook.com','Employee',5),
  ('Hasad Sanders','133-4324 Mus. Ave','(0116) 632 3928','cursus.nunc@yahoo.com','Employee',17),
  ('Declan Herrera','Ap #900-8171 Eros Ave','0988 125 1927','interdum@hotmail.org','Employee',1),
  ('Leigh Harmon','353-1711 Quam Rd.','070 5591 3041','non.quam@outlook.couk','Employee',17),
  ('Hillary Sims','488-5656 Faucibus St.','(01537) 642578','nibh@aol.net','Employee',14),
  ('Hammett Willis','331-9001 Neque Rd.','0847 532 8646','aliquam.gravida@aol.org','Employee',16),
  ('Rudyard Ayala','283-5635 Est, Rd.','0500 659516','arcu.vestibulum@ogle.net','Employee',13),
  ('Selma Cruz','Ap #306-6040 Ut St.','070 5561 8746','proin@outlook.couk','Employee',2),
  ('Aline Caldwell','112-845 Nec St.','(021) 8707 3179','diam@outlook.ca','Supplier',16),
  ('Asher Morin','Ap #313-6893 Vehicula. St.','0800 1111','lorem.ipsum@ogle.couk','Supplier',8),
  ('Leila Paul','448-7140 Molestie St.','07114 557538','sed.et@outlook.com','Supplier',8),
  ('Martena Sellers','Ap #651-4201 Tellus St.','(0121) 872 1933','tellus.non@ogle.com','Supplier',7),
  ('Neve Duffy','2543 Nulla. Rd.','(01333) 282785','donec@aol.com','Supplier',19),
  ('Elaine Clayton','9051 Nonummy. St.','(01701) 21261','orci.ut@hotmail.edu','Supplier',13),
  ('Natalie White','3078 Nullam Av.','0800 427 6968','lorem.auctor@hotmail.ca','Supplier',15),
  ('Noel Morris','Ap #611-6198 Adipiscing Ave','0871 035 2509','scelerisque.neque@outlook.edu','Supplier',9),
  ('Adrian Anthony','Ap #139-9405 Integer Ave','(01615) 13805','vivamus.sit.amet@aol.ca','Supplier',5),
  ('Jocelyn Vazquez','532-1466 Mauris Av.','(025) 1083 4294','senectus.et@icloud.edu','Supplier',6);
  INSERT INTO [Customer] (Discount,IsPremium,IsCompany,TotalSpends,OrdersCount,CustomerID)
VALUES
  (2855,1,0,0,0,1),
  (2009,1,1,0,0,2),
  (419,1,1,0,0,3),
  (2522,1,0,0,0,4),
  (84,0,0,0,0,5),
  (1594,1,0,0,0,6),
  (2678,0,0,0,0,7),
  (2748,0,1,0,0,8),
  (626,0,1,0,0,9),
  (141,0,0,0,0,10);
  INSERT INTO [Employee] (Position,Salary,StartDate,EmployeeID,LocationID)
VALUES
   ('Manager',13329,'2020-12-24',19,0),
  ('Worker',14975,'2017-01-08',20,0),
  ('Manager',14810,'2018-02-23',11,1),
  ('Worker',10748,'2016-03-25',12,1),
  ('Worker',11581,'2020-08-24',13,2),
  ('Manager',12957,'2018-11-28',14,2),
  ('Manager',10389,'2019-10-16',15,3),
  ('Worker',13442,'2014-05-21',16,3),
  ('Worker',11798,'2014-09-04',17,4),
  ('Manager',14558,'2017-11-08',18,4);
  INSERT INTO [Supplier] (SupplierID)
VALUES
  (21),
  (22),
  (23),
  (24),
  (25),
  (26),
  (27),
  (28),
  (29),
  (30);
INSERT [StoneMaterial] ([Name], [Description]) VALUES ('Granite','Intrusive igneous rock composed mostly of quartz, alkali feldspar, and plagioclase.')
INSERT [StoneMaterial] ([Name], [Description]) VALUES ('Marble','Metamorphic rock composed of recrystallized carbonate minerals, most commonly calcite or dolomite.')
INSERT [StoneMaterial] ([Name], [Description]) VALUES ('Marble Bio','Bio marble')
INSERT [StoneMaterial] ([Name], [Description]) VALUES ('Quartz Belenco','Hard, crystalline mineral composed of silica (silicon dioxide).')
INSERT [StoneMaterial] ([Name], [Description]) VALUES ('Quartz Compac','High resistance against heat, scratches, abrasion, impact, humidity, stain and it also lacks water absorption.')


INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Absolute Black', ' ', 21, '\Images\Granite\Absolute-black.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Baltic Brown', ' ', 21, '\Images\Granite\Baltic-Brown.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Black Fusion', ' ', 21, '\Images\Granite\Black-Fusion.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Black Galaxy', ' ', 21, '\Images\Granite\Black-Galaxy.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Blue Pearl', ' ', 21, '\Images\Granite\Blue-Pearl.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Cardinal Bej', ' ', 21, '\Images\Granite\Cardinal-Bej.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Cosmic Black', ' ', 21, '\Images\Granite\Cosmic-Black.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Delicatus ld', ' ', 21, '\Images\Granite\Delicatus-ld.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ('Extra Blue Volga', ' ', 21, '\Images\Granite\Extra-Blue-Volga.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Giallo Sicilia', ' ', 21, '\Images\Granite\Giallo-Sicilia.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Giallo-Veneziano', ' ', 21, '\Images\Granite\Giallo-Veneziano.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Gialo California', ' ', 21, '\Images\Granite\Gialo-California.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Green Butterfly', ' ', 21, '\Images\Granite\Green-Butterfly.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Green Oliva', ' ', 21, '\Images\Granite\Green-Oliva.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Imperial Red', ' ', 21, '\Images\Granite\Imperial-Red.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Kashmir ld', ' ', 21, '\Images\Granite\Kashmir-ld.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Magma', ' ', 21, '\Images\Granite\Magma.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Matrix', ' ', 21, '\Images\Granite\Matrix.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Metallicus', ' ', 21, '\Images\Granite\Metallicus.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Montblanc', ' ', 21, ' \Images\Granite\Montblanc.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Notre Dame', ' ', 21, '\Images\Granite\Notre-Dame.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Ouro Brasil', ' ', 21, '\Images\Granite\Ouro-Brasil.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Paradiso', ' ', 21, '\Images\Granite\Paradiso.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Red Maple-Tree', ' ', 21, '\Images\Granite\Red-Maple-Tree.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Rosso Porrino', ' ', 21, '\Images\Granite\Rosso-Porrino.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Rosso Santia', ' ', 21, '\Images\Granite\Rosso-Santia.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Shivakashi', ' ', 21, '\Images\Granite\Shivakashi.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Splendor White', ' ', 21, '\Images\Granite\Splendor-White.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Tan Brown', ' ', 21, '\Images\Granite\Tan-Brown.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Ukraine Grey', ' ', 21, '\Images\Granite\Ukraine-Grey.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Ukraines star', ' ', 21, '\Images\Granite\Ukraines-star.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Ukranian autumn', ' ', 21, '\Images\Granite\Ukranian-autumn.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Green Matrix', ' ', 21, '\Images\Granite\Green-Matrix.jpg', 1)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Arabescato', ' ', 21, '\Images\Marble\Arabescato.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Bidasar ld', ' ', 21, '\Images\Marble\Bidasar-ld.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Black Fossil', ' ', 21, '\Images\Marble\Black-Fossil.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Botticino Classico', ' ', 21, '\Images\Marble\Botticino-Classico.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Carrara', ' ', 21, '\Images\Marble\Carrara.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Crema Marfil', ' ', 21, '\Images\Marble\Crema-Marfil.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Crema Nova', ' ', 21, '\Images\Marble\Crema-Nova.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Diano Reale', ' ', 21, '\Images\Marble\Diano-Reale.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Emperador Dark', ' ', 21, '\Images\Marble\Emperador-Dark.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Emperador ld', ' ', 21, '\Images\Marble\Emperador-ld.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Eramosa Brown', ' ', 21, '\Images\Marble\Eramosa-Brown.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Eramosa Silver', ' ', 21, '\Images\Marble\Eramosa-Silver.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Kyknos', ' ', 21, '\Images\Marble\Kyknos.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Lilac', ' ', 21, '\Images\Marble\Lilac.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Marfil', ' ', 21, '\Images\Marble\Marfil.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Misty White', ' ', 21, '\Images\Marble\Misty-White.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Mugla White', ' ', 21, '\Images\Marble\Mugla-White.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Nero Marquina', ' ', 21, '\Images\Marble\Nero-Marquina.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Olympys Beige', ' ', 21, '\Images\Marble\Olympys-Beige.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Rosalia', ' ', 21, '\Images\Marble\Rosalia.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Rosso Levanto', ' ', 21, '\Images\Marble\Rosso-Levanto.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Sivic', ' ', 21, '\Images\Marble\Sivic.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Thassos', ' ', 21, '\Images\Marble\Thassos.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Green Guatemala', ' ', 21, '\Images\Marble\Green-Guatemala.jpg', 2)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Afion', ' ', 21, '\Images\Marble-Bio\Afion.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Albufera', ' ', 21, '\Images\Marble-Bio\Albufera.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Anthracite', ' ', 21, '\Images\Marble-Bio\Anthracite.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Beige Faraya', ' ', 21, '\Images\Marble-Bio\Beige-Faraya.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Bering', ' ', 21, '\Images\Marble-Bio\Bering.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Blanco Aura', ' ', 21, '\Images\Marble-Bio\Blanco-Aura.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Blanco Lhasa', ' ', 21, '\Images\Marble-Bio\Blanco-Lhasa.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Caramelo', ' ', 21, '\Images\Marble-Bio\Caramelo.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Crema Valencia', ' ', 21, '\Images\Marble-Bio\Crema-Valencia.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Foggy Nubia', ' ', 21, '\Images\Marble-Bio\Foggy-Nubia.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Jura', ' ', 21, '\Images\Marble-Bio\Jura.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'La Perla', ' ', 21, '\Images\Marble-Bio\La-Perla.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Nacarado', ' ', 21, '\Images\Marble-Bio\Nacarado.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Sunset', ' ', 21, '\Images\Marble-Bio\Sunset.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'White Faraya', ' ', 21, '\Images\Marble-Bio\White-Faraya.jpg', 3)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Aizano', ' ', 21, ' \Images\Quartz-Belenco\Aizano.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Babilon', ' ', 21, '\Images\Quartz-Belenco\Babilon.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Calacatta Venatino', ' ', 21, '\Images\Quartz-Belenco\Calacatta-Venatino.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Chakra Beige', ' ', 21, '\Images\Quartz-Belenco\Chakra-Beige.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Daphne Crack', ' ', 21, '\Images\Quartz-Belenco\Daphne-Crack.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Gala Black', ' ', 21, '\Images\Quartz-Belenco\Gala-Black.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Granada', ' ', 21, '\Images\Quartz-Belenco\Granada.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Iceberg', ' ', 21, '\Images\Quartz-Belenco\Iceberg.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Intestellar', ' ', 21, '\Images\Quartz-Belenco\Intestellar.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Kashmera White', ' ', 21, '\Images\Quartz-Belenco\Kashmera-White.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Kristella White', ' ', 21, '\Images\Quartz-Belenco\Kristella-White.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'La Luna', ' ', 21, '\Images\Quartz-Belenco\La-Luna.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Lumiere', ' ', 21, '\Images\Quartz-Belenco\Lumiere.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Montana', ' ', 21, '\Images\Quartz-Belenco\Montana.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Parma', ' ', 21, '\Images\Quartz-Belenco\Parma.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Sahara Beige', ' ', 21, '\Images\Quartz-Belenco\Sahara-Beige.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Spa Black', ' ', 21, '\Images\Quartz-Belenco\Spa-Black.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Teos', ' ', 21, '\Images\Quartz-Belenco\Teos.jpg', 4)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Absolut Blanc', ' ', 21, '\Images\Quartz-Compac\Absolut-Blanc.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Ama White', ' ', 21, '\Images\Quartz-Compac\Ama-White.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Antico Rover', ' ', 21, '\Images\Quartz-Compac\Antico-Rover.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Arena', ' ', 21, '\Images\Quartz-Compac\Arena.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Azabache', ' ', 21, '\Images\Quartz-Compac\Azabache.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Black Ama', ' ', 21, '\Images\Quartz-Compac\Black-Ama.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Botticino', ' ', 21, '\Images\Quartz-Compac\Botticino.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Brown Ama', ' ', 21, '\Images\Quartz-Compac\Brown-Ama.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Carrara', ' ', 21, '\Images\Quartz-Compac\Carrara.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Ceniza', ' ', 21, '\Images\Quartz-Compac\Ceniza.jpg',5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Cool Gray', ' ', 21, '\Images\Quartz-Compac\Cool-Gray.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Crystal Beach', ' ', 21, '\Images\Quartz-Compac\Crystal-Beach.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Gray Zement', ' ', 21, '\Images\Quartz-Compac\Gray-Zement.jpg',5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Ice Black Genesis', ' ', 21, '\Images\Quartz-Compac\Ice-Black-Genesis.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Ice Zement', ' ', 21, '\Images\Quartz-Compac\Ice-Zement.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Luna', ' ', 21, '\Images\Quartz-Compac\Luna.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Moka', ' ', 21, '\Images\Quartz-Compac\Moka.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Moon', ' ', 21, '\Images\Quartz-Compac\Moon.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Nocturno', ' ', 21, '\Images\Quartz-Compac\Nocturno.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Smoke Gray', ' ', 21, '\Images\Quartz-Compac\Smoke-Gray.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Snow', ' ', 21, '\Images\Quartz-Compac\Snow.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Unique Calacatta', ' ', 21, '\Images\Quartz-Compac\Unique-Calacatta.jpg',5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Volcano Black', ' ', 21, '\Images\Quartz-Compac\Volcano-Black.jpg', 5)
INSERT INTO [StoneType] ([Name], [Description], [SupplierID], [Picture], [StoneMaterialID]) VALUES ( 'Warm Gray', ' ', 21, '\Images\Quartz-Compac\Warm-Gray.jpg', 5)
