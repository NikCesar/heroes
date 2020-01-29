# heroes - mvnFanatics

## Installation Guide
1.) Alle 6 Services starten:
- Registry-Service
- Frontend-Service
- Arena-Service
- Camp-Service
- Equipment-Service
- Promoter-Service

2.) Sicherheitshalber ca. 3 Minuten warten (Teilweise dauert es etwas länger bis sich alle Services korrekt registriert haben)

3a.) In den Browser wechseln und http://localhost:8080/ aufrufen

3b.) Falls KEINE Liste mit Heroes erscheint, bitte ein paar Minuten warten und anschliessend im Browser "Refresh" drücken. Dadurch erhält die Applikation noch etwas Zeit, um sämtliche Services korrekt zu registrieren.

4.) Auf der rechten Bildschirmhälfte die gewünschten Heroes mittels Klick auf den '+'-Button zur eigenen Party hinzufügen

5.) Bei Bedarf die zusammengstellte Party mittels "Save Party" für eine spätere Verwendung speichern (Party wird beim nächsten Aufstarten automatisch angezeigt)

6.) In der Top-Nav auf "Arena" klicken, dort "Fight in the Arena" Knopf drücken, und schon werden die Resultate ausgegeben!


## Additional Features

#### Fight Stats
Die Battle-Performance der einzelnen Heroes wird bewertet und im Anschluss an das Battle in Form einer Kampf-Statistik publiziert. Um dies zu ermöglichen, wird zunächst für jeden Helden ein HeroFightStats Object instanziert. Im Verlaufe des Kampfes sorgt der DefaultBattleService dafür, dass die Hero-spezifischen Statistiken laufend aktualisiert werden.

#### Vornamen-Vielfalt
Im Camp-Service unter util/NameList findet sich eine Liste mit über 1'000 Namen, welche als Quelle für die zufällige Generierung von Hero-Namen genutzt wird.

#### Additional Hero Attributes
Um die Helden etwas interessanter zu gestalten, haben wir uns entschieden die Helden mit weiteren Attributen auszustatten:
- Initiative: Bestimmt die Reihenfolge in welcher die Helden angreifen; der Held mit dem höchsten Initiative-Wert greift zuerst an.
- Dodge Chance: Bestimmt die Chance mit welcher ein Held einer Attacke ausweichen kann und somit keinen Schaden erhält.
- Crit Chance: Bestimmt die Chance mit welcher der Angriff eines Helden kritisch trifft und somit den Schaden verdoppelt.

#### Hero Type
Wir unterscheiden 4 verschiedene Typen von Heroes:
- Warrior
- Rogue
- Mage
- Ranger

Der Hero-Type hat einen Einfluss auf die Ausprägungen der Hero-Attribute. Wie die nachfolgende Tabelle zeigt, ist der Range in dem der tatsächliche Attribut-Wert eines Hero zu liegen kommt, abhängig von der Art des Attributs sowie vom Type des Hero.

| Stats | Warrior   | Rogue     | Mage        | Ranger    |
| ----- | --------- | --------- | ----------- | --------- |
| ATK   | 15 - 20   | 25 - 35   | 10 - 20     | 35 - 40   |
| DEF   | 13 - 18   | 0 - 5     | 5 - 10      | 3 - 8     |
| HP    | 150 - 200 | 50 - 80   | 80 - 120    | 30 - 50   |
| INIT  | 1 - 5     | 7 - 12    | 1 - 20      | 5 - 15    |
| CRIT  | 0.1 - 0.2 | 0.3 - 0.4 | 0.3 - 0.7   | 0 - 0.2   |
| DODGE | 0.1 - 0.2 | 0.5 - 0.7 | 0.1 - 0.3   | 0.3 - 0.5 |

#### Revision of the combat logic
Die gesamte Kampflogik wurde umgeschrieben. Man kämpft immer mit einer Party bestehend aus 4 Helden. Die Angriffsreihenfolge wird durch den Initiative-Wert der einzelnen Helden bestimmt. Es ist also möglich, dass mehrere Helden aus derselben Party nacheinander angreifen können, bevor ein Held der gegnerischen Party die Gelegenheit dazu erhält. Der Kampf findet über mehrere Runden statt. Eine Runde gilt als abgeschlossen wenn alle kampffähigen Helden einmal angegriffen haben.
Der Angriff eines Helden richtet sich grundsätzlich auf jenen Helden der gegnerischen Party, der an vorderster Position steht (Index 0 in der Liste). Ein abweichendes Verhalten zeigen ausschliesslich Helden vom Typ 'Rogue'. Letztere greifen immer das hinterste Mitglied der gegnerischen Party an. Während der Angriffsphase bestimmt die Engine nach dem Zufallsprinzip, ob der angegriffene Held dem Angriff ausweicht (dodge chance), oder der angreifende Held den Gegner kritisch trifft (crit chance). Falls die HP eines Helden unter 0 fallen, gilt dieser als besiegt und scheidet aus dem Kampf aus.

#### Equipment
Helden können mit drei verschiedenen Arten von Ausrüstung ausgestattet werden. Letztere haben einen positiven Einfluss auf spezifische Helden-Attribute. Pro Attribut-Typ kann eine Ausrüstung nur einmal angewendet werden, z.B. 1x Armor, 1x Weapon, 1x Mount und NICHT 2x Armor. Nachfolgend die Ausrüstungs-Arten und ihre Auswirkung auf die Helden-Attribute:
- Armor: Erhöht DEF und DODGE
- Weapon: Erhöht ATK und CRIT
- Mount: Erhöht HP und INIT

Zusätzlich verfügen die oben genannten Ausrüstungen über einen Seltenheitsgrad. Je seltener die Ausrüstungs-Art, desto höher die Attribut-Boni. Folgende Seltenheitsgrade gibt es:
- Common
- Rare
- Epic
- Legendary

#### GUI
Für die komfortablere Bedienung der Applikation haben wir ein simples aber zweckmässiges GUI kreiert. Es erlaubt die Zusammenstellung einer eigenen Hero-Party, welche anschliessend gegen eine Computer-generierte Hero-Party kämpfen wird. Die gewünschten Helden kann man bequem mittels Mausklick einer Party hinzuzufügen und bei Bedarf wieder entfernen. Ausserdem ist es möglich die selbst kreierte Party in der Datenbank zu persistieren. Beim nächsten Aufstarten wird diese dann automatisch angezeigt. Das Ausstatten der Helden mit Ausrüstung konnte aus Zeitgründen leider nicht mehr ins GUI integriert werden.

## Problems and Solutions

#### Hateoas
Die Implementierung von Hateoas Funktionalität war von Beginn weg eine Herausforderung. Nach einer mässig erfolgreichen Phase des Ausprobierens, beschlossen wir uns von der Musterlösung auf Github inspirieren zu lassen. Leider mussten wir feststellen, dass der dortige Code im Rahmen unserer eigenen Konfiguration ebenfalls nicht lauffähig war.

Unsere Nachforschungen im Internet haben ergeben, dass das Release von Spring HATEOAS 1.0 einige gewichtige Neuerungen mit sich brachte. U.a. heisst «ResourceSupport» nun «RepresentationModel» und die Namen einiger zentraler Methoden haben ebenfalls geändert. Details dazu gibt es unter: https://docs.spring.io/spring-hateoas/docs/current/reference/html/

#### "Could not autowire"
Intellij markiert im DefaultPromoterService die beiden Properties «campClient» und «arenaClient» rot. Wenn man mit Cursor darüber fährt, erscheint folgende Fehlermeldung:
Could not autowire. There is more than one bean of 'CampClient' type. Beans: camp-service   (CampClient.java), fallbackCampClient  (FallbackCampClient.java).

Um die Fehlermeldung zu eliminieren, haben wir in der Mainklasse des Promotor-Microservices bei @EnableFeignClients den Pfad zu den beiden Feign-Clients angegeben: @EnableFeignClients(basePackages = {"ch.bfh.swos.promoter.service.impl"}).
Zu einem späteren Zeitpunkt stellte sich heraus, dass der Promoter-Service offenbar genau wegen dieser «basePackages» Ergänzung den Camp-Controller nicht aufrufen konnte und stets ins Fallback fiel. «campClient» und «arenaClient» sind nun weiterhin rot markiert, was aber offenbar keinerlei Einfluss hat auf das Funktionieren der Applikation.

#### Konfiguration von Equipment Service
Die Konfiguration des Equipment Service bereitete uns einige Schwierigkeiten. Wir verwendeten zunächst die Dependency 'spring-cloud-openfeign-core', mussten jedoch feststellen, dass letztere nicht dieselben Funktionalitäten bietet wie 'spring-cloud-starter-openfeign'. Zu dieser Einsicht zu gelangen war nicht ganz einfach, da beim Ausführen des Service mit der "falschen" Dependency keine aussagekräftige Exception geworfen wurde. Der fehlgeschlagene Aufruf des Camp-Service mittels Feign triggerte jeweils die von uns definierte Fallback-Methode, was für die Lokalisierung der Fehlerursache wenig hilfreich war. Uns war zunächst nicht klar, ob der Aufruf selber fehlerhaft war oder aber die Rückmeldung des Camp-Service.

Die Problemlösung brachte schliesslich ein Vergleich unseres Service mit einem Service der Musterlösung. Zusätzlich zum Austausch der Dependency war das Anbringen der @EnableFeignClient-Annotation erforderlich. Ansonsten hätte unser Service die entsprechende Funktionalität nicht nutzen können.


## Erkenntnisse
Die Probleme bei der Konfiguration des Equipment-Service sind fast schon symptomatisch für die Nachteile des Einsatzes von umfangreichen Frameworks. Der Entwickler konfiguriert nach bestem Wissen und Gewissen und verlässt sich darauf, dass es die Framework-Magic, welche "under the hood" stattfindet, schon irgendwie richten wird. Wenn dann eine Exception auftritt, ist diese oftmals derart generisch und komplex, dass man sie kaum nachvollziehen kann. Als Anwender eines Framework bewegt man sich oftmals auf einer derart hohen Abstraktionsebene, dass die Fehlersuche immer schwieriger wird.
Dennoch lohnt sich aus unserer Sicht der Einsatz von Frameworks. Die damit verbundenen Erleichterungen und zusätzlichen Möglichkeiten überwiegen die Nachteile bei weitem.
