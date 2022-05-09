package dataaccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Material;
import model.Type;


public class MaterialTypeFactory {

	public MaterialTypeFactory() {
	}

	public static Material getMaterial(ResultSet resultSet) throws SQLException {
		return new Material (resultSet.getInt("MaterialID"), resultSet.getString("Name"),
				resultSet.getString("Description"), getTypeL(resultSet));
	}

	private static List<Type> getTypeL(ResultSet resultSet) throws SQLException {
		List<Type> typeL = new ArrayList<Type>();
		while (resultSet.next()) {
			typeL.add(resultSetTypes(resultSet));
		}
		return typeL;
	}
	
	private static Type resultSetTypes(ResultSet resultSet) throws SQLException {
		return new Type(resultSet.getInt("TypeID"), resultSet.getString("Name"), resultSet.getString("Description"),
				resultSet.getString("Picture"));
	}
}