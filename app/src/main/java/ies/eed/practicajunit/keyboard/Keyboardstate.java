package ies.eed.practicajunit.keyboard;

import javafx.scene.input.KeyCode;

public class Keyboardstate {
    private KeyCode code;
    private boolean state;
    private boolean aschanged;
    
    public Keyboardstate() {
        this.code=null;
        this.state=false;
        this.aschanged=false;
    }
    public Keyboardstate(KeyCode code, boolean state, boolean aschanged) {
        this.code = code;
        this.state = state;
        this.aschanged = aschanged;
    }
    public KeyCode getCode() {
        return code;
    }
    public void setCode(KeyCode code) {
        this.code = code;
    }
    public boolean isState() {
        return state;
    }
    public boolean isPressed(){
        return this.state;
    }
    public boolean isUnPressed(){
        return !this.state;
    }
    public void setState(boolean state) {
        //registra que se ha producido un cambio
        if(this.state!=state)
            this.aschanged=true;
        else
            this.aschanged=false;
        this.state = state;
    }
    public boolean isAschanged() {
        return aschanged;
    }
    public void setAschanged(boolean aschanged) {
        this.aschanged = aschanged;
    }
    
    public String toString(){
        return "KEY:"+this.code.toString()+" Ispressed:"+this.isPressed()+" asChanged:"+this.aschanged;
    }
}
