# Käyttöohje

Lataa tiedosto [pilvisovellus.jar](https://github.com/civuaine/OTM2018_harjoitustyo/releases/tag/lopullinen)

## Konfigurointi
Ohjelma olettaa, että sen käynnistyshakemistossa on myös yllä olevan linkin takaa löytyvät kolme tekstitiedostoa, joista kukin sisältää tekstiä käyttöliittymään. (teksti.txt, teksti2.txt ja teksti3.txt)

## Ohjelman käynnistäminen

Ohjelma käynnistetään kommennolla 
```
java -jar pilvisovellus.jar
```
## Päänäkymä
Sovellus käynnistyy päänäkymään, jossa on ohjeteksti ja kaksi nappulaa, joista toisesta pääsee katsomaan tilastoja havainnoista
ja toisesta tekemään pilvihavainnon.

<img src="https://raw.githubusercontent.com/civuaine/OTM2018_harjoitustyo/master/Dokumentaatio/paanakyma.png" width="1100">

## Tilastojen havainnoista -näkymä

Voit katsella muiden käyttäjien tekemiä pilvihavaintoja ja järjestellä ne joko päivämään tai havaintopaikan mukaan nappeja
painamalla. "Etusivulle" nappi vie sinut takaisin etusivulle.

<img src="https://raw.githubusercontent.com/civuaine/OTM2018_harjoitustyo/master/Dokumentaatio/Tilastoja.png" width="1100">

## Havainnon tekeminen
Jos valitset päänäkymässä napin "Tee havainto", sinulta kysytään havaintopaikkakuntaa ja päivämäärää, jolloin pilvihavainnon teit.
Syötä päivämäärä ohjeen antamassa muodossa. Jos painat "Etusivulle" nappia sovellus palauttaa sinut päänäkymään.

<img src="https://raw.githubusercontent.com/civuaine/OTM2018_harjoitustyo/master/Dokumentaatio/havaintosivu.png" width="1100">

## Kysely
Varsinaisessa kyselyssä pyri valitsemaan se vaihtoehto, joka on mahdollisimman lähelle todellisuutta.

<img src="https://raw.githubusercontent.com/civuaine/OTM2018_harjoitustyo/master/Dokumentaatio/kyselysivu.png" width="1100">

"Etusivulle" nappi palauttaa sinut päänäkymään, ja pyyhkii kaikki juuri syöttämäsi tiedot automaattisesti.
Jos antamiesi tietojen perusteella löytyy pilvi tietokannasta, saat lopuksi näytölle ennusteen siitä mitä pilvi voi kertoa tulevasta säästä. Jos sovellus tunnistaa havaitsemasi pilven, voit lopussa halutessasi tallentaa pilvihavainnon tietokantaan.




