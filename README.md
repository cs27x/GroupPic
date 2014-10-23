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

 2. As a user, I can send a picture to a group of friends
 3. As a user, a picture I send is only visible to the friends that I have sent it to.
 4. As a user, I must be logged in to use the application
 4. As a user, I can take a picture from within the application and send it to a group of friends.
 5. As a user, I can see all messages that have been sent to me in thumbnails
 6. As a user, I can filter/search messages to a group containing a given person.
 7. As a user, I can remove a picture from the list of my received images.
 8. As a recipient of a picture, I can add a comment to a picture.
 9. As a user, I can view the comments of all the pictures that are visible to me.
 10. As a user, I can upvote/downvote comments
 10. As a sender of an image, I can see past favorite groups/users.


### Other Potential User stories
1. As a user, I can create an account.
2. As a user, I can only send pictures to my friends
3. As a user, I can add friends.
4. As a user, I can see a list of my friends.
5. As a user, I can add and remove friends.
6. As a user, I can see a list of people who have added me as a friend,
7. As a user, I can accept outstanding friend requests.

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

<strong>GET</strong> /photo

 Parameters:
  * User : Current logged in user

 Response:
  * 201 if successful JSON body containing a list images the user has access to
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error

<strong>GET</strong> /photo/{_id}

Parameters:
  * User : currently logged in user
  * id : the requested image

Response:
  * 201 if successful JSON body containing a list of all the images
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error

<strong>GET</strong> /photo/{_id}/captions

Parameters:
  * User : currently logged in user
  * id : the requested image

Response:
  * 201 if successful JSON body containing a list of all the captions for the image
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error

<strong>POST</strong> /photo

Parameters:
  * User : currently logged in user
  * to_users : List of the photo will be sent to

Response:
  * 201 if successful JSON body containing an empty body
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error

<strong>POST</strong> /photo/caption

Parameters:
  * User : currently logged in user
  * photo : the id of the photo the caption will be associated with

Response:
  * 201 if successful JSON body containing a list of all the images
  * 400 if there is an error in the request or request JSON
  * 500 if there is a server error
