USE [CSC-CSD-S212_10407562]
GO

CREATE VIEW [VIEW_ORDERS] AS
SELECT *
FROM OrderLine 
JOIN [VIEW_STONES] AS vs
ON vs.
...
...




CREATE VIEW [VIEW_STONES]

CREATE VIEW [VIEW_PERSONS]
CREATE VIEW [VIEW_EMPLOYEES]
CREATE VIEW [VIEW_SUPPLIERS]
CREATE VIEW [VIEW_CUSTOMERS]

CREATE VIEW [VIEW_MATERIALTYPES]

CREATE VIEW [VIEW_LOCATIONCITY] AS
SELECT * 
FROM Location 
LEFT JOIN City 
ON Location.cityID = City.cityID