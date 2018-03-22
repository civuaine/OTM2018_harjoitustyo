# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjän on mahdollista selvittää (näin alkuvaiheessa) mikä taivaalla näkyvän pilven pilvisuku on ja
mitä se *voi* kertoa tulevasta säästä. Käyttäjä näkee myös mitä pilvihavaintoja muut sovellusta käyttäneet ovat tehneet. Lopuksi käyttäjältä kysytään havainnontekopaikka ja päivämäärä, jonka jälkeen käyttäjä voi lisätä havainnon järjestelmään. Käyttäjälle annetaan myös Ilmatieteen laitoksen sääennuste kyseiselle paikkakunnalle.

(Sovellukseen saatetaan lisätä myöhemmin mahdollisuus selvittää myös havaitun pilven pilvilaji, jolloin pilvien perusteella annettu sääennuste voi parantua.)
## Käyttäjät
Käyttäjärooleja on vain yksi eli normaali käyttäjä.

## Suunnitellut toiminnallisuudet
- Käyttäjä voi nähdä statistiikkaa, eli millaisia pilvihavaintoja milläkin paikkakunnalla on tehty ja milloin
- Statistiikkasivulla havainnot on päivämäärän mukaan järjestetty
- Käyttäjä voi lisätä juuri tehdyn pilvihavainnon järjestelmään (tietokantaan), myös aiemmin tehdyn havainnon voi lisätä

## Käyttöliittymäsuunnittelua...
- Käyttäjä klikkailee nappeja sovelluksen kysyessä kysymyksiä havaintoon liittyen
- Käyttäjälle näytetään esimerkkikuvia kysymyksiin liittyen
- Joidenkin kysymysten ohessa voidaan antaa lisäohjeita tarvittaessa jonnekin sivuun tai napin toiminnallisuuden taakse 
- Jokainen klikkaus johtaa uuteen kysymykseen. Paluu mahdollisuus edelliseen kysymykseen tulee onnistua.
- Ilmatieteen laitokselta haettu sääennuste jotenkin järkevästi ruudulle...

## Toimintaympäristön rajoitteet
- Tulee toimia Linux-ympäristössä
- Käyttäjien havainnot tallennetaan paikallisen koneen levylle


