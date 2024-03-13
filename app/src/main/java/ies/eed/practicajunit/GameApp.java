package ies.eed.practicajunit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class GameApp extends Application {

    public static final int SCALE = 3;
    public static final int CELL_SIZE = 32;
    
    private Canvas primerplano;
    private Canvas segundoplano;
    private Game game;
    private Scene scene;
    private int width = 384 ;
    private int height = 208;
   
    

    @Override
    public void start(Stage stage) {
      
        primerplano = new Canvas(this.width * GameApp.SCALE, (this.height) * GameApp.SCALE); 
        segundoplano = new Canvas(this.width * GameApp.SCALE, (this.height) * GameApp.SCALE); 
      
        Pane root = new Pane(segundoplano,primerplano);

        scene = new Scene(root, this.width * GameApp.SCALE, (this.height) *GameApp.SCALE);
        this.game=new Game(primerplano.getGraphicsContext2D(), segundoplano.getGraphicsContext2D(),new Dimension2D(width,height));
        this.registerKeys();
        stage.setResizable(false);
        stage.setOnCloseRequest(t -> {
            Platform.exit();
           
            System.exit(0);
        });
        stage.setScene(scene);
        stage.show();
        this.game.start();
    }

    private void registerKeys() {
        scene.setOnKeyReleased(e -> {
            this.game.onKeyReleased(
                    e.getCode());
        });
        scene.setOnKeyPressed(e -> {
           this.game.onKeyPressed(e.getCode());

        });
    }
}
