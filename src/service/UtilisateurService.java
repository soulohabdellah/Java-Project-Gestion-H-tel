/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import dao.IDAO;
import entities.Client;
import entities.Utilisateur;
/**
 *
 * @author Souloh
 */
public class UtilisateurService {
    public Boolean create(Utilisateur object) {
		String query = "insert into utilisateur values (null,?,?,?);";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setString(1, object.getNom());
			preparedStatement.setString(2, object.getEmail());
			preparedStatement.setString(3, object.getPassword());
			
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
	public Boolean update(Utilisateur object) {
		String query = "UPDATE utilisateur SET nom=?,email=?,password=? where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setString(1, object.getNom());
			preparedStatement.setString(2, object.getEmail());
			preparedStatement.setString(3, object.getPassword());
			
			preparedStatement.setInt(4, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
	public Boolean delete(Utilisateur object) {
		String query = "delete from utilisateur where id=?";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public Utilisateur findById(int id) {
		String query = "select * from utilisateur where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new Utilisateur(resultSet.getInt("id"), resultSet.getString("nom"),resultSet.getString("email"),resultSet.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

    public List<Utilisateur> findAll() {
		String query = "select * from utilisateur;";
		List<Utilisateur> categories = new ArrayList<Utilisateur>();
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				 categories.add(new Utilisateur(resultSet.getInt("id"), resultSet.getString("nom"),resultSet.getString("email"),resultSet.getString("password")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}
}
