import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import java.awt.event.KeyListener;
import java.lang.Math;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Slider;
import javafx.geometry.Orientation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;


/**
 * Klasa prostokąta
 */
public class Prostokat extends Rectangle {

    double x0,y0;
    ContextMenu contextMenu = new ContextMenu();
    Rotate rotate;
    BorderPane root;

    /**
     * Konstruktor prostokata
     * @param p1 pierwszy wierzchołek prostokąta
     * @param p2 przeciwny do pierwszego wierzchołek
     */
    public Prostokat(Point2D p1, Point2D p2, BorderPane pane) {
        super( Math.min( p1.getX(),p2.getX() ) , Math.min( p1.getY(),p2.getY() ) , Math.abs(p2.getX() - p1.getX()) , Math.abs(p2.getY() - p1.getY()) );

        root = pane;

        /** Wyznaczenie środka trójkąta służącego jako pivot do jego obrotu */
        x0 = Math.min(p1.getX(),p2.getX()) + Math.abs(p2.getX() - p1.getX())/2 ;
        y0 = Math.min(p1.getY(),p2.getY()) +  Math.abs(p2.getY() - p1.getY())/2;

        setOnMouseClicked(new ProstokatEventHandler());
        setOnMouseDragged(new ProstokatEventHandler());
        setOnScroll(new ProstokatScrollHandler());
//        setOnKeyPressed(new ProstokatRotateHandler());


//        Slider slider = new Slider(0, 360, 0);
//        slider.setShowTickLabels(true);
//        slider.setShowTickMarks(true);
//        slider.setMajorTickUnit(90);
//        slider.setOrientation(Orientation.HORIZONTAL);

        rotate = new Rotate();
        this.getTransforms().addAll(rotate);


        ColorPicker colorPicker = new ColorPicker(Color.BLACK);

        MenuItem colorSelector = new MenuItem(null, colorPicker);
        //MenuItem menuSlider = new MenuItem(null, slider);

        contextMenu.getItems().addAll(colorSelector);

        setStrokeWidth(2);

        /** Ustawianie koloru figury wybranego z palety colorpickera */
        colorSelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFill(colorPicker.getValue());
            }
        });

        /** Obsługa obrotu figury podczas użycia slidera */
//        slider.valueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue <?extends Number>observable, Number oldValue, Number newValue){
//                /** Ustawienie wartości obrotu figury */
//                rotate.setAngle((double) newValue);
//            }
//        });
    }

    /**
     * Funkcja sprawdza czy kursor najechal na figure
     * @param x - pozycja x kursora
     * @param y - pozycja y kursora
     * @return - zwraca prawdę lub fałsz zależnie czy x i y zawierają się w figurze
     */
    public boolean isHit(double x, double y) {
        this.setStroke(Color.RED);
        return getBoundsInLocal().contains(x,y);
    }

    /**
     * Zmiana pozycji x prostokąta
     * @param x - wartość o którą przesuwamy prostokąt w osi X
     */
    public void addX(double x) {
        setX(getX()+x);
    }

    /**
     * Zmiana pozycji y prostokąta
     * @param y - wartość o którą przesuwamy prostokąt w osi Y
     */
    public void addY(double y) {
        setY(getY()+y);
    }

//    class ProstokatRotateHandler implements EventHandler<KeyEvent>{
//        Prostokat rectangle;
//
//        @Override
//        public void handle(KeyEvent keyEvent) {
//            rotate.setPivotX(x0);
//            rotate.setPivotY(y0);
//            if(keyEvent.getCode().equals(UP)) {
//                rotate.setAngle(-5);
//            }
//            if(keyEvent.getCode().equals(DOWN)) {
//                rotate.setAngle(5);
//            }
//        }
//    }

    /**
     * Implementacja przesuwania figury
     */
    class ProstokatEventHandler implements EventHandler<MouseEvent>{

        Prostokat rectangle;
        private double x;
        private double y;

        private void doMove(MouseEvent event) {

            double dx = event.getX() - x;
            double dy = event.getY() - y;

            if (isHit(x, y)) {
                rectangle.setStroke(Color.RED);
                rectangle.addX(dx);
                rectangle.addY(dy);
                
                x0 = rectangle.getX() + rectangle.getWidth()/2;
                y0 = rectangle.getY() + rectangle.getHeight()/2;
            }
            x0 += dx;
            y0 += dy;
            x += dx;
            y += dy;
        }

        @Override
        public void handle(MouseEvent event) {

            rectangle = (Prostokat) event.getSource();

            rectangle.setStroke(rectangle.getFill());

            if (event.getEventType()==MouseEvent.MOUSE_CLICKED){
                x = event.getX();
                y = event.getY();
                root.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        rotate.setPivotX(x0);
                        rotate.setPivotY(y0);

                        switch (keyEvent.getText()){
                            case "l":
                                rectangle.rotate.setAngle(rotate.getAngle()+5);
                                break;
                            case "p":
                                rectangle.rotate.setAngle(rotate.getAngle()-5);
                                break;
                        }
                    }
                });
            }
            if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
                contextMenu.hide();
                doMove(event);
            }
            if(event.getButton() == MouseButton.SECONDARY){
                //rotate.setPivotX(x0);
                //rotate.setPivotY(y0);
                contextMenu.show(rectangle,event.getX(),event.getY());
                isHit(x,y);

            }
        }
    }

    /**
     * Implementacja scrollowania które zmienia rozmiary prostokąta
     */
    class ProstokatScrollHandler implements EventHandler<ScrollEvent>{

        Prostokat rectangle;

        private void doScale(ScrollEvent e) {

            double x = e.getX();
            double y = e.getY();

            if (rectangle.isHit(x, y)) {
                rectangle.setWidth (rectangle.getWidth() + rectangle.getWidth()*e.getDeltaY()*0.01);
                rectangle.setHeight(rectangle.getHeight() + rectangle.getHeight()*e.getDeltaY()*0.01);
            }
        }

        @Override
        public void handle(ScrollEvent event) {
            rectangle = (Prostokat) event.getSource();
            if (event.getEventType()==ScrollEvent.SCROLL){
                doScale(event);
            }
            rectangle.setStroke(rectangle.getFill());
        }
    }
}