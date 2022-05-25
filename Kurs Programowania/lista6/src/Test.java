import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test extends Application {
    public static int n,m;
    public static double k,p;

    Random random = new Random();

    public static void main (String [] args){
        try{
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
            k = Double.parseDouble(args[2]);
            p = Double.parseDouble(args[3]);


        }
        catch (NumberFormatException ex){
            System.out.println("Złe dane wejsciowe");
            System.exit(0);
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        int sceneMinX = 800;
        int sceneMinY = 600;
        if(n > sceneMinX)
            sceneMinX = n;
        if(m > sceneMinY)
            sceneMinX = m;

        int rgbBound = 255;

        GridPane gridPane = new GridPane();
        Cell[][] pixelField = new Cell [n][m];

        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j++){
                int r = random.nextInt(rgbBound);
                int g = random.nextInt(rgbBound);
                int b = random.nextInt(rgbBound);

                Cell cell = new Cell(r, g, b, k, random, p, n, m, gridPane);
                cell.widthProperty().bind(gridPane.widthProperty().divide(n));
                cell.heightProperty().bind(gridPane.heightProperty().divide(m));

                pixelField[i][j] = cell;
                gridPane.add(cell,i,j);
            }
        }

        for(int i = 0; i < n; i++ ) {
            for (int j = 0; j < m; j++) {
                int upNeighbour = j - 1;
                int downNeighbour = (j + 1) % m;
                int leftNeighour = i - 1;
                int rightNeighour = (i + 1) % n;

                if (i == 0) {
                    leftNeighour = n - 1;
                }
                if (j == 0) {
                    upNeighbour = m - 1;
                }

                pixelField[i][j].setListOfNeighbours(pixelField[i][upNeighbour], pixelField[i][downNeighbour], pixelField[leftNeighour][j], pixelField[rightNeighour][j]);

                Thread thread = new Thread(pixelField[i][j]);
                thread.setDaemon(true);
                thread.start();
            }
        }

        Scene scene = new Scene(gridPane, sceneMinX, sceneMinY);
        stage.setTitle("Pixels");
        stage.setScene(scene);
        stage.show();
    }
}
