package ies.eed.practicajunit.interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface IDebuggable {

 
    /**
     * @param value
     */
    public void setDebug(boolean value);

    /**
     * @return boolean
     */
    public boolean isDebug();

    /**
     * @param gc
     */
    public void debug(GraphicsContext gc);

}