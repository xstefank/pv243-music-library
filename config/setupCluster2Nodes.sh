#!/bin/sh

WF_HOME=$1

WF_HOME1=./server1
WF_HOME2=./server2

echo "Removing server directories..."
rm -rf $WF_HOME1
rm -rf $WF_HOME2

echo "Creating server directories..."
cp $WF_HOME $WF_HOME1 -r 
cp $WF_HOME $WF_HOME2 -r

echo "Starting H2 Database..."
gnome-terminal -e "java -jar ../h2-1.4.195.jar -webAllowOthers -tcpAllowOthers"

echo "Starting servers..."
gnome-terminal -e "$WF_HOME1/bin/standalone.sh -c standalone-full-ha.xml -Djboss.node.name=node1"
gnome-terminal -e "$WF_HOME2/bin/standalone.sh -c standalone-full-ha.xml -Djboss.node.name=node2 -Djboss.socket.binding.port-offset=100"

echo "Waiting for servers to start..."
sleep 20

echo "Configuring datasource..."
$WF_HOME1/bin/jboss-cli.sh --file=./setupDataSource.cli
sleep 3
$WF_HOME2/bin/jboss-cli.sh --file=./setupDataSource.cli --controller=localhost:10090

echo "Deploying application..."
$WF_HOME1/bin/jboss-cli.sh --file=./deployApp.cli
$WF_HOME2/bin/jboss-cli.sh --file=./deployApp.cli --controller=localhost:10090




