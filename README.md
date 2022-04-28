# Bowling-Game

Das Programm ermittelt aus der Punktzahl einzelner Würfe (Rolls)
beim [Bowling](https://de.wikipedia.org/wiki/Bowlingregeln) die Gesamtpunktzahl (Score) des Spiels. Die Ein- und Ausgabe erfolgt über ein einfaches Text-Interface.

## Abhängigkeiten

- [Gradle](https://gradle.org) Build-Management-Tool
- [Junit 5](https://junit.org/junit5/), [AssertJ](https://assertj.github.io/doc/), [Mockito-Kotlin](https://github.com/mockito/mockito-kotlin) Test-Bibliotheken

## Installation

Das Repository klonen & im Projektverzeichnis den Build-Vorgang starten:

```shell
git clone https://github.com/andilau/bowling-game.git
cd bowling-game
./gradlew build
```

### Starten

Das Programm wird durch Ausführen des Java-Archivs gestartet,
```shell
java -jar build/libs/bowling-game-1.0-SNAPSHOT-standalone.jar 
```

Oder durch Starten des Tasks `:run` in der Gradle-Build-Umgebung:
```shell
./gradlew :run -q --console=plain
```

### Testen

- Navigiere ins Projekt-Hauptverzeichnis
- Starte `./gradlew test`
- Füge `--info`, `--debug` oder `--stacktrace` für detaillierter Ausgabe hinzu

## Referenzen

- [Wikipedia: Ten-pin bowling](https://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring)
- [Bowling Score Calculator](https://bowlinggenius.com/#)

## Autor

- Andreas Lau