# Arkkitehtuurikuvaus

## Rakenne
Pilvisovelluksen rakenne noudattaa kolmitasoista kerrosarkkitehtuuria. Alla kuva koodin pakkausrakenteesta:

<img src="https://raw.githubusercontent.com/civuaine/OTM2018_harjoitustyo/master/Dokumentaatio/EditedCloudSoft.png" width="160">

Pakkaus _CloudSoft.ui_ on sovelluksen käyttöliittymästä vastaava pakkaus (toteutettu JavaFX:llä). _CloudSoft.domain_ sisältää sovelluslogiikan koodin ja _CloudSoft.dao_ huolehtii sovelluksen pysyväisaikasesta tallennuksesta, eli toimii sovelluksen ja tietokannan välissä.
