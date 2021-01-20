# SKM Simulator

## Description  

This simulates very basic subway behaviour such as:
- Moving the trains
- Adding trains / adding compartments / adding passengers
- Removing passengers upon reaching destination
- Passengers are generated and removed automatically, there is no CRUD for them
- All data is stored in a mysql database using liquibase

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


Czesc trzecia:
Celem tego etapu jest zabezpieczenie naszej aplikacji przed nieautoryzowanym uzytkowaniem.

1. Dodaj do bazy danych nowego changeseta, ktory pomoze nam zrealizowac poprwanie dzialajaca autentykacje i autoryzacje. Informacje, ktore powinnismy przechowywac o uzytkownikach to przynamniej, login, zakodowane haslo oraz jego uprawnienia.
2. Nasza aplikacja powinna rozrozniac przynajmniej 4 sytuacje obslugi uzytkownikow:
   a) nieznany uzytkownik - brak dostepu do aplikacji
   b) znany, zwykly uzytkownik - dostep do aplikacji ograniczony do pobierania informacji
   c) znany, uprzywilejowany uzytkownik - j.w. + dostep do endpointow modyfikujacych dane pociagow, przedzialow itp.
   d) admin - j.w. + mozliwosc zarzadzania uzytkownikami
3. Na potrzeby uzywania aplikacji przez administratora, dodaj Controller pozwalajacy na rejestracje nowych uzytkownikow, jak i modyfikacje ich uprawnien i
   usuwanie ich.
4. Wykorzystaj JWT do autentykacji i autoryzacji uzytkownikow.

Termin: 26.01.2021 23:59, max 10/10