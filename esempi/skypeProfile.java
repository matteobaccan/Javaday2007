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

import java.text.SimpleDateFormat;

public class skypeProfile {
    public static void main(String[] args) throws Exception {
        System.out.println("Client partito");

        //Skype.setDebug(true);
        Profile profile = Skype.getProfile();

        // Visualizzo
        System.out.println("ID:"+profile.getId());
        System.out.println("Status:"+profile.getStatus());
        System.out.println("SkyPEOut:"+profile.canDoSkypeOut());
        System.out.println("SkyPEIn:"+profile.canDoSkypeIn());
        System.out.println("VoiceMail:"+profile.canDoVoiceMail());
        System.out.println("Credit:"+profile.getCredit());
        System.out.println("CurrencyUnit:"+profile.getCreditCurrencyUnit());
        System.out.println("FullName:"+profile.getFullName());
        System.out.println("BirthDay:"+new SimpleDateFormat("yyyy/MM/dd").format(profile.getBirthDay()));
        System.out.println("Sex:"+profile.getSex());
        System.out.println("Language:"+profile.getLanguageByISOCode());
        System.out.println("Country:"+profile.getCountry());
        System.out.println("CountryISO:"+profile.getCountryByISOCode());
        System.out.println("CountryIPISO"+profile.getIPCountryByISOCode());
        System.out.println(""+profile.getProvince());
        System.out.println(""+profile.getCity());
        System.out.println(""+profile.getHomePhoneNumber());
        System.out.println(""+profile.getOfficePhoneNumber());
        System.out.println(""+profile.getMobilePhoneNumber());
        System.out.println(""+profile.getWebSiteAddress());
        System.out.println(""+profile.getIntroduction());
        System.out.println(""+profile.getMoodMessage());
        System.out.println(""+profile.getTimeZone());

    }

}
