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

public class skypeChat {
    public static void main(String[] args) throws Exception {
        System.out.println("Client partito");

        //Skype.setDebug(true);
        Skype.setDeamon(false);

        // nome del server
        String name = "javadaynovara";

        System.out.println("Chiamo " +name);

        // Provo a chiamare un altro client
        Call c = Skype.call( name );

        // Pausa di 3 secondi
        Thread.sleep(3*1000);

        System.out.println("Chiudo la chiamata con " +name);

        // Chiudo la chiamata
        c.cancel();


        // Provo a invocare una chat
        Chat ch = Skype.chat( name );

        // Mando un testo
        ch.send("Ciao ragazzo");

        // Pausa di 3 secondi
        Thread.sleep(5*1000);

        // Esco dalla chat
        ch.leave();

    }

}
