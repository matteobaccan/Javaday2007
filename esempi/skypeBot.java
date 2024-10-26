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

public class skypeBot {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java com.skype.sample.skypeBot 'skype_id'");
            return;
        }

        skypeBot sb = new skypeBot();
        sb.run( args[0] );
    }

    public void run( String cID ) throws Exception {
        configChange cc = new configChange( "bot.prop" );
        cc.start();

        // Ultimo ID elaborato
        double nID = -1;

        // Monitoraggio righe inserite
        while( true ){
           // Mi collego alla chat dello skype_id segnalato
           Chat c = Skype.chat( cID );

           // Prendo le ultime righe della chat
           ChatMessage cm[] = c.getRecentChatMessages();

           // Gestione ultimo content
           String cLastContent = "";
           double nLastID      = 0;

           // Stampo tutte le righe
           for (int i = 0; i < cm.length; i++) {
               // Se ho ricevuto un messaggio ..
               if( cm[i].getType()   == ChatMessage.Type.SAID   &&
                   cm[i].getStatus() == ChatMessage.Status.READ ){

                   // ID corrente
                   nLastID = Double.parseDouble( cm[i].getId() );

                   // prendo l'ultimo messaggio
                   cLastContent = cm[i].getContent();
               }
           }
           try {
               Thread.sleep( 1000 );
           } catch (Exception e) {  }

           // se l'ultimo ID elaborato e' diverso da quello memorizzato .. elaboro
           if( nLastID!=nID ) {
              nID = nLastID;

              // Stampo
              System.out.println( cLastContent );

              // Proviamo a rispondere in automatico
              boolean bReply = false;

              // Vedo se devo dire qualcosa
              Enumeration keysEnum = p.keys();
              while(keysEnum.hasMoreElements()){
                  String cK = ""+keysEnum.nextElement();

                  if( (" "+cLastContent+" ").toUpperCase().indexOf( (" "+cK+" ").toUpperCase() )!=-1 ){
                     c.send( ""+p.get(cK) );
                     bReply = true;
                     break;
                  }
              }

              if( !bReply ){
                 // Millisecondi di sistema
                 long nMill = System.currentTimeMillis();

                 // Modulo 11
                 nMill = nMill%3;

                 String cRep = "";
                 switch( (int)nMill ){
                    case 0:
                       cRep = "Pensi di aver detto una cosa intelligente?";
                       break;
                    case 1:
                       cRep = "Quello che dici mi lascia molto perplesso";
                       break;
                    case 2:
                       cRep = "Mi ha detto mio cugggino che non sei per nulla interessante";
                       break;
                 }
                 c.send( cRep );
              }
           }

        }

    }

    // Properties
    private Properties p = new Properties();

    // Aggiorna le properties
    class configChange extends Thread {
       long timestamp;
       String cFile;

       public configChange( String cFile ){
          timestamp = 0;
          this.cFile = cFile;
       }

       public void run(){
          try {
             boolean bLoop = true;
             while( bLoop ){
                File f = new File( cFile );
                if( timestamp!=f.lastModified() ){
                    timestamp = f.lastModified();

                    // Reload
                    FileInputStream fis = new FileInputStream( cFile );
                    p.clear();
                    p.load(fis);
                    fis.close();
                }
                if( bLoop ) Thread.sleep(1000);
             }
          } catch (Throwable e) {}
       }
    }
}
