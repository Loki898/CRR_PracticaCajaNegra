package ies.eed.practicajunit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ies.eed.practicajunit.balls.Ball;
import ies.eed.practicajunit.balls.BallColor;
import ies.eed.practicajunit.balls.BallType;
import ies.eed.practicajunit.interfaces.IDebuggable;
import ies.eed.practicajunit.interfaces.IKeyListener;
import ies.eed.practicajunit.interfaces.IWarnClock;
import ies.eed.practicajunit.keyboard.KeyBoard;
import ies.eed.practicajunit.utils.Clock;
import ies.eed.practicajunit.utils.Resources;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game implements IWarnClock, IKeyListener, IDebuggable {

  public static final int SCALE = 3;

  public static Clock clock = new Clock(30);
  private Dimension2D original_size;
  private Rectangle2D board;

  private GraphicsContext ctx;
  private GraphicsContext ctxbackground;
  private KeyBoard kb;
  private boolean debug;
  private ArrayList<Ball> pelotas;

  public Game(GraphicsContext context, GraphicsContext background, Dimension2D original) {
    this.ctx = context;
    this.ctxbackground = background;
    this.kb = new KeyBoard();
    this.original_size = original;
    this.board = new Rectangle2D(8, 8, original_size.getWidth() - 16, original_size.getHeight() - 16);

    this.pelotas = new ArrayList<Ball>();
    Game.clock.addIWarClock(this);

  }

  public void start() {
    Game.clock.start();
    this.paintbackground();
  }

  public void stop() {
    Game.clock.stop();
  }

  @Override
  public synchronized void TicTac() {
    // procesar la entrada
    this.process_input();
    // actualizar
    this.update();
    // pintar
    this.render();
    this.kb.resetOnChange();

  }

  private void process_input() {

  }

  private void update() {
    for (Ball b : this.pelotas)
      b.move(this.board);
  }

  private void render() {
    this.clear(ctx);
    for (Ball b : this.pelotas)
      b.paint(ctx);

  }

  public boolean isDebug() {
    return debug;
  }

  public void setDebug(boolean debug) {

    this.debug = debug;
    
    for (Ball b : this.pelotas)
      b.setDebug(debug);
  }

  /**
   * limpiar la pantalla
   */
  private void clear(GraphicsContext gc) {
    gc.restore();
    gc.clearRect(
        0,
        0,
        this.original_size.getWidth() * Game.SCALE,
        this.original_size.getHeight() * Game.SCALE);
  }

  private void paintbackground() {
    this.clear(ctxbackground);
    if (this.ctxbackground != null)
      // las dos imágenes
      this.ctxbackground.drawImage(Resources.getInstance().getImage("backgrounds"),
          8, 8, this.original_size.getWidth(), this.original_size.getHeight(),

          0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
  }

  /**
   * gestión de pulsación
   * 
   * @param code
   */
  @Override
  public void onKeyPressed(KeyCode code) {
    this.kb.onKeyPressed(code);
    if (code == KeyCode.ADD) {
      Game.clock.incFrecuency();
    }
    if (code == KeyCode.SUBTRACT) {

      Game.clock.decFrencuecy();
    }
    if (code == KeyCode.D) {
      this.setDebug(!this.isDebug());
    }
    if (code == KeyCode.C) {
      this.createBall();
    }
  }

  private void createBall() {
    Ball b = new Ball(new Point2D(Math.random() * this.original_size.getWidth(), 20),
        BallType.values()[(int) (Math.random() * 4)], BallColor.values()[(int) (Math.random() * 3)]);
    b.setGravity(new Point2D(Math.random() / 100, Math.random() / 10));
    b.setVelocity(new Point2D(3 * Math.random(), -1));
    this.pelotas.add(b);
  }

  @Override
  public void onKeyReleased(KeyCode code) {
    this.kb.onKeyReleased(code);

  }

  @Override
  public void debug(GraphicsContext gc) {
  }
}
