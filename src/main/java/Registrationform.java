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
    private Teacher teacher;

    public Registrationform (JFrame parent){
        super(parent);
        setTitle("Creat New Profile ;)");
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
                String sciencename = txtSciencename.getText();
                int password = Integer.parseInt(txtpassword.getText());
                int checkpassword = Integer.parseInt(txtcheckpassword.getText());
                if(name.isEmpty() || surname.isEmpty() || age <= 0 || sciencename.isEmpty() ||
                        salary<=0 || password <= 0 || checkpassword <= 0 ) {
                    JOptionPane.showMessageDialog(Registrationform.this , "Please fill all ", "Try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(password != checkpassword){
                    JOptionPane.showMessageDialog(Registrationform.this, "Password is not correct", "Please try again" , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                teacher = addTeacherToDatabase(name,surname,age,salary,sciencename,password);
                if(teacher==null){
                    JOptionPane.showMessageDialog(Registrationform.this, "Failed to register user", "Please try again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(Registrationform.this, "Teacher has been registered", "Welcome our tema", JOptionPane.INFORMATION_MESSAGE);

            }
            public Teacher teacher;

            private Teacher addTeacherToDatabase(String name, String surname, int age, int salary, String scienceName, int password) {
                Teacher teacher = null;
                final String DB_URL = "jdbc:mysql://localhost:3306/Teacher";
                final String USERNAME = "root";
                final String PASSWORD = "Trako123.";
                try {
                    Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    String sql = "INSERT INTO teacher (name, surname, age, salary, scienceName, password) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, surname);
                    preparedStatement.setInt(3, age);
                    preparedStatement.setInt(4, salary);
                    preparedStatement.setString(5, scienceName);
                    preparedStatement.setInt(6, password);
                    int addedRows = preparedStatement.executeUpdate();
                    if (addedRows > 0) {
                        teacher = new Teacher();
                        teacher.setName(name);
                        teacher.setSurname(surname);
                        teacher.setScienceName(scienceName);
                        teacher.setAge(age);
                        teacher.setPassword(password);
                        teacher.setSalary(salary);
                    }
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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
