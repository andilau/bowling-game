# Bowling-Game

Das Programm ermittelt aus der Punktzahl einzelner Würfe (Rolls)
beim [Bowling](https://de.wikipedia.org/wiki/Bowlingregeln) die Gesamtpunktzahl (Score) des Spiels. Die Ein- und Ausgabe
erfolgt über ein einfaches Text-Interface.

## Abhängigkeiten

- [Gradle](https://gradle.org) Build-Management-Tool
- [Junit 5](https://junit.org/junit5/) & [AssertJ](https://assertj.github.io/doc/) Test-Bibliotheken

## Installation

Das Repository klonen & im Projektverzeichnis den Build-Vorgang starten:

```shell
git clone https://github.com/andilau/bowling-game.git
cd bowling-game
./gradlew build
```

### Starten

Das Projekt nutzt die Gradle-Build-Umgebung. Es kann wie folgt gestartet werden:

```shell
./gradlew run
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