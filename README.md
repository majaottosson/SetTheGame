# SET

Under sommaren 2025, efter mitt första år på datateknik på LTH, ville jag testa att skapa något eget.
Jag och mina kompisar brukar ibland spela kortspelet SET, vilket gav mig inspirationen att implementera en digital version av spelet. 
Jag gjorde dock detta spel innan jag läste kurser som omd på lth, alltså innan jag fick lära mig om olika design principles...

## Spelregler

Spelet går ut på att hitta SET, det vill säga grupper med tre kort som följer reglerna nedan.

Varje kort har fyra egenskaper:
- Färg: röd, grön eller blå
- Form: romb, rektangel eller oval
- Ifyllnad: ifylld, randig eller tom
- Antal: 1, 2 eller 3 symboler

För att tre kort ska bilda ett set måste varje egenskap vara antingen:

1. Likadan på alla tre korten
   eller
3. Olika på alla tre korten

Detta gäller för var och en av de fyra egenskaperna, vissa egenskaper kan vara lika, medan andra är olika.

### Exempel 1

Ett giltigt set:
- Kort 1: 1 röd ifylld romb
- Kort 2: 2 röda ifyllda rektanglar
- Kort 3: 3 röda ifyllda ovaler

Detta är ett set eftersom alla kort har samma färg och ifyllnad, medan form och antal skiljer sig åt på samtliga kort.

### Exempel 2

Ett giltigt set:
- Kort 1: 1 röd ifylld romb
- Kort 2: 2 gröna randiga ovaler
- Kort 3: 3 blå tomma rektanglar

Detta är också ett set eftersom alla fyra egenskaper är olika på de tre korten.

### Exempel 3 

Inte ett set:
- Kort 1: 1 röd ifylld romb
- Kort 2: 2 röda randiga ovaler
- Kort 3: 3 röda randiga rektanglar

Detta är inte ett set eftersom två kort har samma ifyllnad (randig), medan det tredje har en annan (ifylld). 
För att bilda ett set måste ifyllnaden vara antingen likadan eller olika på alla tre korten.




