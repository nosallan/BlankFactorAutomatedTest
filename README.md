# Blank Factor Automation test

This code is for the automation test for th application to Blank Factor. According to the request:
The assignment’s next step is to automate some parts of our website and send us the code as a .zip or .rar file.

1- Navigate to “http://blankfactor.com”
2- Open the “Blog” section
3- Scroll down to “Why Fintech in Latin America Is Having a Boom” and click the post by Sofia Gonzalez
4- Make a validation (to verify the script is on the correct page by verifying the URL and some of the text on the page)
5- Subscribe to the newsletter using the subscribe form.
6- Go back to the blog section and print a list of all post titles with related links.

## Pre-requisites

Have installed Google Chrome and Firefox.

We need to have installed the [jdk16](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html) and
the environment variable added.

```bash
#1: Download the JDK and unzip the folder (Ex: C:\Program Files\Java\jre1.8.0_331)
#2: On the Environment Variables of the system, create a new System Variable:
name: "JAVA_HOME"
value:  "C:\Program Files\Java\jre1.8.0_331"
#3: Search the system variable "Path", edit it and add "%JAVA_HOME%\bin"
```
OPTIONAL: If we like to execute the tests directly from the Batch files, we need to have the [mvn](https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.zip)
on as a environment variable. If you are going to execute the code directly on a IDE (for example IntelIJ)
normally the IDE's have the maven incorporated

```python
#1: Download the MVN and unzip the folder (Ex: C:\Program Files\apache-maven-3.6.1)
#2: On the Environment Variables of the system, create a new System Variable:
name: "MAVEN_HOME"
value:  "C:\Program Files\apache-maven-3.6.1"
#3: Search the system variable "Path", edit it and add "%MAVEN_HOME%\bin"
```

## Usage

```python
Execute the tests using the Batch files on the main folder, we have 4 different batch files:

# Chrome
Run the batch "executeChrome.bat"

# Chrome with headless mode
Run the batch "executeChromeHeadless.bat"

# Firefox
Run the batch "executeFirefox.bat"

# Firefox with headless mode
Run the batch "executeFirefoxHeadless.bat"

At the end of the execution we can find the report on the folder ExtentOutput with the respective date and hour.

```
## Recommendations
We recommend using headless mode because it does not open a window and the user can continue working with completely normally during execution

## Author
Allan Campos Herrera [CV](https://allancamposcv.com/)




