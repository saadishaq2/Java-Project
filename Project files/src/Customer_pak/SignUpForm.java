package Customer_pak;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class SignUpForm extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JPasswordField p1;
	private JPasswordField passwordField_1;
	private JPasswordField p2;
	private JLabel title_form;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpForm frame = new SignUpForm();
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
	public SignUpForm() {
		
//		JLabel lbl_welcome = new JLabel("Customer Management");
//        lbl_welcome.setBounds(100, 50, 80, 30);
//        .add(lbl_welcome);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel username_lbl = new JLabel("UserName:");
		username_lbl.setBounds(10, 34, 102, 27);
		contentPane.add(username_lbl);
		
		t1 = new JTextField();
		t1.setBounds(138, 38, 110, 23);
		contentPane.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setBounds(138, 75, 110, 19);
		contentPane.add(t2);
		t2.setColumns(10);
		
		p1 = new JPasswordField();
		p1.setBounds(138, 122, 110, 19);
		contentPane.add(p1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(235, 164, 0, 19);
		contentPane.add(passwordField_1);
		
		p2 = new JPasswordField();
		p2.setBounds(138, 164, 110, 19);
		contentPane.add(p2);
		
		JLabel email_lbl = new JLabel("Email:");
		email_lbl.setBounds(10, 78, 85, 16);
		contentPane.add(email_lbl);
		
		JLabel pass_lbl = new JLabel("Password:");
		pass_lbl.setBounds(10, 125, 85, 16);
		contentPane.add(pass_lbl);
		
		JLabel confirmPass_lbl = new JLabel("Confirm Password:");
		confirmPass_lbl.setBounds(10, 167, 102, 16);
		contentPane.add(confirmPass_lbl);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.setBounds(138, 216, 85, 21);
		contentPane.add(submitBtn);
		
		title_form = new JLabel("SignUp Form");
		title_form.setBounds(154, 10, 94, 13);
		contentPane.add(title_form);
		
		submitBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 String userName = t1.getText();
		 String email = t2.getText();
		String pass = p1.getText();
   	    String confirmPass = p2.getText();
   	    if(userName.isEmpty()||email.isEmpty()||pass.isEmpty()||confirmPass.isEmpty()) {
   	    	JOptionPane.showMessageDialog(null,"Please Fill All the Field");
   	    }
   	    else if(!pass.equals(confirmPass)) {
   	    	JOptionPane.showMessageDialog(null,"Password is not Matching");

   	    }
   	    else {   
   	 try {
		String dburl ="jdbc:mysql://localhost:3306/customer_management";
		String username = "root";
		String password = "";
		Connection con = DriverManager.getConnection(dburl ,username ,password);
		String slct_qry = "SELECT * FROM customer_info WHERE username=? AND Password=?";
		
		PreparedStatement st = con.prepareStatement(slct_qry);
		st.setString(1, email);
		st.setString(2,password);
		ResultSet rs = st.executeQuery();
		if(rs.isBeforeFirst()) {
	   	    	JOptionPane.showMessageDialog(null,"Sorry This Email Already Exists:)");
		}
			else {
			
			String insrt_qry="INSERT INTO customer_info (password,email,username) VALUES (?,?,?)";
			st = con.prepareStatement(insrt_qry);
			st.setString(1,pass);
			st.setString(2, email);
			st.setString(3, userName);
			int insertrow = st.executeUpdate();
			
			if(insertrow>0) {
				JOptionPane.showMessageDialog(null,"Registerd Successfully:)");
//				contentPane.enable(false);
				this.dispose();
			//	setVisible(false);
				loginForm login = new loginForm();
				login.setVisible(true);
	   	    	
	   	    		   	     	    	
			}
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

		 
	}
   	    }
}
