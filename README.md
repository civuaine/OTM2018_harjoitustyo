# Pilvisovellus
Pilvisovellus tarjoaa jokaiselle mahdollisuuden ennustaa säätä lähitunneille, tilanteesta riippuen jopa parin päivän päähän. Sovelluksen käyttäjät voivat nähdä myös toistensa tekemiä pilvihavaintoja - tilastoja tehdyistä havainnoista. Käyttäjälle *tarjotaan* mahdollisuus tallentaa oma havainto sovelluksen tietokantaan muiden nähtäville. 

Sovellus kysyy havaitsijalta kysymyksiä nähdystä pilvestä, joihin havaitsija valitsee muutamasta vaihtoehdosta parhaimman. Kysymysten tarkoituksena on iteroida kohti oikeaa pilvityyppiä. Jos oikea pilvityyppi (ja mahdollisesti pilvilaji) löytyy, käyttäjälle kerrotaan mitä kyseinen pilvi voi tarkoitaa lähituntien, jopa lähipäivien sään kannalta.
Jokaiselle käyttäjälle näytetään myös Ilmatieteen laitoksen paikkakuntakohtainen sääennuste lähipäiville.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/civuaine/OTM2018_harjoitustyo/blob/master/Dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/civuaine/OTM2018_harjoitustyo/blob/master/Dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/civuaine/OTM2018_harjoitustyo/blob/master/Dokumentaatio/tuntikirjanpito.md)


## Komentorivitoiminnot

### testien suorittaminen

HUOM! Tätä sovellusta ei voi käynnistää komentoriviltä! komento 

```
mvn compile exec:java-Dexec.mainClass=CloudSoft.ui.CloudSoftUi

```
saa aikaan vain virheilmotuksen, jonka alkuperä ei selvinnyt vielä pajassakaan. Netbeansin vihreä nuoli toimii tosin.


Testit saa suoritettua komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```


## Checkstyle

Komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
saa checkstyle raportin (tarkastelu: _target/site/checkstyle.html_).



