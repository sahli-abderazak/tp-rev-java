package application;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import connexion.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Restaurantcontroller implements Initializable{
	@FXML
	private ComboBox<String> cbplat;
	@FXML
	private TextField txtqte;
	@FXML
	private RadioButton suppOk; 
	@FXML
	private RadioButton SuppNo; 
	
	@FXML
	private Button addhandle; 
	@FXML
	private Button removehandle; 
	@FXML
	private Button calculer; 
	@FXML
	private Label lblmontant; 
	@FXML
	private TableView<Plat_cmd> tableR;
	@FXML
	private TableColumn<Plat_cmd, String> collibelle; 
	@FXML //
	private TableColumn<Plat_cmd, Integer> colqte; 
	@FXML
	private TableColumn<Plat_cmd, Double> colsupp; 
	@FXML
	private TableColumn<Plat_cmd, Double> colpu; 
	@FXML
	private TableColumn<Plat_cmd, Double> colmontant; 
	
	
	ObservableList<Plat_cmd> list1 = FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	ArrayList<String> list = new ArrayList<String>();
	
	try {
		
        Connection conn = Connexion.getConn();
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM plat";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
        	list.add(rs.getString("libelle")); 
        }
        ObservableList<String> cb = FXCollections.observableArrayList(list);
        cbplat.setItems(cb);
    } catch (SQLException e) {
        System.err.println("Error executing query: " + e.getMessage());
    }
	collibelle.setCellValueFactory(new PropertyValueFactory<Plat_cmd,String>("libelle"));
	colqte.setCellValueFactory(new PropertyValueFactory<Plat_cmd,Integer>("quantite"));
	colpu.setCellValueFactory(new PropertyValueFactory<Plat_cmd,Double>("PU"));
	colmontant.setCellValueFactory(new PropertyValueFactory<Plat_cmd,Double>("montant"));
	colsupp.setCellValueFactory(new PropertyValueFactory<Plat_cmd,Double>("supplement"));
	 tableR.setItems(list1);
	}
	@FXML
	void addhandle(ActionEvent event) {
		String p=cbplat.getValue();
		 int q=Integer.parseInt(txtqte.getText());
		 boolean ok=suppOk.isSelected();
		try {
			 Connection conn = Connexion.getConn();
	        Statement stmt = conn.createStatement();
	        String query = "SELECT * FROM plat where libelle= '"+p+"'";
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	double pr=rs.getInt("prix");
	        	double mont=pr*q;
	        	if(ok==true)
	        	{
	        		mont+=2000;
	        		list1.add(new Plat_cmd(p,q,2000,mont,pr));
	        	}else
	        	{
	        		list1.add(new Plat_cmd(p,q,0,mont,pr));
	        	}
	        }
	    } catch (SQLException e) {
	        System.err.println("Error executing query: " + e.getMessage());
	    }
		
	}
	
	@FXML
	void calculer(ActionEvent event) {
		double total=0;
		for(Plat_cmd p :list1) {
			total=p.getMontant();
		}
		lblmontant.setText(String.valueOf(total));
		try {
			FileWriter output= new FileWriter("D:\\fichiers\\commandes.txt");
			output.write(lblmontant.getText());
			output.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void supp() {
		Plat_cmd selected=tableR.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("Form confirmation!");
		 alert.setHeaderText(null);
		 alert.setContentText("tu veut suppprimeeeerrrrrrrrr ????");
		Optional<ButtonType>o= alert.showAndWait();
		if(o.get().equals(ButtonType.OK))
		{
			tableR.getItems().remove(selected)	;
		}
		 
    }
	}
