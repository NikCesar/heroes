# heroes - mvnFanatics

### Installation Guide

### New Parts

### Problems and Solutions

#### Konfiguration von Equipment Service
Die Konfiguration des Equipment Service bereitete uns einige Schwierigkeiten. Wir verwendeten zunächst die Dependency 'spring-cloud-openfeign-core', mussten jedoch feststellen, dass letztere nicht dieselben Funktionalitäten bietet wie 'spring-cloud-starter-openfeign'. Zu dieser Einsicht zu gelangen war nicht ganz einfach, da beim Ausführen des Service mit der "falschen" Dependency keine aussagekräftige Exception geworfen wurde. Der fehlgeschlagene Aufruf des Camp-Service mittels Feign triggerte jeweils die von uns definierte Fallback-Methode, was für die Lokalisierung der Fehlerursache wenig hilfreich war. Uns war zunächst nicht klar, ob der Aufruf selber fehlerhaft war oder aber die Rückmeldung des Camp-Service.

Die Problemlösung brachte schliesslich ein Vergleich unseres Service mit einem Service der Musterlösung. Zusätzlich zum Austausch der Dependency war das Anbringen der @EnableFeignClient-Annotation erforderlich. Ansonsten hätte unser Service die entsprechende Funktionalität nicht nutzen können.

### Erkenntnisse
Die Probleme bei der Konfiguration des Equipment-Service sind fast schon symptomatisch für die Nachteile des Einsatzes von umfangreichen Frameworks. Der Entwickler konfiguriert nach bestem Wissen und Gewissen und verlässt sich darauf, dass es die Framework-Magic, welche "under the hood" stattfindet, schon irgendwie richten wird. Wenn dann eine Exception auftritt, ist diese oftmals derart generisch und komplex, dass man sie kaum nachvollziehen kann. Als Anwender eines Framework bewegt man sich oftmals auf einer derart hohen Abstraktionsebene, dass die Fehlersuche immer schwieriger wird.
Dennoch lohnt sich aus unserer Sicht der Einsatz von Frameworks. Die damit verbundenen Erleichterungen und zusätzlichen Möglichkeiten überwiegen die Nachteile bei weitem.
