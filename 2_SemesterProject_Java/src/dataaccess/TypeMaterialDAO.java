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
	
	public static StoneMaterial buildMaterial(ResultSet resultSet) throws SQLException  {
		int id = resultSet.getInt("MaterialID");
		String name = resultSet.getString("Name");
		String description = resultSet.getString("Description");
		StoneMaterial stoneMaterial = new StoneMaterial(id, name, description);
		List<StoneType> stoneType = buildTypeListOfSameMaterial(stoneMaterial);
		stoneMaterial.setStoneType(stoneType);
		return stoneMaterial;
		
	}
	
	public static StoneType buildType(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("StoneTypeID");
		String name = resultSet.getString("Name");
		String description = resultSet.getString("Description");
		String picturePath = resultSet.getString("Picture");
		StoneType stoneType = new StoneType(id, name, description, picturePath);
		return stoneType;
	}
	
	public static List<StoneType> buildTypeListOfSameMaterial (StoneMaterial stoneMaterial) throws SQLException{
		Connection con = DBConnection.getConnection();
		String sqlStatement = " SELECT *  FROM StoneType "
				+ "WHERE MaterialID = ?";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		pStatement.setInt(0, stoneMaterial.getId());
		ArrayList<StoneType> sTypeList = new ArrayList<>();
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			sTypeList.add(buildType(resultSet));
		}
		return sTypeList;
	}

	@Override
	public Collection<StoneMaterial> getAllStoneMaterials() {
		Connection con = DBConnection.getConnection();
		String sqlStatement = "SELECT * FROM Material";
		PreparedStatement pStatement = con.prepareStatement(sqlStatement);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next() == false)
			return null;
		return buildOrders(resultSet);
	}

	@Override
	public Collection<StoneType> getAllStoneTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoneMaterial getStoneMaterialByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoneType getStoneTypeByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createStoneMaterial(StoneMaterial stoneMaterial) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createStoneType(StoneType stoneType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateStoneMaterial(StoneMaterial stoneMaterial) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStoneType(StoneType stoneType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStoneMaterial(StoneMaterial stoneMaterial) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStoneType(StoneType stoneType) {
		// TODO Auto-generated method stub
		return false;
	}
}
