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
import java.net.*;

public class skypeSearch {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java com.skype.sample.skypeSearch 'skype_id'");
            return;
        }

        skypeSearch sb = new skypeSearch();
        sb.run( args[0] );
    }

    public void run( String cID ) throws Exception {
        // Ultimo ID elaborato
        double nID = -1;
        boolean bFirst = true;

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
               if( cm[i]!=null                                  &&
                   cm[i].getType()   == ChatMessage.Type.SAID   &&
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
           if( nLastID!=nID && !bFirst ){
              nID = nLastID;

              // Stampo
              System.out.println( cLastContent );

              StringBuffer sb = getPage( "http://www.jobcrawler.it/jc/rss_lastinsert.jsp?paese=IT&lingua=IT&k=" +cLastContent.replace(' ','+') );

              int nItem = 0;
              int nPos = 0;
              while(true){
                 int nI  = sb.indexOf( "<item>" , nPos );
                 int nI2 = sb.indexOf( "</item>", nI   );
                 if( nI!=-1 && nI2!=-1 && nItem<3 ){
                    nItem++;
                    String cPos = sb.substring( nI, nI2 );
                    int nT  = cPos.indexOf( "<title>"  );
                    int nT2 = cPos.indexOf( "</title>" , nT );
                    int nL  = cPos.indexOf( "<link>"  );
                    int nL2 = cPos.indexOf( "</link>" , nL );
                    if( nT!=-1 && nT2!=-1 &&
                        nL!=-1 && nL2!=-1 ){
                       c.send( cPos.substring(nT+7,nT2).trim() +"\n" +cPos.substring(nL+6,nL2).trim().replaceAll("&amp;","&") );
                    }
                    nPos = nI2;
                 } else break;
              }
           }

           if( bFirst ) {
               c.send("Ciao, sono il tuo motore di ricerca, scrivimi quello che cerchi e io te lo trovero' :)");
               bFirst = false;
               nID = nLastID;
           }
        }

    }

    protected StringBuffer getPage( String cUrl ) throws Exception {
        URL urlObject = new URL( cUrl );

        URLConnection con = urlObject.openConnection();

        // Lettura risultato
        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()) );

        String inputLine;
        StringBuffer sb = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine).append( (char)13 ).append( (char)10 );
        }

        in.close();

        return sb;
    }

}
