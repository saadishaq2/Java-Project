package Customer_pak;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ShowData extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tbl_model;
//    private CustomerDashboard customerDashboard; // Assuming CustomerDashboard is the name of your existing class

    /**
     * Create the frame.
     */
    public ShowData() {
//        this.customerDashboard = customerDashboard;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 562, 369);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        String[] columnNames = {"Customer Name", "Payment", "Address", "Company"};
        tbl_model = new DefaultTableModel(null, columnNames);
        table = new JTable(tbl_model);
        table.setSize(500, 200);
        table.setLocation(20, 50);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 500, 200);
        contentPane.add(scrollPane);

        JButton loadbtn = new JButton("LOAD DATA");
        loadbtn.setBounds(20, 10, 117, 29);
        contentPane.add(loadbtn);

        JButton logoutbtn = new JButton("LOGOUT");
        logoutbtn.setBounds(406, 306, 117, 29);
        contentPane.add(logoutbtn);

        loadbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loaddata();
            }
        });

        logoutbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
    }

    private void loaddata() {
        String dburl = "jdbc:mysql://localhost:3306/customer_management";
        String username = "root";
        String password = "";

        try (Connection con = DriverManager.getConnection(dburl, username, password)) {
            String query = "SELECT * FROM customer_details";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rs_meta = rs.getMetaData();

            // Clear existing rows
            tbl_model.setRowCount(0);

            // Get column names and set them in the table model
            int columnCount = rs_meta.getColumnCount();
            String[] colname = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                colname[i] = rs_meta.getColumnName(i + 1);
            }
            tbl_model.setColumnIdentifiers(colname);

            // Add rows to the table model
            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getString(i + 1);
                }
                tbl_model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void logout() {
        // Implement logout functionality here
        JOptionPane.showMessageDialog(this, "Logout successful!");
        dispose(); // Close the frame
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Assuming you have an instance of CustomerDashboard already created
                CustomerDashboard customerDashboard = new CustomerDashboard();
                ShowData frame = new ShowData();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
