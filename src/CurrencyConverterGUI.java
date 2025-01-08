import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CurrencyConverterGUI extends JFrame {
    public CurrencyConverterGUI() {
        setTitle("Conversor de Moedas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Digite o valor em Reais:");
        JTextField textField = new JTextField(10);
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "ILS", "JOD"});
        JButton button = new JButton("Converter");
        JLabel resultLabel = new JLabel("Resultado: ");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String jsonRates = CurrencyAPI.getExchangeRates();
                    String selectedCurrency = (String) comboBox.getSelectedItem();
                    double rate = ExchangeRateParser.getRate(jsonRates, selectedCurrency);
                    BigDecimal amount = new BigDecimal(textField.getText());
                    BigDecimal result = CurrencyConverter.convert(amount, rate);
                    resultLabel.setText("Resultado: " + result);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(comboBox);
        panel.add(button);
        panel.add(resultLabel);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterGUI().setVisible(true));
    }
}
