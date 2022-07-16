# picocli reference

In this repo are illustrated some harder-to-remember [picocli][1] ([site][2]) features.

## completion

`./gradlew :completion:shadowJar`

`cd completion`

`alias main='java -jar ./build/libs/completion-1.0-SNAPSHOT-all.jar`

`main autocomplete > main.autocomplete && . ./main.autocomplete`

[1]: https://mvnrepository.com/artifact/info.picocli/picocli
[2]: https://picocli.info/
