# heroes - mvnFanatics

# installation guide

# new parts

# problems
Beim Aufsetzen des neuen Equipment Service trafen wir auf einzelne konfigurative Probleme.
Zuerst hatten wir die Dependency 'spring-cloud-openfeign-core' verwendet. 
Diese liefert aber nicht dieselben Funktionalitäten wie die korrekte dependency 'spring-cloud-starter-openfeign'
Grundsätzlich warf die Ausführung des Service mit der falschen Dependency aber keine Exceptions, abgesehen davon,
dass der Aufruf des weiteren Service über Feign nicht erfolgreich war. 
Nachdem wir unsere Lösung mit der Musterlösung verglichen, konnten wir unseren Fehler entdecken und austauschen.
Auf der EquipmentApplication Klasse im Equipment Service mussten wir auch noch die @EnableFeignClients Annotation ergänzen,
da sonst unser Service diese Funktionalität nicht nutzen kann.
