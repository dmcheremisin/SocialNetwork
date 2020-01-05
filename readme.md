# Social Network Jsp and Servlets Project
Maven multi-module project that simulates social network of Game of Thrones creatures.
Admin user for any profile is Tyrion Lannister as the most smart character. 

Demo may be observer on Heroku by link: [Social Network](https://dcsocial.herokuapp.com).

Tyrion Lannister credentials for login are email: tyrion@adm.ru, password: aaa123.

Heroku disables application if it is not used for a long time(more than 2 hours or more). So it may
take some time for the application initialization and start. 

## Used technologies:
* Java 8 as backend language
* Maven 3.5.4 as build tool
* Servlets 
* Jsp as template engine
* Javascript, jQuery as client-side language/library
* Java API for WebSocket(JSR 356) for notifications
* Bootstrap 3.3.7 framework for UI
* CSS for markup
* Bootstrap-notify plugin for push notifications
* JUnit for unit testing
* Mockito for servlets testing
* Jacoco for checking test coverage
* Git as version control system

## Environment
* Tomcat 8 as web-server
* H2/MySql as data bases - both supported
* Heroku as cloud and deployment tool
* Intellij Idea as IDE

## Features
* Custom / Tomcat connection pool both supported
* DAO pattern for objects access
* Auth filter for security
* Jsp tags
* Bootstrap client-side validation without library
* Drag&Drop avatar image upload without library
* Pagination without library

## Functionality
* Multi-language portal(Ru, En)
* Data initialization on start-up.
* Registration
* Login/Logout
* Administrator user with facilities to block/unblock user or make him(her) admin
* Change user settings(avatar, first name, last name, date of birth,...)
* Messages
* Push notifications about recent messages
* User search by first name and last name 
* Users pagination
* Friends requests and friendship acceptance
* Friends search
* Error page and page for a blocked user

## About the project and its purpose
This project is based on JSP and Servlets technology stack. 
The purpose of the project was to master these core java technologies for web without any framework.

### Profiles
The project has 2 profiles for build: custom and tomcat, by default custom is used.
Depending on the chosen profile the application would use custom connection pool or tomcat connection pool. 

I wanted to train my jdbc skills and write my custom connection pool. Module custom.connection is used for
this. Is is activated by default or explicitly by choosing profile "custom". If choose this profile, than all stub data
will be initialized with all game of thrones characters.

It seemed to me a good idea to have some other module for tomcat connection pool testing. 
Module tomcat.connection is used for this reason, it may be activated by choosing profile "tomcat". 
It was supposed to be a connection that should represent production connection. 
That's why this module will initialize only data base schema and admin user.

### Security
An authorization filter is used for security of the web application. It checks user's session and performs different actions
depending on the data obtained.

For disabling possibility of session hijacking and man in the middle attacks some preferences were written in the web.xml file.
A block "\<session-config\>" contains two lines "\<http-only\>" and "\<secure\>", one of them "\<secure\>" blocks local development
because it requires https connection. It is the reason why it is commented by default, but it supposed to be uncommented in
production environment. Actually, Maven may pack different web.xml file depending on profile used, but I did not want to do this.


### TODO
A lot of things should be done to make this project ready for a real production including testing.
But my aim was to prototype social network project based on Servlets and Jsp technologies. 
I made here everything I wanted to use.

And....I have no time. 

+ move project to SPA(single page application)
+ expand test coverage
+ expand validation and error messages for not only index.jsp page, but also for other pages
+ make a new module for JSON objects requesting and responding. It is a good idea to write something like a custom lib for 
information exchange in JSON format using Jackson or Gson. But it will require to change all templates. 
Additionally, all js code should be updated for parsing and sending json objects.
+ add screenshots of the project and update readme.md
