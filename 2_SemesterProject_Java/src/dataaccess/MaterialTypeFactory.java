package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Material;
import model.Type;

public class MaterialTypeFactory {

	public MaterialTypeFactory() {
	}

	public static Material getMaterial(ResultSet resultSet) throws SQLException {
		return new Material(resultSet.getInt("MaterialID"), resultSet.getString("Name"), resultSet.getString(""));
}
}