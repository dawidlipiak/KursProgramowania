import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.input.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Dialog;
import java.lang.Math;

/**
 * Głowna klasa programu
 * @author Dawid Lipiak
 */
public class Paint extends Application{
    /**
     * Metoda Tworząca okno aplikacji z całą jego obsługą
     * @param stage - głowne okno programu
     */
    @Override
    public void start(Stage stage){
        MenuBar myMenu = new MenuBar();
        Menu menu1 = new Menu("File");

        MenuItem info = new MenuItem("Info");
        MenuItem exit = new MenuItem("Exit");
        MenuItem instrukcja = new MenuItem("Instrukcja");

        VBox vbox = new VBox();
        Button button1 = new Button("Prostokat");
        Button button2 = new Button("Okrag");
        Button button3 = new Button("Trojkat");
        Button button4 = new Button("Edit Mode");

        menu1.getItems().addAll(info,instrukcja,exit);
        myMenu.getMenus().addAll(menu1);

        vbox.getChildren().addAll(button1,button2,button3,button4);

        /** Utworzenie okienka dialogowego z informacjami do programu */
        Dialog<String> dialogInfo = new Dialog<String>();
        dialogInfo.setTitle("Info");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialogInfo.setContentText("Edytor graficzny do tworzenia i edycji figur\nDawid Lipiak 268485 ");
        dialogInfo.getDialogPane().getButtonTypes().add(type);


        /** Utworzenie okienka dialogowego z instrukcja do programu */
        Dialog<String> dialogInfo2 = new Dialog<String>();
        dialogInfo2.setTitle("Instrukcja");
        ButtonType type2 = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialogInfo2.setContentText("Instrukcja obsługi programu\n\n" +
                "1. W edytotorze wybieramy jedna z 3 dostepnych figur, po kliknieciu przycisku wchodzimy w tryb tworzenia figury\n\n" +
                "2. Aby edytować figurę klikamy przycisk Edit Mode\n\n" +
                "3. Aby przesunąć figurę przeciągamy ją po ekranie lewym przyciskiem myszy\n\n" +
                "4. Aby wybrać jej kolor klikamy prawym przyciskiem myszy na figurę i wybieramy kolor\n\n" +
                "5. Aby obrócić figurę klikamy na nią prawym przyciskiem i obracamy sliderem\n\n");
        dialogInfo2.getDialogPane().getButtonTypes().add(type2);


        BorderPane root = new BorderPane();
        root.setLeft(vbox);
        root.setTop(myMenu);

        /**
         * Obsługa Menubara programu
         */
        EventHandler<ActionEvent> evnHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ActionEvent) {
                MenuItem m = (MenuItem) ActionEvent.getSource();

                if (m.getText().equals("Exit")) System.exit(0);
                if (m.getText().equals("Info")) {
                    dialogInfo.showAndWait();
                }
                if(m.getText().equals("Instrukcja")){
                    dialogInfo2.showAndWait();
                }
            }
        };
        info.setOnAction(evnHandler);
        exit.setOnAction(evnHandler);
        instrukcja.setOnAction(evnHandler);


        /**
         * Obsługa Vboxa z przyciskami do korzystania z programu
         */
        EventHandler<ActionEvent> buttonEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Button b = (Button) event.getSource();

                // Wybranie jednego z 4 przycisków i zapisanie jego stanu
                if(b.getText().equals("Prostokat")){
                    ShapeSelector.PROSTOKAT.setTrue();
                    ShapeSelector.TROJKAT.setFalse();
                    ShapeSelector.KOLO.setFalse();
                    ShapeSelector.EDIT.setFalse();
                }
                if(b.getText().equals("Okrag")){
                    ShapeSelector.PROSTOKAT.setFalse();
                    ShapeSelector.TROJKAT.setFalse();
                    ShapeSelector.KOLO.setTrue();
                    ShapeSelector.EDIT.setFalse();
                }
                if(b.getText().equals("Trojkat")){
                    ShapeSelector.PROSTOKAT.setFalse();
                    ShapeSelector.TROJKAT.setTrue();
                    ShapeSelector.KOLO.setFalse();
                    ShapeSelector.EDIT.setFalse();
                }
                if(b.getText().equals("Edit Mode")){
                    ShapeSelector.PROSTOKAT.setFalse();
                    ShapeSelector.TROJKAT.setFalse();
                    ShapeSelector.KOLO.setFalse();
                    ShapeSelector.EDIT.setTrue();
                }

            }
        };
        button1.setOnAction(buttonEventHandler);
        button2.setOnAction(buttonEventHandler);
        button3.setOnAction(buttonEventHandler);
        button4.setOnAction(buttonEventHandler);


        PointPosition point = new PointPosition();

        /**
         * Obsługa tworzenia figur i ich edycji
         */
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    double x = event.getSceneX();
                    double y = event.getSceneY();

                    /**
                     * Zablokowanie dodawania punktów w trybie do edycji
                     */
                    if(!ShapeSelector.EDIT.shapeState())
                        point.addPoint(x,y);

                    System.out.println(x + " " + y);

                    /**
                     * Utworzenie prostokata
                     */
                    if(ShapeSelector.PROSTOKAT.shapeState()){
                        if (point.pointNumber > 1) {

                            Point2D p1 = point.pointPositions.get(0);
                            Point2D p2 = point.pointPositions.get(1);

                            Prostokat rect = new Prostokat(p1,p2,root);

                            root.getChildren().addAll(rect);

                            point.pointNumber = 0;
                            point.pointPositions.clear();
                            return;
                        }

                    }

                    /**
                     * Utworzenie koła
                     */
                    if(ShapeSelector.KOLO.shapeState()){
                        if(point.pointNumber > 1){
                            Point2D p1 = point.pointPositions.get(0);
                            Point2D p2 = point.pointPositions.get(1);

                            Okrag okrag = new Okrag(p1.getX(),p1.getY(), p2.distance(p1));

                            root.getChildren().addAll(okrag);

                            point.pointNumber = 0;
                            point.pointPositions.clear();
                            return;
                        }
                    }

                    /**
                     * Utworzenie trójkąta
                     */
                    if(ShapeSelector.TROJKAT.shapeState()){
                        if (point.pointNumber > 2) {
                            Point2D p1 = point.pointPositions.get(0);
                            Point2D p2 = point.pointPositions.get(1);
                            Point2D p3 = point.pointPositions.get(2);


                            Trojkat triangle = new Trojkat(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY());

                            root.getChildren().addAll(triangle);

                            point.pointNumber = 0;
                            point.pointPositions.clear();
                            return;
                        }
                    }
                }
            }
        });

        Scene scene = new Scene(root, 1080, 720);
        stage.setTitle("Paint");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Klasa enum w której określamy stan 4 obiektów odpowiadających przyciskom używanych w programie
     */
    public enum ShapeSelector implements ShapeInterface{
        PROSTOKAT{
            boolean state;
            public void setTrue(){ state = true;}
            public void setFalse(){ state = false; }
            public boolean shapeState(){ return state; }
        },KOLO{
            boolean state;
            public void setTrue(){ state = true; }
            public void setFalse(){ state = false; }
            public boolean shapeState(){ return state; }
        },TROJKAT{
            boolean state;
            public void setTrue(){ state = true; }
            public void setFalse(){ state = false; }
            public boolean shapeState(){ return state; }
        },EDIT{
            boolean state;
            public void setTrue(){ state = true; }
            public void setFalse(){ state = false; }
            public boolean shapeState(){ return state; }
        }

    }

    /**
     * Interfejs do określania stanu wybranego przycisku
     */
    public interface ShapeInterface{
        /**
         * Ustawia stan na wartość true
         */
        public void setTrue();

        /**
         * Ustawia stan na wartość false
         */
        public void setFalse();

        /**
         * Zwraca wartosć stanu
         * @return Zwraca wartosć stanu
         */
        public boolean shapeState();
    }

    /**
     * Funckja main
     * @param args parametry wejsciowe podawane przez użytkownika
     */
    public static void main (String [] args){
        Application.launch(args);
    }
}
