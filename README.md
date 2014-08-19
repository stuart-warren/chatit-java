ChatIt
======

Websocket based multiroom-chat server using Nettosphere and Hazelcast.

Any servers on the same local network should cluster together and broadcast messages to the others.

Still lots to do. See issues.

Build It
=======

    mvn clean package

Run It
======

    java -jar target/chatit-complete-{version}.jar

Then go to http://localhost:8080 in a modern browser

