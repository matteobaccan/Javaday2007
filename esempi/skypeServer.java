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

public class skypeServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Server partito");

        //Skype.setDebug(true);
        Skype.setDeamon(false);

        // Facciamo partire l'applicazione javaday
        Application application = Skype.addApplication( "javaday" );

        // Aggiungiamo un listener
        application.addApplicationListener(new ApplicationAdapter() {
            @Override

            // Alla connessione
            public void connected(Stream stream) throws SkypeException {

                // Visualizziamo l'ID di connessione
                System.out.println("connected:" + stream.getId());

                // Aggiungiamo un listener che visualizza i daati ricevuti
                stream.addStreamListener(new StreamAdapter() {
                    @Override
                    public void textReceived(String receivedText) throws SkypeException {
                        System.out.println("Byte  ricevuti:" + Integer.toHexString(receivedText.length()));
                        System.out.println("Testo ricevuto:" + receivedText );
                    }
                });

                // Rispondo con un ciao
                Friend f = stream.getFriend();
                if( f.getSex().equals( Friend.Sex.FEMALE ) ) stream.write("Ciao topolona!!");
                else                                         stream.write("Buongiorno " +f.getFullName());
            }
        });
    }
}
