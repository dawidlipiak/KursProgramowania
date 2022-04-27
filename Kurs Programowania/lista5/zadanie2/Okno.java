import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;



import java.io.*;

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
    private static TextArea textArea;

    public Okno (){
        button = new Przycisk (this);
        tf1 = new TextField(50);    
        text = new Label("Wprowadz dane wejsciowe: ");
        text.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
        textArea = new TextArea("");
        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
    
        setLayout(new GridLayout (4,1));
        
        setFont(new Font(Font.SANS_SERIF,Font.PLAIN,35));

        add(text);
        add(tf1);
        add(button);
        add(textArea);

        //setLayout(new GridLayout(0,1));
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

                ProcessReaderCpp.processReaderCpp(n);

            }

        }


        catch (NumberFormatException ex){
            button.setLabel("Powtorz");
            text.setBounds(220, 180, 190, 20);
            text.setText(value + " - Nieprawidlowy numer wiersza");
        }
    }

    public class ProcessReaderCpp {
        public static void processReaderCpp(int n){    
            try {
                String dane = "main.exe "+ n;
                for(int i = 0; i <= n; i++){
                    dane = dane + " " + i;
                }
                Process getTriangle = Runtime.getRuntime().exec(dane);

                BufferedReader reader = new BufferedReader(new InputStreamReader(getTriangle.getInputStream()));
                String line;
                textArea.setText("");
                
                while((line = reader.readLine()) != null){
                    textArea.setText(textArea.getText()+line+"\n");
                }
                
                
                
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    
}
