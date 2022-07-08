# Kill Doctor Lucky Java GUI game

This repo represents the coursework for CS 5010, the Spring 2022 Edition!

**Name:** Komal Shah, Jun Wang

**Email:** shah.koma@northeastern.edu, wang.jun6@northeastern.edu

**Preferred Name:** Komal, June
![image](https://user-images.githubusercontent.com/77699526/168950703-c55d5938-3702-4dbc-901a-997d843da8c6.png)


### About/Overview

The world of our project consists of a number of non-overlapping spaces that are laid out on a 2D grid. The details of the world is specified in a simple text file that consists of three sections:

1. world description including the size, the name, the target character, and the target character's pet.
2. a detailed list of all of the spaces or rooms that make up the world
3. a detailed list of all of the items that can be found in the world

The program as specified in the problem description reads the details of the world from a text file and creates a model corresponding to that. The program uses the Readable interface to read the world specification and hence can read data from multiple sources like file or a string. The program internally uses a Map representation to model the layout of the World. Using Map makes the operations like getting neighbours extremely fast. Lastly, the program outputs the World in the form of an Image of the World layout. The image is provided in the form of BufferedImage.

This game has two types of players: Human and Computer Players. Each player should be identified by their name. They enter the world in a space of their choice. With multiple players, each player gets a turn in the order in which they were added to the game. The options for actions that player can take in a single turn are:

1. Moving to a neighboring space.
2. Picking up an item from the space they are currently occupying.
3. Look around the space they are currently occupying.
4. Moving Target Pet to a space.
5. Attempt to kill a Target using the Items they have.

### List of Features

The program provides the following features:

1. Display information about a specified space on the view.
2. Create a graphical representation of the world map and provide the ability to save the graphical representation to a file as a PNG file.
3. Allow users to restart game or start game with new game settings.
4. Add a human-controlled player to the game.
5. Add a computer-controlled player to the game.
6. Allow a player to Move. This represents a turn.
7. Allow a player to pick up an item. This represents a turn.
8. Allow a player to look around. This represents a turn.
9. Allow a player to move Target's pet to a different space. This represents a turn.
10. Allow a player to make an attempt to kill Target. This represents a turn.
11. Display a description of a specific player.
12. Game ends when:
        - the maximum number of turns is reached in which case the target character escapes and runs away to live another day and nobody wins.
        - a player successfully kills the target character in which case they win the game.
13. Automatically move the Target character around the world after every turn.
14. Automatically move the Target character's pet around the world after every turn using Depth First Search.
15. Hints are displayed before every move.
![image](https://user-images.githubusercontent.com/77699526/168951499-de986c60-ba23-4b92-a999-70eec9cf2cb3.png)


### How to Run

Command to run the program with GUI: `java -jar mansion-milestone4.jar netflixMansion.txt 5 10 GUI`

Command to run the program within Console: `java -jar mansion-milestone4.jar netflixMansion.txt 5 10 TEXT`

where,  
`mansion-milestone4.jar` - Name of the jar file.
`netflixMansion.txt` - Name of the file with World Specification.
`5` - Number of turns for the game.
`10` - Number of players allowed in the game.
`GUI`/`TEXT` - Display mode.

### How to Use the Program

#### TEXT Mode

Upon program launch, the program creates a world object based on the world specification file. Then the user is presented with a menu of 10 items:

1. Display Information of a Room - On using this option, the progam asks for the room name for which details are to be displayed.
2. Create Graphical Representation for the World/Mansion - No further input needed.
3. Add a Human Player - On using this option, the progam asks for the player name, player location and player capacity to add human player to the game.
4. Add a Computer Player - On using this option, the progam asks for the player name, player location and player capacity to add computer player to the game.
5. Play Game - On using this option, following sub menu is prompted:

        BACK - Return to Main Menu - No further input needed.
        MOVE - Move to a neighboring Room - On using this option, the progam asks for the neighboring room name to which the player wants to move.
        PICK - Pick an Item from the Room - On using this option, the progam asks for the item name to pick from the room.
        LOOK - Look around the Room - No further input needed.
        MOVEPET - Move target's pet to a Room - On using this option, the progam asks for the room name to which the player wants the target's pet to move.
        KILL - Make an attempt to kill Target - On using this option, the program asks for the item name using which the player wants to attack the Target.

6. Display Information of a Player - On using this option, the progam asks for the player name for which details are to be displayed.
7. Determine if Player A can see Player B - On using this option, the program asks for the name of Player A and Player B to determine if Player A can see Player B.
8. Restart Game - On using this option, the program asks for confirmation and restarts the game.
9. New Game - On using this option, the program asks for confirmation and creates new game by taking in file path for new configuration file, number of turns and maximum number of players.
10. Quit - No further input needed.

#### GUI Mode

Upon program launch, the program creates a world object based on the world specification file. Then the user is presented with welcome screen with 2 main menus:

**Game Menu**

1.1 New Game - Allows user to create a new game with new world configuration:

        Game setting example:
        res/netflixMansion.txt - Name of the file with World Specification.
        5 - Number of turns for the game.
        10 - Maximum number of players allowed in the game.

1.2 Restart Game - Allows user to start the game with the previous settings.

1.3 Add Player - Allows user to add a player to the game.

        Player setting example: 
        Player Name:`Frank` 
        Start Room:`Library` 
        Capacity of Player's Bag: -1 for no limit of the capacity
                                   5 capacity is 5 items
        Type of Player: computer Player

1.4 Quit Game - Allows user to quit the game.

**Help Menu**

1.1 About Game - Displays a brief introduction of the functions and features of the game.

1.2 How to Play Game - Displays information on how to play the game.

Game Player Hot Keys:

1. Press l  display information of neighbors.
2. Press k  player can attempt an attack with one item.
3. Press p  player can pick up an item from the room.

Mouse Click:

1. Click on a player's icon displays player's description.
2. Click on target's icon displays target's information.
3. Click on a neighbor room, the current player moves to that room.
4. Right Click on a room, the target pet moves to that room. 

### Example Runs

NA

### Design/Model Changes

1. v4.0:

        - Refactored the Model and Controller to suite the MVC Pattern.
        - Added Action Listener classes.
        - Added GUI View.

2. v4.1:

        - Refactored the Model and Controller to suite the Command Pattern.

3. v4.2:

        - Refactored View and Controller methods.
        - Added methods in Model to facilitate GUI.

### Assumptions

1. Input world specification should be in correct format.
2. Name of Rooms should be unique.
3. Target will wrap around once it moves through all the rooms.
4. There can be 0 damage items in a room.
5. Name of Players should be unique.
6. Name of Items should be unique.
7. Each input while playing the game needs to be sepeated by a new line character.
8. Each input is case sensitive.
9. Details of room do not display Target Details.
10. Player can move the pet to any room in the mansion.
11. Player can choose to poke even when it has items.
12. Player’s attempt to kill is a turn even if it is unsuccessful.
13. If pet is in a room, player cannot move into that room.
14. You can see all the details of the neighbouring rooms while looking around.
15. If Target and Player are not in the same room, we do not show an option to kill.
16. At the beginning of choosing the action, player will know if the Target is in the same room as them.
17. If Player A can see Player B, that does not mean Player B can see Player A.
18. Player can move pet even when they are not in the same room.

### Limitations

NA

### Citations

NA
