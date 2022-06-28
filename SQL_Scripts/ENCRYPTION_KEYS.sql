GO

CREATE COLUMN MASTER KEY [CMK_Auto1]
WITH
(
	KEY_STORE_PROVIDER_NAME = N'MSSQL_CERTIFICATE_STORE',
	KEY_PATH = N'CurrentUser/my/D0143DF42C89F099436593E6046458D4E5527CC9'
)
GO

CREATE COLUMN ENCRYPTION KEY [CEK_Auto1]
WITH VALUES
(
	COLUMN_MASTER_KEY = [CMK_Auto1],
	ALGORITHM = 'RSA_OAEP',
	ENCRYPTED_VALUE = 0x016E000001630075007200720065006E00740075007300650072002F006D0079002F006400300031003400330064006600340032006300380039006600300039003900340033003600350039003300650036003000340036003400350038006400340065003500350032003700630063003900C6C610883134AE554ECF54F88483C29AF803058EBB4EF8308DF2A540886720CDDD96C4DBF4627540C2331203FB835B9A146250FEA19ACBAB25FC710313FF30C7ED3FA8231214DCCCE9DEA232A507DAA4797E56A9C9B6DB76D837A78B9EF8964BF59C5AEBF1C2FD9382516D90805BD9E3046A2E42B10CA2D2AF193CD98217E0A30E91CAEAC2861CCC4CED36E7FCC60A8EA3BC9F7B0FF06C845282D64521C0A30324AA0BA4E2919C273ED75AABED558C8F124A3B7F2EE140DCCA7585A96572BCA1C5107E80E097C5610B3B40A12AF7946EB40477827C00E1855C12D53C6BBBECE95022077B617AA979A94B276EAE27B75E49B714EDF8E168FAD76D1BCB7F8534478102D36E3502E1B4DA3708452EE5C50127733B260A571A08DC18C9EFD214330A769723693910713B1E9979E682FB8D560E2C08D534C929C450E3929F959A634CC50A3003D15E98256B6269362FF4E26AA87FF6CB7051C998160F0533D92C8C3DBC9B3432BC344967F40F4F8E3374DB62F5F501E0D7E6FD82F2DD955D721920D6825200710C819A7B4F5E7B74533EFCF3A051B709E1E575A3C5E0F98306BC353548804D0EB25221DA1C60CD381E155939580C754D1F2F258C1360F18322454DE68047A32CBE422101F99C9938FE3F1EBE540D342392DC41A1E97849A579FCF3EA343AA2DD80593581505EEF11B84F0A41C1B71A4983E89560706ED2CBE0D3975F
)
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

CREATE PROCEDURE Authentication @UserN varchar(50)
AS
OPEN SYMMETRIC KEY Credentials_Key
	DECRYPTION BY CERTIFICATE Credentials01;
SELECT convert(varchar, DECRYPTBYKEY(Password)) as 'Decrypted_Password' FROM Login
	WHERE Username = @UserN
GO