package dataaccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Material;
import model.StoneType;

public class MaterialTypeFactory {

	public MaterialTypeFactory() {
	}

	public static Material getMaterial(ResultSet resultSet) throws SQLException {
		return new Material (resultSet.getInt("MaterialID"), resultSet.getString("Name"),
				resultSet.getString("Description"), getStoneTypes(resultSet));
	}

	private static List<StoneType> getStoneTypes(ResultSet resultSet) throws SQLException {
		List<StoneType> stoneTypes = new ArrayList<StoneType>();
		while (resultSet.next()) {
			stoneTypes.add(getStoneType(resultSet));
		}
		return stoneTypes;
	}
	
	public static StoneType getStoneType(ResultSet resultSet) throws SQLException {
		return new StoneType(resultSet.getInt("TypeID"), resultSet.getString("Name"), resultSet.getString("Description"),
				resultSet.getString("Picture"));
	}
}