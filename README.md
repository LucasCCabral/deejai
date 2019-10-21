# deejai
This project consists of an android app that is capable of generating playlists for groups. Each playlist belongs to a "room" and to enter a room you must enter the rooms ID or select a room from the list given by the app. Room's are listed from closest to farthest. 

# Users 
This app can be used by any group of people that want to share and discover music through group interaction. Be it a barbecue, a party, dinner at grandmas... It doesn't matter, deejai will deliver the best playlist for your group!


# Features
[] Accessing the spotify API.
[] Sharing the room ID.
[] Acessing a room by it's ID.
[] Show participants of a room.
[] Generate playlist based on User tastes.
[] Listing closest rooms.
[] Open generated Spotify playlist.

# How to use it
The app can be summed up to 4 different views.
1. Splash, which verifies wether the user has an access toke to the spotify API. In case he does, the user will be redirected to the list of avaiable rooms. Otherwise, the user will be redirected to the log-in view.
2. Spotifty login, the user will be prompted to give the app permissions to access the spotify API of the user account. If this succeedes  the user will be redirected to a list of the avaiable rooms.
3. In the rooms view, the user can: look for a room by it's ID, enter a room displayed by the recycler view (ordered from closest to farthest of the user location) and creating a room.
4. Inside the room. After the user has entered a room, he can see all the other users in the same room as well as the link to the spotify playlist and what music is being played now.

# Work Division
The app itself will be developed by both group members. Since the app uses spotify, Victor Niceas, is responsible to design the access routes for the spotify api and Lucas Cabral will be responsible for the recommendation.
