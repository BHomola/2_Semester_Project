USE [CSC-CSD-S212_10407557]
GO

--DROP VIEW IF EXISTS [VIEW_ORDERS];
--CREATE VIEW [VIEW_ORDERS] AS
--SELECT *
--FROM OrderLine 
--JOIN [VIEW_STONES] AS vs
--ON vs.
--...
--...


DROP VIEW IF EXISTS SelectAllStoneUnits;
GO

CREATE VIEW SelectAllStoneUnits AS
SELECT StoneUnit.StoneUnitID, StoneUnit.Width, StoneUnit.Weight, StoneUnit.Description, StoneUnit.Status, StoneUnit.StoneType, StoneUnit.CreatedDate, StoneUnit.Origin, StoneUnit.Updates, StoneUnit.StoneTypeID, StoneType.StoneMaterialID, StoneUnit.LocationID, StoneUnit.SupplierID, StoneUnit.EmployeeID, Stone.TotalSize, StoneProduct.Price, StoneProduct.OrderID, Remains.Pieces, StoreLocation.LocationName, StoreLocation.Address, StoreLocation.CityID, City.CityName, City.Country, City.Zipcode  FROM StoneUnit
FULL OUTER JOIN Stone ON StoneUnit.StoneUnitID = Stone.StoneID
FULL OUTER JOIN Remains ON StoneUnit.StoneUnitID = Remains.RemainsID
FULL OUTER JOIN StoneProduct ON Stone.StoneID = StoneProduct.StoneID
INNER JOIN StoneType ON StoneUnit.StoneTypeID  = StoneType.StoneTypeID 
INNER JOIN StoreLocation ON StoreLocation.LocationID = StoneUnit.LocationID
INNER JOIN City ON StoreLocation.CityID = City.CityID
GO

-- Select all children of cutting stones
SELECT SelectAllStoneUnits.* FROM CuttableStone
JOIN SelectAllStoneUnits ON CuttableStone.StoneUnitID = SelectAllStoneUnits.StoneUnitID
WHERE StoneID = 1
--SELECT * FROM SelectAllStoneUnits WHERE StoneUnitID=1

GO

--CREATE VIEW [VIEW_STONES]

--CREATE VIEW [VIEW_PERSONS]
--CREATE VIEW [VIEW_EMPLOYEES]
--CREATE VIEW [VIEW_SUPPLIERS]
--CREATE VIEW [VIEW_CUSTOMERS]

--CREATE VIEW [VIEW_MATERIALTYPES]

--CREATE VIEW [VIEW_LOCATIONCITY] AS
--SELECT StoreLocation.Name
--FROM StoreLocation
--LEFT JOIN City 
--ON StoreLocation.CityID = City.CityID
