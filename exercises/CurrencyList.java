import java.awt.GridLayout;
import java.util.Currency;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CurrencyList extends JFrame {

  javax.swing.JList<Currency> currencyList;
  javax.swing.JScrollPane currencyScrollPane;

  Currency[] defaultCurrencies = {
    Currency.getInstance("USD"),
    Currency.getInstance("CAD"),
    Currency.getInstance("MXN"),
    Currency.getInstance("HTG"),
    Currency.getInstance("RUB"),
  };

  public CurrencyList() {
    super("Fun with JList");

    currencyScrollPane = new javax.swing.JScrollPane();
    currencyList = new javax.swing.JList<Currency>(defaultCurrencies);
    currencyScrollPane.setViewportView(currencyList);

    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridLayout(0, 1, 20, 20));
    leftPanel.add(new JLabel("Default Currencies"));
    leftPanel.add(currencyScrollPane);

    add(leftPanel, java.awt.BorderLayout.WEST);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 150);
    setVisible(true);
  }

  public static void main(String[] args) {
    CurrencyList app = new CurrencyList();
    app.setVisible(true);
  }
}
