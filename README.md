# heroes - mvnFanatics

### Installation Guide

### Additional Features

#### Fight Stats
Die Battle-Performance der einzelnen Heroes wird bewertet und im Anschluss an das Battle in Form einer Kampf-Statistik publiziert. Um dies zu ermöglichen, wird zunächst für jeden Helden ein HeroFightStats Object instanziert. Im Verlaufe des Kampfes sorgt der DefaultBattleService dafür, dass die Hero-spezifischen Statistiken laufend aktualisiert werden.

#### Vornamen-Vielfalt
Im Camp-Service unter util/NameList findet sich eine Liste mit über 1'000 Namen, welche als Quelle für die zufällige Generierung von Hero-Namen genutzt wird.

### Problems and Solutions

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

### Erkenntnisse
Die Probleme bei der Konfiguration des Equipment-Service sind fast schon symptomatisch für die Nachteile des Einsatzes von umfangreichen Frameworks. Der Entwickler konfiguriert nach bestem Wissen und Gewissen und verlässt sich darauf, dass es die Framework-Magic, welche "under the hood" stattfindet, schon irgendwie richten wird. Wenn dann eine Exception auftritt, ist diese oftmals derart generisch und komplex, dass man sie kaum nachvollziehen kann. Als Anwender eines Framework bewegt man sich oftmals auf einer derart hohen Abstraktionsebene, dass die Fehlersuche immer schwieriger wird.
Dennoch lohnt sich aus unserer Sicht der Einsatz von Frameworks. Die damit verbundenen Erleichterungen und zusätzlichen Möglichkeiten überwiegen die Nachteile bei weitem.
