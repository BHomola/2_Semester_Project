INSERT INTO Stone(TotalSize, OrderID, StoneID) VALUES (100, 1, 1);
INSERT INTO Shape(Name, ShapeType, ShapeID) VALUES('other1', 'other', 1);
INSERT INTO OtherShape(ShapeID) VALUES(1);
INSERT INTO ShapePoint(OrderIndex, X, Y, ShapeID) VALUES(1, 10, 20, 1);
INSERT INTO ShapePoint(OrderIndex, X, Y, ShapeID) VALUES(1, 40, 20, 1);
INSERT INTO ShapePoint(OrderIndex, X, Y, ShapeID) VALUES(1, 25, 50, 1);



INSERT INTO Stone(TotalSize, OrderID, StoneID) VALUES (300, 2, 2);
INSERT INTO Shape(Name, ShapeType, ShapeID) VALUES('other2', 'other', 2);
INSERT INTO OtherShape(ShapeID) VALUES(2);
INSERT INTO ShapePoint(OrderIndex, X, Y, ShapeID) VALUES(1, 30, 60, 2);
INSERT INTO ShapePoint(OrderIndex, X, Y, ShapeID) VALUES(1, 60, 60, 2);
INSERT INTO ShapePoint(OrderIndex, X, Y, ShapeID) VALUES(1, 30, 80, 2);
INSERT INTO ShapePoint(OrderIndex, X, Y, ShapeID) VALUES(1, 60, 80, 2);
