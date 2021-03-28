# Prequests
## P=python + request (for Java) = Prequest

Dont you like how simple it is to use the request library in Python?  

The main advantages of Python's request library is  
A. Simple and easy to use  
B. Interact with Jsons using Dictionary. 

Here is a similar library in Java (jar will be soon available via MavenCentral) which attempts to do exactly the same after Author got bored writing long code just for doing a GET/POST request. 

The library allows you to interact with json as Map<String,Object> or with array of json as List<Map<String,Object>>
All responses are processed as List<Map<String,Object>> to convininently support response of JSON/Array of JSON just like requests library. 

**Please find usage samples in the test-cases.** 

**How to use?**   
A. Copy classes directly to your project.  
B. Build a jar. A pre-compiled jdk 1.8+ jar will be given.  
C. Future via MavenCentral.  

**Requirements:-**  
This code depends on Java HTTPClient and GSON library.  
GSON is used here as many codebases hevily rely on GSON instead of Jackson JSON.  
Future versions will come with ability to use desired JSON parser.  
