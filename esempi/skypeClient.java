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

public class skypeClient {
    public static void main(String[] args) throws Exception {
        System.out.println("Client partito");

        //Skype.setDebug(true);
        Skype.setDeamon(false);

        // nome del server
        String name = "javaday";

        // Provo a connettermi al server
        Stream[] streams = connectToServer(name);

        // Se ho un server in ascolto
        if( streams.length>0 ){

           // Aggiungo un listener
           streams[0].addStreamListener(new StreamAdapter() {
               @Override
               public void textReceived(String receivedText) throws SkypeException {
                   System.out.println("Byte  ricevuti:" + Integer.toHexString(receivedText.length()));
                   System.out.println("Testo ricevuto:" + receivedText );
               }
           });

           // Rispondo con un ciao
           streams[0].write("Ciao io sono matteo :)");
         //streams[0].disconnect();
        } else {
           System.out.println("Nessuno stream disponibile");
           System.exit(0);
        }

    }

    /*
    private static String createData(int length, char character) {
        byte[] data = new byte[length];
        Arrays.fill(data, (byte)character);
        return new String(data);
    }
    */

    private static Stream[] connectToServer(String name) throws SkypeException {
        Application application = Skype.addApplication(name);
        application.addApplicationListener(new ApplicationAdapter() {
            @Override
            public void connected(Stream stream) throws SkypeException {
                printApplicationAndStreamName("connected:", stream);
            }

            @Override
            public void disconnected(Stream stream) throws SkypeException {
                printApplicationAndStreamName("disconnected:", stream);
            }

            private void printApplicationAndStreamName(String header, Stream stream) {
                System.out.println(header + stream.getApplication().getName() + "-" + stream.getId());
            }
        });

        // Mi connetto all'utente javadaynovara
        //return application.connect( "javadaynovara" );
        return application.connect( "framik76" );
    }
}
