[![Build Status](https://travis-ci.org/fourbarman/job4j_design.svg?branch=master)](https://travis-ci.org/fourbarman/job4j_design)
[![codecov](https://codecov.io/gh/fourbarman/job4j_design/branch/master/graph/badge.svg)](https://codecov.io/gh/fourbarman/job4j_design)

# job4j_design
Repository for Java tasks.
This project is based on [JDK 14](https://www.oracle.com/ru/java/technologies/javase/jdk14-archive-downloads.html) and uses
[maven](https://maven.apache.org/).

Consists of 5 chapters, divided by themes:

### chapter_001
- collections
- generics
- iterators
### chapter_002
- io
- socket
- log4j
### chapter_003
- jdbc (PostgreSql)
- quartz
- jsoup
### chapter_004
- garbage collectors
- cache implementation on soft reference
### chapter_005
- SOLID

##Build
To build a project:
- You need to either open it in Intellij Idea IDE and open it as a maven project.
- Or You can build it from scratch. Go to project directory and run a command in terminal:
```
mvn package
```
Than in each chapter folder You will see 'target' folder with chapter_00X-4.0.jar file in it.

##TODO list
- [ ] Cover project code with unit tests.
- [ ] Add proper JavaDoc.
- [ ] Complete SOLID chapter.

##Contacts
Feel free for contacting me:
- Skype: login pankovmv.