# Arkkitehtuurikuvaus

## Rakenne
Pilvisovelluksen rakenne noudattaa kolmitasoista kerrosarkkitehtuuria. Alla kuva koodin pakkausrakenteesta:

<img src="https://raw.githubusercontent.com/civuaine/OTM2018_harjoitustyo/master/Dokumentaatio/EditedCloudSoft.png" width="160">

Pakkaus _CloudSoft.ui_ on sovelluksen käyttöliittymästä vastaava pakkaus (toteutettu JavaFX:llä). _CloudSoft.domain_ sisältää sovelluslogiikan koodin ja _CloudSoft.dao_ huolehtii sovelluksen pysyväisaikasesta tallennuksesta, eli toimii sovelluksen ja tietokannan välissä.

## Sovelluslogiikka
Sovelluksen sovelluslogiikka on siinä mielessä erilainen, että siinä ei ole yksittäistä käyttäjää, jonka tarvitsisi kirjautua sisään sovellukseen nähdäkseen jotain tiettyä. Sovellus on avoin kaikille, minkä vuoksi sovelluslogiikan muodostavat enemmälti käyttäjän syötteen perusteellinen tarkistaminen, sekä tietojen hakeminen ja vieminen tietokantaan. Käyttäjältä kysymysten kysyminen ja niiden hyödyntäminen on hyvin iso (vielä toteuttamaton) osa sovelluksen sovelluslogiikkaa.

Sovelluksen toiminnallisesta kokonaisuudesta vastaa ainoastaan luokka _CloudSoftService_. Luokka tarjoaa käyttöliittymälle yksinkertaiset metodit päivämäärän ja paikan syötteen tarkistamiseen, tietokannan alustamiseen, havaintojen järjestämiseen jne.

_CloudSoftService_ pääsee käsiksi kolmeen erilliseen luokkaan: _CityCheck_, joka vastaa käyttäjän syöttämän paikan oikeellisuuden tarkistamisesta, _ObservationDateCheck_, joka varmistaa, että käyttäjän syöttämä päivämäärä on oikein ja järkevä, sekä _Cloud_, joka kuvaa yhtä pilveä ja sen ominaisuuksia (käytetään vain oikean pilven löytämiseen tietokannasta). Näiden lisäksi _CloudSoftService_ pääsee käsiksi pakkauksessa CloudSoft.dao oleviin tietokantoihin rajapinnat _CloudDao_ ja _ObservationDao_ toteuttavien luokkien kautta. Nämä luokat on _CloudSoftService_:n käytettävissä erillisten metodikutsujen kautta.

Alla kuva sovelluksen pakkaus/luokkakaavio hieman tarkemmin:
