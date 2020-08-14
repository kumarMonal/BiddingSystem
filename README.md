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
Used Tech Stack
Java 8,Spring Boot,Data Jpa,Spring Security,Junit,Mockito,GIT,Maven,SonarLint
->Decision on Tech Stack
Java 8
Good to choose portable and oop language
Many new features(Ex-stream,lamda) came with java 8.
Compatibility is good.

Spring Boot
Auto Configuration
Fast Build
Good flexibility for other framework like hibernate.

Data Jpa
It is providing effortless way to deal with Db.
Less code needed.

Spring Security
Nice Authentication & Authorization provided by Spring.
Multiple interface and class to use to reduce your effort

Junit
Automated test
Rich lib

Mockito
Nice for Mocking
Multiple methods gives a lot flexibility

GIT
Nice Version control
Easy handling

Maven
Easy to use
Nice build tool coming default with spring

SonarLint
Cool tool to check Code quality
Present eclipse market place
Compatible with Spring tool suite

Features implemented in this application
Concurrency          ->        ETag implemented
Pagination           ->        Pageable implemented and related stuff
version control      ->        GIT integrated
Build Tool           ->        Maven integerated

Future Scope
Integrate jenkins(skipped because i have no artifactory and env to deploy)




