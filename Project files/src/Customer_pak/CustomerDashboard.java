package Customer_pak;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class CustomerDashboard extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton addBtn;
	private JButton updatebtn;
	private JButton deletebtn;
	private JButton showbtn;
	
	private JTable showTable;
    private DefaultTableModel tableModel;
	


	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDashboard window = new CustomerDashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerDashboard() {
		initialize();
		// Declare showbtn as a JTable

		
	}

	
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Name:");
		lblNewLabel.setBounds(26, 29, 106, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 24, 232, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Payment:");
		lblNewLabel_1.setBounds(68, 74, 144, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(149, 79, 232, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setBounds(71, 133, 61, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(149, 128, 232, 26);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Company:");
		lblNewLabel_3.setBounds(68, 181, 74, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(149, 176, 232, 26);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		addBtn= new JButton("ADD");
		
		addBtn.setBounds(6, 237, 117, 29);
		frame.getContentPane().add(addBtn);
		
		updatebtn = new JButton("UPDATE");
		
		updatebtn.setBounds(112, 237, 117, 29);
		frame.getContentPane().add(updatebtn);
		
		deletebtn = new JButton("DELETE");
		
		deletebtn.setBounds(219, 237, 117, 29);
		frame.getContentPane().add(deletebtn);
		
		showbtn = new JButton("SHOW TABLE");
		showbtn.setBounds(327, 237, 117, 29);
		frame.getContentPane().add(showbtn);
		
		showTable = new JTable();
	    tableModel = new DefaultTableModel();
		showTable.setModel(tableModel);
		frame.setVisible(true);
		addBtn.addActionListener(this);
		updatebtn.addActionListener(e -> updatedata());
		deletebtn.addActionListener(e -> deletePeople());
		showbtn.addActionListener(this);
	}

	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addBtn) {
			
		
       adddata();
       }
		else if
			(e.getSource()==updatebtn) {
				
				
			       updatedata();
			       }
		 else if
		 (e.getSource()==deletebtn) {
			 
			 deletePeople();
		 }
		
		 else if
             (e.getSource()==showbtn) {
			 System.out.println("I am here ");
			 showtable();
		 }
		}
    
	
	public void adddata() {
     
		String customername = textField_1.getText();
      
        int payment = Integer.parseInt(textField_2.getText());
       
		String address = textField_3.getText();
  
		String company = textField_4.getText();
        String dburl = "jdbc:mysql://localhost:3306/customer_management";
        String username = "root";
        String password = "";

        try (Connection con = DriverManager.getConnection(dburl, username, password)) {
            String query = "INSERT INTO customer_details (cst_Name,cst_payment,cst_address,cst_company) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, customername );
            statement.setInt(2, payment);
            statement.setString(3, address );
            statement.setString(4, company);
            int rowsInserted = statement.executeUpdate();
            //System.out.println("I am Running");
           // System.out.println(rowsInserted);
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog( null, "Inserted records");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
	public void updatedata() {
		    String Name = textField_1.getText();
		    int payment = Integer.parseInt(textField_2.getText());
	        String Address = textField_3.getText();
	        String company = textField_4.getText();
	        String dburl = "jdbc:mysql://localhost:3306/customer_management";
	        String username = "root";
	        String password = "";

	        try (Connection con = DriverManager.getConnection(dburl, username, password)) {
	        	String sql_update = "UPDATE customer_details SET cst_Name=?, cst_payment=?, cst_address=?, cst_company=? WHERE cst_address=?";
	        	
	            PreparedStatement statement = con.prepareStatement(sql_update);
	            statement.setString(1, Name);
	            statement.setInt(2, payment);
	            statement.setString(3, Address);
	            statement.setString(4, company);
	            statement.setString(5, Address);
	            
	            int rowsUpdated = statement.executeUpdate();
	           // System.out.println("I am Running");
	           // System.out.println(rowsUpdated);
	        
	            if (rowsUpdated > 0) {
	                JOptionPane.showMessageDialog(null, "Updated records");
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	}
	
	public void deletePeople() 
	{
		String Name = textField_1.getText();
	    String paymentText = textField_2.getText();
        String Address = textField_3.getText();
        String company = textField_4.getText();
        String dburl = "jdbc:mysql://localhost:3306/customer_management";
        String username = "root";
        String password = "";
        
        
        try (Connection con = DriverManager.getConnection(dburl, username, password)) {
            String sql = "DELETE FROM customer_details WHERE cst_Name= ?";
            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setString(1, Name);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Deleted records");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

	public void showtable() {
		setVisible(false);
		ShowData d1 = new ShowData();
		d1.setVisible(true);
		
		
		
		
		
		
//		String Name = textField_1.getText();
//	    String paymentText = textField_2.getText();
//        String Address = textField_3.getText();
//        String company = textField_4.getText();
//        String dburl = "jdbc:mysql://localhost:3306/customer_management";
//        String username = "root";
//        String password = "";
//        
//        try (Connection con = DriverManager.getConnection(dburl, username, password)) {
//            String query = "SELECT * FROM customer_details";
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            ResultSetMetaData rs_meta = rs.getMetaData();
//            // Assuming showbtn is the table model for your JTable
//       
//			 System.out.println("I am here ");
//
//            // Clear existing rows
//            tableModel.setRowCount(0);
//            // Add rows to the table model
//            int columnCount = rs_meta.getColumnCount();
//            String[] colname = new String[columnCount];
//            for (int i = 0; i < columnCount; i++) {
//                colname[i] = rs_meta.getColumnName(i + 1);
//            }
//            tableModel.setColumnIdentifiers(colname);
//
//            while (rs.next()) {
//                String[] row = new String[columnCount];
//                for (int i = 0; i < columnCount; i++) {
//                    row[i] = rs.getString(i + 1);
//                }
//                tableModel.addRow(row);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }
}
