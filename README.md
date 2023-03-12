# Postcode App

## Project Brief

This project aims to showcase my abilities in developing a backend Java Spring service and a frontend in ReactJS, with a particular focus on implementing an authentication process for multiple levels of users with different access levels.

### MVP

- Create an API in Java that allows mobile clients to retrieve and add suburb and postcode combinations. You do not have to write the mobile app!

Required to implement:

- An API that allows mobile clients to retrieve the suburb information by postcode.
- An API that allows mobile clients to retrieve a postcode given a suburb name
- A secured API to add new suburb and postcode combinations (you'll have to work out how this should work)
- Some form of persistence (a database)
- Testing for controller / service layers

Design Considerations

Best Practice

- Develop with current best practice
- Design patterns, software architecture, secure coding
- Libraries, industry standards

Reusability

- Is there anything that can be reused in future projects?

Support knowledge sharing

- Documentation
- Comments
- Anything can help with knowledge sharing with the technical team

Development Process and Quality Control

- Testing

## Technical Implementation

This app allows users to search for suburbs by postcode or suburb name, and an editor can register a new suburb. The frontend was built in React JS using axios for the https request, and the Rest API is built in Spring Boot.

This is an initial version of the app where I have implemented Basic Auth with fixed 2 users: an Editor who has full access including POST requests to register a new suburb, and a Viewer who can only search and view the suburb list. Since the user credentials are fixed and hardcoded, it is not a secure way of implementing authentication. However, I plan to log my development journey from here to implementing JWT Token, making users' credentials dynamic, and thus making the login authentication process more secure and reliable.

## Future Goals

- Implement JWT Token in Spring to make the authentication process more secure and reliable.
- Refactor the authorization and login process logic once JWT Token is implemented so that hardcoded credentials can be removed.
- Write logic for error and edge cases with regards to http requests.
- Improve the UI and add more customized UI responses depending on the errors/edge cases.
