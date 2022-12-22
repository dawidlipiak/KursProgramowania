import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * Klasa Koła
 */
public class Okrag extends Circle {

    ContextMenu contextMenu = new ContextMenu();

    /**
     * Konstuktor koła
     * @param x - pozycja x środka koła
     * @param y - pozycja y środka koła
     * @param r - promień koła
     */
    public Okrag (double x, double y, double r){
        super(x, y, r);

        setOnMouseClicked(new OkragEventHandler());
        setOnMouseDragged(new OkragEventHandler());
        setOnScroll(new OkragScrollHandler());

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        MenuItem colorSelector = new MenuItem(null, colorPicker);
        contextMenu.getItems().addAll(colorSelector);

        setStrokeWidth(2);

        /** Ustawianie koloru figury wybranego z palety colorpickera */
        colorSelector.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setFill(colorPicker.getValue());
            }
        });
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
     * Zmiana wspolrzednej x koła
     * @param x - wartość o którą przesuwamy pozycję x środka koła
     */
    public void addX(double x) {
        setCenterX(getCenterX()+x);
    }

    /**
     * Zmiana wspolrzednej y koła
     * @param y - wartość o którą przesuwamy pozycję y środka koła
     */
    public void addY(double y) {
        setCenterY(getCenterY()+y);
    }

    /**
     * Zmiana promienia koła
     * @param r - wartość o którą zmieniamy promień okręgu
     */
    public void addRadius(double r) {
        setRadius(getRadius()+r);
    }


    /**
     * Implementacja przesuwania figury
     */
    class OkragEventHandler implements EventHandler<MouseEvent>{
        Okrag okrag;

        private double x;
        private double y;

        private void doMove(MouseEvent event){
            double dx = event.getX() - x;
            double dy = event.getY() - y;

            if(okrag.isHit(x,y)){
                okrag.addX(dx);
                okrag.addY(dy);
            }
            x += dx;
            y += dy;
        }

        @Override
        public void handle(MouseEvent event) {

            okrag = (Okrag) event.getSource();
            okrag.setStroke(okrag.getFill());

            if (event.getEventType()==MouseEvent.MOUSE_CLICKED){
                x = event.getX();
                y = event.getY();
            }
            if (event.getEventType()==MouseEvent.MOUSE_DRAGGED){
                contextMenu.hide();
                doMove(event);
            }
            if(event.getButton() == MouseButton.SECONDARY){
                contextMenu.show(okrag,event.getSceneX(),event.getSceneY());
                isHit(x,y);
            }

        }
    }

    /**
     * Implementacja scrollowania które zmienia rozmiary koła
     */
    class OkragScrollHandler implements EventHandler<ScrollEvent>{

        Okrag okrag;

        private void doScale(ScrollEvent e) {

            double x = e.getX();
            double y = e.getY();

            if (okrag.isHit(x, y)) {
                okrag.addRadius(e.getDeltaY()*0.2);

            }
        }

        @Override
        public void handle(ScrollEvent event) {

            okrag = (Okrag) event.getSource();
            if (event.getEventType()==ScrollEvent.SCROLL){
                doScale(event);
            }
            okrag.setStroke(okrag.getFill());
        }
    }

}
