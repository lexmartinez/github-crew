# github-crew  [![Build Status](https://travis-ci.org/lexmartinez/github-crew.svg?branch=master)](https://travis-ci.org/lexmartinez/github-crew)

Github People Advisor [BACK-END]:Find interesting and known people profiles on Github.
Born as [Hystrix](https://github.com/Netflix/Hystrix) example, `github-crew` uses Github API on searching interesting
Github profiles for you starting on people that you follow.

With [Hystrix](https://github.com/Netflix/Hystrix) the advisor always return some Github profile using three diferent commands:
+ Github API Query Command.
+ MongoDB Storage: Some popular Github profiles on the web stored into local MongoDB
+ Dev Outsider Team: Static data with github-crew develop team profiles

#####Project Keywords: `Hystrix` `Github API` `Spring` `MongoDB` `Log4J` `Spring-Data` 


## Service Endpoint

+ getPeople
```java
    GET: github-crew/api/advisor/{username}
```
> You can add optionally request parameter `max` to specify max number of profiles that you wanna get

+ HystrixStream
```java
    GET: github-crew/hystrix.stream
```