import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankTransactionSystemGUI {
    private static BankAccount account = new BankAccount(1000);

    public static void main(String[] args) {
        // Create a frame for the GUI
        JFrame frame = new JFrame("Bank Transaction System");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create UI elements
        // This panel used not to have any borders, making the buttons stretch a lot
        JPanel panel = new JPanel();
        JTextField depositField = new JTextField(10);
        JTextField withdrawField = new JTextField(10);
        JLabel balanceLabel = new JLabel("Balance: " + account.getBalance(), SwingConstants.CENTER);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        // Creating Error labels
        JLabel depositErrorLabel = new JLabel("");
        JLabel withdrawErrorLabel = new JLabel("");
        //depositErrorLabel.setForeground(Color.RED);
        withdrawErrorLabel.setForeground(Color.RED);
        
        // Layout settings
        panel.add(balanceLabel);
        
        panel.setLayout(new GridLayout(9, 4));
       
        
        panel.add(new JLabel("Deposit Amount:"));
        panel.add(depositField);
        panel.add(depositButton);
        panel.add(depositErrorLabel); 
        //panel.add(new JLabel(""));
        panel.add(new JLabel("Withdraw Amount:"));
        panel.add(withdrawField);
        panel.add(withdrawButton);
        panel.add(withdrawErrorLabel);
        //panel.add(new JLabel(""));
        

        frame.add(panel);
        frame.setVisible(true);

        // Action for deposit
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	double amount = Double.parseDouble(depositField.getText());
                new Thread(() -> {
                    String message = account.deposit(amount);
                    SwingUtilities.invokeLater(() -> {
                        if (message.startsWith("Deposited")) {
                            balanceLabel.setText("Balance: " + account.getBalance());
                            // For clearing the error message
                            depositErrorLabel.setText(message);
                        } else {
                        	depositErrorLabel.setForeground(Color.RED);
                        	depositErrorLabel.setText(message);
                        }
                    });
                }).start();
            }
        });

        // Action for withdrawal
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(withdrawField.getText());
                new Thread(() -> account.withdraw(amount)).start();
                balanceLabel.setText("Balance: " + account.getBalance());
            }
        });
    }
}
