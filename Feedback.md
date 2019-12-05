# Design/Code Review 3

## Project: Yu-Gi-Oh Card Application

### Developer: Lee Yang

#### Reviewer: Dave Queiser

|Category|Criteria|Rating/Comments|
|--------|---------|---------------|
|**Project Overview**|| |
||Which planned functionality has been met? | Much of the project is in place and functioning. The ability to upload a file of cards was in place but not quite complete; I would say that functionality was at least 50% complete. A service to reach to a card website for information was also in place but needed a little tweaking. We also discussed a potential hook into eBay, but Lee indicated that was future scope and not part of the 12/18 project implementation. Overall I was very impressed with what I saw and how well it all worked. Very smart-looking interface.|
|| What planned functionality has not been met? |See above|
||Describe the GitHub history and what it demonstrates about the project progress during the semester.| I am seeing 152 Git commits, which is far above the number of commits on my own project. Embarrasingly so. To me that shows consistent work that has been done during the whole semester. The Reflections.md document shows a large gap of inactivity between Week 4 and Week 12; I don't believe that to be totally accurate. Based on my own experience, I suspect some work was done and the log was simply not updated during those weeks.|
||Describe how peer and instructor feedback/recommendations were incorporated into the project.|A couple of minor suggestions from the first code review (with Joe) were not implemented; they weren't necessarily regarding functionality. The next code review (with Julie) had no suggestions to implement. The Instructor Feedback from week 10 had two bullets with suggestions; all of those have been addressed. The code review that I did last night using SonarLint found 175 issues in 33 files, but not all were marked as urgent.|
||Other comments/notes? |Overall I think it's a pretty tidy little application, well coded and mostly working. Should be a good demo on 12/18.|
|**JSPs**|| |
||Evaluate the JSPs for templating, business logic, data validation, overall look and feel. Overall I think it's a pretty tidy little application, well coded and mostly working. Should be a good demo on 12/18.|Simply put the JSPs look like they are textbook quality. Very well written, easy to understand. I've learned a thing or two by looking at them, particularly when it comes to data validation. |
||Other comments/notes?|Good use of Bootstrap in the JSPs.|
|**Java code quality**|Evaluate the code quality for the following and identify specific areas for improvement (class, method or line number) <li>single-purpose methods <li>well-structured project <li>descriptive naming of packages, classes, methods, variables <li>classes appropriately-sized  (no monster classes) <li> CPD (copy paste detection, meaning are the same lines of code repeated?) <li>are there candidates for super/subclass relationships, abstract classes, interfaces? <li>are any values hard-coded that should be in a properties file? <li>proper exception handling <li>proper error reporting to the user <li> code documentation ||Other comments/notes?| | 
|**Logging**|Evaluate the use of logging, for example:<li>appropriate use of logging statements in the code for error logging and debugging <li>logging levels used - info, debug, error <li> no occurrences of  System.out.printlns or printStackTrace() <li> logging works on AWS deploy||Other comments/notes?| |
|**Unit Tests**|Evaluate the unit tests, for example: <li>tests are truly a unit test rather than a high level functional test <li>test data is appropriately cleaned up or handled <li> there is full coverage of methods that perform business logic or utility functions <li>redundant code is eliminated by using set up and tear down methods, i.e., @Before, @After
||Other comments/notes?| |
|**Security**|Evaluate authentication/authorization:| |
||Is it implemented correctly and working locally as well as on AWS?
||Other comments/notes?| |
|**Web Service/API integration**|Evaluate the service/api integration, for example: <li> Which service/api is implemented? <li>How is  error handling of the service implemented? For example, what happens if the service is not available?| |
||Other comments/notes?| |
|**Independent research topic**| What is the independent research topic?| |
||Is the independent research topic/technique implemented in the project?| |
||Other comments/notes?| |
|**Deployment**| Has the application been successfully deployed to AWS?| |
||Is the hosted application fully functioning?| |
||Other comments/notes?| |
**
|**Dave's comments**:

  Code is condensed and neat. I am seeing well-named classes and methods which perform functions well. No large classes or methods. I would recommend adding a few more comments, and a little more logging. Logging is being used, just not very often.
We did not look at the code running on AWS, so I can't vouch for it logging there. There is logging throughout the code, particularly in the servlets. I can see adding a little more throughout the project.
Test cases are very well-written, very in-depth testing is done. All CRUD actions are being tested for all entities. More test cases are present when there are one-to-many relationships in place (or vice versa).
We did not look at the application on AWS (but to be fair, Lee's VM croaked as we were starting our review). Authentication  was complete and worked very well locally.
Two services are in place, a file reader used to upload files into the application, and a TCG Player service which retrieves data from a website. Both are coded correctly and partially functioning. TRY sections are in place, and error-handling looks functional.
N/A - Lee did not utilize an independent research topic
With the issues with Lee's VM, we were not able to review the app on AWS. 
There is still some work to be completed, as Lee indicated to me, but I felt it was at least 80% completed.   
  



