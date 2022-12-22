import java.awt.*;
//import java.awt.event.*;
import java.awt.GridLayout;

public class Test {
    public static void main (String[] args) {
        Frame okno = new Okno();
        okno.setBounds(250,250,1080,720);
        okno.setLayout(new GridLayout(4,1));
    }
}
