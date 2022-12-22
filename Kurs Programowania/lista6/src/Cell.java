import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell extends Rectangle implements Runnable{
    double speedVariable;
    Random random;
    GridPane gridPane;
    double p;
    List <Cell> listOfNeighbours = new ArrayList<>();

    boolean activityState;

    public void setListOfNeighbours (Cell up, Cell down, Cell left, Cell right){
        if(up.activityState == true)
            listOfNeighbours.add(up);
        if(down.activityState == true)
            listOfNeighbours.add(down);
        if(left.activityState == true)
            listOfNeighbours.add(left);
        if(right.activityState == true)
            listOfNeighbours.add(right);
    }
    public Cell(int r, int g, int b, double k, Random random, double p, int m, int n, GridPane gridPane){
        speedVariable = k;
        this.random = random;
        this.p = p;
        this.gridPane = gridPane;
        activityState = true;

        setFill(Color.rgb(r,g,b));

        setOnMouseClicked(new ThreadEventHandler());
    }

    public void run (){

        while (true){
            if(this.activityState == true) {
                colorChange();
            }
            try{
                Thread.sleep((int) (random.nextDouble(1.5*speedVariable - 0.5*speedVariable)+0.5*speedVariable));

            }
            catch (InterruptedException e){}
        }

    }

    synchronized void colorChange(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        if(random.nextDouble(1) < p )
            Platform.runLater(() -> { setFill(Color.rgb(r,g,b)); });

        else {
            int rTemp = 0;
            int gTemp = 0;
            int bTemp = 0;

            int amountOfNeighbours = 0;
            int index = -1;
            for(int i = 0; i< listOfNeighbours.size(); i++){
                if(listOfNeighbours.get(i).activityState){
                    amountOfNeighbours++;
                }
                else
                    index = i;
            }

            for(int i = 0; i < listOfNeighbours.size(); i++){
                if(i != index) {
                    Color color = (Color) listOfNeighbours.get(i).getFill();

                    rTemp += (int) (color.getRed() * 255);
                    gTemp += (int) (color.getGreen() * 255);
                    bTemp += (int) (color.getBlue() * 255);
                }
            }
            int rAverage = rTemp/amountOfNeighbours;
            int gAverage = gTemp/amountOfNeighbours;
            int bAverage = bTemp/amountOfNeighbours;

            Platform.runLater(() -> { setFill(Color.rgb(rAverage,gAverage,bAverage)); });
        }
    }

    class ThreadEventHandler implements EventHandler<MouseEvent>{

        Cell cell;

        private void threadActivityChange(){
            if(cell.activityState == true){
                cell.activityState = false;
            }
            else{
                cell.activityState = true;
            }

        }

        @Override
        public void handle(MouseEvent event) {
            cell = (Cell) event.getSource();
                threadActivityChange();
        }
    }



}
