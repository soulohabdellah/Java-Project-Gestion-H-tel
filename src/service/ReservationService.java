package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;  
 
import java.util.List;

import dao.IDAO;
import entities.Categorie;
import entities.Chambre;
import entities.Client;
import entities.Reservation;

public class ReservationService implements IDAO<Reservation>{
	List<Reservation> reservations;

	public ReservationService() {
		reservations = new ArrayList<Reservation>();
	}
     /*   
      public boolean createa(Reservation object) {
       String query = "INSERT INTO `reservation` (`id`, `id_chambre`, `id_client`, `date_debut`, `date_fin`) VALUES (NULL,?,?,?,?);";
                List<Reservation> list = this.findAll();
		if(list.isEmpty()) {
			try {
				PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
				preparedStatement.setInt(1, object.getChambre().getId());
				preparedStatement.setInt(2, object.getClient().getId());
				preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
				preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
				return preparedStatement.executeUpdate()>0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			for( Reservation r : list) {
                             if (object.getChambre().getId() == r.getChambre().getId()){
                                if (object.getDatedebut().after(r.getDatefin())){
                                        try {
				PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
				preparedStatement.setInt(1, object.getChambre().getId());
				preparedStatement.setInt(2, object.getClient().getId());
				preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
				preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
				return preparedStatement.executeUpdate()>0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
                                    }
                             }else {
                                 try {
				PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
				preparedStatement.setInt(1, object.getChambre().getId());
				preparedStatement.setInt(2, object.getClient().getId());
				preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
				preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
				return preparedStatement.executeUpdate()>0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
                             }
                        }
			
		}
		return false;
		
	}
	*/

	@Override
	public Boolean create(Reservation object) {
       String query = "INSERT INTO `reservation` (`id`, `id_chambre`, `id_client`, `date_debut`, `date_fin`) VALUES (NULL,?,?,?,?);";
		if(this.findAll() == null) {
					return false;

		}else {
			try {
				PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
				preparedStatement.setInt(1, object.getChambre().getId());
				preparedStatement.setInt(2, object.getClient().getId());
				preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
				preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
				return preparedStatement.executeUpdate()>0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		
		
	}

	@Override
	public String toString() {
		return "ReservationService [reservations=" + reservations + "]";
	}

	@Override
	public Boolean update(Reservation object) {
		String query = "UPDATE reservation SET id_chambre=?,id_client=?,date_debut=?,date_fin=? where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, object.getChambre().getId());
			preparedStatement.setInt(2, object.getClient().getId());
			preparedStatement.setDate(3, new Date(object.getDatedebut().getTime()));
			preparedStatement.setDate(4, new Date(object.getDatefin().getTime()));
			preparedStatement.setInt(5, object.getId());
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean delete(Reservation object) {
		String query = "delete from reservation where id=?";
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

	@Override
	public Reservation findById(int id) {
		ChambreService chambreService = new ChambreService();
		ClientService clientService = new ClientService();
		String query = "select * from reservation where id=?;";
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new Reservation(resultSet.getInt("id"), chambreService.findById(resultSet.getInt("id_chambre")),clientService.findById(resultSet.getInt("id_client")),resultSet.getDate("date_debut"),resultSet.getDate("date_fin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		ChambreService chambreService = new ChambreService();
		ClientService clientService = new ClientService();
		String query = "select * from reservation;";
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				reservations.add(new Reservation(resultSet.getInt("id"), chambreService.findById(resultSet.getInt("id_chambre")),clientService.findById(resultSet.getInt("id_client")),resultSet.getDate("date_debut"),resultSet.getDate("date_fin")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservations;
	}
        
        public List<Reservation> findChambreBetweenDates(java.util.Date datedebut,java.util.Date datefin,Client client) {
        
		ChambreService chambreService = new ChambreService();
		ClientService clientService = new ClientService();
		String query = "select * from reservation where date_debut>=? and date_fin<=? and id_client=? ;";
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
                        preparedStatement.setDate(1, new Date(datedebut.getTime())); 
                        preparedStatement.setDate(2, new Date(datefin.getTime())); 
                        preparedStatement.setInt(3, client.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				reservations.add(new Reservation(resultSet.getInt("id"), chambreService.findById(resultSet.getInt("id_chambre")),clientService.findById(resultSet.getInt("id_client")),resultSet.getDate("date_debut"),resultSet.getDate("date_fin")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservations;
	}

  
        
        


}
