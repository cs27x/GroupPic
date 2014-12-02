GroupPic
========

###Team Members
 * Andrew Bachman (Front-end)
 * Chris Reynolds (Front-end)
 * Ethan Dixius (Project Manager/Backend)
 * Jejo Koola (Backend)
 * Joe Navin (Backend)
 * Michael Schoenfield (Backend)
 * Taylor Beck (Front-end)

### User Stories

 1. As a user, I can send a picture to a group of people.
 2. As a user, a picture I send is only visible to the users that I have sent it to.
 3. As a user, I must be logged in to use the application
 4. As a user, I can take a picture from within the application and send it to a group of users.
 5. As a user, I can see all messages that have been sent to me with a preview of the image.
 7. As a user, I can remove a picture from the list of my received images.
 8. As a recipient of a picture, I can add a comment to a picture.
 9. As a user, I can view the comments of all the pictures that are visible to me.
 10. As a user, I can upvote/downvote comments
 11. As a user, I can create an account.

### Acceptance Criteria

 1. As a user, I can send a picture to a group of people.
       - I can send a picture to a list of people based on username.
       - The pictures I send are visible to all the users I indicate.
 2. As a user, a picture I send is only visible to the users that I have sent it to.
       - The pictures I send are not visible to any users I have not sent them to.
 3. As a user, I must be logged in to use the application
       - Users cannot access the application without logging in.
       - Users that have not logged in can only access the login page.
 4. As a user, I can take a picture from within the application and send it to a group of users.
       - I can access the camera from within the application.
       - I can send pictures I take from within the application.
 5. As a user, I can see all messages that have been sent to me with a preview of the image.
       - I can see all picture previews in a list form.
 6. As a user, I can remove a picture from the list of my received images.
       - If I delete an image, it no longer appears to my user.
 6. As a recipient of a picture, I can add a comment to a picture.
       - If I post a comment, it is visible to all users who have access to that image.
 8. As a user, I can view the comments of all the pictures that are visible to me.
       - I can see all comments that have been made on pictures that I can access.
 9. As a user, I can upvote/downvote comments
       - A user can only upvote a comment once.
       - Users can see the total vote count on a picture.
 11. As a user, I can create an account.
       - New users can create account information from the login screen.
       - Users can use accounts they create to sign in
       - New users are signed in after they create an account.

### Team Planning

#### Development Platforms

 * Android Studio for Android Development
 * Eclipse for Spring Backend development
 * Common Folder (Use Mobilecloud teplate)

#### Communication and Team Structure

 * Groupme for communication
 * Daily progress updates
 * Git
  * Individual Branches for each developer /{role(backend or frontend)}/{name}
  * Pull requests before merging into upstream
  * Developers will branch off of backend/frontend branch and will push to this branch before adding code to master
 * Meet once per week.

#### Testing
 * Developers should add tests for own code
 * Add tests before pushing upstream
 * One JUnit test class per Java Class with lots of individual
 * Add comments for methods include name

#### Risk
 * Unfamiliarity with technologies
 *

### API

<strong>GET</strong> /picture

 Parameters:
  * User : Current logged in user

 Response:
  * 201 if successful JSON body containing a list images the user has access to
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error

<strong>GET</strong> /picture/{_id}

Parameters:
  * User : currently logged in user
  * id : the requested image

Response:
  * 201 if successful JSON body containing a single image with the given id.
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error

<strong>GET</strong> /picture/{_id}/captions

Parameters:
  * User : currently logged in user
  * id : the requested image

Response:
  * 201 if successful JSON body containing a list of all the captions for the image
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error

<strong>POST</strong> /picture

Parameters:
  * User : currently logged in user
  * picture : the picture that will be sent
  * to_users : List of the photo will be sent to

Response:
  * 201 if successful JSON body containing an empty body
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error

<strong>POST</strong> /picture/caption

Parameters:
  * User : currently logged in user
  * id : the id of the photo the caption will be associated with

Response:
  * 201 if successful JSON body containing a list of all the images
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error
