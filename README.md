JPA Java Entity Generator
=========================
  
Just a thin wrapper around Hibernate Tools to generate java entity class from
database using command line. I wrote this tool because there are very few examples on how to reverse
engineer database to generate entity code automatically outside of using maven
or via IDE.  
  
  
License
-------
  
Apache License 2.0  
  
  
Build
-----
  
set JAVA_HOME to installed JDK. Tested with jdk8  
Execute `mvn package`.  
  
Required libraries will be copied to target/lib folder  
Sample of generated code is in target/entity-generated folder.  
Sample of DB schema source, hibernate properties and reverse engineering customizer is in src/main/resources.  
  
Documentation about hibernate customizer xml file can be seen [here](https://www.codejava.net/frameworks/hibernate/how-to-customize-hibernate-reverse-engineering-code-generation).
  
  
Command Line
------------
  
    java -cp ./target/lib/*;./target/drivers/*;./target/hibernate-tools-jpa-entity-generator-1.0-SNAPSHOT.jar \
        id.dys.tools.jpaentitygenerator.Main \
        -o (output folder) \
        -p (hibernate.properties file path) \
        -r (hibernate reverse engineering customizer xml path) \
        -P (package for generated sources) \
        -s (fully qualified class name of custom strategy)
  
  
Disclaimer
----------
  
The generated code is simply vanilla output of Hibernate Tools. It only serves
as base template for further development and is most likely unsuitable for production as is.  
  
  