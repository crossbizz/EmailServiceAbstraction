# EmailServiceAbstraction
Note: This is the first time I coded a service in Java, My intention was not only finish this challenge but also learn from it. Hence, I decided to use Java even though it's not my strongest language i.e. C#
============

Manish Maheshwari, manish.neu@gmail.com, 06/11/2015


A service that accepts the necessary information and sends emails. It should provide an abstraction between two different email service providers. If one of the services goes down, your service can quickly failover to a different provider without affecting your customers.



##Technical track
Back-end

##Reasoning behind your technical choices
Java+Jersey+Maven: 

 1. Java-Easy to use, implement and integrate
 2. Jersey-Well documented and well adopted among a larger community
 3. Jersey is mainly devoted to RESTful services and pretty good for a JAX-RS framework
 4. Alternate Restful implementations could have been very well used but I have read about Jersey before and felt easier to implement
 5. Maven- It supports dependency management 


##Trade-offs you might have made, anything you left out, or what you might do differently if you were to spend additional time on the project

 - The service consumes main variables like "from, to, subject, html, text, there can be additional variables that can be set to provide more control on the emails.
 
**Left out**
----------
**Attachment support**  
-Support emails with attachments, videos, photos  
-wanted to quickly code the prototype for the abstraction and then add the attachments support, this should be trivial to add

**Security**  
-Support authentication for API consumption  
-CSRF prevention  
-wanted to quickly code the prototype for the abstraction, this can be http authentication and can be implemented using a auth filter at the routing level  

**Reporting(Out of scope)**  
-Reporting the emails sent  
-provide statistics over the usage of the email service  

##Link to your resume or public profile
Linkedin: https://www.linkedin.com/in/manishma

##How to use this service
The service has a RESTful API.

###Main API 
URL: 
https://emailabstraction.herokuapp.com/SendEmails/Email
method: POST

input: 
input format: json

| Key     |                Description            |
| --------|:-------------------------------------:|
| from    | string, sender email address          |
| to      | string, Recipient email address       |
| subject | string,the subject of the email       |
| text    | string,Plain text content of the email|
| html    | string,html email content of the email|

Following is a sample input json:
```
{
  "from": "test user<test@ub.com>",
  "to": "testrecipient@gmail.com",
  "subject":"test subject",
  "text": "Hello Email!!",
  "html": "Hello email"
}
```

output:
- message 

output format: json
 
Following is a sample output json for a successful transaction:
```
{'status': 0, 'message':'success'}
```

##Testing
-Tested the model
-Tested E2E, but a better way of testing needs to be done that can mock out the actual call to the email service providers

##Deployed on Heroku
https://emailabstraction.herokuapp.com/SendEmails/Email

