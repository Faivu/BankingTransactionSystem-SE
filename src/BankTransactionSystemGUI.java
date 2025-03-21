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
        JPanel panel = new JPanel();
        JTextField depositField = new JTextField(10);
        JTextField withdrawField = new JTextField(10);
        JLabel balanceLabel = new JLabel("Balance: " + account.getBalance(), SwingConstants.CENTER);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        // Creating Error labels
        JLabel depositErrorLabel = new JLabel("");
        JLabel withdrawErrorLabel = new JLabel("");
        
        
        
        // Layout settings
        panel.add(balanceLabel);
        panel.setLayout(new GridLayout(9, 4));
       
        // Initialising the UI elements
        panel.add(new JLabel("Deposit Amount:"));
        panel.add(depositField);
        panel.add(depositButton);
        panel.add(depositErrorLabel); 
        panel.add(new JLabel("Withdraw Amount:"));
        panel.add(withdrawField);
        panel.add(withdrawButton);
        panel.add(withdrawErrorLabel);
        

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

                         // Changing the colours of the message depending on if it is an error or a confirmation message
                            depositErrorLabel.setForeground(Color.BLACK);
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
                new Thread(() -> {
                    String message = account.withdraw(amount);
                    SwingUtilities.invokeLater(() -> {
                        if (message.startsWith("Withdrawn")) {
                            balanceLabel.setText("Balance: " + account.getBalance());
                            
                            // Changing the colours of the message depending on if it is an error or a confirmation message
                            withdrawErrorLabel.setForeground(Color.BLACK);
                            withdrawErrorLabel.setText(message);
                        } else {
                            withdrawErrorLabel.setForeground(Color.RED);
                            withdrawErrorLabel.setText(message);
                        }
                    });
                }).start();
            }
        });
        
        }
    }

