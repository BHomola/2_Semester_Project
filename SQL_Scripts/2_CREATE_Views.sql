
GO

DROP VIEW IF EXISTS VIEW_PERSONS;
GO

--PERSON
CREATE OR ALTER VIEW VIEW_Persons AS
SELECT *
FROM Person
FULL OUTER JOIN Customer ON Person.PersonID = Customer.CustomerID
FULL OUTER JOIN Supplier ON Person.PersonID = Supplier.SupplierID
FULL OUTER JOIN Employee ON Person.PersonID = Employee.EmployeeID;
GO


DROP VIEW IF EXISTS VIEW_MaterialTypes;
GO

--MATERIALTYPES
CREATE VIEW VIEW_MaterialTypes AS
SELECT StoneType.StoneTypeID, StoneType.Name AS TypeName, StoneType.Description AS TypeDescription,
StoneType.Picture, StoneMaterial.StoneMaterialID, StoneMaterial.Name AS MaterialName, 
StoneMaterial.Description as MaterialDescription, StoneType.SupplierID as StoneTypeSupplierID
FROM StoneType
JOIN StoneMaterial ON
StoneType.StoneMaterialID = StoneMaterial.StoneMaterialID;
GO

DROP VIEW IF EXISTS VIEW_Shapes;
GO

--SHAPES
CREATE OR ALTER VIEW VIEW_Shapes AS
SELECT Shape.ShapeID, Shape.Name as ShapeName, Shape.ShapeType,
CircleShape.Diamater, 
ElipseShape.DiamaterX, ElipseShape.DiamaterY,
ShapePoint.OrderIndex, ShapePoint.X, ShapePoint.Y
FROM Shape
FULL OUTER JOIN CircleShape ON Shape.ShapeID = CircleShape.ShapeID
FULL OUTER JOIN ElipseShape ON Shape.ShapeID = ElipseShape.ShapeID
FULL OUTER JOIN OtherShape ON Shape.ShapeID = OtherShape.ShapeID
FULL OUTER JOIN ShapePoint ON OtherShape.ShapeID = ShapePoint.ShapeID;
GO

DROP VIEW IF EXISTS VIEW_LocationCity;
GO

--LOCATIONCITY
CREATE OR ALTER VIEW VIEW_LocationCity AS
SELECT StoreLocation.LocationID, StoreLocation.LocationName, StoreLocation.Address,
City.CityID, City.Zipcode, City.CityName, City.Country
FROM StoreLocation
JOIN City ON
StoreLocation.CityID = City.CityID;
GO

--StoneUnit


DROP VIEW IF EXISTS VIEW_Stones;
GO

CREATE VIEW VIEW_Stones AS
SELECT 
StoneUnit.StoneUnitID,
StoneUnit.Width,
StoneUnit.Weight,
StoneUnit.Description AS StoneDescription,
StoneUnit.Status,
StoneUnit.StoneType,
StoneUnit.CreatedDate,
StoneUnit.Origin,
StoneUnit.Updates,
StoneUnit.SupplierID,
StoneUnit.EmployeeID,
Stone.TotalSize,
StoneProduct.Price,
StoneProduct.OrderID,
Remains.Pieces,
VIEW_LocationCity.*,
VIEW_MaterialTypes.*

FROM StoneUnit
FULL OUTER JOIN Stone ON StoneUnit.StoneUnitID = Stone.StoneID
FULL OUTER JOIN Remains ON StoneUnit.StoneUnitID = Remains.RemainsID
FULL OUTER JOIN StoneProduct ON Stone.StoneID = StoneProduct.StoneID
INNER JOIN VIEW_MaterialTypes ON VIEW_MaterialTypes.StoneTypeID = StoneUnit.StoneTypeID
INNER JOIN VIEW_LocationCity ON VIEW_LocationCity.LocationID = StoneUnit.LocationID

GO



--ORDERS
CREATE OR ALTER VIEW VIEW_OrderInfo AS
SELECT OrderInfo.*, Invoice.PaymentDate, Invoice.VATratio, Invoice.FinalPrice
FROM OrderInfo
LEFT JOIN Invoice
ON OrderInfo.OrderID = Invoice.OrderID