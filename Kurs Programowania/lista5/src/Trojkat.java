import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Slider;
import javafx.geometry.Orientation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Klasa trójkata
 */
public class Trojkat extends Polygon {

    double x0,y0;
    ContextMenu contextMenu = new ContextMenu();
    Rotate rotate;

    /**
     * Konstruktor trójkąta
     * @param x1 pozycja x pierwszego wierzchołka
     * @param y1 pozycja y pierwszego wierzchołka
     * @param x2 pozycja x drugiego wierzchołka
     * @param y2 pozycja y drugiego wierzchołka
     * @param x3 pozycja x trzeciego wierzchołka
     * @param y3 pozycja y trzeciego wierzchołka
     */
    public Trojkat (double x1, double y1, double x2, double y2, double x3,double y3) {
        super(x1,y1,x2,y2,x3,y3);

        /** Wyznaczenie środka trójkąta służącego jako pivot do jego obrotu */
        x0 =(x1+x2+x3)/3;
        y0 = (y1+y2+y3)/3;

        setStrokeWidth(2);

        setOnMouseClicked(new TrojkatEventHandler());
        setOnMouseDragged(new TrojkatEventHandler());
        setOnScroll(new TrojkatScrollHandler());


        Slider slider = new Slider(0, 360, 0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(90);
        slider.setOrientation(Orientation.HORIZONTAL);


        rotate = new Rotate();
        rotate.setPivotX(this.x0);
        rotate.setPivotY(this.y0);
        this.getTransforms().addAll(rotate);

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);

        MenuItem colorSelector = new MenuItem(null, colorPicker);
        MenuItem menuSlider = new MenuItem(null, slider);

        contextMenu.getItems().addAll(colorSelector, menuSlider);

        /** Ustawianie koloru figury wybranego z palety colorpickera */
        colorSelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFill(colorPicker.getValue());
            }
        });

        /** Obsługa obrotu figury podczas użycia slidera */
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue <?extends Number>observable, Number oldValue, Number newValue){
                /** Ustawienie wartości obrotu figury */
                rotate.setAngle((double) newValue);
            }
        });

    }

    /**
     * Funkcja sprawdza czy kursor najechal na figure
     * @param x - pozycja x kursora
     * @param y - pozycja y kursora
     * @return - zwraca prawdę lub fałsz zależnie czy x i y zawierają się w figurze
     */
    boolean isHit (double x, double y){
        this.setStroke(Color.RED);
        return getBoundsInLocal().contains(x,y);
    }

    /**
     * Implementacja przesuwania figury
     */
    class TrojkatEventHandler implements EventHandler<MouseEvent> {
        Trojkat trojkat;
        private double x, y;
        private void doMove(MouseEvent event) {

            double dx = event.getX() - x;
            double dy = event.getY() - y;

            if (trojkat.isHit(x,y)) {
                trojkat.getPoints().set(0,trojkat.getPoints().get(0) + dx);
                trojkat.getPoints().set(2,trojkat.getPoints().get(2) + dx);
                trojkat.getPoints().set(4,trojkat.getPoints().get(4) + dx);

                trojkat.getPoints().set(1,trojkat.getPoints().get(1) + dy);
                trojkat.getPoints().set(3,trojkat.getPoints().get(3) + dy);
                trojkat.getPoints().set(5,trojkat.getPoints().get(5) + dy);

                x0 = (trojkat.getPoints().get(0) + trojkat.getPoints().get(2) + trojkat.getPoints().get(4))/3;
                y0 = (trojkat.getPoints().get(1) + trojkat.getPoints().get(3) + trojkat.getPoints().get(5))/3;

            }
            x0 += dx;
            y0 += dy;
            x += dx;
            y += dy;
        }

        @Override
        public void handle(MouseEvent event) {
            trojkat = (Trojkat) event.getSource();
            trojkat.setStroke(trojkat.getFill());

            if (event.getEventType()==MouseEvent.MOUSE_CLICKED){
                x = event.getX();
                y = event.getY();
            }
            if (event.getEventType()==MouseEvent.MOUSE_DRAGGED && event.getButton() == MouseButton.PRIMARY){
                contextMenu.hide();
                doMove(event);
            }
            if(event.getButton() == MouseButton.SECONDARY){
                rotate.setPivotX(trojkat.x0);
                rotate.setPivotY(trojkat.y0);
                contextMenu.show(trojkat,event.getX(),event.getY());
                isHit(x,y);
            }
        }
    }

    /**
     * Implementacja scrollowania które zmienia rozmiary trójkata
     */
    class TrojkatScrollHandler implements EventHandler<ScrollEvent> {
        Trojkat trojkat;

        private void doScale (ScrollEvent scrollEvent){
            double x = scrollEvent.getX();
            double y = scrollEvent.getY();

            if(isHit(x,y)){
                trojkat.setScaleX(trojkat.getScaleX() + 0.1*Math.signum(scrollEvent.getDeltaY()));
                trojkat.setScaleY(trojkat.getScaleY() + 0.1*Math.signum(scrollEvent.getDeltaY()));
            }

        }

        @Override
        public void handle(ScrollEvent scrollEvent) {
            trojkat = (Trojkat) scrollEvent.getSource();
            if(scrollEvent.getEventType() == ScrollEvent.SCROLL){
                doScale(scrollEvent);
            }
            trojkat.setStroke(trojkat.getFill());
        }
    }
}
