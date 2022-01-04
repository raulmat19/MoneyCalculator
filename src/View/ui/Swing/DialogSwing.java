package View.ui.Swing;

import View.ui.Dialog;
import Controller.MainFrame;
import Model.Currency;
import Model.Money;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class DialogSwing extends JPanel implements Dialog{
    
    protected final List<Currency> currencies;
    public double amount = 0;
    private final MainFrame controller;
    private Currency currencyTo;
    private Currency currencyFrom;
    private JComboBox comboBoxFrom;
    private JComboBox comboBoxTo;

    public DialogSwing(List<Currency> currencies, MainFrame controller) {
        this.currencies = currencies;
        this.controller = controller;
        initGUI();
    }   

    @Override
    public Money getMoney() {
        return new Money(this.amount, this.currencyFrom);
    }

    @Override
    public Currency getCurrencyTo() {
        return currencyTo;
    }
    
    // JPanel components creation
    private JComboBox comboBoxCurrencyFrom() {
        comboBoxFrom = createComboBox();
        comboBoxFrom.addItemListener(currencyFromChange());
        currencyFrom = (Currency) comboBoxFrom.getSelectedItem();
        return comboBoxFrom;
    }
    
    private JComboBox comboBoxCurrencyTo() {
        comboBoxTo = createComboBox();
        comboBoxTo.addItemListener(currencyToChange());
        currencyTo = (Currency) comboBoxTo.getSelectedItem();
        return comboBoxTo;
    }
    
    private JScrollPane textAreaAmount() {
        JTextArea textArea = new JTextArea(2, 15);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.getDocument().addDocumentListener(amountChange());
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(textArea);
        
        scroll.setSize(211, 50);
        scroll.setMaximumSize(new Dimension(211, 50));
        return scroll;
    }
    
    
    // ItemListener events management
    private ItemListener currencyFromChange(){
        return (ItemEvent e) -> {
            if ((e.getStateChange() == ItemEvent.SELECTED)) {
                currencyFrom = (Currency) e.getItem();
                controller.updateDisplay();
            }
        };
    }
    
    private ItemListener currencyToChange(){
        return (ItemEvent e) -> {
            if ((e.getStateChange() == ItemEvent.SELECTED)) {
                currencyTo = (Currency) e.getItem();
                controller.updateDisplay();
            }
        };
    }
    
    private DocumentListener amountChange(){
        return new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
                amountUpdate(de.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                amountUpdate(de.getDocument());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                amountUpdate(de.getDocument());
            }
            
            private void amountUpdate(Document d){
                try{
                    String line = d.getText(0, d.getLength());
                    try{
                        amount = Double.parseDouble(line);
                        controller.updateDisplay();
                    } catch (NumberFormatException e){
                        controller.DisplayError("Flaco mete números");
                    }
                } catch (BadLocationException ble) {
                    System.out.println(ble.getMessage());
                }
            }
        };
    }
    
    // ComboBox auxiliar method
    private JComboBox createComboBox(){
        JComboBox cb = new JComboBox();
        cb.setSize(75, 25);
        cb.setMaximumSize(new Dimension(75, 25));
        currencies.forEach((currency) -> {
            cb.addItem(currency);
        });
        
        return cb;
    }

    // GUI creation
    private void initGUI(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Title Label
        JLabel label = new JLabel("MoneyCalculator");
        label.setFont(new java.awt.Font("Georgia", 3, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(label);
        
        this.add(Box.createRigidArea(new Dimension(0, 25)));  // Space
        
        // From Label
        JLabel label2 = new JLabel("FROM");
        label2.setFont(new java.awt.Font("Georgia", 1, 14));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(label2);
        
        // From ComboBox
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(comboBoxCurrencyFrom());
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(textAreaAmount());
        
        this.add(Box.createRigidArea(new Dimension(0, 20)));  // Space
        
        // To Label
        JLabel label3 = new JLabel("TO");
        label3.setFont(new java.awt.Font("Georgia", 1, 14));
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(label3);
        
        // To ComboBox
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(comboBoxCurrencyTo());
        this.add(Box.createRigidArea(new Dimension(0, 5)));
    }
}
