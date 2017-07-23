# Better Twitter #

## Installation ##

1. install mysql database 

        database: better-twitter
        username: better
        password: twitter

1. run application from command line by:

        java -jar better-twitter-1.0-SNAPSHOT.jar
      
 
1. application by default run on port _4000_ 

## Endpoints ##

### Posting ###
#### Description ####
Purpose of this endpoint is to create post for specific user.

#### Example ####

    url: http://localhost:4000/rest/post/1
    body: {content:"test content text"}

### Wall ###
#### Description ####
This endpoint return list of user posts in JSON format in reverse chronological order.
If user doesn't exist application throw UserNotFoundException

#### Example ####

    http://localhost:4000/rest/wall/1

### Following ###
#### Description ####

This endpoint add user as a follower to given one

#### Example ####

    url: http://localhost:4000/rest/follow/1
    body: {following: 2}

### Timeline ###
#### Description ####
Purpose of this endpoint is to display all messages form following users, return as JSON

#### Example ####

    http://localhost:4000/rest/timeline/1

### User ###
#### Description ####
Additional endpoint to create user. Return created user id.

#### Example ####

    http://localhost:4000/rest/user


## SCENARIO OF USE ##

1. Add first user
  
        url: http://localhost:4000/rest/user
        body: {firstName:"Jan", lastName: "Kowalski"}
        response: 1
        
1. Add second user

        url: http://localhost:4000/rest/user
        body: {firstName:"Mirek", lastName: "Nowak"}
        response: 2

1. Add post to first user

        url: http://localhost:4000/rest/post/1  
        body: {content:"test content text"}  
        response: 200
   
1. Add another post for first user

        url: http://localhost:4000/rest/post/1  
        body: {content:"test content text"}  
        response: 200
        
1.  Get wall of the first user

        url: http://localhost:4000/rest/wall/1
        response: [{"id":2,"content":"test content text"},{"id":1,"content":"test content text"}]

1. Add post to second user

        url: http://localhost:4000/rest/post/2
        body: {content:"test content text"} 
        response: 200
        
1. Follow second user by first one

        url: http://localhost:4000/rest/follow/1
        body: {following: 2}
        response: 200
        
1. List all posts of users that first user fallow

        url: http://localhost:4000/rest/timeline/1
        response: [{"userId":2,"posts":[{"id":3,"content":"test content text"}]}]