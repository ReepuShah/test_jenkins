set projectLocation=automationtests
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\src\test\java\com\framework\TestSuites\UI_Test.xml
mvn clean test