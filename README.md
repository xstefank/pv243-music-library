# pv243-music-library
Semestral project for the PV243: JBoss - Advanced Java EE technologies - FI MUNI - spring 2017

## How to run
We use WildFly maven project to simplify application deployment

### One node configuration

#### Easiest way to run
``mvn`` in project main directory - following build steps are executed
* Clean and rebuild all modules
* Download and start WildFly
* Deploy application on WildFly server
You can stop server using CTRL+C

#### Development 
* Start server ``mvn wildfly:start``. 
* Rebuild application and deploy it using ``mvn clean install wildfly:deploy``
* Server is running and you can use previous command to redebuild and redeploy application after modifications.

### Cluster configuration
First, you have to start H2 database, run in project root directory``java -jar h2-1.4.195.jar -tcpAllowOthers -webAllowOthers``
Second, run ``mvn -Pcluster`` in project main directory - following build steps are executed
* Clean and rebuild all modules
* Download and start WildFly in managed domain
* Execute configuration script and create topology with one loadbalancer and two worker nodes
* Deploy application on WildFly domain

It leaves managed domain running and you can simply redeploy your app using ``mvn clean install wildfly:deploy -Pcluster``
Finally, you have to stop servers using ``mvn wildfly:shutdown``

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