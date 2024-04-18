import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


public class Function extends JFrame{
    public void funcFrame(){
        JPanel infopanel = new JPanel();
        infopanel.setLayout(new BoxLayout(infopanel, BoxLayout.Y_AXIS));
        JLabel Menu = new JLabel("Menu");
        Menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        Menu.setBounds(0,0,100,100);
        Menu.setFont(new Font("Archivo", Font.ITALIC, 40));
        infopanel.add(Menu);

        JButton Tablebtn = new JButton("Table");
        Tablebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        Tablebtn.setMaximumSize(new Dimension(300, 300));
        // Tablebtn.setBounds(MAXIMIZED_BOTH, ABORT, 100, 100);
        Tablebtn.setFont(new Font("Archivo", Font.ITALIC, 40));
        Tablebtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String bloodtable[][] = getBloodData(" ",true);
                if (bloodtable != null){
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(bloodtable);
                };
                
            }
            
        });
        infopanel.add(Tablebtn);

        JButton ABloodtn = new JButton("A Blood");
        ABloodtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        ABloodtn.setMaximumSize(new Dimension(300, 300));
        // ABloodtn.setBounds(MAXIMIZED_BOTH, ABORT, 100, 100);
        ABloodtn.setFont(new Font("Archivo", Font.ITALIC, 40));
        ABloodtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String bloodtable[][] = getBloodData("A",false);
                if (bloodtable != null){
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(bloodtable);
                };
            }
            
        });
        infopanel.add(ABloodtn);

        JButton BBloodbtn = new JButton("B Blood");
        BBloodbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        BBloodbtn.setMaximumSize(new Dimension(300, 300));
        BBloodbtn.setFont(new Font("Archivo", Font.ITALIC, 40));
        BBloodbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String bloodtable[][] = getBloodData("B",false);
                if (bloodtable != null){
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(bloodtable);
                };
            }
            
        });
        infopanel.add(BBloodbtn);

        JButton ABBloodbtn = new JButton("AB Blood");
        ABBloodbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        ABBloodbtn.setMaximumSize(new Dimension(300, 300));
        ABBloodbtn.setFont(new Font("Archivo", Font.ITALIC, 40));
        ABBloodbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String bloodtable[][] = getBloodData("AB",false);
                if (bloodtable != null){
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(bloodtable);
                };
            }
            
        });
        infopanel.add(ABBloodbtn);

        JButton OBloodtn = new JButton("O Blood");
        OBloodtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        OBloodtn.setMaximumSize(new Dimension(300, 300));
        OBloodtn.setFont(new Font("Archivo", Font.ITALIC, 40));
        OBloodtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String bloodtable[][] = getBloodData("O",false);
                if (bloodtable != null){
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(bloodtable);
                };
            }
            
        });
        infopanel.add(OBloodtn);


        add(infopanel);

        setTitle("Menu");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 550);
        setVisible(true);
    }

    private String[][] getBloodData(String Bloodtype, Boolean Table){
        String BloodTable[][] = new String[120][4];
        final String DB_URL = "jdbc:mysql://localhost:3306/login";
        final String USERNAME = "root";
        final String PASSWORD = "ChengHao19961211";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            if (connect != null) {
                System.out.println("success");
            }
            else{
                System.out.println("connect Fail");
            }

            String sql;
            PreparedStatement preparedStatement;
            if (Table) {
                sql = "SELECT * FROM bloodbank"; 
                preparedStatement = connect.prepareStatement(sql);               
            }
            else{
                sql = "SELECT * FROM bloodbank WHERE Blood_Group=?";
                preparedStatement = connect.prepareStatement(sql);  
                preparedStatement.setString(1, Bloodtype);
            }

            // BloodTable = new String[120][4];
            ResultSet resultfind = preparedStatement.executeQuery();
            int count = 0;
            while (resultfind.next()) {
                BloodTable[count][0] = resultfind.getString("NAME");
                BloodTable[count][1] = resultfind.getString("Blood_Group");
                BloodTable[count][2] = resultfind.getString("Rhd");
                BloodTable[count][3] = resultfind.getString("Availability");
                count++;
            };
            preparedStatement.close();
            connect.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return BloodTable;
    }
}
