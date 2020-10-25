# jaz_first

## Objectives

symulator SKM:

- Gradle, Docker, Git :heavy_check_mark:
- dwie aplikacje: symulator i klient :heavy_check_mark:
- wszystkie stacje od GDYNIA GLOWNA do GDANSK GLOWNY (powinno ich byc ok 15) :heavy_check_mark: 
- dystans miedzy stacjami uznajemy za jednakowy :heavy_check_mark: ustawiony na jeden
- bedzie naraz jezdzilo X pociagow, kazdy pociag ma miec Y przedzialow, kazdy przedzial - pomiesci Z osob :heavy_check_mark:
- X,Y,Z ladujemy z pliku konfiguracyjnego :heavy_check_mark:
- gdy pociag dojezdza do stacji, pojawia sie na niej losowa (2-8) ilosc osob, ktore chca sie dostac na losowy przystanek na dalszej czesci tej linii. :heavy_check_mark:
  Wysiadaja z pociagu gdy dotra na miejsce :heavy_check_mark:
- gdy pociag dojezdza na koniec linii, robi postoj na 2 tury, po czym rusza w przeciwnym kierunku :heavy_check_mark:
- symulator powinien miec:
  - endpoint na przesuniecie symulacji do przodu - pociagi sie przesuwaja o jedno pole, np:
        POST symulator:9000/doprzodu :heavy_check_mark: zrobione '/move'
  - wystawic endpointy dla klienta :heavy_check_mark:
- klient ma miec mozliwosc odpytania symulatora o:
  - ilosc pociagow np:
	GET api:8080/pociagi - i otrzymac w odpowiedzi JSONa z danymi pociagow: w tym przypadku tylko numer identyfikacyjny :heavy_check_mark:
  - stan pociagu np:
        GET api:8080/pociagi/1234 - otrzymac w odpowiedzi JSONa z danymi pociagu o id 1234: numer id, procentowe zapelnienie przedzialow, ilosc osob :heavy_check_mark:

Dodatkowo:
- kazdy czlowiek ma miec losowe imie i nazwisko, klient powinien moc dopytac symulator o informacje o przedziale i w odpowiedzi otrzymac informacje o pasazerach :heavy_check_mark: Przedzialy numerowane od 0