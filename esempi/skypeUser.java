/*******************************************************************************
 * Copyright (c) 2007 Matteo Baccan <matteo@baccan.it>
 *
 * This program and the accompanying materials are made available under the
 * terms of the Common Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors: Matteo Baccan - initial implementation
 ******************************************************************************/
import com.skype.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class skypeUser {
    public static void main(String[] args) throws Exception {
       // Stampo la versione
       System.out.println( "\nSkype installato: " +Skype.getVersion() +"\n" );

       // Prendo la contactList
       ContactList cl = Skype.getContactList();

       // Prendo gli amici della contact list
       Friend[] af = cl.getAllFriends();

       // Stampo tutti quelli online
       for (int i = 0; i < af.length; i++) {
           String cStatus   = ""+af[i].getOnlineStatus();
           String cName     = ""+af[i].getFullName();
           String cID       = ""+af[i].getId();
           String cSex      = ""+af[i].getSex();
           String cMood     = af[i].getMoodMessage();

           String cBuddy                = "";
           BufferedImage oBufferedImage = null;

           // Non nella versione 1.0, ma nella versione sotto sviluppo
           cBuddy         = ""+af[i].getBuddyStatus();

           if( !cStatus.equals( "OFFLINE" ) ){
               System.out.println( cStatus +":" +cBuddy +" - " +cID +":" +cName +" - " +cSex  +" - " +cMood );

               // Salvo l'avatar solo se non l'ho gia' salvato :)
               File outputFile = new File( cID +".png");
               if( !outputFile.exists() ) {
                  oBufferedImage = af[i].getAvatar();
                  if( oBufferedImage!=null ) {
                     try {
                        ImageIO.write(oBufferedImage, "PNG", outputFile);
                     } catch (Throwable e) {}
                  }
               }
           }

           // Altre API Interessanti:
           // addPropertyChangeListener()
           // public void propertyChange(PropertyChangeEvent evt)
           //   getPropertyName()
           //   getNewValue()
           //   getOldValue()
       }

       return;
    }
}
