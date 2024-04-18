import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class LoginFrame extends JFrame {
    
    private static JLabel UsernameJLabel = new JLabel("Uasrname:");
    private static JTextField UsernameText = new JTextField(25);
    private static JLabel PasswordLable = new JLabel("Password:");
    private static JPasswordField PasswordText = new JPasswordField(25);
    private static JButton loginbutton = new JButton("Login");

    public void initialize(){
        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        UsernameJLabel.setBounds(0,0,100,30);
        UsernameJLabel.setFont(new Font("Sedan", Font.ITALIC, 20));
        Panel.add(UsernameJLabel);
        UsernameText.setBounds(150,0,200,30);
        UsernameText.setFont(new Font("Archivo", Font.ITALIC, 20));
        Panel.add(UsernameText);
        PasswordLable.setBounds(0,100,100,30);
        PasswordLable.setFont(new Font("Sedan", Font.ITALIC, 20));
        Panel.add(PasswordLable);
        PasswordText.setBounds(150,100,200,30);
        PasswordText.setFont(new Font("Archivo", Font.ITALIC, 20));
        Panel.add(PasswordText);

        loginbutton.setBounds(300,200,100,50);
        loginbutton.setFont(new Font("Sedan", Font.ITALIC, 20));
        loginbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String User = UsernameText.getText();
                String Pass = String.valueOf(PasswordText.getPassword());

                if (getAuthenticatedUser(User, Pass)) {
                    Function function = new Function();
                    function.funcFrame();
                    dispose();                    
                }
                else{
                    JOptionPane.showMessageDialog(LoginFrame.this,
                     "Invalid Username or Passworsd",
                     "try again",
                     JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        Panel.add(loginbutton);

        add(Panel);

        setTitle("welcome");
        setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Boolean getAuthenticatedUser(String User, String Pass){
        Boolean login = false;
        final String DB_URL = "jdbc:mysql://localhost:3306/login";
        final String USERNAME = "root";
        final String PASSWORD = "ChengHao19961211";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM login WHERE UserName=? AND Passwords=?";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, User);
            preparedStatement.setString(2, Pass);

            ResultSet resultfind = preparedStatement.executeQuery();
            if (resultfind.next()) {
                login = true;
            }

            preparedStatement.close();
            connect.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return login;
    }

    public static void main(String[] args) {
        LoginFrame Login = new LoginFrame();
        Login.initialize();
    }
}
