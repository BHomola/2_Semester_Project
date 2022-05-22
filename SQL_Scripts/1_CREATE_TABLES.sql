USE [CSC-CSD-S212_10407557]
GO

CREATE TABLE Material
(
  MaterialID INT NOT NULL,
  Name VARCHAR(50) NOT NULL,
  Description VARCHAR(200) NOT NULL,
  PRIMARY KEY (MaterialID)
);

CREATE TABLE StoneType
(
  TypeID INT NOT NULL,
  Name VARCHAR(50) NOT NULL,
  Description VARCHAR(200) NOT NULL,
  Picture VARCHAR(100) NOT NULL,
  MaterialID INT NOT NULL,
  PRIMARY KEY (TypeID),
  FOREIGN KEY (MaterialID) REFERENCES Material(MaterialID)
);

CREATE TABLE City
(
  CityID INT NOT NULL,
  Zipcode VARCHAR(10) NOT NULL,
  CityName VARCHAR(50) NOT NULL,
  Country VARCHAR(50) NOT NULL,
  PRIMARY KEY (CityID)
);

CREATE TABLE Person
(
  PersonID INT NOT NULL,
  Name VARCHAR(100) NOT NULL,
  Address VARCHAR(100) NOT NULL,
  PhoneNumber VARCHAR(20) NOT NULL,
  Email VARCHAR(100) NOT NULL,
  DateOfBirth DATE NOT NULL,
  Age INT NOT NULL,
  Description VARCHAR(200) NOT NULL,
  Note VARCHAR(200) NOT NULL,
  PersonType VARCHAR(50) NOT NULL,
  CityID INT NOT NULL,
  PRIMARY KEY (PersonID),
  FOREIGN KEY (CityID) REFERENCES City(CityID)
);

CREATE TABLE Customer
(
  discount INT NOT NULL,
  IsPremium bit NOT NULL,
  IsCompany bit NOT NULL,
  totalSpends INT NOT NULL,
  OrdersCount INT NOT NULL,
  LastOrderID INT NOT NULL,
  PersonID INT NOT NULL,
  PRIMARY KEY (PersonID),
  FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE Supplier
(
  PersonID INT NOT NULL,
  PRIMARY KEY (PersonID),
  FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE Invoice
(
  InvoiceID INT NOT NULL,
  PaymentDate DATE NOT NULL,
  VATratio INT NOT NULL,
  FinalPrice INT NOT NULL,
  PRIMARY KEY (InvoiceID)
);

CREATE TABLE Location
(
  LocationID INT NOT NULL,
  LocationName VARCHAR(50) NOT NULL,
  Address VARCHAR(100) NOT NULL,
  CityID INT NOT NULL,
  PRIMARY KEY (LocationID),
  FOREIGN KEY (CityID) REFERENCES City(CityID)
);

CREATE TABLE Employee
(
  Position VARCHAR(200) NOT NULL,
  Salary INT NOT NULL,
  StartDate DATE NOT NULL,
  EmployeeID INT NOT NULL,
  LocationID INT NOT NULL,
  PRIMARY KEY (EmployeeID),
  FOREIGN KEY (EmployeeID) REFERENCES Person(PersonID),
  FOREIGN KEY (LocationID) REFERENCES Location(LocationID)
);

CREATE TABLE Login
(
  Username INT NOT NULL,
  Password INT NOT NULL,
  EmployeeID INT NOT NULL,
  PRIMARY KEY (EmployeeID),
  FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

CREATE TABLE OrderInfo
(
  OrderID INT NOT NULL,
  Office VARCHAR(50) NOT NULL,
  DelivaryStatus INT NOT NULL,
  DelivaryDate DATE NOT NULL,
  Address VARCHAR(100) NOT NULL,
  deposit INT NOT NULL,
  IsPaid bit NOT NULL,
  Note VARCHAR(200) NOT NULL,
  Updates VARCHAR(200) NOT NULL,
  status INT NOT NULL,
  PersonID INT NOT NULL,
  InvoiceID INT NOT NULL,
  CityID INT NOT NULL,
  PRIMARY KEY (OrderID),
  FOREIGN KEY (PersonID) REFERENCES Person(PersonID),
  FOREIGN KEY (InvoiceID) REFERENCES Invoice(InvoiceID),
  FOREIGN KEY (CityID) REFERENCES City(CityID)
);

CREATE TABLE PlacedOrders
(
  OrderID INT NOT NULL,
  PersonID INT NOT NULL,
  PRIMARY KEY (OrderID, PersonID),
  FOREIGN KEY (OrderID) REFERENCES OrderInfo(OrderID),
  FOREIGN KEY (PersonID) REFERENCES Customer(PersonID)
);

CREATE TABLE StoneUnit
(
  StoneUnitID INT NOT NULL,
  Width INT NOT NULL,
  Weight INT NOT NULL,
  Description VARCHAR(200) NOT NULL,
  Status INT NOT NULL,
  StoneType VARCHAR(50) NOT NULL,
  Origin VARCHAR(50) NOT NULL,
  Note VARCHAR(200) NOT NULL,
  MaterialID INT NOT NULL,
  LocationID INT NOT NULL,
  PRIMARY KEY (StoneUnitID),
  FOREIGN KEY (MaterialID) REFERENCES Material(MaterialID),
  FOREIGN KEY (LocationID) REFERENCES Location(LocationID)
);

CREATE TABLE Stone
(
  ShapeType VARCHAR(50) NOT NULL,
  TotalSize INT NOT NULL,
  CreatedDate DATE NOT NULL,
  OrderID INT NOT NULL,
  StoneID INT NOT NULL,
  PRIMARY KEY (StoneID),
  FOREIGN KEY (StoneID) REFERENCES StoneUnit(StoneUnitID)
);

CREATE TABLE Remains
(
  Pieces INT NOT NULL,
  RemainsID INT NOT NULL,
  PRIMARY KEY (RemainsID),
  FOREIGN KEY (RemainsID) REFERENCES StoneUnit(StoneUnitID)
);

CREATE TABLE CircleShape
(
  Diamater INT NOT NULL,
  Name VARCHAR(50) NOT NULL,
  StoneID INT NOT NULL,
  PRIMARY KEY (StoneID),
  FOREIGN KEY (StoneID) REFERENCES Stone(StoneID)
);

CREATE TABLE ElipseShape
(
  DiamaterX INT NOT NULL,
  DiamaterY INT NOT NULL,
  Name VARCHAR(50) NOT NULL,
  StoneID INT NOT NULL,
  PRIMARY KEY (StoneID),
  FOREIGN KEY (StoneID) REFERENCES Stone(StoneID)
);

CREATE TABLE OtherShape
(
  Name VARCHAR(50) NOT NULL,
  StoneID INT NOT NULL,
  PRIMARY KEY (StoneID),
  FOREIGN KEY (StoneID) REFERENCES Stone(StoneID)
);

CREATE TABLE ShapePoints
(
  X INT NOT NULL,
  Y INT NOT NULL,
  OrderIndex INT NOT NULL,
  StoneID INT NOT NULL,
  PRIMARY KEY (OrderIndex, StoneID),
  FOREIGN KEY (StoneID) REFERENCES OtherShape(StoneID)
);

CREATE TABLE StoneProduct
(
  Price INT NOT NULL,
  StoneID INT NOT NULL,
  PRIMARY KEY (StoneID),
  FOREIGN KEY (StoneID) REFERENCES Stone(StoneID)
);

CREATE TABLE CuttableStoneSubunits
(
  StoneID INT NOT NULL,
  StoneUnitID INT NOT NULL,
  PRIMARY KEY (StoneID, StoneUnitID),
  FOREIGN KEY (StoneID) REFERENCES Stone(StoneID),
  FOREIGN KEY (StoneUnitID) REFERENCES StoneUnit(StoneUnitID)
);

CREATE TABLE OrderLine
(
  OrderID INT NOT NULL,
  StoneID INT NOT NULL,
  PRIMARY KEY (OrderID, StoneID),
  FOREIGN KEY (OrderID) REFERENCES OrderInfo(OrderID),
  FOREIGN KEY (StoneID) REFERENCES StoneProduct(StoneID)
);