package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.City;
import model.Location;

public class LocationCityFactory {
	LocationCityFactory() {
	}

	public static Location getLocation(ResultSet resultSet) throws SQLException {
		return new Location(resultSet.getInt("LocationID"), resultSet.getString("LocationName"),
				resultSet.getString("Address"), getCity(resultSet));
	}

	public static City getCity(ResultSet resultSet) throws SQLException {
		return new City(resultSet.getInt("CityID"), resultSet.getString("CityName"), resultSet.getString("Zipcode"),
				resultSet.getString("Country"));
	}

}
