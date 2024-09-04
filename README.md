# Data Structures & Algorithms
This project relies on two primary sources consisting of _Data Structures & Algorithms in Java_ by Goodrich, Tamassia, and Goldwasser, and _Data Structures and Algorithm Analysis in Java_ by Weiss. I'm starting by breaking down content by chapter, naming them after the book authors. E.g. content from chapter three of the Weiss book is labeled as a class W03.java. 

## Project Cheat Codes

### Build and run a JAR
To create and run a JAR from terminal:
1) Navigate to the project root (where the POM.xml is)
```
$ cd /MyPrograms/dsa-java
```
2) Ensure all dependencies are updated with Maven
```
~/MyPrograms/dsa-java $ mvn clean package
```
3) Compile and run the JAR
```
~/MyPrograms/dsa-java $ java -cp target/dsa-java-0.1.0-jar-with-dependencies.jar smorgasbord.Main
```

### Build and run with Maven
This does something similar to the previous workflow but doesn't create a JAR. It also requires an extra plugin for the POM.xml file. 
```
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <configuration>
    <mainClass>smorgasbord.Main</mainClass>
  </configuration>
</plugin>
```
1) Navigate to the project root (where the POM.xml is)
```
$ cd /MyPrograms/dsa-java
```
2) Run the Maven command that removes the target dir to start "clean", compiles the source code, executes a `exec-maven-plugin` goal `exec:java`, and then runs the "main" method from the class specified in the plugin configuration.
```
~/MyPrograms/dsa-java $ mvn clean compile exec:java -Prun
```
