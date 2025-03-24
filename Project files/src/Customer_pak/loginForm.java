package Customer_pak;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class loginForm extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField t1;
	private JPasswordField p1;
    protected static  int userId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginForm frame = new loginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		t1 = new JTextField();
		t1.setBounds(148, 63, 96, 19);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JLabel email_lbl = new JLabel("Email:");
		email_lbl.setBounds(61, 66, 45, 13);
		contentPane.add(email_lbl);
		
		JLabel pass_lbl = new JLabel("Password:");
		pass_lbl.setBounds(61, 113, 77, 13);
		contentPane.add(pass_lbl);
		
		JButton login_btn = new JButton("LogIn");
		login_btn.addActionListener(this);
		login_btn.setBounds(148, 183, 85, 21);
		contentPane.add(login_btn);
		
		JLabel title_lbl = new JLabel("Login Form");
		title_lbl.setFont(new Font("Serif", Font.BOLD, 15));
		title_lbl.setBounds(163, 10, 109, 19);
		contentPane.add(title_lbl);
		
		p1 = new JPasswordField();
		p1.setBounds(148, 110, 96, 19);
		
		contentPane.add(p1);
		contentPane.enable(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		String email = t1.getText();
//		String pass = p1.getText();
		char[] passwordChars = p1.getPassword();

		// Convert the character array to a String (if needed)
		String pass = new String(passwordChars);
		if(email.isEmpty()||pass.isEmpty()) {
			
		}
		
		else {
       	     try {
       	    	String dburl ="jdbc:mysql://localhost:3306/customer_management";
       			String username = "root";
       			String password = "";
			   Connection con =DriverManager.getConnection(dburl ,username ,password);
			   String slct_qry = "SELECT * FROM customer_info WHERE email=? AND password=?";
			   PreparedStatement st = con.prepareStatement(slct_qry);
			   st.setString(1, email);
			   st.setString(2, pass);
			   ResultSet rs = st.executeQuery();

			   if(rs.next()) {
				   setVisible(false);
				   JOptionPane.showMessageDialog(null, "Login Sucessful");
				   userId = (int) rs.getLong("id");	
                   
				    CustomerDashboard cst = new CustomerDashboard();
			   	    cst.setVisible(true);
			   	    System.out.println("control below");
			   }
			   else {
				   JOptionPane.showMessageDialog(null, "Error");				
			   }

       	     } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	}
