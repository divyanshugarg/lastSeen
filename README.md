# lastSeen
A java program that accepts a DateTime as a command line param and returns the output Last seen # %unit% ago

## Problem Statement
Most of you must have used a messaging app like WhatsApp. In these apps, you can see the last seen time of a given person. Typically the logic to calculate the last seen time is as follows:

 |   Last Activity Time  | Reported as             |
 |:---------------------:|-------------------------|
 |  Greater than 1 Year  | Last seen # years ago   |
 | > 1 Month & < 1 Year  | Last seen # months ago  |
 |  > 1 Day & < 1 Month  | Last seen # days ago    |
 | > 1 Hour & < 1 Day    | Last seen # hours ago   |
 | > 1 Minute & < 1 Hour | Last seen # minutes ago |

 Please write a program in your choice of language that accepts a DateTime object or similar, and returns the output as described in the above table.
 
 ## Project Build/Compile Requirements
 
 1. JDK > 1.11 version .
 2. Gradle.
 
 ## Build Instructions
 
 Run the following command -
 ```sh
 ./gradlew clean build
 ```
 
 ## Running the project
 
 JRE need to present on the machine.
 JAVA_HOME environment variable setup which is pointing to the file system location where the JDK or JRE was installed.
 From the project directory, run below command - 
 
 ### Interactive command-line mode: 
 ```java
    $ java -jar build/libs/lastSeen-1.0.jar
 ```

 ## Assumptions
 1.  We need to return minimum time at-least from when we didn't see any one.
 2.  Assuming Less than 1 minute means 'online' anf returning the same.
 3. Assuming user'device can be any part of the world. Client/app have to send the timeframe along with timezone. 
 4. Assuming this code logic/snippet is a part of program running on client/app side.  
 5. The string must represent a valid date-time and is parsed using java.time.format.DateTimeFormatter#ISO_ZONED_DATE_TIME. it is format that extends the ISO-8601. E.g. '2011-12-03T10:15:30+01:00[Europe/Paris]'.
 6. Can accept data as per Min and Max value defined by Java Data.Time class.
 7. Any data time provided in future will be consider as invalid. 
