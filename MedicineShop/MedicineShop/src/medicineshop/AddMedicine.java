/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicineshop;


import javax.swing.table.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


class MyFrame2 extends JFrame  implements ActionListener
{
    public static String tableName;
    JLabel l1, l2, l3,l4,l5,img;
    JButton b1,b2;
    JTextField t1,t2,t3,t4,t5;
    JPasswordField p1;
    JLabel imageLabel;
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);

    MyFrame2()
    {
        super("Add Medicine");
        
        JPanel addPanel = new JPanel(); // Create a panel to hold the login components
        addPanel.setLayout(null);
        addPanel.setBounds(20, 30, 600, 500);
        
        img = new JLabel();
         ImageIcon imageIcon = new ImageIcon("Addmed3.jpg");
        Image image = imageIcon.getImage();
        
       
        Image scaledImage = image.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(scaledImage)); // Set the scaled image as the icon
        img.setBounds(300, -50, 450, 400);
         
        
        l1 = new JLabel("Medicine Name:");
        l2 = new JLabel("Cost Per Unit:");
        l3 = new JLabel("Company Name:");
        l4 = new JLabel("Type:");
        //l5 = new JLabel("Description:");
        b1=new JButton("Save");
        b2=new JButton("cancel");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        //t5 = new JTextField();
        
        
        
        l1.setBounds(20, 45, 100, 25);
        l2.setBounds(20, 85, 100, 25);
        l3.setBounds(20, 125, 100, 25);
        l4.setBounds(20, 165, 100, 25);
      //  l5.setBounds(20, 200, 100, 20);
        
        t1.setBounds(130, 50, 150, 25);
        t2.setBounds(130, 85, 150, 25);
        t3.setBounds(130, 125, 150, 25);
        t4.setBounds(130, 165, 150, 25);
       // t5.setBounds(130, 200, 150, 20);
        
        b1.setBounds(120,220,70,30);
        b2.setBounds(210,220,75,30);
           
        
        
       addPanel.add(l1);
       addPanel.add(l2);
       addPanel.add(l3);
       addPanel.add(l4);
      // addPanel.add(l5);
    
      addPanel.add(t1);
      addPanel.add(t2);
      addPanel.add(t3);
      addPanel.add(t4);
     // addPanel.add(t5);
      
      addPanel.add(b1);
      addPanel.add(b2);
      
       addPanel.add(img);
      
      JScrollPane pg = new JScrollPane(jtbl);
        pg.setBounds(60, 300, 550, 200);
        cnt.add(pg);
        cnt.setLayout(null);
        
    TitledBorder addPanelTitledBorder = BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(Color.RED, 2), // Create a red line border with thickness 2
        "Medicine Management",
        TitledBorder.DEFAULT_JUSTIFICATION,
        TitledBorder.DEFAULT_POSITION,
        new Font("SansSerif", Font.BOLD, 16), // Customize font and size
        Color.red // Change the title color to orange
);

addPanel.setBorder(addPanelTitledBorder);

       
    add(addPanel);
      
    b1.addActionListener(this);
     b2.addActionListener(this);
  //  b1.doClick();//button is clicked when Frame is load
    setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1)
        {
          try {
              Connection connection = DatabaseConnection.getConnection();
             String s1 = t1.getText();
             String s2=t2.getText();
             String s3=t3.getText();
             String s4=t4.getText();
              if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Fill the TextFiled","Error",JOptionPane.ERROR_MESSAGE);
                return;
              }else {
               String query = "INSERT INTO medicine ( medicine_name, cost, company, type) VALUES (?, ?, ?, ?)";
               // Create the PreparedStatement
               PreparedStatement p = connection.prepareStatement(query);  
               p.setString(1, s1);
               p.setString(2, s2);
               p.setString(3, s3);
               p.setString(4, s4);
               int rowsInserted = p.executeUpdate();
               if (rowsInserted > 0) {
                  JOptionPane.showMessageDialog(null, " Added successfully!");  
                } else {
                    JOptionPane.showMessageDialog(null,"Error","Failed to Add Task",JOptionPane.ERROR_MESSAGE);
                }
            }
             String query ="SELECT * FROM medicine" ;
 
          // Prepare the statement with the query
           PreparedStatement p = connection.prepareStatement(query);
            ResultSet rs = p.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            String[] colname = new String[cols];
            for (int i = 0; i < cols; i++)
                colname[i] = rsmd.getColumnName(i + 1);
            model.setColumnIdentifiers(colname);
            while (rs.next()) {
                Object[] rowData = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    rowData[i] = rs.getString(i + 1);
                }
                model.addRow(rowData);
            }
            connection.close();
            p.close();
        } 
          catch (Exception e1) {
            System.out.println(e1);
        }  
        }
        if(e.getSource()==b2)
        {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
        }
        // Set custom renderer for the table header (first row)
       jtbl.getTableHeader().setDefaultRenderer(new CustomTableHeaderRenderer());

        // Set custom renderer for the table cells
        jtbl.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        }
        // Custom table header renderer for changing the background color
    class CustomTableHeaderRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                    column);

            cellComponent.setBackground(Color.RED);
            cellComponent.setForeground(Color.WHITE);

            return cellComponent;
        }
    }

    // Custom table cell renderer for changing the color of the rows
    class CustomTableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                    column);

            if (!isSelected) {
                // Change the background color of alternate rows (excluding the header row)
                if (row % 2 != 0 && row != 0) {
                    cellComponent.setBackground(Color.DARK_GRAY);
                    cellComponent.setForeground(Color.WHITE);
                } else {
                    cellComponent.setBackground(table.getBackground());
                    cellComponent.setForeground(table.getForeground());
                }
            }

            return cellComponent;
        }
    }
   
}

