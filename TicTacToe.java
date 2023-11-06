import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener{
	JFrame myFrame = new JFrame("Mega Tic Tac Toe");
    JPanel panel = new JPanel();
    JButton[] btn = new JButton[25];
    int turn = 0;//0==o's turn, 1==x's turn
    String [][] board = new String[5][5];//2D array of O, X
    int row;//row of board
    int col;//col of board
    int avalibleSpots = 25; //total avalible spots on the board
    public TicTacToe()
    {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1000,1000);//sets frame size to 1000x1000
        panel.setLayout(new GridLayout(5,5));//adds GridLayout of 5x5
        for(int i=0;i<25;i++) //loops through all 25 positions and adds a button each time
        {
            btn[i] = new JButton();
            btn[i].setPreferredSize(new Dimension(200, 200));//button size to 200x200
            btn[i].setBackground(Color.white);//sets color to white
            panel.add(btn[i]);//adds btn to panel
            btn[i].addActionListener(this);
        }
        myFrame.add(panel); //adds panel to myFrame
        myFrame.setVisible(true);
        for(int i = 0; i<5; i++)//initialize board with " "
        {
            for (int j = 0; j<5; j++)
            {
                board[i][j]=" ";
            }
        }
    }

    public void actionPerformed(ActionEvent button)
    {
        for(int i = 0; i<25; i++)//loops through every buttons, btn 
        {
            if(button.getSource() == btn[i] && turn == 0)//button were pressed and O's turn
            {
                if(!btn[i].getModel().isPressed())
                {
                    btn[i].setText("O");//set button text "O"
                    btn[i].setBackground(Color.green);//set background to green
                    btn[i].setFont(new Font("Arial", Font.PLAIN, 65));// set font and size
                    turn = 1;//switch to X's turn
                    btn[i].setEnabled(false);//makes the button unclickable after it's been clicked once
                    row = i/5;
                    col = i%5;
                    board[row][col] = "O";//adding String "O" to the 2D array board
                    avalibleSpots--; // decrease avalible spots by 1
                    if(checkWin())//check win condition
                    {
                        btnWinAction();
                    }
                    else if(checkDraw())// if checkWin() equals false, check if the game is draw
                    {
                        btnDrawAction();
                    }
                }
            }
            else if (button.getSource() == btn[i] && turn == 1)
            {
                if(!btn[i].getModel().isPressed())//button were pressed and X's turn
                {
                    btn[i].setText("X");//set button text "X"
                    btn[i].setBackground(Color.red);//set background to red
                    btn[i].setFont(new Font("Arial", Font.PLAIN, 65));// set font and size
                    turn = 0;//switch to O's turn
                    btn[i].setEnabled(false);//makes the button unclickable after it's been clicked once
                    row = i/5;
                    col = i%5;
                    board[row][col] = "X";//adding text "X" to the 2D array board
                    avalibleSpots--; // decrease avalible spots by 1
                    if(checkWin())//check win condition
                    {
                        btnWinAction();
                    }
                    else if(checkDraw())// if checkWin() equals false, check if the game is draw
                    {
                        btnDrawAction();
                    }
                }
            }
        }
    }

    public boolean checkWin()//checkWin() method contains every possible win condition
    {
        if(checkHorizontal())
        {
            return true;
        }
        if(checkVertical())
        {
            return true;
        }
        if(checkDiagonal())
        {
            return true;
        }
        if(checkCorners())
        {
            return true;
        }
        return false;
    }

    public boolean checkDraw()
    {
        if(avalibleSpots == 0)//if there's no spots on the board, return true
        {
            return true;  
        }
        return false; 
    }

    public boolean checkHorizontal()
    {
        for (int i = 0; i<5; i++)//loops through every row in board
        {
            if ( !board[i][0].equals(" ") && //checks if String equals " "
            !board[i][1].equals(" ") && 
            !board[i][2].equals(" ") && 
            !board[i][3].equals(" ") && 
            !board[i][4].equals(" ")) 
            {
                if (board[i][0].equals(board[i][1]) && //checks is Strings equal to one another
                board[i][1].equals(board[i][2]) && 
                board[i][2].equals(board[i][3]) && 
                board[i][3].equals(board[i][4]))
                {
                    return true; //return true if both condition are true
                }
            }
        }
        return false; //return false otherwise
    }

    public boolean checkVertical()
    {
        for (int i = 0; i<5; i++)//loops through every column in board
        {
            if ( !board[0][i].equals(" ") && //checks if String equals " "
            !board[1][i].equals(" ") && 
            !board[2][i].equals(" ") && 
            !board[3][i].equals(" ") && 
            !board[4][i].equals(" ")) 
            {
                if (board[0][i].equals(board[1][i]) && //checks is Strings equal to one another
                board[1][i].equals(board[2][i]) && 
                board[2][i].equals(board[3][i]) && 
                board[3][i].equals(board[4][i]))
                {
                    return true; //return true if both condition are true
                }
            }
        }
        return false; //return false otherwise
    }

    public boolean checkDiagonal()
    {
        boolean majorIsMatching = true; //boolean majorIsMatching is true 
        boolean minorIsMatching = true; //boolean minorIsMatching is true 
        String major = board[0][0];//initial value, every element in major diagonal should be equal to this value 
        String minor = board[0][4];//initial value, every element in minor diagonal should be equal to this value 
        for (int i = 0; i<5; i++)//checks major(right to left)
        {
            if (major.equals(" "))//return false and break if initial value equals " "
            {
                majorIsMatching = false;
                break; 
            }
            if(board[i][i].equals(" "))//return false and break if elements in the diagonal line is equal to " "
            {
                majorIsMatching = false; 
                break; 
            }
            else if(!board[i][i].equals(major))//return false and break if elements in the diagonal line is not equal to the initial value. 
            {
                majorIsMatching = false;
                break;
            }
        }

        for (int i = 0,j=4; i<5; i++, j--)//checks minor(left to right)
        {
            if (minor.equals(" "))//return false and break if initial value equals " "
            {
                minorIsMatching = false;
                break; 
            }
            if(board[i][j].equals(" "))//return false and break if elements in the diagonal line is equal to " "
            {
                minorIsMatching = false; 
                break; 
            }
            else if(!board[i][j].equals(minor))//return false and break if elements in the diagonal line is not equal to the initial value
            {
                minorIsMatching = false;
                break;
            }
        }

        if ((majorIsMatching || minorIsMatching))//if a diagonal line has 5 same values{except " "}, return true
        {
            return true; 
        }
        return false; //return false otherwise
    }

    public boolean checkCorners()
    {
        if (!board[0][0].equals(" ") && //check if 4 corners is equal to " "
        !board[0][4].equals(" ") && 
        !board[4][0].equals(" ") && 
        !board[4][4].equals(" "))
        {
            if (board[0][0].equals(board[0][4]) && //check if 4 corners has the same value
            board[0][4].equals(board[4][4]) && 
            board[4][4].equals(board[4][0]))
            {
                return true; //return true if they have the same values 
            }
        }
        return false;//return false otherwise
    }

    private void btnWinAction()// wiondow for any win conditions
    {
        int dialogBtn = JOptionPane.YES_NO_OPTION;
        int response;
        if (turn == 1){
            response = JOptionPane.showConfirmDialog (null, "O Wins!!! Would you like to restart?", 
                "Game", dialogBtn);
        }
        else{
            response = JOptionPane.showConfirmDialog (null, "X Wins!!! Would you like to restart?", 
                "Game", dialogBtn);
        }
        OptionAction(response);
    }

    private void btnDrawAction()//wiondow for draw game
    {
        int dialogBtnDraw = JOptionPane.YES_NO_OPTION;
        int responseDraw = JOptionPane.showConfirmDialog (null, "Draw Game. Would you like to restart?", 
                "Game", dialogBtnDraw);
        OptionAction(responseDraw);
    }
    
    private void OptionAction(int option)//resets the board or close the game
    {
        if(option == JOptionPane.YES_OPTION)
        {
            resetBoard();
        }
        else
        {
            System.exit(0);
        }
    }

    private void resetBoard()
    {
        for (int i = 0; i<25; i++)//reset buttons
        {
            btn[i].setEnabled(true);//set every buttons clickable
            btn[i].setText(" ");//set each button to blank
            btn[i].setBackground(Color.white);//set background color to white
        }
        for(int i = 0; i<5; i++)//reset the 2D array board
        {
            for (int j = 0; j<5; j++)
            {
                board[i][j]=" ";//set text equals blank 
            }
        }
        avalibleSpots = 25; //set avalibleSpots to 25
    }
    public static void main(String[] args) {
		new TicTacToe();
	}

}

	
