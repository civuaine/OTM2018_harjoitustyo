# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjän on mahdollista selvittää mikä taivaalla näkyvän pilven pilvisuku on ja
mitä se *voi* kertoa tulevasta säästä. Käyttäjä näkee myös mitä pilvihavaintoja löytyy sovelluksen tietokannasta. Kyselyn aluksi käyttäjältä kysytään havainnontekopaikka ja päivämäärä, jotka tarkistetaan perusteelliseti virheellisen syötteen vuoksi. Jos pilvitietokannasta löytyy havaintoa vastaava pilvi, voi käyttäjä lisätä havainnon järjestelmään. Joka tapauksessa käyttäjälle tarjotaan mahdollisuus uusia testi.

## Käyttäjät
Käyttäjärooleja on vain yksi eli normaali käyttäjä.

## Toiminnallisuudet
- Käyttäjä voi nähdä statistiikkaa, eli millaisia pilvihavaintoja milläkin paikkakunnalla on tehty ja milloin
- Jokainen käyttäjä voi nähdä tietokannassa olevia havaintoja. Käyttäjät ovat anonyymejä.
- Statistiikkasivulla havainnot on päivämäärän mukaan järjestetty
- käyttäjä voi valita kuinka haluaa havainnot järjestää, päivämäärän vai paikan mukaan
- Syötetyn paikkakunnan olemassa olo tarkistetaan Yahoon sääpalvelusta.
- Käyttäjä voi lisätä juuri tehdyn pilvihavainnon järjestelmään (tietokantaan), myös aiemmin tehdyn havainnon voi lisätä.
- Käyttäjältä kysytään haluaako hän tallentaa havainnon järjestelmään
- käyttäjä voi suorittaa testin uudelleen sulkematta sovellusta
- Tehtyjä havaintoja ei voi poistaa
- Tulevaisuudessa olevat havainnot aiheuttavat virheilmoituksen

## Käyttöliittymäsuunnittelua...
- Kun käyttäjä käynnistää sovelluksen, tulee näytölle ohjeet sovelluksen ideasta ja tarkoituksesta, sekä siihen liittyvästä toiminnallisuudesta
- Käyttäjä klikkailee nappeja sovelluksen kysyessä kysymyksiä havaintoon liittyen
- Jokainen klikkaus johtaa uuteen kysymykseen. Paluuta edelliseen kysymykseen ei sallita. Koko kyselyn voi toki suorittaa uudestaan.

## Toimintaympäristön rajoitteet
- Tulee toimia Linux-ympäristössä
- Käyttäjien havainnot tallennetaan paikallisen koneen levylle

## Jatkokehitysideoita
- Sovellukseen saatetaan lisätä myöhemmin mahdollisuus selvittää myös havaitun pilven pilvilaji, jolloin pilvien perusteella annettu sääennuste voi parantua.
- Yahoon sääpalvelusta voisi käyttäjälle antaa myös sääennusteen lähivuorokausille.
- kivoja kuvia sovellukseen. Kuvat voisivat helpottaa myös kysymyksiin vastaamista.

