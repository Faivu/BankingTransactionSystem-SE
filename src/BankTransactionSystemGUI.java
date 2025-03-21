import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankTransactionSystemGUI {
    private static BankAccount account = new BankAccount(1000);

    // Declare UI components at the class level
    private static JLabel balanceLabel;
    private static JTextField depositField;
    private static JTextField withdrawField;
    private static JLabel depositErrorLabel;
    private static JLabel withdrawErrorLabel;
    private static JButton depositButton;
    private static JButton withdrawButton;

    public static void main(String[] args) {
        // Create a frame for the GUI
        JFrame frame = new JFrame("Bank Transaction System");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create UI elements
        JPanel panel = new JPanel();
        depositField = new JTextField(10);
        withdrawField = new JTextField(10);
        balanceLabel = new JLabel("Balance: " + account.getBalance(), SwingConstants.CENTER);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        // Creating Error labels
        depositErrorLabel = new JLabel("");
        withdrawErrorLabel = new JLabel("");

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
                            depositErrorLabel.setForeground(Color.GRAY);
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
                            withdrawErrorLabel.setForeground(Color.GRAY);
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

    // Getter methods for the test class
    public static JLabel getBalanceLabel() {
        return balanceLabel;
    }

    public static JTextField getDepositField() {
        return depositField;
    }

    public static JTextField getWithdrawField() {
        return withdrawField;
    }

    public static JLabel getDepositErrorLabel() {
        return depositErrorLabel;
    }

    public static JLabel getWithdrawErrorLabel() {
        return withdrawErrorLabel;
    }

    public static JButton getDepositButton() {
        return depositButton;
    }

    public static JButton getWithdrawButton() {
        return withdrawButton;
    }
}