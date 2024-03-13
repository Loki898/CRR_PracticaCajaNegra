package ies.eed.practicajunit.utils;


import java.net.URISyntaxException;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class Resources {

  private HashMap<String, Image> imagenes;
  private HashMap<String, MediaPlayer> sonidos;
  private HashMap<String, Font> fuentes;
  private String path_imagenes[][] = {
    { "backgrounds", "Backgrounds.png" },
    { "ballons", "Ballons.png" }

  };
  private String path_sonidos[][] = {
    { "jump", "Jump.wav" }
        
  };
  private String path_fuentes[][] = { { "wonder", "8BIT WONDER Nominal" } };

  private static Resources resource;

  {
    resource = null;
  }

  private Resources() {
    load();
  }

  private void load() {
    this.imagenes = new HashMap<>();
    this.sonidos = new HashMap<>();
    this.fuentes= new HashMap<>();
    ClassLoader classLoader = getClass().getClassLoader();
    //cargar las imagenes
    for (int i = 0; i < this.path_imagenes.length; i++) {
      this.imagenes.put(
          this.path_imagenes[i][0],
          new Image(
            classLoader.getResource(this.path_imagenes[i][1]).toString()
          )
        );
    }
    for (int i = 0; i < this.path_sonidos.length; i++) {
      this.sonidos.put(
          this.path_sonidos[i][0],
          new MediaPlayer(
            new Media(
              classLoader.getResource(this.path_sonidos[i][1]).toString()
            )
          )
        );
    }
    for (int i = 0; i < this.path_fuentes.length; i++) {
      try {
        String path =
          this.getClass().getResource("/WONDER.TTF").toURI().toString();
       
        Font f = Font.loadFont(path, 10);
        Font font = new Font("8BIT WONDER Nominal",15);
        System.out.println(f);
        this.fuentes.put(
            "WONDER",
            font
          );
      } catch (URISyntaxException ex) {
        System.err.println(ex);
      }
    }
  }

  public static Resources getInstance() {
    if (Resources.resource == null) {
      Resources.resource = new Resources();
      Resources.resource.load();
    }
    return Resources.resource;
  }

  public Image getImage(String name) {
    Image i = this.imagenes.get(name);
    return i;
  }

  public MediaPlayer getSound(String name) {
    return this.sonidos.get(name);
  }

  public Font getFont(String name) {
    return this.fuentes.get(name);
  }
}
