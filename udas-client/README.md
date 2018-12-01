# Baza-Podataka-UDAS (UDAS database application)

## The idea

App that connects to and manages database for [UDAS Organization](https://en.wikipedia.org/wiki/The_Organization_of_Amputees_UDAS_Republike_Srpske) .

UDAS organization has around 4000 members, and there is need to provide easy way to organize their data.

Informations such as member name, surname, date of birth, injury type, invalidity kategory, employment, education etc. 
are currently written on papers which makes extracting any kind of information tedious and slow job.

Since MS Excel was not suitable for solving particular problems they have and I wanted to provide simple an unique interface
we decided to make application with UI. 

## Some implementation details

### Langauge - Java

I picked Java language as the one I feel most comfortable with and because of its portability, althought all the computers 
they are using are Windows OS.

For user interface I am using JavaFX, strictly using FXML files, to easily separate view from implementation.

### Database - MySQL

Simple and portable. 

*There is big possibility of switching to SQLite, since DB is simple, it won't hold big amount of data in computatinal sense
and it is self contained so it will me easier to maintain it on their side.

DB implementation is pretty simple. There is one "central" table member (clan) which is linked to few simple key-value tables 
that are containing data as city, province, injury type, invalidity category, work status etc. 

![ER Diagram](https://i.imgur.com/nNNRNdw.png "ER Diagram")

### How to build?

You will need Maven 3.5.3. To pack executable jar we are currently using javafx-maven-plugin (zen.java). 

cd /project/root, mvn compile exec:java

Jar will be generated in /target/jfx/app folder.

We are using google styleguide for code formatting. *I am thinking to customize it !

https://github.com/google/styleguide

## How does it look like now ?

Application is still in prototype phase and most of actions doesn't work at all or doesn't work as they should.
Also, GUI elements are behaving inconsistent and some of those are overleaping. Also, this probably is not final look,
as I said it is still in prototyping phase, also I will try to add localization support as soon as possible.

Login:

![Login](https://i.imgur.com/45ochmn.png "Login")

Navigation:

![Navigation](https://i.imgur.com/TxTYVuZ.png "Navigation")

Member management panel:

![Member Management](https://i.imgur.com/4ZTTNvI.png "Member management")

Add member (still needs translations):

![add member](https://i.imgur.com/6UZWRzC.png "Add member")
