package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;

public class Signup extends JFrame implements ActionListener {
    Choice loginASCho;

    TextField meterText, EmployerText, userNameText, nameText, passwordText;

    JButton create, back;

    Signup() {
        super("Signup Page");
        getContentPane().setBackground(Color.white);

        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setBounds(170,50,120,20);
        add(loginASCho);

        JLabel meterNo= new JLabel("Meter Number");
        meterNo.setBounds(30,100,125,20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170,100,125,20);
        meterText.setVisible(false);
        add(meterText);

        JLabel Employer = new JLabel("Employer ID");
        Employer.setBounds(30,100,125,20);
        Employer.setVisible(true);
        add(Employer);

        EmployerText = new TextField();
        EmployerText.setBounds(170,100,125,20);
        EmployerText.setVisible(true);
        add(EmployerText);

        JLabel userName = new JLabel("User Name");
        userName.setBounds(30,140,125,20);
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(170,140,125,20);
        add(userNameText);

        JLabel name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);

        nameText = new TextField();
        nameText.setBounds(170,180,125,20);
        add(nameText);


        JLabel password = new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170,220,125,20);
        add(passwordText);

        loginASCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginASCho.getSelectedItem();
                if (user.equals("Customer")){
                    Employer.setVisible(false);
                    EmployerText.setVisible(false);

                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }else {
                    Employer.setVisible(true);
                    EmployerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(0, 99, 225));
        create.setForeground(Color.white);
        create.setBounds(50,280,100, 25);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(0,99,225));
        back.setForeground(Color.white);
        back.setBounds(180,280,100,25);
        back.addActionListener(this);
        add(back);

        ImageIcon loginImage = new ImageIcon(ClassLoader.getSystemResource("icon/login.jpg"));
        Image loginImg = loginImage.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon loginImage2 = new ImageIcon(loginImg);
        JLabel loginLabel = new JLabel(loginImage2);
        loginLabel.setBounds(320,30,250,250);
        add(loginLabel);



        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);
    }

   /* @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String sloginAs = loginASCho.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            try{
                Database c = new Database();
                String query = null;
                query = "insert into signup values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+sloginAs+"')";
                c.statement.executeUpdate(query);
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String sloginAs = loginASCho.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();

            // ..........................
            /*if (susername.isEmpty() || sname.isEmpty() || spassword.isEmpty() || (sloginAs.equals("Customer") && smeter.isEmpty())) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields.");
                return;
            }*/

            try {
                Database c = new Database();
                if (c.connection == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed.");
                    return;
                }

                String query = "INSERT INTO singup (meter_no, username, name, password, usertype) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, smeter.isEmpty() ? null : smeter);
                pstmt.setString(2, susername);
                pstmt.setString(3, sname);
                pstmt.setString(4, spassword);
                pstmt.setString(5, sloginAs);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Account created successfully!");
                    setVisible(false);
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(null, "Account creation failed.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while creating account: " + ex.getMessage());
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
