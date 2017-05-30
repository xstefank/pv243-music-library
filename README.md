# pv243-music-library
semestral project for the PV243 - FI MUNI - spring 2017

## How to run
### One node configuration
* ``mvn`` in project main directory - it rebuilds all modules starts wildly and run web app on WildFly server. Server is stopped on CTRL+c
* better option for development : First, start server ``mvn wildfly:start``. Then build application and deploy it using ``mvn clean install wildfly:deploy``  

### Cluster configuration
* Start H2 database ``TODO``
* ``mvn -Pcluster`` in project main directory - it rebuilds all modules starts wildly managed domain, configures it and deploys web app on WildFly server.
* It leaves managed domain running and you can simply redeploy your app using ``mvn clean install wildfly:redeploy``
* Finally, you have to stop servers using ``mvn wildfly:shutdown``


### Context roots
* web app context root 127.0.0.1:8080/music
* rest endpoints context root 127.0.0.1:8080/music/api
