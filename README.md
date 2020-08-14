/*
Author Monal Kumar
*/

=>BiddingSystem
In Scope
1. Fetch API for running auctions
2. Place the bid for running auctions
3. Only logged in user can place the bid

Out of scope
1. User Onboarding
2. Start and stop the auction
-----------------------------------------------------------------------------
=>Used Tech Stack
1>Java 8
->Good to choose portable and oop language
->Many new features(Ex-stream,lamda) came with java 8
->Compatibility is good

2>Spring Boot
->Auto Configuration
->Fast Build
->Good flexibility for other framework like hibernate

3>Data Jpa
->It is providing effortless way to deal with Db
->Less code needed

4>Spring Security
->Nice Authentication & Authorization provided by Spring
->Multiple interface and class to use to reduce your effort

5>Junit
->Automated test
->Rich lib

6>Mockito
->Nice for Mocking
->Multiple methods gives a lot flexibility

7>GIT
->Nice Version control
->Easy handling

8>Maven
->Easy to use
->Nice build tool coming default with spring

=>SonarLint
->Cool tool to check Code quality
->Present eclipse market place
->Compatible with Spring tool suite

=>Features implemented in this application
1>Concurrency          ->        ETag implemented
2>Pagination           ->        Pageable implemented and related stuff

Future Scope
Integrate jenkins(skipped because i have no artifactory and env to deploy)


=>How to set up env for this?
1>Pull code from Github.
2>Build it.
3>Run with "java -jar <jar name>"
4>Use get api call to list running auction "http://localhost:8080/auction?status=RUNNING&page=0&size=7" or "http://localhost:8080/auction?status=RUNNING".
5>Check item id in get api and use that to place a bid useing post call "http://localhost:8080/auction/<itemid>/bid".  





