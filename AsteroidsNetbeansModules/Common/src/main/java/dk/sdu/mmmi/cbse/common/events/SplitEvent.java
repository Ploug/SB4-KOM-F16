/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.events;

/**
 *
 * @author Mads
 */
public class SplitEvent extends Event {
    private String id;
    
    public SplitEvent(EventType type, String id) {
        super(type);
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
}
