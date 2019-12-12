cd ~/workspace/jee-eclipse/delphi-server
rm -R src/main/webapp/WEB-INF/lib
rm -R src/main/webapp/WEB-INF/classes
mvn clean compile war:inplace package -Pqlt
mv target/delphi-server.war ~/workspace/
rm -R src/main/webapp/WEB-INF/lib
rm -R src/main/webapp/WEB-INF/classes

