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


Czesc druga:
Przerobic nasz symulator SKM tak, by:
  - pociagi i przedzialy pochodzily z bazy danych (pociagi moga teraz miec rozna ilosc przedzialow, kazdy przedzial moze miec rozna pojemnosc), :heavy_check_mark:
    konfiguracja poczatkowa jest dowolna (przyklad - po jednym pociagu w Gdyni i w Gdansku, kazdy pociag inna ilosc przedzialow, zaczynaja puste), :heavy_check_mark:
  - Controllery do CRUD'a dla pociagu i przedzialu (Create, Read, Update, Delete), :heavy_check_mark:
  - wykorzystac liquibase do zainicjalizowania i obslugi zmian w schemie bazodanowej, :heavy_check_mark:
  - Controller powinien teraz uzywac ResponseEntity do komunikacji zwrotnej + odpowiednie HttpStatus (prosta obsluga bledow -> blad = kod 500), :heavy_check_mark:
  - pociagi powinny dalej jezdzic, ladowac i rozladowywac ludzi, robic postoje, zawracac na stacjach koncowych. :heavy_check_mark:
  - testy!!!!!!!!! :heavy_check_mark:

termin oddania pracy: 22.12.2020*
ilosc punktow do zdobycia: 10 + 2*
