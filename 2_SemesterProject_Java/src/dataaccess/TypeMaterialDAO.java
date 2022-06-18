package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.StoneMaterial;
import model.StoneType;
import model.Supplier;

public class TypeMaterialDAO implements ITypeMaterialDAO {

	public static StoneMaterial buildMaterial(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("StoneMaterialID");
		String name = resultSet.getString("MaterialName");
		String description = resultSet.getString("MaterialDescription");
		StoneMaterial stoneMaterial = new StoneMaterial(id, name, description);
		return stoneMaterial;
	}

	public static List<StoneMaterial> buildMaterials(ResultSet resultSet) throws SQLException {
		ArrayList<StoneMaterial> sMaterialList = new ArrayList<>();
		while (resultSet.next())
			sMaterialList.add(buildMaterial(resultSet));
		return sMaterialList;
	}

	public static StoneType buildType(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("StoneTypeID");
		String name = resultSet.getString("TypeName");
		String description = resultSet.getString("TypeDescription");
		String picturePath = resultSet.getString("Picture");
		StoneMaterial material = buildMaterial(resultSet);
		int supplierID = resultSet.getInt("StoneTypeSupplierID");
		StoneType stoneType = new StoneType(id, name, description, picturePath,supplierID, material);
		return stoneType;
	}

	public static List<StoneType> buildTypes(ResultSet resultSet) throws SQLException {
		ArrayList<StoneType> sTypeList = new ArrayList<>();
		while (resultSet.next())
			sTypeList.add(buildType(resultSet));
		return sTypeList;
	}

	public List<StoneType> getTypeListOfSameMaterial(StoneMaterial stoneMaterial) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = " SELECT *  FROM [VIEW_MaterialTypes] " + "WHERE StoneMaterialID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, stoneMaterial.getId());
		ArrayList<StoneType> sTypeList = new ArrayList<>();
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next())
			sTypeList.add(buildType(resultSet));
		return sTypeList;
	}

	@Override
	public Collection<StoneMaterial> getAllStoneMaterials() throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "SELECT * FROM [VIEW_MaterialTypes]";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return null;
		return buildMaterials(resultSet);
	}

	@Override
	public Collection<StoneType> getAllStoneTypes() throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "SELECT * FROM [VIEW_MaterialTypes]";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return null;
		return buildTypes(resultSet);
	}

	@Override
	public StoneMaterial getStoneMaterialByID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = " SELECT *  FROM [VIEW_MaterialTypes] " + "WHERE StoneMaterialID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			return buildMaterial(resultSet);
		}
		return null;
	}

	@Override
	public StoneType getStoneTypeByID(int id) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = " SELECT *  FROM [VIEW_MaterialTypes] " + "WHERE StoneTypeID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, id);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			return buildType(resultSet);
		}
		return null;
	}

	@Override
	public int createStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "INSERT INTO StoneMaterial(Name, Description) " + " VALUES(?,?) "
				+ " SELECT SCOPE_IDENTITY() AS generatedID;";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, stoneMaterial.getName());
		pStatement.setString(2, stoneMaterial.getDescription());
		ResultSet resultSet = pStatement.executeQuery();
		int generatedID = 0;
		if (resultSet.next())
			generatedID = resultSet.getInt("generatedID");
		return generatedID;
	}

	@Override
	public int createStoneType(StoneType stoneType) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "INSERT INTO StoneType(Name, Description, Picture, SupplierID, StoneMaterialID)"
				+ "VALUES(?,?,?,?,?)" + "SELECT SCOPE_IDENTITY() AS generatedID;";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setString(1, stoneType.getName());
		pStatement.setString(2, stoneType.getDescription());
		pStatement.setString(3, stoneType.getpicturePath());
		pStatement.setInt(4, stoneType.getSupplierID());
		pStatement.setInt(5, stoneType.getMaterial().getId());
		ResultSet resultSet = pStatement.executeQuery();
		int generatedID = 0;
		if (resultSet.next())
			generatedID = resultSet.getInt("generatedID");
		return generatedID;
	}

	@Override
	public boolean updateStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
			String sqlStatement = "UPDATE StoneMaterial SET Name = ?, Description = ? WHERE StoneMaterialID = ? ";
			PreparedStatement pStatement = con.prepareStatement(sqlStatement);
			pStatement.setString(1, stoneMaterial.getName());
			pStatement.setString(2, stoneMaterial.getDescription());
			pStatement.setInt(3, stoneMaterial.getId());
			pStatement.executeUpdate();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean updateStoneType(StoneType stoneType) throws SQLException {
		Connection con = DBConnection.getConnection();
		boolean success = false;
		try {
			String sqlStatement = "UPDATE StoneType SET Name = ?, Description = ?, Picture = ?, SupplierID = ? WHERE StoneTypeID = ?";
			PreparedStatement statement = con.prepareStatement(sqlStatement);
			statement.setString(1, stoneType.getName());
			statement.setString(2, stoneType.getDescription());
			statement.setString(3, stoneType.getpicturePath());
			statement.setInt(4, stoneType.getSupplierID());
			statement.setInt(5, stoneType.getId());
			statement.executeUpdate();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean deleteStoneMaterial(StoneMaterial stoneMaterial) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "DELETE FROM StoneMaterial " + "WHERE StoneMaterialID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, stoneMaterial.getId());
		int rowsAffected = pStatement.executeUpdate();
		if (rowsAffected == 1)
			return true;
		return false;
	}

	@Override
	public boolean deleteStoneType(StoneType stoneType) throws SQLException {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "DELETE FROM StoneType " + "WHERE StoneTypeID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(1, stoneType.getId());
		int rowsAffected = pStatement.executeUpdate();
		if (rowsAffected == 1)
			return true;
		return false;
	}
}
