package gui;
import general.Board;
import general.Game;
import general.Move;
import general.Piece;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import player.Player;
import player.PlayerHuman;
import tools.Index;
/* ---------------------------------------------------------------------------------
* 	 Program Name: Game.java 
* 	 Date Written: April 19th, 2023
* 		   Author: Sofia Arturo Gomez,Zoey Zhao, Ashley Zhang
*         ----> Credit to Petri Tuononen 
*         Purpose: 1. Create a chess board
*                   
* ----------------------------------------------------------------------------------
* Modification History: 
* ----------------------------------------------------------------------------------
* Date			Person	CSR#	Description
* 2023-04-19	SA 		1.01	Initial Version 
* 2023-04-20	SA 		1.01	Add comments to methods
* 2023-04-20	SA 		1.01	Deleted unnecessary or not understood methods 
* 2023-04-20	SA 		1.01	Deleted AI vs AI, AI vs human methods and classes 
* 2023-04-20	SA 		1.01	Initializes humanVsHumanMode once the main method 
*                               is called
* ----------------------------------------------------------------------------------
* Future Enhancements: 
* -------------------- 	
* 1. Delete text based version
* 								
*/ 

public class Gui extends JPanel implements Runnable, MouseListener  {

	private static final long serialVersionUID = -5154317893203520132L;
	//global variables
	private static Board board;
	private Graphics g1;
	private Game game;
	Player player;
	private boolean srcSelected = false;
	private boolean destSelected = false;
	private String selectedSrc;
	private String selectedDest;
	private String clickedSquare;
	private int sizeX, sizeY;
	private Player player1, player2;	
	private Move move;
	private int concecutMEvent = 0;
	private boolean showLegalMoves = true;
	private static boolean humanVsHumanMode;	
	private String graphicsPath="./graphics/";
	private String jarGraphicsPath="/graphics/";
	//gui variables
	private JFrame f;
	
	
	/**
	 * Get move object.
	 * @return
	 */
	public Move getMove() {
		return move;
	}

	/**
	 * Set move object.
	 * @param move
	 */
	public void setMove(Move move) {
		this.move = move;
	}

	/**
	 * Returns true if source square is selected, false otherwise.
	 * @return
	 */
	public boolean isSrcSelected() {
		return srcSelected;
	}

	/**
	 * Set selected source square.
	 * @param srcSelected
	 */
	public void setSrcSelected(boolean srcSelected) {
		this.srcSelected = srcSelected;
	}

	/**
	 * Returns true if destination square is selected, false otherwise.
	 * @return
	 */
	public boolean isDestSelected() {
		return destSelected;
	}

	/**
	 * Set selected destination square.
	 * @param destSelected
	 */
	public void setDestSelected(boolean destSelected) {
		this.destSelected = destSelected;
	}

	/**
	 * Get clicked square.
	 * @return
	 */
	public String getClickedSquare() {
		return clickedSquare;
	}

	/**
	 * Set clicked square.
	 * @param clickedSquare
	 */
	public void setClickedSquare(String clickedSquare) {
		this.clickedSquare = clickedSquare;
	}

	/**
	 * Get selected source square.
	 * @return
	 */
	public String getSelectedSrc() {
		return selectedSrc;
	}

	/**
	 * Set selected source square.
	 * @param selectedSrc
	 */
	public void setSelectedSrc(String selectedSrc) {
		this.selectedSrc = selectedSrc;
	}

	/**
	 * Get selected destination square.
	 * @return
	 */
	public String getSelectedDest() {
		return selectedDest;
	}

	/**
	 * Set selected destination square.
	 * @param selectedDest
	 */
	public void setSelectedDest(String selectedDest) {
		this.selectedDest = selectedDest;
	}

	/**
	 * Get game object.
	 * @return
	 */
	private Game getGame() {
		return game;
	}

	/**
	 * Get panel horizontal size.
	 * @return
	 */
	private int getSizeX() {
		return sizeX;
	}

	/**
	 * Set panel horizontal size.
	 * @param sizeX
	 */
	private void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * Get panel vertical size.
	 * @return
	 */
	private int getSizeY() {
		return sizeY;
	}

	/**
	 * Sets panel vertical size.
	 * @param sizeY
	 */
	private void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 * Sets panel size.
	 */
	public void setSize(int x, int y) {
		setSizeX(x);
		setSizeY(y);
	}
	
	/**
	 * Get global graphics object.
	 */
	private Graphics getG1() {
		return g1;
	}

	/**
	 * Set global graphics object.
	 * @param g1
	 */
	private void setG1(Graphics g1) {
		this.g1 = g1;
	}
	
	/**
	 * Returns true/false according to human vs human mode state.
	 * @return
	 */
	public boolean isHumanVsHumanMode() {
		return humanVsHumanMode;
	}
	
	/**
	 * Creates players.
	 */
	private void createPlayers() {
		//create players
		player1 = new PlayerHuman(0);
		player2 = new PlayerHuman(1);
	}

	/**
	 * Initialises game.
	 */
	private void initGame() {
		board = new Board();
		move = new Move(board);
		game = new Game();
		createPlayers();
	}
	
	/**
	 * Constructor.
	 */
	public Gui() {
		//declare starting mode
		humanVsHumanMode = false;
		player = new Player();
		//add mouse listener to the panel
		addMouseListener(this);
		//set panel size
		setSize(600, 600);
		Dimension d = new Dimension(getSizeX(), getSizeY());
		setPreferredSize(d);
		//initGuiComponents();
		initGame();
		getGame().setWhosTurn(0); //white starts
		this.initHumanVsHumanMode();
	}
	
	/**
	 * Initializes Gui components.
	 */
	
	
	/**
	 * Responsible for drawing graphics on the screen.
	 * Paint method is run every time repaint() is called.
	 */
    protected void paintComponent(Graphics g) {
    	setG1(g);
        Graphics2D g2 = (Graphics2D) getG1();
        drawBoard(getG1());
        Color blue = new Color(0, 135, 205);
        Color green = new Color(0, 255, 0);
        if (isSrcSelected()) {
        	if (showLegalMoves) {
        		//paint own piece's background
        		paintSquare(getSelectedSrc(), blue);
        		//paint legal moves background
        		Piece piece = board.notationToPiece(getSelectedSrc());
        		//get all movements that are allowed for the selected piece
        		ArrayList<ArrayList<Integer>> legalMoves = move.possiblePieceMoves(piece, false);
        		ArrayList<Integer> x = legalMoves.get(0); //list of row numbers
        		ArrayList<Integer> y = legalMoves.get(1); //list of column numbers
        		ListIterator<Integer> xList = x.listIterator();  //row iterator
        		ListIterator<Integer> yList = y.listIterator();  //column iterator
        		while (xList.hasNext() && yList.hasNext()) { //while lists have coordinates
        			//paint squares where the selected piece is allowed to move
        			paintSquare(board.coordinatesToNotation(xList.next(), yList.next()), green);
        		}
        	}
        }
		drawPieces(g2);
    }
 
    /**
     * Draws pieces.
     * @param g2
     */
    public void drawPieces(Graphics2D g2) {
    	//get all pieces
    	ArrayList<Piece> pieces = board.getPieces();
    	Toolkit toolkit = getToolkit();
    	Image img = null;
    	URL imgURL = null;
    	//draw all pieces
    	for (Piece p : pieces) {
    		if (p.getColor()==0) { //white piece
    			//get image
    			switch(p.getType()) {
    			case 1:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"sryb.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"sryb.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 2:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"srb.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"srb.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 3:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"stb.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"stb.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 4:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"scb.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"scb.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 5:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"sab.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"sab.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 6:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"spb.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"spb.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			default:
    				break;
    			}
    		} else if (p.getColor()==1) { //black piece
    			//get image
    			switch(p.getType()) {
    			case 1:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"sryn.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"sryn.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 2:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"srn.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"srn.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 3:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"stn.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"stn.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 4:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"scn.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"scn.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 5:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"san.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"san.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			case 6:
    				try {
        				imgURL = this.getClass().getResource(jarGraphicsPath+"spn.png");
        				img = toolkit.getImage(imgURL);
					} catch (Exception e) {
						try {
							img = toolkit.getImage(graphicsPath+"spn.png");
						} catch (Exception e2) {
							System.out.println("img not found");
						}
					}
    				break;
    			default:
    				break;
    			}
    		}
    		if (p!=null) {
    			String notation = board.coordinatesToNotation(p.getRow(), p.getCol());
    			System.out.println("-Manejo de Piesas: "+notation);
    			Index idx = notationToPieceLoc(notation);
    			g2.drawImage(img, idx.getX(), idx.getY(), this);
    		}
    	}
    }
    
    /**
     * Moves piece.
     * @param board
     * @param move
     * @param player
     * @param src
     * @param dest
     */
    public void movePiece(Board board, Move move, Player player, String src, String dest) {
    	//get piece to move
    	Piece p = board.notationToPiece(src);
    	//array coordinates for new destination
		Index newLoc = board.notationToIndex(dest);
		//remove captured piece from the board
		board.removePiece(newLoc.getX(), newLoc.getY()); 
		p.setRow(newLoc.getX()); //change piece row
		p.setCol(newLoc.getY()); //change piece column
		//place source and destination square to history of moves
		if (player.getSide()==0) { //if white
			//add white piece move to history
			move.getHistoryOfMoves().addWhiteMove(src, dest); 
		} else if (player.getSide()==1) { //if black
			//add black piece move to history
			move.getHistoryOfMoves().addBlackMove(src, dest); 
		}
		//promote pawns to queens if they reach enemy's end
		board.promotePawnsToQueen(player.getSide());
    }
    
    /**
     * Draws board.
     * @param g
     */
    public void drawBoard(Graphics g) {
    	int width = getWidth();
        int height = getHeight();
        int even=0, x, y, w, h;
        Color color1 = Color.WHITE;
        Color color2 = Color.BLACK;
        for (int i=0; i<8; i++) { //rows
        	//change color order on new row
        	if (even%8==0) {
        		Color temp = color1;
        		color1=color2;
        		color2=temp;
        	}
        	for (int j=0; j<8; j++) { //columns
        		if (even%2==0) {
        			g.setColor(color2);
        		} else {
        			g.setColor(color1);
        		}
        		if (i==0) {
        			y=0;
        			h=(height/8)-1;
        		} else {
        			y=i*(height/8)-1;
        			h=height/8;
        		}
        		if (j==0) {
        			x=0;
        			w=(width/8)-1;
        		} else {
        			x=j*(width/8)-1;     
        			w=width/8;
        		}
        		g.fillRect(x, y, w, h);
        		even++;
        	}	
        }
    }
    
    /**
     * Paint defined square on the board.
     */
    public void paintSquare(String notation, Color color) {
    	//get piece coordinates
    	Index squareLoc = notationToPieceLoc(notation);

    	//redraw square color
		getG1().setColor(color);
		getG1().fillRect(squareLoc.getX(), squareLoc.getY(), 75, 75);
    }
    
    /**
     * Returns true if given notation is white square
     * @param notation
     * @return boolean
     */
    public boolean isWhiteSquare(String notation) {
    	char c;
    	int num;
    	try {
        	c = notation.charAt(0);
        	num = notation.charAt(1);
		} catch (Exception e) {
		}
    	c = notation.charAt(0);
    	num = notation.charAt(1);
    	if (c=='a' || c=='c' || c=='e' || c=='g') {
    		if (num%2==0) { //if even
    			return true;
    		} else {
    			return false;
    		}
    	} else if (c=='b' || c=='d' || c=='f' || c=='h') {
    		if (num%2!=0) { //if odd
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
    
    /**
     * Returns Index object which contains x&y coordinates
     * for upper left corner of selected square notation.
     * @param notation
     * @return Index
     */
    private Index notationToPieceLoc(String notation) {
    	//get frame size
    	int width = getWidth();
    	int height = getHeight();

    	//horizontal square's left corner location possibilities
    	int[] columnCoord = {0, (width/8)-1, 2*(width/8)-1, 3*(width/8)-1, 4*(width/8)-1,
    	5*(width/8)-1, 6*(width/8)-1, 7*(width/8)-1};

    	//vertical square's left corner location possibilities
    	int[] rowCoord = {0, (height/8)-1, 2*(height/8)-1, 3*(height/8)-1, 4*(height/8)-1,
    	5*(height/8)-1, 6*(height/8)-1, 7*(height/8)-1};
    	
    	Index loc, gLoc = new Index(0, 0);
    	//get square location
    	loc = new Board().notationToIndex(notation);
    	//get upper left corner location
    	gLoc.setX(columnCoord[loc.getY()]);
    	gLoc.setY(rowCoord[loc.getX()]);
    	
    	return gLoc;
    }
 
    
    /**
     * This method is run automatically when the object is created.
     */
    public void run() {
    	//create new frame
        f = new JFrame("Chess Game for Beginners");
        //close frame when pressing close button
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add content pane to frame
        f.getContentPane().add(new Gui());
        
        //try to load icon image
        try { 
        	f.setIconImage(new ImageIcon("./graphics/icon.jpg").getImage());
        }catch (Exception e) { 
        	//icon load error
        }
        //make frame visible
        f.setVisible(true);
        
        Dimension d = new Dimension(getSizeX(), getSizeY());
        f.setSize(d);
        f.setResizable(false);

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Determine the new location of the frame
        int w = f.getSize().width;
        int h = f.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        
        // Move the frame to the center of the screen
        f.setLocation(x, y);
        
        f.pack();
    }
 
    /**
     * Defines which column represents clicked x coordinate.
     * @param x
     * @return
     */
    public String preciseCoordinatesToNotationX(int x) {
    	if (0<=x && x<=74) { //a
    		return "a";
    	} else if (75<=x && x<=149) { //b
    		return "b";
    	} else if (150<=x && x<=224) { //c
    		return "c";
    	} else if (225<=x && x<=299) { //d
    		return "d";
    	} else if (300<=x && x<=374) { //e
    		return "e";
    	} else if (375<=x && x<=449) { //f
    		return "f";
    	} else if (450<=x && x<=524) { //g
    		return "g";
    	} else if (525<=x && x<=599) { //h
    		return "h";
    	} else {
    		return "";
    	}
    }
    
    /**
     * Defines which row represents clicked y coordinate.
     * @param y
     * @return
     */
    public String preciseCoordinatesToNotationY(int y) {
    	if (0<=y && y<=74) { //8
    		return "8";
    	} else if (75<=y && y<=149) { //7
    		return "7";
    	} else if (150<=y && y<=224) { //6
    		return "6";
    	} else if (225<=y && y<=299) { //5
    		return "5";
    	} else if (300<=y && y<=374) { //4
    		return "4";
    	} else if (375<=y && y<=449) { //3
    		return "3";
    	} else if (450<=y && y<=524) { //2
    		return "2";
    	} else if (525<=y && y<=599) { //1
    		return "1";
    	} else {
    		return "";
    	}
    }
    
    /**
     * Defines which notation represents clicked location on the board.
     * @param x
     * @param y
     * @return
     */
    public String preciseCoordinatesToNotation(int x, int y) {
    	String s1 = preciseCoordinatesToNotationX(x);
    	String s2 = preciseCoordinatesToNotationY(y);
    	//if another coordinate is out of scope (empty String)
    	//make another coordinate to be empty String as well
    	//for easy validity checking
    	if (s1=="" || s2=="") {
    		s1="";
    		s2="";
    	}
    	String notation = s1+s2;
    	return notation;
    }
    
    /**
     * Checks if the selected source square is valid.
     * @param srcSq
     * @param move
     * @param board
     * @param player
     * @return
     */
    public boolean isSrcSqValid(String srcSq, Move move, Board board, Player player) {
    	//check source square notation for validity
    	if (!move.checkSqValidity(srcSq)) {
    		return false;
    	}
		//check if own piece exists on selected source square.
		ArrayList<Piece> ownPieces = board.getPiecesFromOneSide(player.getSide());
		for (Piece p : ownPieces) {
			if (board.coordinatesToNotation(p.getRow(), p.getCol()).equalsIgnoreCase(srcSq)) {
				return true;
			}
		}
		return false;
    }
    
    /**
     * Checks if the selected destination square is valid.
     * @param srcSq
     * @param destSq
     * @param move
     * @param board
     * @param player
     * @return
     */
    public boolean isDestSqValid(String srcSq, String destSq, Move move, Board board, Player player) {
    	//check source square notation for validity
    	if (!move.checkSqValidity(destSq)) {
    		return false;
    	}
		Piece piece = board.notationToPiece(srcSq);
		//get all movements that are allowed for the selected piece
		ArrayList<ArrayList<Integer>> legalMoves = move.possiblePieceMoves(piece, false);
		//array coordinates for new destination
		Index newLoc = board.notationToIndex(destSq);
		//find out if destination location is included in the legal moves list
		ArrayList<Integer> x = legalMoves.get(0); //list of row numbers
		ArrayList<Integer> y = legalMoves.get(1); //list of column numbers
		ListIterator<Integer> xList = x.listIterator();  //row iterator
		ListIterator<Integer> yList = y.listIterator();  //column iterator
		int xL, yL;
		while (xList.hasNext() && yList.hasNext()) { //while lists have coordinates
			//listiterator next() method doesn't work inside if statement -> assign to variables
			xL = xList.next();
			yL = yList.next();
			if (newLoc.getX()==xL && newLoc.getY()==yL) { //legal move
				return true;
			}
		}	
		return false;
    }
    
       
    /**
     * Mouse event handler for clicked buttons.
     */
	public void mouseClicked(MouseEvent e) {
			//identify which player turn it is
			int color = getGame().getWhosTurn();
			if (color==0) {
				player = player1;
			} else {
				player = player2;
			}
			//set ready to accept mouse click for destination square
			if (concecutMEvent==1) {
				concecutMEvent=2;
			}
			
			switch(e.getModifiers()) {
			//left mouse button pressed
			case InputEvent.BUTTON1_MASK: {
				//set the clicked square by mapping coordinates to notation
				setClickedSquare(preciseCoordinatesToNotation(e.getX(), e.getY()));
				
				//if source square hasn't been selected
				if(!isSrcSelected()) {
					//check if clicked source square has player's own piece
					if(isSrcSqValid(getClickedSquare(), getMove(), board, player)) {
						//set source square as selected
						setSrcSelected(true);
						//set clicked square as selected source
						setSelectedSrc(getClickedSquare());
						concecutMEvent=1;
						if (showLegalMoves) {
							repaint();
						}
					} else {
						JOptionPane.showMessageDialog(f, "Source square not valid.",
								"Move error", JOptionPane.PLAIN_MESSAGE);
					}
				}
				if (concecutMEvent==2) {
					//if source is selected but not destination
					if (isSrcSelected() && !isDestSelected()) {
							//check if clicked destination square is empty or has enemy piece
							if(isDestSqValid(getSelectedSrc(), getClickedSquare(), getMove(),
									board, player)) {
								//set destination square as selected
								setDestSelected(true);
								//set clicked square as selected destination
								setSelectedDest(getClickedSquare());
								//reset concecutive mouse event
								concecutMEvent=0;
							} else {
									JOptionPane.showMessageDialog(f, "Destination square not valid.",
											"Move error", JOptionPane.PLAIN_MESSAGE);	
							}
					}
				}
				//if valid source and destination is selected
				if (isSrcSelected() && isDestSelected()) {
					//do move
					movePiece(board, getMove(), player, getSelectedSrc(), getSelectedDest());
					//repaint the board
					repaint(); 
					//set source and destination unselected after
					//the movement has been done
					setSrcSelected(false);
					setDestSelected(false);
					//check if check mate
					//another player's turn
					if (isHumanVsHumanMode()) {
						if (getGame().getWhosTurn()==0) {
							getGame().setWhosTurn(1);
						} else {
							getGame().setWhosTurn(0);
						}					
					}
					
				}
				break;
			}
			//right mouse button pressed
			case InputEvent.BUTTON3_MASK: {
				//clear piece to move selection
				setSrcSelected(false);
				repaint();
				break;
			}
			}
		
	}
	
	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	/**
	 * Initialises human vs human mode.
	 */
	private void initHumanVsHumanMode() {
		board.setPieces(new ArrayList<Piece>(32)); //set empty piece list
		board.createPieces(); //create piece objects
		createPlayers();		
		humanVsHumanMode = true;
		
	}
	
	/**
	 * Main method.
	 * @param args
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Gui());
        
    }
    
}