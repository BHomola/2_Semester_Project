USE [CSC-CSD-S212_10407562]
GO

--PERSON
CREATE OR ALTER VIEW VIEW_PERSONS AS
SELECT *
FROM Person
FULL OUTER JOIN Customer ON Person.PersonID = Customer.CustomerID
FULL OUTER JOIN Supplier ON Person.PersonID = Supplier.SupplierID
FULL OUTER JOIN Employee ON Person.PersonID = Employee.EmployeeID

--MATERIALTYPES
CREATE VIEW VIEW_MATERIALTYPES AS
SELECT StoneType.StoneTypeID, StoneType.Name AS TypeName, StoneType.Description AS TypeDescription,
StoneType.Picture, StoneMaterial.StoneMaterialID, StoneMaterial.Name AS MaterialName, 
StoneMaterial.Description as MaterialDescription
FROM StoneType
JOIN StoneMaterial ON
StoneType.StoneMaterialID = StoneMaterial.StoneMaterialID

--SHAPES
CREATE OR ALTER VIEW VIEW_SHAPES AS
SELECT Shape.ShapeID, Shape.Name AS ShapeName, Shape.ShapeType,
CircleShape.Diamater as CircleDiameter, 
ElipseShape.DiamaterX AS ElipseDiamaterX, ElipseShape.DiamaterY AS ElipseDiamaterY,
ShapePoint.OrderIndex, ShapePoint.X, ShapePoint.Y
FROM Shape
FULL OUTER JOIN CircleShape ON Shape.ShapeID = CircleShape.ShapeID
FULL OUTER JOIN ElipseShape ON Shape.ShapeID = ElipseShape.ShapeID
FULL OUTER JOIN OtherShape ON Shape.ShapeID = OtherShape.ShapeID
FULL OUTER JOIN ShapePoint ON OtherShape.ShapeID = ShapePoint.ShapeID

--LOCATIONCITY
CREATE OR ALTER VIEW VIEW_LOCATIONCITY AS
SELECT StoreLocation.LocationID, StoreLocation.LocationName, StoreLocation.Address,
City.CityID, City.Zipcode, City.CityName, City.Country
FROM StoreLocation
JOIN City ON
StoreLocation.CityID = City.CityID

--StoneUnit
DROP VIEW IF EXISTS SelectAllStoneUnits;
GO

CREATE VIEW SelectAllStoneUnits AS
SELECT StoneUnit.StoneUnitID, StoneUnit.Width, StoneUnit.Weight, StoneUnit.Description, StoneUnit.Status, StoneUnit.StoneType, StoneUnit.CreatedDate, StoneUnit.Origin, StoneUnit.Updates, StoneUnit.StoneTypeID, StoneType.StoneMaterialID, StoneUnit.LocationID, StoneUnit.SupplierID, StoneUnit.EmployeeID, Stone.TotalSize, StoneProduct.Price, StoneProduct.OrderID, Remains.Pieces, VIEW_LOCATIONCITY.LocationName, VIEW_LOCATIONCITY.Address, VIEW_LOCATIONCITY.CityID, VIEW_LOCATIONCITY.CityName, VIEW_LOCATIONCITY.Country, VIEW_LOCATIONCITY.Zipcode  FROM StoneUnit
FULL OUTER JOIN Stone ON StoneUnit.StoneUnitID = Stone.StoneID
FULL OUTER JOIN Remains ON StoneUnit.StoneUnitID = Remains.RemainsID
FULL OUTER JOIN StoneProduct ON Stone.StoneID = StoneProduct.StoneID
INNER JOIN StoneType ON StoneUnit.StoneTypeID  = StoneType.StoneTypeID 
INNER JOIN VIEW_LOCATIONCITY ON StoneUnit.LocationID = VIEW_LOCATIONCITY.LocationID
--INNER JOIN StoreLocation ON StoreLocation.LocationID = StoneUnit.LocationID
--INNER JOIN City ON StoreLocation.CityID = City.CityID
GO

-- Select all children of cutting stones
SELECT SelectAllStoneUnits.* FROM CuttableStone
JOIN SelectAllStoneUnits ON CuttableStone.StoneUnitID = SelectAllStoneUnits.StoneUnitID
WHERE StoneID = 1
--SELECT * FROM SelectAllStoneUnits WHERE StoneUnitID=1

GO

--ORDERS
