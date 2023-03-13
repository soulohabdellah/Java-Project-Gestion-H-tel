/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.labels.PieSectionLabelGenerator;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
import org.jfree.chart.plot.PiePlot;  
import org.jfree.data.general.DefaultPieDataset;  
import org.jfree.data.general.PieDataset;  
/**
 *
 * @author Souloh
 */
public class StatistiquesForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form StatistiquesForm
     */
    public StatistiquesForm() {
        initComponents();
          
  
    // Create dataset  
    PieDataset dataset = createDataset();  
  
    // Create chart  
    JFreeChart chart = ChartFactory.createPieChart(  
        "Nombre de chambre par Categorie",  
        dataset,  
        true,   
        true,  
        false);  
  
    //Format Label  
    PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(  
        "Nombre de Chambre de {0} : {1}  ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));  
    ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);  
      
    // Create Panel  
    ChartPanel panel = new ChartPanel(chart);  
    setContentPane(panel);  
    
    
    
    
       
    }
    private PieDataset createDataset() {  
  
    DefaultPieDataset dataset=new DefaultPieDataset();  
    String query = "select count(*),categorie.libelle from chambre,categorie WHERE id_categorie=categorie.id group by id_categorie;";
		try {
                   PreparedStatement preparedStatement = connexion.Connexion.getConnexion().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
                        while(resultSet.next()){
                         dataset.setValue(resultSet.getString(2), resultSet.getInt(1)); 
                        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
   
    return dataset;  
  }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(102, 204, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 824, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
