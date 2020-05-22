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
Example of generated code is in target/entity-generated folder.  
Example of DB schema source, hibernate properties and reverse engineering 
customizer is in src/main/resources db_init.sql, hibernate.properties and 
hibernate.reveng.xml respectively.  
  
Documentation about hibernate customizer xml file can be seen [here](https://www.codejava.net/frameworks/hibernate/how-to-customize-hibernate-reverse-engineering-code-generation).
  
  
Command Line
------------
  
    java -cp ./target/lib/*:./target/hibernate-tools-jpa-entity-generator-1.0-SNAPSHOT.jar \
        id.dys.tools.jpaentitygenerator.Main \
        -o (output folder) \
        -p (hibernate.properties file path) \
        -r (hibernate reverse engineering customizer xml path) \
        -P (package for generated sources) \
        -s (fully qualified class name of custom strategy)
  
The given package for generated sources can be changed from custom strategy and
customizer xml. Hibernate uses both customizer xml and custom strategy class when
reverse engineering.  
Add the database driver jar to target/lib directory. If custom strategy is used,
add the jar of the custom strategy to target/lib as well.  
  
Disclaimer
----------
  
The generated code is simply vanilla output of Hibernate Tools. It only serves
as base template for further development and is most likely unsuitable for production as is.  
  
  