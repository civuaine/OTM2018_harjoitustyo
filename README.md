# Pilvisovellus
Pilvisovellus tarjoaa jokaiselle mahdollisuuden ennustaa säätä lähitunneille, tilanteesta riippuen jopa parin päivän päähän. Sovelluksen käyttäjät voivat nähdä myös toistensa tekemiä pilvihavaintoja - tilastoja tehdyistä havainnoista. Käyttäjälle *tarjotaan* mahdollisuus tallentaa oma havainto sovelluksen tietokantaan muiden nähtäville. 

Sovellus kysyy havaitsijalta muutamia kysymyksiä havaitusta pilvestä, joihin havaitsija vastaa niin hyvin kuin voi. Kysymysten tarkoituksena on iteroida havainto kohti oikeaa pilvityyppiä. Jos oikea pilvityyppi löytyy, käyttäjälle kerrotaan mitä kyseinen pilvi voi tarkoitaa lähituntien, jopa lähipäivien sään kannalta.
Jokaiselle käyttäjälle näytetään myös Ilmatieteen laitoksen paikkakuntakohtainen sääennuste lähipäiville.

## Dokumentaatio

[Käyttöohje](https://github.com/civuaine/OTM2018_harjoitustyo/blob/master/Dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/civuaine/OTM2018_harjoitustyo/blob/master/Dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/civuaine/OTM2018_harjoitustyo/blob/master/Dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/civuaine/OTM2018_harjoitustyo/blob/master/Dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/civuaine/OTM2018_harjoitustyo/releases/tag/viikko5)
[Viikko 6]()

## Komentorivitoiminnot

### testien suorittaminen

Seuraava komento ajaa koodin komentoriviltä:
```
mvn compile exec:java -Dexec.mainClass=cloudsoft.ui.CloudSoftUi

```

Testit saa suoritettua komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn test jacoco:report
```


## Checkstyle

Komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
saa checkstyle raportin (tarkastelu: _target/site/checkstyle.html_).

## JavaDoc
JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```
JavaDocia voi tarkistella selaimessa avaamalla tiedoston index.html kyseiseltä polulta: target/site/apidocs/index.html

## Jar-tiedoston generointi

Komennolla
```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston PilviSovellus-1.0-SNAPSHOT.jar
Itse siirrän .jar-tiedoston hakemistosta _target_ hakemistoon _PilviSovellus_ ennen komentoriviltä ajamista.

PilviSovellus-kansiosta voi ajaa käskyn
```
java -jar target/PilviSovellus-1.0-SNAPSHOT.jar
```
