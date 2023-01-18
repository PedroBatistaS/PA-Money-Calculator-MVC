package application;

import view.CurrencyLoaderFromFile;
import control.CalculateMoneyCommand;
import control.Command;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

import model.*;
import view.*;

public class Application extends JFrame{
    
    private Map<String, Command> commands = new HashMap<>();
    private static Map<String, JComponent> components = new HashMap<>();
    private static CurrencySet currencySet;
    
    public static void main(String[] args) {
        new Application().setVisible(true);
    }
    
    public Application() {
        currencySet = new CurrencyLoaderFromFile("C:/Users/Pedro/Desktop/COLEGIO/UNIVERSIDAD/3º AÑO/INGENIARÍA DEL SOFTWARE II/moneycalculatorfile.txt").loadCurrency();
        deployUI();
        addCommands();
    }

    private void deployUI() {
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 100));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(aplicationPanel());
    }

    private void addCommands() {
        commands.put("Calculate", new CalculateMoneyCommand());
    }

    private JPanel aplicationPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Original: "));
        panel.add(originalCombo());
        panel.add(new JLabel("Money: "));
        panel.add(originalMoneyTextField());
        panel.add(new JLabel("Exchange To: "));
        panel.add(exchangeToCombo());
        panel.add(exchangeButton());
        panel.add(new JLabel("Result: "));
        panel.add(resultTextField());
        return panel;
    }


    private JComboBox originalCombo() {
        JComboBox comboBox = new JComboBox();
        components.put("OriginalCombo", comboBox);
        currencySet.currencyMap().keySet().forEach(comboBox::addItem);
        comboBox.addActionListener(e -> {
            ((JComboBox)components.get("ExchangeToCombo")).removeAllItems();
            currencySet.currencyMap().
                    keySet().
                    stream().
                    filter(code -> !comboBox.getSelectedItem().toString().equals(code)).
                    forEach(((JComboBox)components.get("ExchangeToCombo"))::addItem);
            ((JTextField) components.get("ResultTextField")).setText("");
        });
        return comboBox;
    }


    private JTextField originalMoneyTextField() {
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(80, 24));
        components.put("OriginalMoneyTextField", text);
        return text;
    }

    private JComboBox exchangeToCombo() {
        JComboBox comboBox = new JComboBox();
        components.put("ExchangeToCombo", comboBox);
         currencySet.currencyMap().
                keySet().
                stream().
                filter(code -> !((JComboBox)components.get("OriginalCombo")).getSelectedItem().toString().equals(code)).
                forEach(comboBox::addItem);
        comboBox.addActionListener(e -> ((JTextField) components.get("ResultTextField")).setText(""));
        return comboBox;
    }

    private JButton exchangeButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> commands.get("Calculate").execute());
        return button;
    }

    private JTextField resultTextField() {
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(160, 24));
        components.put("ResultTextField", text);
        return text;
    }
    
    public static Map<String, JComponent> components() {
        return components;
    }    
    
    public static CurrencySet currencySet() {
        return currencySet;
    }
}
