# Spring Boot - React Exercise: 

This exercise includes 2 parts. A back end part, and a front end part

#Backend
Your Backend is a very simple RESTful, JSON API to power a note-taking application.

The API expose CRUD calls to perform note related operations 

The notes API lives at the route /api/notes, port 80 . So, when running on localhost, the user can access the 'notes' API at http://localhost/api/notes .

The note model is simple:
```
{
"id" : 1,
"body" : "Remind me to SpringBoot."
}
```

## Setup 
The easiest way to test this is to:
### Clone this project

`git clone https://github.com/efossi/springboot-react.git`

### Cd into the backend source folder an run
```
cd springboot-react/backend/api/
./mvnw spring-boot:run
```

### Try querying the note api
```
curl  -H "Content-Type: application/json" -X POST -d '{"body" : "Remember me why I need taking notes..."}' http://localhost/api/notes
```

### To run the tests available, 
Build the package
```
./mvnw package
```

##Examples
###Create a note

```
curl -i -H "Content-Type: application/json" -X POST -d '{"body" : "Go have fun!!!"}' http://localhost/api/notes
```


```
curl -i -H "Content-Type: application/json" -X POST -d '{"body" : "Go have some fun!!!"}' http://localhost/api/notes
```

###Get all notes

This endpoint supports pagination and sorting through the params: ```offset```, ```sort```, ```order```, ```max``` with the following default values: offset=0, sort="id", order="asc", max=10

The endpoint also supports the query string parameter ```query```. It can be used to retrieve the notes that match a certain pattern:

Some examples

```
curl -i -H "Content-Type: application/json" -X GET http://localhost/api/notes
```

```
curl -i -H "Content-Type: application/json" -X GET 'http://localhost/api/notes?offset=10&max=25&sort=body'
```

```
curl -i -H "Content-Type: application/json" -X GET 'http://localhost/api/notes?offset=10&max=25&sort=body&query=fun'
```

###Update a note
curl -i -H "Content-Type: application/json" -X PUT -d '{"body" : "Go have as much fun as you can!!!"}' http://localhost/api/notes/1

###Delete a note
curl  -H "Content-Type: application/json" -X DELETE http://localhost/api/notes/2

#Fronend

The Frontend is a React application that integrates with the Github REST API to search for issues in the Angular Github repo for the
previous days (7 days be default).

The results from the API are displayed, in HTML, the returned values with their title, body, user login, and assignee login.

##Setup

### Make sure you have a proper dependencies installed

This project was tested using Node: v9.4.0 NPM: 5.6.0

If your build fails, please use NVM(https://github.com/nvm-sh/nvm) to install the version above and retry

```
nvm install 9.4.0

```

### Cd into the frontkend source folder an run
```
cd springboot-react/frontend/git-search/
```

```
npm install && npm start
```
### To run the tests  available, 
Build the package
```
npm test
```

