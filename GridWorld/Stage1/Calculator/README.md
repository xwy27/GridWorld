# Calculator

## Usage

1. Ensure java and ant are set up
1. Input the command ```ant``` at the root folder to compile
	
1. use java command to run the calcutor

## Description

Originally, there is just one src foler. After ant compile, it will turns to below statu.

1. Folder Structure
	+ Calcultor
		+ build.xml
		+ sonar-project.properties
		+ class
			+ *.class
		+ dist
			+ calculator.jar
		+ lib
			+ *.jar
		+ src
			+ *.java

1. Files and Folders Function
	+ **build.xml** for ant to auto compile
	+ **sonar-project.properties** for sonar to test the coding style
	+ **class** contains all the compiled *.class* file
	+ **dist** contains the project jar to be used
	+ **lib** contains the libary jar
	+ **src** contains all the java code file

