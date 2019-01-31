import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Component;

public class LocaleList extends JFrame {
    
    javax.swing.JList<Locale> localeList;
    javax.swing.JScrollPane localeScrollPane;
    
    private Locale[] locales = 
        {new Locale("en", "US"), new Locale("fr", "FR"), 
         new Locale("th", "TH"), new Locale("es", "MX"), 
         new Locale("ja", "JP")};

    public LocaleList() {
        super("Fun with JList");

        localeScrollPane = new javax.swing.JScrollPane();
        localeList = new javax.swing.JList<Locale>(locales);

        localeScrollPane.setViewportView(localeList);

        add(localeScrollPane, java.awt.BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }
    
    
    public static void main(String args[]) {
        LocaleList app = new LocaleList();
        app.setVisible(true);
    }
}

