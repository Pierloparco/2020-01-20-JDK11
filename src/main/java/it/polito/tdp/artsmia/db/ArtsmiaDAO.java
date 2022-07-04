package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.polito.tdp.artsmia.model.Adiacenza;
import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;

public class ArtsmiaDAO {

	
	public List<Exhibition> listExhibitions() {
		
		String sql = "SELECT * from exhibitions";
		List<Exhibition> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Exhibition exObj = new Exhibition(res.getInt("exhibition_id"), res.getString("exhibition_department"), res.getString("exhibition_title"), 
						res.getInt("begin"), res.getInt("end"));
				
				result.add(exObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> listofRuoli() {
		
		String sql = "SELECT DISTINCT `role` "
				+ "FROM `authorship` "
				+ "ORDER BY `role` ";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				result.add(res.getString("role"));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getArtisti(String ruolo) {
		
		String sql = "select a.artist_id from artists a, authorship au " +
				"where a.artist_id = au.artist_id and au.role = ?";
				
		List<Integer> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, ruolo);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				result.add(res.getInt("artist_id"));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Adiacenza> getArchi(String ruolo) {
		
		String sql = "SELECT a1.`artist_id` as art1, a2.`artist_id` as art2, COUNT(DISTINCT e1.`exhibition_id`) as peso "
				+ "FROM `authorship` a1, `authorship` a2, `exhibition_objects` e1, `exhibition_objects` e2 "
				+ "WHERE a1.`role`= ? AND a1.`role`=a2.`role` AND a1.`artist_id`>a2.`artist_id` AND a1.`object_id`=e1.`object_id` AND a2.`object_id`=e2.`object_id` "
				+ "	AND e1.`exhibition_id`=e2.`exhibition_id` "
				+ "GROUP BY a1.`artist_id`, a2.`artist_id` ";
				
		List<Adiacenza> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, ruolo);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				result.add(new Adiacenza(res.getInt("art1"), res.getInt("art2"), res.getInt("peso")));
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
