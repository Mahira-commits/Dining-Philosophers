# Dining-Philosophers

This code implements the dining philosophers’ program. When it is at a default stage, the philosophers are represented by blue and the forks by black dots. When the philosophers are eating, they change to their corresponding color which might be (RED, ORANGE, CYAN, GREEN, PINK) along with their available forks.
	The code is divided into three classes according to MVC and a driver class called Main. The Model class includes the black dot’s class and it’s used to create black dot objects which serve as forks for the philosophers. The View class is in charge of showing the output  which is the dialog box and the frame. The dialog box allows the user to choose the number of philosophers between 2 to 5 and then the frame is shown which displays the buttons and the chosen number of philosophers along with the forks which are the black dots. The Controller class is in charge of creating threads and objects (forks) for the chosen number of philosophers and assigns the forks (black dots) to each philosopher and it holds the action listeners for the JButtons which are start, pause, reset and quit.

**HOW IT WORKS**

**Explanation of JButtons used in the frame:**
When Start Button is pressed:
Pause Button: Enabled
Reset Button: Disabled 
Quit Button: Enabled
Start Button: Disabled

**When Pause Button is pressed:**
Pause Button: Disabled
Reset Button: Enabled
Quit Button: Enabled
Start Button: Enabled

**When Reset Button is pressed:**
Pause Button: Disabled
Reset Button: Disabled
Quit Button: Enabled
Start Button: Enabled

**When Quit Button is pressed:**
The program exits.

**Further Explanation:**
When the start button is pressed, the pause button is enabled but the reset button is not enabled and the quit button is enabled. When pause button is pressed then the reset button becomes available. The reset button only becomes avaiable when pause is pressed. When start button is pressed, the reset button shouldn’t appear because there would be bugs in the program.

![image](https://github.com/Mahira-commits/Dining-Philosophers/assets/78906881/232ff377-bf37-496e-8d24-833453115a62)


![image](https://github.com/Mahira-commits/Dining-Philosophers/assets/78906881/03863594-87ac-4392-baf3-4bcd51b27433)
![image](https://github.com/Mahira-commits/Dining-Philosophers/assets/78906881/41930cad-8882-4e3b-811b-dc7acb054d18)
![image](https://github.com/Mahira-commits/Dining-Philosophers/assets/78906881/ea84c89b-3453-4a2b-b3bc-fe448001974d)
