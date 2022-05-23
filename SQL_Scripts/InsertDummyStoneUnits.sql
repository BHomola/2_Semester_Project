USE [CSC-CSD-S212_10407557]
GO
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone1',0,'CuttableStone','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone2',0,'CuttableStone','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone3',0,'CuttableStone','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone4',0,'CuttableStone','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone5',0,'StoneProduct','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone6',0,'StoneProduct','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone7',0,'StoneProduct','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone8',0,'Remains','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone9',0,'Remains','2022-05-23','Italy',1,1,21,11);
INSERT INTO StoneUnit(Width,Weight,Description,Status,StoneType,CreatedDate,Origin,StoneTypeID,LocationID,SupplierID,EmployeeID) VALUES(50,100,'Test Stone10',0,'Remains','2022-05-23','Italy',1,1,21,11);

INSERT INTO Stone(StoneID,TotalSize) VALUES(1, 40);
INSERT INTO Stone(StoneID,TotalSize) VALUES(2, 40);
INSERT INTO Stone(StoneID,TotalSize) VALUES(3, 40);
INSERT INTO Stone(StoneID,TotalSize) VALUES(4, 40);
INSERT INTO Stone(StoneID,TotalSize) VALUES(5, 40);
INSERT INTO Stone(StoneID,TotalSize) VALUES(6, 40);
INSERT INTO Stone(StoneID,TotalSize) VALUES(7, 40);


INSERT INTO OrderInfo(DeliveryStatus, DeliveryDate, Address, CityID, Deposit, IsPaid, CustomerNote, LocationID, CustomerID, OrderPrice, EmployeeID, Updates) VALUES(0, '2022-5-29', 'Random Address 5', 10, 110, 1, ' ', 1, 1, 220, 11, ' ');
GO
INSERT INTO StoneProduct(StoneID,Price, OrderID) VALUES(5, 40,1);
INSERT INTO StoneProduct(StoneID,Price, OrderID) VALUES(6, 60,1);
INSERT INTO StoneProduct(StoneID,Price, OrderID) VALUES(7, 120,1);


INSERT INTO CuttableStone(StoneID,StoneUnitID) VALUES(1, 5);
INSERT INTO CuttableStone(StoneID,StoneUnitID) VALUES(1, 8);
INSERT INTO CuttableStone(StoneID,StoneUnitID) VALUES(1, 2);

INSERT INTO CuttableStone(StoneID,StoneUnitID) VALUES(2, 9);
INSERT INTO CuttableStone(StoneID,StoneUnitID) VALUES(2, 7);

INSERT INTO CuttableStone(StoneID,StoneUnitID) VALUES(3, 6);
INSERT INTO CuttableStone(StoneID,StoneUnitID) VALUES(4, 10);

INSERT INTO Remains(RemainsID,Pieces) VALUES(8, 10);
INSERT INTO Remains(RemainsID,Pieces) VALUES(9, 10);
INSERT INTO Remains(RemainsID,Pieces) VALUES(10, 10);
GO


