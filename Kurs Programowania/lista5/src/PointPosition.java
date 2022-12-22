import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa PointPosition w której z zebranych punktów tworzymy figurę
 */
public class PointPosition {
    public int pointNumber;
    public List <Point2D> pointPositions;

    /**
     * Konstruktor klasy
     */
    PointPosition (){
        pointNumber = 0;
        pointPositions = new ArrayList<>();
    }

    /**
     * Metoda w której dodajemy do listy punkty do utworzenia figury
     * @param x - pozycja x punktu
     * @param y - pozycja y punktu
     */
    void addPoint (double x, double y){
        Point2D point = new Point2D(x,y);

        pointNumber++;
        pointPositions.add(point);
    }
}