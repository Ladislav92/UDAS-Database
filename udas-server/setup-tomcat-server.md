## Setup Tomcat server 8.5.35

### Step 1. - Add tomcat user
Open `tomcat-users.xml` file located in `TOMCAT_HOME/conf/`
Add new user eg.
```<user username="__USERNAME__" password="__PASSWORD__" roles="manager-script"/>```
into `<tomcat-users>` tag.

### Step 2. - Add server

There are two locations where a settings.xml file may live:
The Maven install: `${maven.home}/conf/settings.xml`
A user’s install: `${user.home}/.m2/settings.xml`

Open `settings.xml` file and add new server eg.
```
<server>
  <id>tomcat-udas-server</id>
  <username>__USERNAME__</username>
  <password>__PASSWORD__</password>
</server>
```
ANNT: add this `<server>` tag into `settings > servers`

Username and password have to be the same on both files.
`tomcat-udas-server` is name that I choose, and you can change in `udas-server/pom.xml`

### How to deploy udas-server project
Start tomcat server
If you are not on http://127.0.0.1:8080 please change host:port in `udas-server/pom.xml` in `tomcat7-maven-plugin` plugin.

Then run:
`mvn tomcat7:deploy`

Are are also
`mvn tomcat7:undeploy` and `mvn tomcat7:redeploy`, this last one is useful while you are working on code.