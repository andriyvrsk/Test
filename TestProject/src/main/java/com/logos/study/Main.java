package com.logos.study;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		Class.forName("com.mysql.jdbc.Driver");
		
		Properties properties = new Properties();

		InputStream is = new Main().getClass().
				getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(is);
		
		String url = properties.getProperty("url");
		
		Connection conn = DriverManager.getConnection(url, properties);

		Statement statement = conn.createStatement();

		String sql = "select * from CITY";
		
		ResultSet rs = statement.executeQuery(sql);
		
		System.out.println(rs);
		List<City> city = new ArrayList<>();
		
		while (rs.next()) {
			city.add(new City(rs.getInt("id"), rs.getString("name"), rs.getString("countryCode"), rs.getString("district"), rs.getLong("population")));
		}
		
		for (City u : city) {
			System.out.println(u);
		}

	}

}
