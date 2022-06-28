GO

CREATE CERTIFICATE Credentials01
   WITH SUBJECT = 'Usernames and Passwords';  
GO  

CREATE SYMMETRIC KEY Credentials_Key  
    WITH ALGORITHM = AES_256  
    ENCRYPTION BY CERTIFICATE Credentials01;  
GO  

CREATE PROCEDURE CreateUser @UserName varchar(50), @Pass varchar(256),  @EmplID int
	AS 
	OPEN SYMMETRIC KEY Credentials_Key
	DECRYPTION BY CERTIFICATE Credentials01;
		INSERT INTO Login
		VALUES (@UserName, ENCRYPTBYKEY(key_guid('Credentials_Key'),@Pass), @EmplID)
GO

CREATE PROCEDURE Authentication @UserN varchar(50), @Pass varchar(50)
AS
OPEN SYMMETRIC KEY Credentials_Key
	DECRYPTION BY CERTIFICATE Credentials01;
SELECT EmployeeID AS [EmployeeID],convert(varchar, DECRYPTBYKEY(Password)) 
	AS  [Decrypted_Password] INTO Temp FROM Login
	WHERE Username = @UserN COLLATE Latin1_General_CS_AS
SELECT EmployeeID AS [Number] FROM Temp
	WHERE [Decrypted_Password] = @Pass COLLATE Latin1_General_CS_AS
DROP TABLE Temp;

GO

/* Here you can find the script for ALTER the procedure, if you already have it

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[Authentication] @UserN varchar(50), @Pass varchar(50)			    
AS
OPEN SYMMETRIC KEY Credentials_Key
	DECRYPTION BY CERTIFICATE Credentials01;
SELECT EmployeeID AS [EmployeeID],convert(varchar, DECRYPTBYKEY(Password)) AS  [Decrypted_Password] INTO Temp FROM Login
WHERE Username = @UserN COLLATE Latin1_General_CS_AS
SELECT EmployeeID AS [Number] FROM Temp
WHERE [Decrypted_Password] = @Pass COLLATE Latin1_General_CS_AS
DROP TABLE Temp;

**/