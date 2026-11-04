package Swing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import SetLogic.Cards;
import SetLogic.Game;
import SetLogic.Rules;
import SetLogic.SetFinder;

public class PlaySwing {
	//game
	Game game;

	//board
	JPanel gridPanel = new JPanel(new GridLayout(3, 4));
	List<Cards> selectedcards = new ArrayList<>();
	List<JButton> selectedbuttons = new ArrayList<>();
	List<Cards> cards;
	Map<JButton, Cards> buttonToCard = new HashMap<>();
	List<Cards> cardsOnBoard = new ArrayList<>();
	Rules rule = new Rules();


	public PlaySwing (Game game) {
		this.game = game;
		this.cards = game.deck.getDeck();
	}
	
	public void run(){
		SwingUtilities.invokeLater(() -> createWindow(game, "game", 500, 500));
	}

	private void startGame(JTextField points, JFrame frame){
		//Start Deck
		game.deck.shuffle();

		points.setText("Nummber of SETs found: " + game.player.getPoints());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				
				Cards card = cards.remove(0);
				JButton cardbutton = new JButton(createCardIcon(card));
				buttonToCard.put(cardbutton, card);
				cardsOnBoard.add(card);
				
				cardbutton.putClientProperty("card", card);

				cardbutton.addActionListener(e -> {
				    JButton source = (JButton) e.getSource();
			        Cards clickedCard = (Cards) source.getClientProperty("card");
				    
				    if (selectedcards.contains(clickedCard)) {
				    	selectedcards.remove(clickedCard);
				    	selectedbuttons.remove(source);
				    	source.setBorder(BorderFactory.createEmptyBorder());
				    	
				    } else if (selectedcards.size() < 3) {
				    	selectedcards.add(clickedCard);
				    	selectedbuttons.add(source);
		                source.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
				    } 
				    
				    if (selectedcards.size() == 3) {
				        if (rule.AllRules(selectedcards.get(0), selectedcards.get(1), selectedcards.get(2))) {
				        	game.player.setPoint(game.player.getPoints() + 1);
				            points.setText("Nummber of SETs found: " + game.player.getPoints());
				            showPopupMessage(frame, "SET found!", 1500);
				            for (Map.Entry<JButton, Cards> entry : buttonToCard.entrySet()) {
				                entry.getKey().setBorder(BorderFactory.createEmptyBorder());
						    }
				            
				            for (int k = 0; k < selectedbuttons.size(); k++) {
				                JButton b = selectedbuttons.get(k);
				                Cards oldCard = selectedcards.get(k);
				                cardsOnBoard.remove(oldCard);

				                if (!cards.isEmpty()) {
				                    Cards newCard = cards.remove(0);
				                    cardsOnBoard.add(newCard);
				                    b.setIcon(createCardIcon(newCard));
				                    buttonToCard.put(b, newCard);
				                    b.putClientProperty("card", newCard);
				                } else {
				                    b.setIcon(null);
				                    b.setText("✓");
				                    b.setEnabled(false);
				                }
				            }
				        } else {
				        	showPopupMessage(frame, "Not a SET", 1000);
				        }
			            for (JButton b : selectedbuttons) {
			                b.setBorder(BorderFactory.createEmptyBorder());
			            }
				        selectedcards.clear();
				        selectedbuttons.clear();
				    }
				});
				gridPanel.add(cardbutton);
			}
		}

	}

	private void createWindow(Game game, String title, int height, int width) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		JPanel buttonPanel = new JPanel();
		pane.add(buttonPanel, BorderLayout.SOUTH);
		
		JTextField points = new JTextField("Nummber of SETs found:" + " " + game.player.getPoints());
		points.setEditable(false);           
		points.setFocusable(false);  
		frame.add(points, BorderLayout.NORTH);

		startGame(points, frame);

		pane.add(gridPanel, BorderLayout.CENTER);
		
		
		// Shuffle cards on board / new board
		JButton shuffle = new JButton("New Cards");

		shuffle.addActionListener(e -> {
		    
		    //remove selected cards highlight 
		    for (Map.Entry<JButton, Cards> entry : buttonToCard.entrySet()) {
                entry.getKey().setBorder(BorderFactory.createEmptyBorder());
		    }
		    selectedcards.clear();
		    selectedbuttons.clear();
		    
		    // Prevent duplicates by clearing used cards before reshuffling
		    List<Cards> usedCards = new ArrayList<>(cardsOnBoard);

		    if (cards.size() > 0) {
		    	
		    	// Add all cards from the board back to the deck
			    cards.addAll(usedCards);
			    cardsOnBoard.clear();  // Clear the board

			    // Shuffle the deck
			    Collections.shuffle(cards);
			    					    
		        List<Cards> usedOLDCARDS = new ArrayList<>();

		        // Deal new cards to the board
		        int numCardsToDeal = Math.min(12, cards.size());
		        for (int l = 0; l < numCardsToDeal; l++) {
		            JButton button = (JButton) gridPanel.getComponent(l); 
		            Cards newCard = cards.remove(0);  // Get a card from the deck

		            // Set the new icon for the button and update the board
		            button.setIcon(createCardIcon(newCard));
		            buttonToCard.put(button, newCard);
		            button.putClientProperty("card", newCard);
		            cardsOnBoard.add(newCard);
		            usedOLDCARDS.add(newCard);  // Track used cards
		        }

		        // Remove used cards from the deck
		        for (Cards c : usedOLDCARDS) {
		            cards.remove(c);
		        }
		        
		    } else if (cards.size() <= 0){
		        showPopupMessage(frame, "There are no more cards in the deck", 2000);
		    }
		});
		buttonPanel.add(shuffle);
		
		
		//check if set exists
		JButton exsistsSet = new JButton("Is there a Set?");
		SetFinder finder = new SetFinder();
		
		exsistsSet.addActionListener(e -> {
			ArrayList<Cards> setcards;
			if (finder.SetFinders(cardsOnBoard) != null) {
				 setcards = new ArrayList<>(finder.SetFinders(cardsOnBoard)); 
			} else {
				setcards = new ArrayList<>();
			}
			
			if (setcards.isEmpty()) {
				showPopupMessage(frame, "There is no set", 2000);
			} else {
				showPopupMessage(frame, "There exsists a set", 2000);
			}
		});
		buttonPanel.add(exsistsSet);
		
		//Highlight set if it exists
		JButton highlightset = new JButton("Find Set");
		highlightset.addActionListener(e -> {
			ArrayList<Cards> setcards;
			if (finder.SetFinders(cardsOnBoard) != null) {
				 setcards = new ArrayList<>(finder.SetFinders(cardsOnBoard)); 
			} else {
				setcards = new ArrayList<>();
			}
			
			if (setcards.isEmpty()) {
				showPopupMessage(frame, "There is no set", 2000);
				
			} else {
				//remove selected cards highlight 
				for (Map.Entry<JButton, Cards> entry : buttonToCard.entrySet()) {
		                entry.getKey().setBorder(BorderFactory.createEmptyBorder());
		        }
				selectedcards.clear();
				selectedbuttons.clear();
				
		        for (Map.Entry<JButton, Cards> entry : buttonToCard.entrySet()) {
		            if (setcards.contains(entry.getValue())) {
		                entry.getKey().setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            }
		        }
			}
		});
		
		buttonPanel.add(highlightset);
		frame.pack();
		frame.setVisible(true);

		JButton restartGame = new JButton("Restart Game");
		restartGame.addActionListener(e -> {
			game.restart();
			
			selectedcards.clear();
			selectedbuttons.clear();
			cards = game.deck.getDeck();
			buttonToCard.clear();
			cardsOnBoard.clear();
			gridPanel.removeAll();
	
			startGame(points, frame);
			shuffle.doClick();
		});

		buttonPanel.add(restartGame);
		frame.pack();
		frame.setVisible(true);

	}
	
	//graphic for each card
	public static Icon createCardIcon(Cards card) {
	    CardComponent cardComponent = new CardComponent(card);
	    int width = cardComponent.getPreferredSize().width;
	    int height = cardComponent.getPreferredSize().height;

	    cardComponent.setSize(width, height);  
	    cardComponent.doLayout();              

	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = image.createGraphics();
	    cardComponent.paint(g2);  
	    g2.dispose();

	    return new ImageIcon(image);
	}
	
	//message popup
	public static void showPopupMessage(JFrame parent, String message, int durationMillis) {
	    JDialog dialog = new JDialog(parent, false);
	    dialog.setUndecorated(true);
	    JLabel label = new JLabel(message, SwingConstants.CENTER);
	    label.setOpaque(true);
	    label.setBackground(new Color(0, 0, 0, 200));  
	    label.setForeground(Color.WHITE);
	    label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	    
	    dialog.getContentPane().add(label);
	    dialog.pack();

	    // Center on parent frame
	    int x = parent.getX() + parent.getWidth()/2 - dialog.getWidth()/2;
	    int y = parent.getY() + parent.getHeight()/2 - dialog.getHeight()/2;
	    dialog.setLocation(x, y);
	    dialog.setVisible(true);

	    // Auto-close after duration
	    new Timer(durationMillis, e -> dialog.dispose()).start();
	}
}
