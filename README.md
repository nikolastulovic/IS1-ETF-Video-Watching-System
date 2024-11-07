# IS1-ETF-Video-Watching-System
The system consists of a user application, a central server, and three subsystems. The user application should enable the handling of all user requests. It creates REST requests, sends them to the central server, and returns the results of these requests. The central server processes REST requests and forwards them to the subsystems using JMS. The subsystems communicate exclusively via JMS.

The client application is a Java program (Java SE) that receives requests from users through the console or a graphical interface, creates REST requests, and sends them to the central server.

The central server does not store any data itself but serves as a link between the client application and other subsystems.

Subsystem 1 stores data about locations, users, and their connections in its database.

Subsystem 2 stores data about categories, videos, users, and their connections in its database.

Subsystem 3 stores data about packages, subscriptions, views, ratings, users, videos, and their connections in its database.
