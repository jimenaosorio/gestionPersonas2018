/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author alumnossur
 */
@MessageDriven(mappedName = "jms/Queue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MensajeBean implements MessageListener {
    
    public MensajeBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try{
            MapMessage msg = (MapMessage) message;
            System.out.println("----------------------");
            System.out.println(msg.getString("mensaje"));
            
            for(int i=0;i<40;i++){
                System.out.println("i="+i);
                Thread.sleep(500);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
