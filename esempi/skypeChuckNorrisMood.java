/*******************************************************************************
 * Copyright (c) 2007 Matteo Baccan <matteo@baccan.it>
 *
 * This program and the accompanying materials are made available under the
 * terms of the Common Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors: Matteo Baccan - initial implementation
 ******************************************************************************/
import com.skype.Skype;

public class skypeChuckNorrisMood {
    public static void main(String[] args) throws Exception {
       // Stampo la versione
       System.out.println( "\nSkype installato: " +Skype.getVersion() +"\n" );

       // Imposto il mood di default
       Skype.getProfile().setMoodMessage("Chuck Norris non partecipa al javaday .. e' il javaday che partecipa al Walker Texas Ranger");
       Skype.getProfile().setMoodMessage("Chuck Norris con un calcio rotante pu• castare una Stringa a qualsiasi cosa .. e lo dimostrer… al javaday");
       Skype.getProfile().setMoodMessage("I modificatori di accesso in java sono public private protected e protectedByChuck ... non provare mai ad accedere a qualcosa con quest'ultimo modificatore");

       // By the way, my name is Sandor Szabo. I'm Hungarian. Did you ever read the Latin poet Ovid on The Art of Love?

       ChuckNorris gr = new ChuckNorris();
       while( true ) {
           // Pausa di 60 secondi
           Thread.sleep(60 *1000);

           // Reimposta il mood
           String cNorris = gr.getNorris();
           System.out.println( cNorris );
           Skype.getProfile().setMoodMessage( cNorris );
       }
    }
}
