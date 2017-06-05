# pv243-music-library
Semestral project for the PV243: JBoss - Advanced Java EE technologies - FI MUNI - spring 2017

## How to run
We use WildFly maven project to simplify application deployment

### First setup
You need to run ``mvn clean install -Pnpm`` to setup frontend technologies. This doesn't start application, just prepares frontend libraries.

### One node configuration

#### Easiest way to run
``mvn`` in project main directory - following build steps are executed
* Clean and rebuild all modules
* Download and start WildFly
* Configure security domain
* Deploy application on WildFly server

#### Manual
* Start WildFly - in Wildfly bin directory ``sh standalone.sh -c standalone-full.xml``
* Configure Wildfly - in Wildfly bin directory ``sh jboss-cli.sh -c --file=$MUSIC_LIB_HOME/config/securityDomain.cli``
* Build application - in MusicLib root directory ``mvn clean install``
* Deploy application - in Wildfly bin directory ``sh jboss-cli.sh -c --command="deploy $MUSIC_LIB_HOME/music-library-web/target/music-library-web-1.0-SNAPSHOT.war"``

### Cluster configuration
#### Easiest way to run
First, you have to start H2 database, run in project root directory``java -jar h2-1.4.195.jar -tcpAllowOthers -webAllowOthers``
Second, run ``mvn -Pcluster`` in project main directory - following build steps are executed
* Clean and rebuild all modules
* Download and start WildFly in managed domain
* Execute configuration script and create topology with one loadbalancer and two worker nodes
* Deploy application on WildFly domain

It leaves managed domain running and you can simply redeploy your app using ``mvn wildfly:undeploy install wildfly:deploy -Pcluster``
Finally, you have to stop servers using ``mvn wildfly:shutdown``

#### Manual
* Start database in project root directory``java -jar h2-1.4.195.jar -tcpAllowOthers -webAllowOthers``
* Start WildFly - in Wildfly bin directory ``sh domain.sh``
* Configure Wildfly - in Wildfly bin directory ``sh jboss-cli.sh -c --file=$MUSIC_LIB_HOME/config/domainModCluster.cli``
* Build application - in MusicLib root directory ``mvn clean install -Pcluster``
* Deploy application - in Wildfly bin directory ``sh jboss-cli.sh -c --command="deploy $MUSIC_LIB_HOME/music-library-web/target/music-library-web-1.0-SNAPSHOT.war --server-groups=musiclib-server-group"``

#### Cluster topology
There are three Wildfly servers runnning
* Loadbalancer available on ``localhost:8080/music`` is an access point for clients. It uses modcluster and forwards request to backend workers
* Workers available on ``localhost:8180/music`` and ``localhost:8280/music``. These servers are in cluster and both look to shared database. In case of failure of one server, client connected to loadbalancer fails over to another worker transparently

## Context roots
* web app context root 127.0.0.1:8080/music
* rest endpoints context root 127.0.0.1:8080/music/api

## Technologies
* Wildfly application server
* HTTP loadbalancing using Mod-Cluster
* WildFly maven plugin for simple usage
* Batching - obtain detailed artist biography from publicly available REST endpoints
* Rest API - all functionality is available via REST
* Rest client - call public REST API to get data from other applications
* WebSocket - recommend a song and get the latest recommendations from othres
* Infinispan - cahce for storing active WebSocket sessions
* JMS - message driven beans doing asynchronous work
* Angular - modern front end
* Bean Validation - ensure defined constrains holds
* JPA - persistence layer
* Hibernate Search and Apache Lucene - advanced fuzzy queries and indexation
* Logging - JBoss enterprise logging
* Security - keeps your data safe
* Arquillian - automated testing 