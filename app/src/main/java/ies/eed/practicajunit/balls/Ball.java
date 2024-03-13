package ies.eed.practicajunit.balls;

import ies.eed.practicajunit.Game;
import ies.eed.practicajunit.interfaces.IDebuggable;
import ies.eed.practicajunit.interfaces.IDrawable;
import ies.eed.practicajunit.utils.Resources;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/**
 * Representa una pelota del juego, rebota contra las paredes
 * posee una velocidad, le afecta la gravedad y el rozamiento en el 
 * eje x
 */
public class Ball implements IDebuggable, IDrawable {
    private final static String imgname = "ballons";

    private boolean debug;

    private Point2D position;
    //velocidad en el eje x e y
    private Point2D velocity;
    //la gravedad tiene 2 componentes, al del eje x que 
    //sería rozamiento y la del eje y que es la propia gravedad
    private Point2D gravity;
    private Dimension2D size;
    //margenes para parar la pelota en cada uno de los ejes
    private double deltay = 0.53;
    private double deltax = 0.33;
    //factor de rebote en el eje y al tocar el suelo
    private double factorbounce = 0.7;
    //tipo, color e imagen de los recursos de la pelota
    private BallType type;
    private BallColor color;
    private Image img;
    /**
     * Constructor sobrecargado
     * @param position posicion en el tablero
     * @param type  
     * @param color
     */
    public Ball(Point2D position, BallType type, BallColor color) {
        this.position = position;
        this.size = new Dimension2D(type.width, type.heigth);
       
        this.color = color;
        this.type = type;

    }
    
    
    /**
     * Constructor por defecto
     */

    public Ball() {
      
    }
    /**
     * Mueve la pelota por el tablero, teniendo
     * en cuenta los bordes y actualizando la posición,
     * velocidad y gravedad
     * @param board
     */
    public void move(Rectangle2D board) {

        
    }
    /**
     * Para el moviento en el eje y
     */
    private void stopinY() {
     }
    /**
     * Para el movimiento en el ejex
     */
    private void stopinX() {
      }
    /**
     * Cambia la velocidad en el ejey
     */
    private void reverseVy() {
    }
    /*
     * Cambia la velocidad en el eje x
     */
    private void reverseVx() {
     }
    /**
     * Actualiza la velocidad del ejex y ejey en función de la gravedad(aceleración)
     */
    private void updateVelocity() {
      }
    /**
     * Pinta la pelota en la pantalla, 
     * en caso de estar activa la depuración
     * muestra los datos mas relevantes
     */
    @Override
    public void paint(GraphicsContext gc) {
        //deberia estar en el constructor pero da fallo en las pruebas
        if(this.img==null){
            this.img = Resources.getInstance().getImage(Ball.imgname);
        }
        gc.drawImage(img,
                this.type.getStartx(),
                this.color.getCenter() - this.type.heigth / 2,
                this.type.getWidth(),
                this.type.getHeigth(),
                this.position.getX() * Game.SCALE,
                this.position.getY() * Game.SCALE,
                this.size.getWidth() * Game.SCALE,
                this.size.getHeight() * Game.SCALE);

        if (this.isDebug())
            this.debug(gc);
    }
    /**
     * muestra en pantalla los datos más relevantes
     */
    @Override
    public void debug(GraphicsContext gc) {

     
        gc.strokeText("x:" + (int) this.getPosition().getX() + " y:"
                + (int) this.getPosition().getY()
                + " vx:" + (this.velocity.getX())
                + " vy:" + (this.velocity.getY()),
                (this.getPosition().getX() - 5) * Game.SCALE,
                (this.getPosition().getY() - 5) * Game.SCALE);

    }

    public Point2D getPosition() {
        return position;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getGravity() {
        return gravity;
    }

    public void setGravity(Point2D gravity) {
        this.gravity = gravity;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Dimension2D getSize() {
        return size;
    }

    public void setSize(Dimension2D size) {
        this.size = size;
    }

    @Override
    public void setDebug(boolean value) {
        this.debug = value;
    }

    @Override
    public boolean isDebug() {
        return this.debug;
    }

}
