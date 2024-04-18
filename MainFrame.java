import javax.swing.*;

public class MainFrame extends JFrame {

    public void initialize(String[][] table) {
        JFrame frame = new JFrame();
        String[] columnName = {"Name", "BloodGroup", "Rhd", "Availability"};
        JTable bloodtable = new JTable(table,columnName);
        bloodtable.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(bloodtable);

        frame.add(sp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
