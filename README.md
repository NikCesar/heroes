# heroes - mvnFanatics

# installation guide

# new parts

# problems
Beim Aufsetzen des neuen Equipment Service trafen wir auf einzelne konfigurative Probleme.
Zuerst hatten wir die Dependency 'spring-cloud-openfeign-core' verwendet. 
Diese liefert aber nicht dieselben Funktionalitäten wie die korrekte dependency 'spring-cloud-starter-openfeign'.
Grundsätzlich warf die Ausführung des Service mit der falschen Dependency aber keine offensichtlichen Exceptions.
Der Aufruf des camp Service mit Feign war nicht erfolgreich, wir fielen einfach immer gleich in den FallbackService Code hinein, konnten aber nicht erkennen welcher Teil den Fehler auslöst. Ob wir die Anfrage falsch machen, oder ob die 
Rückmeldung des camp Service fehlerhaft ist. 
Nachdem wir unsere Lösung mit der Musterlösung für einen neuen Service verglichen,
konnten wir unseren Fehler entdecken und korrigieren.
Auf der EquipmentApplication Klasse im Equipment Service mussten wir auch noch die @EnableFeignClients Annotation ergänzen, da sonst unser Service diese Funktionalität nicht nutzen kann.
An diesem Beispiel merkten wir die Gefahr von grossen Frameworks. 
Man verlässt sich auf das Framework und implementiert es nach bestem Wissen und Gewissen. Wenn etwas fehlschlägt, 
hofft man dass die Frameworkentwickler zu jedem Fehler eine brauchbare Exception zurücksenden. 
Allerdings ist es oft so, dass man eine generische Exception erhält und selber forschen muss wo der Fehler liegt, 
was je nach Komplexität der abstrahierten Aufgabe sehr schwierig sein kann.
Unserer Meinung nach lohnt es sich aber dennoch solche Frameworks zu nutzen. Der Mehrwert aus deren Verwendung 
überwiegt die Probleme, auf welche man von Zeit zu Zeit stösst.
