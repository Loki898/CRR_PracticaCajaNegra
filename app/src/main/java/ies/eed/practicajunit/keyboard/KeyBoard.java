 package ies.eed.practicajunit.keyboard;

import java.util.HashMap;

import ies.eed.practicajunit.interfaces.IKeyListener;
import javafx.scene.input.KeyCode;

public class KeyBoard implements IKeyListener {
    private HashMap<KeyCode, Keyboardstate> state;
    public KeyBoard(){
        Keyboardstate keystate;

        KeyCode[] keyscodes = KeyCode.values();
        this.state= new HashMap<>();
        for(KeyCode k: keyscodes){
            keystate= new Keyboardstate(k,false,false);
            this.state.put(k, keystate);
        }
    }
    public Keyboardstate getKeyState(KeyCode code){
        return this.state.get(code);
    }
 
    public void resetOnChange(){
       for(Keyboardstate kbs:this.state.values()){
        kbs.setAschanged(false);
       } 
    }
    @Override
    public void onKeyPressed(KeyCode code) {
        this.state.get(code).setState(true);
       
        
    }
    @Override
    public void onKeyReleased(KeyCode code) {
        this.state.get(code).setState(false);
       
     }
    public String toString(){
        StringBuilder sb= new StringBuilder();
        for(Keyboardstate kbs:this.state.values()){
            if((kbs.getCode()==KeyCode.DOWN ||
                kbs.getCode()==KeyCode.UP ||
                kbs.getCode()==KeyCode.LEFT || 
                kbs.getCode()==KeyCode.RIGHT ||
                kbs.getCode()==KeyCode.SPACE) )
                sb.append(kbs+"\n");
        }
        return sb.toString();
    }
}
