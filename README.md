
# wordcloud

Wordcloud using JSoup, Java and OOP, Advanced OO project assignment for college.

# RUNNABLE JAR IN THE DIST FOLDER

# About

This project was created as part of a fourth year project to demontrate OO principals and design patterns. The entire project was coded using Java.

# What does the project do?

The goal of the assignment was to create a jar file that you could pass in a wordfile or url with a list of words for a wordcloud. You could also parse in a blacklist file to keep common words out of the word cloud.

# How to run

* Get a copy of the runnable jar.
* Navigate to it using the command line.  
* You must have your Java enviroment variable set up correctly for the following commands to work.

The runnable jar requires 3 arguments

```<ui> <blacklistfilelocationstring> <wordfilelocationstring> ``` 

You can choose between ```gui``` or ```cmd``` to have either a command line or gui interface for the ```<ui>``` field.

For ```<wordfilelocationstring>``` you can pass in a valid url or a file location on the desktop, otherwise a default internal file is used to create the list of wordcloud files.

Finally for the ```<blacklistfilelocationstring>``` parameter you can either pass a file location for blacklist word file or stopwords file. If you don't pass a file in an internval file will be used to create the blacklist of commonwords that won't make it to the word cloud.

Examples

```cmd stopwords.txt  warandpeace.txt ```

```gui stopwords.txt http://www.independent.ie/```

If you have no files to pass in you can pass dummy arguments

```cmd 0 0```

Below is an example for running the whole program

```java -jar WordCloud.jar cmd C:Users\User\Desktop\stopfiles.txt http://www.independent.ie/```

# Future Changes

I hope to clean up the file importing so you dont have to compile the source to a runnable jar to run it.

# Libaries Used

* commons-validator-1.5.0
* jsoup-1.8.3
* junit-4.10
