package cus1156.proj3;

import java.io.IOException;

/**
 * @author Aidan Battad
   This program simulates an email messaging system.
*/
public class PublishSubscribeSimulation
{  
   public static void main(String[] args) throws IOException
   { 
      MessagingSystem system = new MessagingSystem();
      MenuInterface menu = new MenuInterface();
      menu.run(system);

   }
}