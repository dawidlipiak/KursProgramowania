import java.awt.*;
//import java.awt.event.*;
import java.awt.GridLayout;

public class Test {
    public static void main (String[] args) {
        Frame okno = new Okno();
        okno.setBounds(250,250,640,480);
        okno.setLayout(new GridLayout(3,1));
    }
}
