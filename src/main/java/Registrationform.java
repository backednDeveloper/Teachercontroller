import com.example.restcontrollerdemo.entity.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registrationform extends JDialog {
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtAge;
    private JTextField txtSalary;
    private JTextField txtSciencename;
    private JButton cancelButton;
    private JButton createButton;
    private JPanel registerpanel;
    private JPasswordField txtpassword;
    private JPasswordField txtcheckpassword;
    private JTextField txtID;
    private Teacher teacher;

    public Registrationform (JFrame parent){
        super(parent);
        setTitle("Create new account");
        setContentPane(registerpanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTeacher();
            }
            private void createTeacher() {
                String name = txtName.getText();
                String surname = txtSurname.getText();
                int age = Integer.parseInt(txtAge.getText());
                int salary = Integer.parseInt(txtSalary.getText());
                String scienceName = txtSciencename.getText();
                String ID = txtID.getText();
                String password = String.valueOf(txtpassword.getPassword());
                String checkpassword = String.valueOf(txtcheckpassword.getPassword());
                if(name.isEmpty() || surname.isEmpty() || age==0 || scienceName.isEmpty() ||
                        salary==0 || password.isEmpty() || checkpassword.isEmpty() || ID.isEmpty()) {
                    JOptionPane.showMessageDialog(Registrationform.this , "Please fill all ", "Try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!password.equals(checkpassword)){
                    JOptionPane.showMessageDialog(Registrationform.this, "Password is not correct", "Please try again" , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                teacher = addTeacherToDatabase(name,surname,age,salary,scienceName,password,ID);
                if(teacher==null){
                    JOptionPane.showMessageDialog(Registrationform.this, "Tecaher is not available", "Please registered", JOptionPane.ERROR_MESSAGE);
                }

            }
            public Teacher teacher;

            private Teacher addTeacherToDatabase(String name, String surname, int age, int salary, String scienceName, String password, String ID) {
                Teacher teacher = null;
                final String DB_URL = "jdbc:mysql://localhost:3306/Teacher";
                final String USARNAME = "root";
                final String PASSWORD = "Trako123.";
                try{
                    Connection connection = DriverManager.getConnection(DB_URL, USARNAME, PASSWORD);
                    Statement stm = connection.createStatement();
                    String sql = "INSERT INTO teacher(name,surname,age,salary,scienceName,password,ID)" + "VALUES (?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1,name);
                    preparedStatement.setString(2,surname);
                    preparedStatement.setString(3, String.valueOf(age));
                    preparedStatement.setString(4, String.valueOf(salary));
                    preparedStatement.setString(5,scienceName);
                    preparedStatement.setString(6,password);
                    int addedRows = preparedStatement.executeUpdate();
                    if(addedRows>0){
                        teacher = new Teacher();
                        teacher.setName(name);
                        teacher.setSurname(surname);
                        teacher.setScienceName(scienceName);
                        teacher.setAge(age);
                        teacher.setID(ID);
                        teacher.setPassword(password);
                        teacher.setSalary(salary);
                    }
                    stm.close();
                    connection.close();
                } catch (SQLException e) {
                    e.getMessage();
                }
                return teacher;
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Registrationform registrationform = new Registrationform(null);
        Teacher teacher = registrationform.teacher;
        if(teacher != null){
            System.out.println("Succesfull registration of : " + teacher.getName());
        }
        else {
            System.out.println("Registration is cancelled");
        }
    }
}
