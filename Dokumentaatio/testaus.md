# Testausdokumentti

Ohjelmaa on testattu monilla eri testeillä, pääasiassa JUnitilla.

## Sovelluslogiikka

Sovelluslogiikan testit ovat pakkauksissa servicerests ja checktests. Ensimmäinen sisältää varsinaisen sovelluslogiikkaluokan
testaamisen, ja jälkimmäinen käyttäjän syötteiden (paikkakunta ja päivämäärä) testaamisen. 
Näissä testeissä on käytetty yksinomaan JUnitin yksikkötestausominaisuuksia. Checktestit pyrkivät testaamaan kaikkia
niitä tilanteita, joita käyttäjä voi saada aikaan syötteellään. Servicetest keskittyy enemmänkin useiden metodien
yhteistoimintaan ja niissä esiintyviin tilanteisiin.


## Databaset
Databaseiden testaus jäi hieman heikolle pohjalle ajan puutteen vuoksi.

## Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 75% ja haarautumiskattavuus 87%.

<img src="https://raw.githubusercontent.com/civuaine/OTM2018_harjoitustyo/master/Dokumentaatio/lopullinenjacoco.png" width="800">

Testaamatta jäi iso osa cloudin ja cloudsoftservicen settereistä ja gettereistä, sekä jonkin verran tietokannasta.

## Järjestelmätestaus
Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

Sovellusta on testattu suoraan jar-tiedostosta ajamalla. Tilanteet, joissa tietokanta on valmiiksi olemassa ja toisaalta
joissa sovellus joutuu ne itse luomaan on myös testattu.

## Toiminnallisuudet
Kaikki määrittelydokumentin ja käyttöohjeen toiminnallisuudet on käyty läpi. Syötekenttiä saa koittaa täyttää vaikka millä.
Aika moni asia on otettu huomioon. Joten olipa käyttäjä kuinka tyhmä tahansa, sovelluksen pitäisi tilanne handlata.

## Sovellukseen jääneet laatuongelmat

SOvellus ei näytä käyttäjälle virheilmoituksia jos sovellus kaatuu. Muutenkin järkevien virheilmoitusten näyttämisessä saattaa olla puutteita.
