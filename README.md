# rock-paper-scissor
It's 2 player game, after sign in, each player should start a game and if there is an opponent game will be started, 
every game is 3 rounds but if one round ends up equal, another round will be added, after each game player rank will be updated.
## How it works?
Players talk to the server over WebSocket, which each of them had been subscribed to a game topic. Every game is created and kept in 
Memory after at least one player starts a game. Using H2 DB to persist player data, and spring security to handle player authentication,
In engine package logic of game has been developed. 
## Known issues and potential improvement
* I have not implemented sign up users, I just create 2 test player at startup.
* Improvement in UI/UX
* Improve login page
* add expiration time for each user selection
