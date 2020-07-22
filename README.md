# exercise1-contact-api
Contact API v0.0.1

Progress:
base source code = done
UT = ongoing
CI/CD = ongoing

For the supported REST functionalities, please refer to SupportedRestOperations.pdf.
For the Database Relationship diagrams, please refer to TableDiagram.pdf.

Prerequisites:
1. JDK 1.8 or later
2. Maven 3.2+
3. PostgreSQL 12+ installed (if on another machine, please make sure port 5432 is allowed by the firewall)
4. pgadmin4 web UI for Postgre 12 (optional)
5. Apache Tomcat 9+ installed (if on another machine, please make sure port 8080 is allowed by the firewall)

How to install:
1. Copy the schema.sql from the /src/main/resources/ to the PostgreSQL machine.
2. Apply the schema.sql to the Postgre DB. Make sure there is no existing database called "contactinfodb".
   If there is, feel free to open schema.sql and edit "contactinfodb".
   (Windows command)
   > psql.exe -U <username> -f  schema.sql
   or
   (Linux command)
   $ psql -U <username> -f  schema.sql
3. Edit the application.properties file inside /src/main/resources/ and change the following fields according to your PostgreSQL settings.
     spring.datasource.url=jdbc:postgresql://<IP address or hostname>:<PostgreSQL port (default is 5432)>/contactinfodb
     spring.datasource.username=<PostgreSQL username>
     spring.datasource.password=<PostgreSQL password>
3. From the application's root repository, run the following command:
   $ mvn clean package
   This will create a WAR package called contact_api.war

4. Copy the contact_api.war file inside the webapps/ folder inside the Tomcat directory.

5. Restart Tomcat.

6. The application should start after a few seconds.