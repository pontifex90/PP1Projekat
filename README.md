# PP1Projekat
Programski Prevodioci projekat 2016/2017


1/15
Elektrotehnički fakultet u Beogradu
Katedra za računarsku tehniku i informatiku
Predmet: Programski prevodioci 1
Nastavnik: dr Dragan Bojić, vanr. prof.
Asistenti: dipl. ing. Nemanja Kojić, dipl. ing. Maja Vukasović
Školska: 2016/2017.
Ispitni rok: Januarsko-februarski ispitni rok
Datum: 06.12.2016.
Projekat
– Kompajler za Mikrojavu –
Važne napomene: Pre čitanja ovog teksta, obavezno pročitati opšta pravila predmeta i pravila vezana za izradu domaćih zadataka! Pročitati potom ovaj tekst u celini i pažljivo, pre započinjanja realizacije ili traženja pomoći. Ukoliko u zadatku
2/15
1. Uvod
Cilj projektnog zadatka je realizacija kompajlera za programski jezik Mikrojavu. Kompajler omogućava prevodjenje sintaksno i semantički ispravnih Mikrojava programa u Mikrojava bajtkod koji se izvršava na virtuelnoj mašini za Mikrojavu. Sintaksno i semantički ispravni Mikrojava programi su definisani specifikacijom.
Programski prevodilac za Mikrojavu ima četiri osnovne funkcionalnosti: leksičku analizu, sintaksnu analizu, semantičku analizu i generisanje koda.
Leksički analizator treba da prepoznaje jezičke lekseme i vrati skup tokena izdvojenih iz izvornog koda, koji se dalje razmatraju u okviru sintaksne analize. Ukoliko se tokom leksičke analize detektuje leksička greška, potrebno je ispisati odgovarajuću poruku na izlaz.
Sintaksni analizator ima zadatak da utvrdi da li izdvojeni tokeni iz izvornog koda programa mogu formiraju gramatički ispravne sentence. Tokom parsiranja Mikrojava programa potrebno je na odgovarajući način omogućiti i praćenje samog procesa parsiranja na način koji će biti u nastavku dokumenta detaljno opisan. Nakon parsiranja sintaksno ispravnih Mikrojava programa potrebno je obavestiti korisnika o uspešnosti parsiranja. Ukoliko izvorni kod ima sintaksne greške, potrebno je izdati adekvatno objašnjenje o detektovanoj sintaksnoj grešci, izvršiti oporavak i nastaviti parsiranje.
Semantički analizator se dobija proširenjem funkcionalnosti sintaksnog analizatora. Semantička analiza se vrši sprovodi kroz sintaksno-upravljano prevođenje. Osnovnoj gramatici, kojom je specificiran sintaksni analizator, dodaju se atributi i akcije (atributivno-translaciona gramatika).
Generator koda prevodi sintaksno i semantički ispravne programe u
3/15
3. Specifikacija zahteva
I Leksička analiza (5 poena)
U nastavku teksta su navedeni i opisani projektni zahtevi za implementaciju leksičkog analizatora. Leksička analiza nosi maksimalno 5 poena.
Potrebno je realizovati leksički analizator (skener) izvornih programa napisanih na jeziku Mikrojava.
Leksički analizator se mora implementirati na programskom jeziku Java.
Interfejs leksičkog analizatora prema sintaksnom analizatoru mora biti standardni CUP interfejs. Za više informacija, pogledati primer mini domaćeg u vežbama na sajtu predmeta.
Skener prihvata fajl za izvornim kodom na jeziku Mikrojava i deli ga na tokene.
Token se vraća eksplicitnim pozivom leksičkog analizatora (operacija next_token()).
Potrebno je detektovati i obraditi sledeće leksičke strukture:
– identifikatore,
– konstante,
– ključne reči,
– operatore,
– komentare.
Leksičke strukture implementirati prema specifikaciji jezika [MJ§A.2p3].
Leksički analizator treba da preskače komentare i "beline" u tekstu programa.
Pod "belinama" se smatraju: tabulatori (\t), prelazak u novi red (\r \n), razmak (' '), bekspejs (\b), prelazak na novu stranu (\f, form feed).
U slučaju leksičke greške analizator, ispisuje se greška i nastavlja se obrada teksta programa.
Poruka o grešci treba da sadrži sledeće informacije:
– niz znakova koji nije prepoznat,
– broj linije teksta programa u kojoj se desila greška, i
– kolonu (poziciju prvog znaka) u kojoj je detektovana greška.
Potrebno je napisati test program (main) koji testira rad leksičkog analizatora. Program poziva leksički analizator sve dok se ne stigne do kraja ulaznog fajla i ispisuje redom vraćene tokene na izlaz.
Test program treba da ima opciju da rezultat ispiše ili na standardni izlaz ili u zadati fajl.
Obavezno je korišćenje alata JFlex.
Moraju se koristiti jdk 1.8 ili 1.7 i aktuelna verzije JFlex alata kao što je opisano u primerima na vežbama.
Korišćenje integrisanih okruženja (Eclipse ili Netbeans) se preporučuje, ali prevedeni kod mora da može da se pokrene iz komandne linije na računaru koji je podešen kao što je opisano u JFlex primerima u okviru vežbi.
4/15
II Sintaksna analiza (maks. 10 poena)
Potrebno je napisati LALR(1) gramatiku na osnovu specifikacije jezika i implementirati sintaksni analizator (parser) za programe napisane na jeziku Mikrojava.
Sintaksna analiza nosi maksimalno 10 poena.
Raspodela poena po nivoima je: A(6), B(8), C(10).
Opšti tehnički zahtevi (maks. 4 poena)
Gramatika jezika Mikrojava mora biti napisana u skladu sa specifikacijom jezika definisanom u [MJ].
Za implementaciju parsera mora se koristiti generator sintaksnih analizatora CUP.
Specifikacija parsera mora biti napisana u CUP fajlu, u formatu koji CUP generator prepoznaje.
Sintaksni analizator mora biti integrisan sa CUP kompatibilnim leksičkim analizatorom za jezik Mikrojava.
U slučaju uspešnog parsiranja ulaznog fajla parser na kraju rada na standardnom izlazu prikazuje poruke o broju prepoznatih jezičkih elemenata (kako je definisano u zadatku) i na kraju poruku o uspešnom parsiranju (vidi Prilog 2).
U slučaju nailaska na sintaksnu grešku parser:
– ispisuje poruku greške u log fajl,
– vrši oporavak od greške i
– nastavlja sa parsiranjem ostatka fajla.
Parser treba da omogući oporavak od sintaksnih grešaka za zadate jezičke elemente.
Opis sintaksne greške TREBA da sadrži:
– broj linije ulaznog programa u kojoj je greška detektovana (videti realizaciju u primeru mini domaćeg sa sajta predmeta),
– opis greške.
Implementacija parsera
Nije dozvoljeno koristiti opciju precedence u .cup fajlu za definisanje prioriteta operatora. Izuzetno je dozvoljeno korišćenje direktive precedence left ELSE; da se razreši konflikt između smena za if iskaz (statement) sa else delom i if iskaz bez else dela.
Gramatika mora da sadrži neterminale koji su nazvani isto kao u odabranoj specifikaciji uz eventualne dodatne neterminale ukoliko se za tim ukaže potreba.
Generisana klasa parsera mora da ima metodu main koja pokreće parsiranje nad MJ fajlom.
Ime MJ fajla se zadaje isključivo preko komandne linije.
Preprojavanje simbola (maks. 3 poena)
U parser se mora ugraditi prebrojavanje jezičkih elemenata. Potrebno je implementirati prebrojavanje sledećih elemenata:
NIVO A (1 poen):
– definicije globalnih promenljivih,
– definicije lokalnih promenljivih (u main funkciji),
– definicije globalnih konstanti
– deklaracije globalnih nizova;
NIVO B (2 poena, uključujući sve iz A):
– definicije globalnih i statičkih funkcija unutrašnjih klasa,
– blokovi naredbi,
– pozive funkcija u telu metode main,
– deklaracije formalnih argumenata funkcija;
NIVO C (3 poena, uključujući sve iz B, a samim ti i iz A):
– definicije unutrašnjih klasa,
5/15
– definicije metoda unutrašnjih klasa,
– deklaracije polja unutrašnjih klasa.
Oporavak od grešaka (maks. 3 poena)
U gramatiku TREBA dodati smene i akcije za oporavak od grešaka. Implementirati oporavak od grešaka za sledeće jezičke elemente:
NIVO A (1 poen):
– definicija globalne promenljive – ignorisati karaktere do prvog znaka ";" ili sledećeg ","
– deklaracija lokalnih promenljivih – ignorisati karaktere do prvog ";" ili "{"
– konstrukcija iskaza dodele – ignorisati karaktere do ";"
NIVO B (2 poena, uključujući i A):
– deklaracija formalnog parametra funkcije – ignorisati znakove do znaka "," ili ")"
– logički izraz unutar if konstrukcije - ignorisati karaktere do prvog znaka ")"
– izrazi unutar for(...) konstrukcije – ignorisati karaktere do prvog znaka ")" ili ";"
– izraz za indeksiranje niza – ignorisati karaktere do prvog znaka "]"
NIVO C (3 poena, uključujući i sve za B):
– liste parametara u pozivu metode objekta – ignorisati karaktere do prvog znaka ")"
– deklaracija polja unutrašnje klase – ignorisati karaktere do prvog ";" ili "{"
– deklaracija proširenja natklase (extends) – ignorisati znakove do prvog znaka "{".
Testiranja rada implementiranog parsera:
Napisati ulazne fajlove na programskom jeziku Mikrojava koji sadrže sve sintaksno ispravne kombinacije elemenata MJ gramatike (npr. funkcija bez formalnih parametara, sa jednim formalnim parametrom, sa više formalnih parametara).
Napisati ulazne fajlove na programskom jeziku Mikrojava koji sadrže sve kombinacije grešaka koje su navedene u zahtevima [R58-R69].
6/15
III Semantička analiza
Potrebno je proširiti sintaksni analizator iz prethodnog poglavlja da vrši semantičku analizu MJ programa i ažurira tabelu simbola.
Semantička analiza se boduje sa maksimalno 10 poena. Funkcionalnosti su raspoređene po nivoima. Raspored bodova po nivoima je: A (4), B(7), C(10).
Opšti zahtevi (1 poen)
Semantička analiza se implementira proširenjem specifikacije sintaksnog analizatora (uvođenjem atributa neterminala i terminala, kao i akcija za implementaciju semantičkih provera).
Sintaksno-semantički analizator je potrebno integrisati sa tabelom simbola. Tabela simbola čuva informacije o simbolima pronađenim u MJ programu koji se prevodi.
Mora se koristiti implementacija tabele simbola dostupna na sajtu predmeta: http://ir4pp1.etf.rs/Domaci/symboltable.jar.
Tabela simbola se uvezuje sa ostatkom programa kao Java biblioteka (.jar) i dozvoljeno je koristiti sve njene javne klase, metode i polja. Uz biblioteku se dostavlja i izvorni kod koji je predviđen samo za čitanje i informisanje o detaljima implementacije.
NIJE DOZVOLJENO raspakivati biblioteku za tabelu simbola, menjati njenu implementaciju i ponovo je prevoditi i uvezivati sa projektom.
Ukoliko postojeća implementacija tabele simbola ne zadovoljava sve zahteve iz date specifikacije, može se nadograditi ISKLJUČIVO pomoću izvođenja klasa i redefinisanja postojećih metoda. Tabela simbola ima nekoliko tačaka za proširenja.
Ukoliko ni redefinisanje klasa ne doprinosi željenom ponašanju tabele simbola, a takav zaključak se MORA izvesti samo posle adekvatne diskusije na listi predmeta ili na časovima vežbi, upućuje se zahtev na listu predmeta i kontrolu preuzima predmetni asistent da u što kraćem roku prilagodi implementaciju tabele simbola potrebama realizacije domaćeg zadatka.
Parser mora da ima javno dostupnu metodu void dump()za ispis sadržaja tabele simbola. Videti prilog 3.
U glavnom programu parsera metoda dump se poziva nakon završetka parsiranja.
Unos simbola u Tabelu simbola
Implementirati unos svih simbola predviđenih za odgovarajući nivo u tabelu simbola dodavanjem semantičkih akcija u postojeći sintaksni analizator. Obrađuju se jezički elementi predviđeni u specifikaciji [MJ].
Detektovanje korišćenja simbola (Bodovanje: NivoA - 1, NivoB - 2, NivoC - 3)
Implementirati detektovanje korišćenja simbola u programu dodavanjem semantičkih akcija u postojeći sintaksni analizator, i to za sledeće jezičke elemente:
NIVO A (1 poena)
– konstante,
– globalne promenljive,
– lokalne promenljive.
NIVO B (2 poena, uključuje i sve zahteve iz A)
– globalne funkcije (pozivi)
– člana niza
– korišćenje formalnog argumenta funkcije
NIVO C (3 poena, uključuje i sve zahteve iz B)
– unutrašnje klase (pravljenje objekta)
– polja unutrašnjih klasa (pristup polju)
– metode unutrašnjih klasa (pozivi).
Za svaki detektovani simbol potrebno je proveriti sledeće:
7/15
– da li ime postoji u tabeli simbola,
– da li je ispravnog tipa.
Format poruke
Poruka o detektovanom simbolu MORA da sadrži sledeće podatke (videti Prilog 3 za format poruke):
– linija izvornog koda u kojoj je pronađen simbol,
– naziv pronađenog simbola,
– ispis objektnog čvora iz tabele simbola koji odgovara pronađenom simbolu.
Provera kontekstnih uslova (Bodovanje: Nivo A - 1, NivoB - 2, NivoC - 3)
Implementirati proveru svih kontekstnih uslova navedenih u specifikaciji [MJ§A.4p5] predviđenih za odabranu grupu funkcionalnosti.
Testiranja rada implementiranog semantičkog analizatora:
Napisati ulazne fajlove na programskom jeziku Mikrojava koji sadrže sve sintaksno i semantički ispravne MJ programe uz pokrivanje svih smena iz gramatike.
Napisati ulazne fajlove na programskom jeziku Mikrojava koji sadrže sve kombinacije semantičkih grešaka.
8/15
IV Generisanje koda
Potrebno je proširiti sintaksno-semantički analizator iz prethodnog poglavlja da vrši generisanje bajtkoda za izvršno okruženje MJVM.
Generisanje koda nosi maksimalno 15 poena. Zahtevi su po težini podeljeni u grupe. Raspodela poena po nivoima je: A(5), B(10), C(15).
Opšti zahtevi
Generator koda mora da generiše ispravan bajtkod za MJVM:
Za implementaciju generatora koda moraju se koristiti alati Code, disasm i Run. http://ir4pp1.etf.rs/Domaci/mj-runtime-1.1.jar
Izlaz generatora koda mora da bude izvršivi .obj fajl za MJVM.
NIVO A (5 poena) potrebno je implementirati generisanje koda za SVE gramatičke smene u nastavku (osnovni iskazi, aritmetički izrazi i rad sa nizovima prostih tipova):
Statement := DesignatorStatement ";". DesignatorStatement := Designator Assignop Expr. DesignatorStatement := Designator"++". DesignatorStatement := Designator "--".
Statement := DesignatorStatement ";". Statement := "read" "(" Designator ")" ";". Statement := "print" "(" Expr [“,” number] ")" ";". Expr := ["‐"] Term {Addop Term}. Term := Factor {Mulop Factor}. Factor := numConst | charConst | "(" Expr ")" | boolConst | "new" Type [ "[" Expr "]" ]. Designator := ident "[" Expr "]".
Assignop := "=" | "+=" | "-=" | "*=" | "/=" | "%=".
Addop := AddopLeft | AddopRight. AddopLeft := "+" | "‐" .
AddopRight := "+=" | "-=" .
Mulop := MulopLeft | MulopRight. MulopLeft := "*" | "/" | "%".
MulopRight := "*=" | "/=" | "%=".
Od nizova, treba podržati samo vektore ugrađenih tipova podataka. Program treba da ima samo jednu funkciju (main), globalne/lokalne promenljive (proste ili nizovne), globalne konstante. Ne treba obrađivati unutrašnje klase i globalne funkcije.
NIVO B (10 poena) potrebno je implementirati SVE zahteve za Nivo A i još dodatno SVE gramatičke smene u nastavku (kontrolne strukture, uslovni izrazi, pozivi globalnih metoda): DesignatorStatement := Designator "(" [ActPars] ")" . Statement := "if" "(" Condition ")" Statement ["else" Statement ]. Statement := ʺforʺʺ(ʺ [DesignatorStatement ] ʺ;ʺ [Condition] ʺ;ʺ [DesignatorStatement] ʺ)ʺ Statement . Statement := "break" “;”.
Statement := "continue" “;”. Statement := "return" [Expr] “;”. Statement := "{" {Statement} "}". ActPars := Expr {"," Expr }. Condition := CondTerm { "||" CondTerm }. CondTerm := CondFact { "&&" CondFact }. CondFact = Expr [ Relop Expr ]. // samo jedan izraz (Expr) u slučaju bool promenljive Factor := Designator [ "(" [ActPars] ")" ].
Nivo C (15 poena) potrebno je implementirati SVE zahteve za Nivo B i još dodatno zahteve u nastavku (unutrašnje klase, supstitucija i polimorfizam): - implementirati nasleđivanje klasa;
9/15
- implementirati supstituciju (na mestu gde se očekuje referenca na osnovnu klasu može se predati referenca na objekat izvedene klase); - implementirati pravljenje objekata unutrašnjih klasa i nizova objekata unutrašnjih klasa; - implementirati pravljenje tabela virtuelnih funkcija; - implementirati polimorfno pozivanje metoda unutrašnjih klasa i pozivanje statičkih metoda.
10/15
V Ocenjivanje domaćih zadataka
Opšti zahtevi
Generisanje koda, koje proizvodi ispravne izvršne bajtkod (predmetne) fajlove u skladu sa opisanim zahtevima, je POTREBAN i DOVOLJAN uslov za izlazak na odbranu domaćeg zadatka.
Na odbrani se ocenjuju FUNKCIONALNOSTI kompajlera, koje su opisane gramatikom jezika.
FUNKCIONALNOST je izvršiva jedinica kompajlera koja odgovarajuću naredbu, izraz ili definiciju prevodi u izvršne bajtkod instrukcije ili generiše podatke zaglavlja predmetnog (obj) fajla. Mora biti implementirana na sva četiri nivoa (leksika, sintaksa, semantika i generisanje koda ili podataka zaglavlja predmetnog fajla).
Važeća specifikacija jezika se nalazi na sajtu predmeta, u odeljku za Domaći zadatak.
Bodovanje projekta
Projekat se boduje prema cenovniku za Nivo A, akko su ispunjeni SVI zahtevi predviđeni za Nivo A. Maksimalan broj poena predviđen za Nivo A je 20.
Projekat se boduje prema cenovniku za Nivo B, akko ispunjava SVE zahteve za Nivo A i, ako ispunjava DEO, a najviše SVE zahteve propisane za Nivo B. Maksimalan broj poena predviđen za Nivo B je 30.
Projekat se boduje prema cenovniku za Nivo C, akko ispunjava SVE zahteve predviđene za Nivo B, i ako ispunjava DEO ili SVE funkcionalnosti predviđene za Nivo C. Maksimalan broj poena predviđen za Nivo C je 40.
Ukoliko student ne implementira SVE funkcionalnosti za Nivo A, projekat neće biti razmatran na odbrani.
11/15
Primer programa:
program Program
class A{
int x[],y[];
}
const int pi = 3, e = 2;
class B extends A
{
int i; int x[];
{
int getValue(int a) int b; bool c;
{ return this.i + this.x[0] + a; }
}
}
class C extends B{
A theA;
int a;
}
{
void main() A a; C c; int i; int x[];
{
a = new A;
a.x = new int[5];
a.y = new int[5];
c = new C;
c.theA = a;
c.x = new int[5];
x = new int[3];
i=0;
read(c.i);
for(; i<5; i++)
{
read(c.x[i]);
read(c.theA.x[i]);
}
print(c.getValue(c.theA.x[0]));
}
}
12/15
4. Napomene u vezi sa izradom i odbranom rešenja
Elementi rešenja su sledeći:
a) Propratna dokumentacija u obliku Word dokumenta MJProjekat.doc koji treba da se nalazi u korenom direktorijumu rešenja i da sadrži:
b) naslovnu stranu,
c) kratak opis postavke zadatka od nekoliko rečenica,
d) opis komandi za generisanje java koda alatima, prevođenje koda kompajlerom, pokretanje i testiranje rešenja,
e) kratak opis priloženih test primera (ne uključivati ulaze niti izlaze testiranja u izveštaj).
f) kratak opis novouvedenih klasa.
Izvorni i prevedeni programski kod za java varijantu mora da sledi direktorijumsku strukturu koja je opisana u vežbama. Dakle moraju se poslati .flex i .cup fajlovi, svi izgenerisani i rukom pisani .java fajlovi koji čine rešenje i odgovarajući prevedeni .class fajlovi. U korenu rešenja treba da se nalaze i .jar arhive cup i flex alata.
3. U posebnom folderu test treba da se nalaze svi ulazni test fajlovi sa ekstenzijom .MJ, kao i odgovarajući izlazni fajlovi koji su rezultat testiranja, sa istim imenom kao ulazni fajl, ali sa ekstenzijom .out za standardni izlaz i .err za izlaz greške,. Uputstvo: Pri pokretanju programa standardni izlaz može se preusmeriti u fajl izlaz.out ako se na komandnoj liniji navede >izlaz.out, a izlaz greške se preusmerava sa 2>izlaz.err.
Pravila za izradu i odbranu domaćeg zadatka
1. Domaći zadaci se rade individualno i brane usmeno pre ispita u prvom ispitnom roku. Uspešna odbrana projekta je uslov za izlazak na ispit. Ukoliko se na odbrani utvrdi nedozvoljena saradnja između studenata prilikom izrade domaćeg, u moguće posledice osim gubitka poena spada i trajno dobijanje negativnih poena koji će biti uključeni u zbir za konačnu ocenu.
Odbrana se organizuje posle roka predaje domaćeg, prema naknadnom obaveštenju. Radovi se predaju preko specijalne veb forme. Zadaci ne mogu da se brane na sopstvenom računaru.
Po potrebi će ulazni test fajlovi biti pokretani na odbrani domaćeg.
2. Na odbrani će, pored samog rešenja, biti proveravano i poznavanje rada sa alatima jflex, CUP.
VAŽNO
Studenti su dužni da svoje zadatke testiraju na računarima u laboratoriji pre dana odbrane, ukoliko nisu potpuno sigurni da im je rešenje prenosivo na drugi računar. Na odbrani se zadatak isključivo brani. Nije dozvoljena nikakva dorada, osim ako to ne bude zahtevala osoba zadužena za ispitivanje na odbrani. Student ima maksimalno 20 minuta za odbranu zadatka.
13/15
5. Prilog
Prilog 1 – Transformisanje gramatike
2 U slučaju da je potrebno napisati smenu u kojoj se neki pojam ponavlja jednom ili više puta, odgovarajuća smena se može uraditi na sledeći način:
Parameter_list → Parameter_list Parameter | Parameter
Gde je Parameter_list neterminal koji opisuje jedno ili više pojavljivanja objekta Parameter, dok je Parameter objekat koji treba da se ponavlja jednom ili više puta.
2 U slučaju da se grupa različitih objektat pojavljuje jednom ili više puta može se koristiti sledeći oblik smene:
Parameter_list → Parameter_list Parameter_part | Parameter_part
Parameter_part → Parameter1 | Parameter2 | Parameter3 | ...
Gde su Parameter1, Parameter2, ... Tipovi objekata iz grupe koji se pojavljuju jednom ili više puta.
3 U slučaju da se neki objekat opciono pojavljuje u nekoj smeni smena se razdvaja na dve smene. Prvu koja ima traženi objekat i drugu koja ga ne sadrži. Primer takve smene je:
Funkcija → ImeFunkcije (Parameter_list ) | ImeFunkcije ( )
Druga varijanta je da se uvede prazna smena (za prazne smene u CUPu samo na mestu gde bi stajala desna strana smene napisati komentar /* epsilon */).
3 U slučaju da se neki objekat može ponavljati nula ili više puta u nekoj smeni koristi se kombinacija pravila iz tački 1. I 2.
Funkcija → Ime (Parameter_list ) | Ime ( )
Parameter_list → Parameter_list Parameter | Parameter
U prikazanoj smeni parametri funkcije se mogu pojaviti jednom ili više puta ali i ne moraju.
Druga varijanta bi bila:
Funkcija → Ime (Parameter_list )
Parameter_list → Parameter_list Parameter | /* epsilon */
Ova varijanta ima tu prednost da se ne multipliciraju smene za neterminal Funkcija.
14/15
Prilog 2 - Primeri izlaza
Ulazni program:
class P
const int size = 10;
int pos[];
{
void main()
int x, i;
char x;
{ //---------- Initialize val
x.i=1;
pos = new int[size];
i = 0;
while (i < size) {
pos[i] = 0;
i++;
}
//---------- Read values
read(x);
while (x >= 0) {
if (x < size) {
pos[x]++;
}
read(x);
}
}
}
Referentni izlaz kompajlera za navedeni program je dat u nastavku. Prijave grešaka (prikazano podebljano) treba da idu na standardni izlaz greške, ostalo na standarni izlaz.
Napomena: Primer je ilustrativnog karaktera namenjen prikazivanju akcija pretrage i obrade grešaka kao što je navedeno u postavci projekta.
==================SEMANTICKA OBRADA====================
Greska na 7: x vec deklarisano
Pretraga na 9(x), nadjeno Var x: int, 0, 1
Greska na 9(i) nije nadjeno
Pretraga na 10(pos), nadjeno Var pos: Arr of int, 0, 1
Pretraga na 10(size), nadjeno Con size: int, 10, 1
Pretraga na 11(i), nadjeno Var i: int, 0, 1
Pretraga na 12(i), nadjeno Var i: int, 0, 1
Pretraga na 12(size), nadjeno Con size: int, 10, 1
Pretraga na 13(pos), nadjeno Var pos: Arr of int, 0, 1
Pretraga na 13(i), nadjeno Var i: int, 0, 1
Pretraga na 14(i), nadjeno Var i: int, 0, 1
Pretraga na 17(x), nadjeno Var x: int, 0, 1
Pretraga na 18(x), nadjeno Var x: int, 0, 1
Pretraga na 19(x), nadjeno Var x: int, 0, 1
Pretraga na 19(size), nadjeno Con size: int, 10, 1
Pretraga na 20(pos), nadjeno Var pos: Arr of int, 0, 1
Pretraga na 20(x), nadjeno Var x: int, 0, 1
Pretraga na 22(x), nadjeno Var x: int, 0, 1
==================SINTAKSNA ANALIZA====================
1 classes
1 methods in the program
0 global variables
1 global constants
1 global arrays
3 local variables in main
13 statements in main
2 function calls in main
==============SADRZAJ TABELE SIMBOLA===================
(Level 0)
Type int: int, 0, 0
Type char: char, 0, 0
Con eol: char, 10, 0
Con null: Class, 0, 0
Meth chr: char, 0, 1 [Var i: int, 0, 1 ]
Meth ord: int, 0, 1 [Var ch: char, 0, 1 ]
Meth len: int, 0, 1 [Var arr: Arr of notype, 0, 1 ]
Prog P: notype, 0, 0
[Con size: int, 10, 1 ]
[Var pos: Arr of int, 0, 1 ]
[Meth main: notype, 0, 0 [Var x: int, 0, 1 ][Var i: int, 0, 1 ]]
15/15
6. Zapisnik revizija
Ovaj zapisnik sadrži spisak izmena i dopuna ovog dokumenta po verzijama.
Verzija 1.1
