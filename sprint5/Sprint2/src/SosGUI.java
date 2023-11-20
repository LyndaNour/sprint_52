import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
	public class SosGUI extends JFrame {
	
		public static final int CELL_SIZE = 80;
		public static final int GRID_WIDTH = 4;
		public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 

		public static final int CELL_PADDING = CELL_SIZE / 6;
		public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
		public static final int SYMBOL_STROKE_WIDTH = 8; 

		private int CANVAS_WIDTH; 
		private int CANVAS_HEIGHT;

		private GameBoardCanvas gameBoardCanvas; 
		private JLabel gameStatusBar; 
		private JLabel gameStatusBar2; 
		private JLabel boardSize;

		private Board board;
	
		JRadioButton sButton;
		JRadioButton oButton;
		JRadioButton simpleButton;
		JRadioButton generalButton;
		JTextField sizeSelect;
		JLabel blueScore;
		
		JRadioButton sHuman;
		JRadioButton oHuman;
		JRadioButton sComputer;
		JRadioButton oComputer;
		
		JRadioButton sHuman2;
		JRadioButton oHuman2;
		JRadioButton sComputer2;
		JRadioButton oComputer2;
		
		JRadioButton humanPlayer;
		JRadioButton computerPlayer;
		JRadioButton humanPlayer2;
		JRadioButton computerPlayer2;
		JLabel redScore;
		JLabel warningLabel = new JLabel(" ");
		
	
		
		

		public SosGUI(Board board) {
			this.board = board;
			setContentPane();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack(); 
			setTitle("SOS GAME");
			setVisible(true);  
			
		}
		
		
		//update board size
		private void updateBoardSize() {
			
			CANVAS_WIDTH = CELL_SIZE *board.getTotalRows();
			CANVAS_HEIGHT = CELL_SIZE * board.getTotalColumns();
			
			repaint();
		}
		//check for the valid board size
		public boolean setBoardSize(int size) {
			if(size<3 || size>6) {
	
				return false;
			} 
			return true;
		}

		private void setContentPane(){
			gameBoardCanvas = new GameBoardCanvas();  
			CANVAS_WIDTH = CELL_SIZE * board.getTotalRows();  
			CANVAS_HEIGHT = CELL_SIZE * board.getTotalColumns();
			gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
			
			gameStatusBar = new JLabel(" ");
			gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));;
			gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2,5,4,5));
			
			gameStatusBar2 = new JLabel(" ");
			gameStatusBar2.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));;
			gameStatusBar2.setBorder(BorderFactory.createEmptyBorder(2,5,4,5));

			Container contentPane = getContentPane();
			contentPane.setLayout(new BorderLayout());
		//	contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
			//contentPane.add(gameStatusBar, BorderLayout.PAGE_END);
			
			//control for players
			JPanel playerPanel = new JPanel();
			playerPanel.setLayout(new BorderLayout());
			//playerPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			playerPanel.setPreferredSize(new Dimension(100,100));
			playerPanel.setBackground(Color.GRAY);
			
			// blue player control panel
			JPanel pl1 = new JPanel();
			pl1.setPreferredSize(new Dimension(200,200));
			pl1.setBackground(new Color(31, 190, 214));

			// create a pl1 panel that holds blue player information 
			JLabel blueLabel = new JLabel("BLUE PLAYER:");
			
			JLabel humanLabel = new JLabel("Human player");
			JLabel computerLabel = new JLabel("Computer player");
			
			blueScore = new JLabel("Score:");
		
			//RadioButton group S and O for human
			ButtonGroup humanGroup = new ButtonGroup();
			sHuman = new JRadioButton("S");
			oHuman = new JRadioButton("O");
			humanGroup.add(oHuman);
			humanGroup.add(sHuman);
			
			
			//RadioButton group S and O for computer
			
			ButtonGroup computerHuman = new ButtonGroup();
			
			ButtonGroup computerGroup = new ButtonGroup();
			sComputer = new JRadioButton("S");
			oComputer = new JRadioButton("O");
			computerPlayer = new JRadioButton("Computer");
			humanPlayer = new JRadioButton("Human");
			
			computerGroup.add(oComputer);
			computerGroup.add(sComputer);
			
			computerHuman.add(computerPlayer);
			computerHuman.add(humanPlayer);
			
			pl1.add(blueLabel);
			pl1.add(computerPlayer);
			pl1.add(oHuman);
			pl1.add(sHuman);
			pl1.add(humanPlayer);	
			pl1.add(blueScore); 
			
		
			
			// red player control panel
			JPanel pl2 = new JPanel();
			pl2.setPreferredSize(new Dimension(200,200));
			pl2.setBackground(new Color(255,56, 100));

			// create a pl2 panel that holds red player information 
			JLabel redLabel = new JLabel("RED PLAYER:");
			JLabel humanLabel2 = new JLabel("Human player");
			JLabel computerLabel2 = new JLabel("Computer player");
			redScore = new JLabel("Score:");
			//RadioButton group S and O for human
			ButtonGroup humanGroup2 = new ButtonGroup();
			sHuman2 = new JRadioButton("S");
			oHuman2 = new JRadioButton("O");
			humanGroup2.add(oHuman2);
			humanGroup2.add(sHuman2);
			
			//RadioButton group S and O for computer
			ButtonGroup computerGroup2 = new ButtonGroup();
			sComputer2 = new JRadioButton("S");
			oComputer2 = new JRadioButton("O");
			computerGroup2.add(oComputer2);
			computerGroup2.add(sComputer2);
				
			ButtonGroup computerHuman2 = new ButtonGroup();
			computerPlayer2 = new JRadioButton("Computer2");
			humanPlayer2 = new JRadioButton("Human2");
			
			computerHuman2.add(humanPlayer2);
			computerHuman2.add(computerPlayer2);
				
				pl2.add(computerPlayer2);
				pl2.add(oHuman2);
				pl2.add(sHuman2);
				pl2.add(humanPlayer2);
				pl2.add(redScore);
	
			
			//add blue player content and red player content to the main player panel
			playerPanel.add(pl1, BorderLayout.NORTH);
			playerPanel.add(pl2, BorderLayout.SOUTH);
			
			// create a panel to hold simple and general game 
			JPanel modePanel = new JPanel();
		//	modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.X_AXIS));
			modePanel.setPreferredSize(new Dimension(50,50));
			modePanel.setBackground(Color.cyan);
			simpleButton = new JRadioButton("Simple Game");
			generalButton = new JRadioButton("General Game");
			ButtonGroup gameMode = new ButtonGroup();
			gameMode.add(simpleButton);
			gameMode.add(generalButton);
			
			modePanel.add(simpleButton);
			modePanel.add(generalButton);
			
			// create panel that holds size 
			JLabel sizeLabel = new JLabel("Board size");
			
			 sizeSelect = new JTextField("Enter size here");
			
			modePanel.add(sizeLabel);
			modePanel.add(sizeSelect);
			modePanel.add(warningLabel);
			
			sizeSelect.addActionListener(new ActionListener() {;
				
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer newSize = Integer.parseInt(sizeSelect.getText());
				setBoardSize(newSize);
				if(setBoardSize(newSize) ==false) {
					warningLabel.setText("invalid size");
					
				
				}
				else {
					warningLabel.setText("valid size");
						board.setRows(newSize);
						board.setColumns(newSize);
						updateBoardSize();
						sizeSelect.setEditable(false);
						
				}
				
			}
			});
		
			contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
			contentPane.add(gameStatusBar, BorderLayout.PAGE_END);
			contentPane.add(playerPanel, BorderLayout.WEST);
			contentPane.add(modePanel, BorderLayout.BEFORE_FIRST_LINE);
		}
	

		class GameBoardCanvas extends JPanel {
			
			GameBoardCanvas(){

				
				addMouseListener(new MouseAdapter() {
				
					public void mouseClicked(MouseEvent e) {  
						if (board.getGameState() == Board.GameState.PLAYING) {
							int rowSelected = e.getY() / CELL_SIZE;
							int colSelected = e.getX() / CELL_SIZE;
							Random rand = new Random();
							int r = rand.nextInt(board.getTotalRows());
							int c = rand.nextInt(board.getTotalColumns());
							
							//blue player move 
						if(board.changeTurn()=="Blue player") {
							
						// human player
							if(humanPlayer.isSelected()) {
							System.out.println("Blue player selected to be a human selected");		
								
								if(sHuman.isSelected())		{
								board.makeSmove(rowSelected, colSelected);
								warningLabel.setText("blue player places S at (" + rowSelected + ", " +colSelected +")");
								System.out.println("Blue player places S at (" + rowSelected + ", " +colSelected +")");
							
							} else if(oHuman.isSelected()) {
								board.makeOmove(rowSelected, colSelected);
								warningLabel.setText("blue player places O at (" + rowSelected + ", " +colSelected +")");
								System.out.println("Blue player places o at (" + rowSelected + ", " +colSelected +")");
							} 
							}// end of human player
								if(simpleButton.isSelected()) {
								System.out.println("win: "+board.simpleCheck("Blue player"));
								} else {
									blueScore.setText("Score:" + board.generalBlueCheck(rowSelected, colSelected, board.getTotalRows()));	
								}
					
								
						} // end of blue player turn
						else if(board.changeTurn()=="Red player") {
							System.out.println("red turn");
							
							// human player for red player
							if(humanPlayer2.isSelected()) {
								warningLabel.setText("Red player selected to be a human");
								
							//red player move for human
								if(sHuman2.isSelected()) {
								board.makeSmove(rowSelected, colSelected);
								warningLabel.setText("Red player places S at ( " + rowSelected + ", " +colSelected +")");
								System.out.println("red player places s at (" + rowSelected + ", " +colSelected +")");
							
			
							} else if(oHuman2.isSelected()) {
								board.makeOmove(rowSelected, colSelected);
								warningLabel.setText("Red player places O at (" + rowSelected + ", " +colSelected +")");
							}
								if(simpleButton.isSelected()) {
									System.out.println("win: "+board.simpleCheck("Red player"));
								}else {
									redScore.setText("Score:" + board.generalRedCheck(rowSelected, colSelected, board.getTotalRows()));	
								}
								
								
							}// end of red player 	
						}
						System.out.println("Draw: "+board.isDraw() );
							sizeSelect.setEditable(false);
						} // close playing game  
	
						// initialize the game board 
						else {        
							board.initBoard(); 
							warningLabel.setText("  ");
							sizeSelect.setText("Enter size here");
							sizeSelect.setEditable(true);
							board.bpScore=0;
							board.rpScore=0;
							blueScore.setText("Score:");
							redScore.setText("Score:");
				
						}
							repaint();
					// end mouseClicked 
					}
				
					public void mouseEntered(MouseEvent e) {
						if (board.getGameState() == Board.GameState.PLAYING) {
						Random rand = new Random();
						int r = rand.nextInt(board.getTotalRows());
						int c = rand.nextInt(board.getTotalColumns());
						
						// computer player and blue turn
						 if(computerPlayer.isSelected() && board.changeTurn()=="Blue player") {
							warningLabel.setText("Blue player selected to be a computer");						
						 
							if(sHuman.isSelected()) {
								
								board.makeSmove(r, c);
								warningLabel.setText("Blue player computer places S at (" + r + ", " +c+")");
						
								} else if(oHuman.isSelected()) {
								board.makeOmove(r, c);
								warningLabel.setText("Blue player computer places S at (" + r + ", " +c+")");
							
							if(simpleButton.isSelected()) {
								if (board.simpleCheck("Blue player")) {
							
								}
								
							} else {
								blueScore.setText("Score:" + board.generalBlueCheck(r, c, board.getTotalRows()));	
							}
							
						}
						 }// end of computer player  for blue player 
						 else if (computerPlayer2.isSelected() && board.changeTurn()=="Red player") {
							
								if(sHuman2.isSelected()) {
									board.makeSmove(r, c);
									warningLabel.setText(" Red player computer places S at (" + r + ", " +c+")");
				
									} else if(oHuman2.isSelected()) {
										board.makeOmove(r, c);
										warningLabel.setText("Red player computer places O at (" + r + ", " +c+")");
									} 
								if(simpleButton.isSelected()) {
									if (board.simpleCheck("Red player")) {
										
									}
									
								} else {
									redScore.setText("Score:" + board.generalRedCheck(r, c, board.getTotalRows()));	
								}
								
						 }// end red player 
						 
						 if(! simpleButton.isSelected() && !generalButton.isSelected()) {
								warningLabel.setText("Choose a game mode to continue!");
								warningLabel.setForeground(Color.red);
							}// game mode 	
					}
						}
					
					
			
				});
			}

			@Override
			public void paintComponent(Graphics g) { 
				super.paintComponent(g);   
				setBounds(150, 100,CANVAS_WIDTH, CANVAS_HEIGHT);
				setBackground(Color.GRAY);
				drawGridLines(g);
				drawBoard(g);
				printStatusBar();
			}
			
			private void drawGridLines(Graphics g){
				g.setColor(Color.WHITE);
				for (int row = 1; row < board.getTotalRows(); ++row) {
					g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
							CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
				}
				for (int col = 1; col < board.getTotalColumns(); ++col) {
					g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
							GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
				}
				
			}

			private void drawBoard(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				
				for (int row = 0; row < board.getTotalRows(); ++row) {
					for (int col = 0; col < board.getTotalColumns(); ++col) {
						int x1 = col * CELL_SIZE + CELL_PADDING;
						int y1 = row * CELL_SIZE + CELL_PADDING;
						int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
					
						
						g2d.setFont(new Font(" ",Font.BOLD,30));
						
						g2d.setColor(Color.BLACK);
				
						if (board.getCell(row,col) == Board.Cell.S_PLACEMENT) {
							g2d.drawString("S", (x1+x2)/2, (y1+y2)/2);
							if(simpleButton.isSelected()) {
							if(board.simpleCheck(board.changeTurn())) {
								g2d.drawLine(x1,y1, x2, y2);
							}
							}
						
						} else if (board.getCell(row,col) == Board.Cell.O_PLACEMENT) {
							g2d.drawString("O", (x1+x2)/2, (y1+y2)/2);
						
							if(simpleButton.isSelected()) {
							if(board.simpleCheck(board.changeTurn())) {
								g2d.drawLine(x1, y1, x2, y2);
							}
							}
						}
						
					}
				}
			}

			private void printStatusBar(){
				
				board.compareScore();
					
				if (board.getGameState() == Board.GameState.PLAYING) {
					gameStatusBar.setForeground(Color.BLACK);
					if (board.changeTurn() == "Blue player") {
						gameStatusBar.setText("Blue player's Turn");
						
					} else {
						gameStatusBar.setText("Red player's Turn");
					}
					
				} else if (board.getGameState() == Board.GameState.DRAW) {
					gameStatusBar.setForeground(Color.RED);
					gameStatusBar.setText("It's a Draw! Click to play again.");
				} else if (board.getGameState() == Board.GameState.BLUE_WON) {
					gameStatusBar.setForeground(Color.RED);
					gameStatusBar.setText("Blue player' Won! Click to play again.");
				} else if (board.getGameState()== Board.GameState.RED_WON) {
					gameStatusBar.setForeground(Color.RED);
					gameStatusBar.setText("Red player's Won! Click to play again.");
					
				} else if (board.getGameState()== Board.GameState.RED_WON && board.isFull()) {
					gameStatusBar.setText("Red player's Won! Click to play again");
				} else if (board.getGameState()== Board.GameState.BLUE_WON && board.isFull()) {
				gameStatusBar.setText("Blue player's Won! Click to play again");
			}
				
					
			}
		
		}

		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new SosGUI(new Board()); 
				}
			});
			
			Random rand = new Random();
			int r = rand.nextInt(3);
			int c = rand.nextInt(3);
			System.out.println("rand " + r + "c" + c);
			
		}
	}
