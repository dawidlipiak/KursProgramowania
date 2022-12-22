import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;

class OknoWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e) { System.exit(0); }
}
class Przycisk extends Button {
    Przycisk(Okno k) {
        super("Potwierdz");
        addActionListener(new PrzyciskAdapter(k));
    }
}
class PrzyciskAdapter implements ActionListener {
    Okno k;
    PrzyciskAdapter(Okno k) {
        this.k = k;
    }
    public void actionPerformed(ActionEvent e) {
        k.action();
    }
}

public class Okno extends Frame {
    Przycisk button;
    private Label text;
    private TextField tf1;

    public Okno (){
        button = new Przycisk (this);
        tf1 = new TextField(50);    
        text = new Label("Wprowadz ilosc wierszy: ");
        text.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
        

        setLayout(new GridLayout(1,1));
        setFont(new Font(Font.SANS_SERIF,Font.PLAIN,35));

        add(text);
        add(tf1);
        add(button);

        setLayout(new GridLayout(0,1));
        setVisible(true);
        addWindowListener(new OknoWindowAdapter());
    }
    public void action (){
        String value = tf1.getText();
        try {
            int n = Integer.parseInt(value);

            if (n < 0) {
                button.setLabel("Powtorz");
                text.setText(value + " - Nieprawidlowy numer wiersza");
            }
            else{
                this.setVisible(false);
                TrojkatPascala finalFrame = new TrojkatPascala(n);
                finalFrame.setVisible(true);
            }

        }


        catch (NumberFormatException ex){
            button.setLabel("Powtorz");
            text.setBounds(220, 180, 190, 20);
            text.setText(value + " - Nieprawidlowy numer wiersza");
        }
    }

    class TrojkatPascala extends Frame {
        TrojkatPascala(int size){
            super("Trojkat Pascala");
            setBounds(300, 300, (size + 1) * 80, size * 70);
            setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
            setLayout(new GridLayout(0, 1));

            for(int i = 0; i <= size; i++){
                Label wiersz = new Label();
                add(wiersz);
                wiersz.setAlignment(Label.CENTER);
                String toString = "";
                for(int j = 0; j<= i; j++){
                    toString = toString + "  " + ncr(i,j);
                }
                wiersz.setText(toString);
            }
            addWindowListener(new OknoWindowAdapter());
        }
        
        
        static int factorial(int n) {
           int f;
     
           for(f = 1; n > 1; n--){
              f *= n;
           }
           return f;
        }
        static int ncr(int n,int r) {
           return factorial(n) / ( factorial(n-r) * factorial(r) );
        }


    }
}
