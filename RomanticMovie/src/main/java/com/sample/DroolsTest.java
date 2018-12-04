package com.sample;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.DroolsTest.Message.RomanticUI;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
        	 // KieServices is the factory for all KIE services
	        KieServices ks = KieServices.Factory.get();
	        // From the kie services, a container is created from the classpath
    	    KieContainer kContainer = ks.getKieClasspathContainer();    
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	new Message().init(true);
        	
        	String q = "WHO ARE YOU WATHING IT WITH?";
        	String[] possibilities = { "My main squeeze", "My family"};
        	Question question = new Question(q, possibilities);			//Dodaje do pami�ci pierwsze pytanie
        	
        	kSession.insert(question);
            kSession.fireAllRules();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class Message {

		public void init( boolean exitOnClose) {

	        RomanticUI ui = new RomanticUI( );
	        ui.createAndShowGUI(exitOnClose);
		}

		public static class RomanticUI extends JPanel implements ActionListener {
			
			private static final long serialVersionUID = 510l;
	        
	        private static JButton answers[] = new JButton[2];	//Mo�liwe odpowiedzi
			private static JLabel l2;							//Tre�� pytania

			public RomanticUI() {
				
				
				this.setPreferredSize(new Dimension(800,600));
							
				for(int i = 0; i < 2; i++){						//Ustawiam przyciski
					JButton tmp = new JButton(String.valueOf(i));
					tmp.setBounds(200 + 210*i, 400, 200, 100);
					tmp.addActionListener(this);
					answers[i] = tmp;		//tablica z przyciskami do odpowiedzi
					this.add(tmp);
				}
																//Label z pytaniem
				JLabel l1 = new JLabel("obrazek", SwingConstants.CENTER);
				l1.setBorder(LineBorder.createGrayLineBorder());
				l1.setBounds(10,10,780,200);
				
				this.add(l1);
				
				l2 = new JLabel("Pytanie", SwingConstants.CENTER);
				l2.setBorder(LineBorder.createGrayLineBorder());
				l2.setBounds(10,250,780,100);
				
				this.add(l2);
	            
			}

			public void createAndShowGUI(boolean exitOnClose) {
				//Create and set up the window.
	            JFrame frame = new JFrame( "Romantic Movie" );
	            frame.setDefaultCloseOperation(exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);

	            setOpaque( true );
	            frame.setContentPane( this );
	            frame.setLayout(null);

	            //Display the window.
	            frame.pack();
	            frame.setLocationRelativeTo(null); // Center in screen
	            
	            frame.setVisible( true );
				
			}
			/**
			 * Metoda ustawiaj�ca tre�ci pytania i odpowiedzi na labelu
			 * i przyciskach, po wykryciu przez regu��
			 * */
			public static void setGUI(String question, String[] possibilities){
				
				l2.setText(question);
				for(int i = 0; i < possibilities.length; i++){
					answers[i].setText(possibilities[i]);
				}
			}

			public void actionPerformed(ActionEvent e) {
				
				//TODO co sie dzieje po wci�ni�ciu przycisku zatwierdzenia odpowiedzi
				// Nie mam pomys�u jak to rozwi�za� na razie bo musimy wiedzie� jakiemu pytaniu
				// nale�y ustawi� odpowied� a po drugie takie ustawienie samemu odpowiedzi 
				// nie wiem czy jest dozwolone, czy nie trzeba zrobi� tak �e system regulowy
				// jakos wykrywa ze nacisnelismy i sam ustawia odpowiedz
				
				if(e.getSource() == answers[0]){
					System.out.println("0 button");
				}
				else if(e.getSource() == answers[1]){
					System.out.println("1 button");
				}
			}
		}
    }
    /**
     * Klasa kt�rej obiektem jest pytanie z dan� tre�ci� i mo�liwymi odpowiedziami
     * dodatkowo rozr�niamy pole picked_answer oznaczaj�ce wybran� odpowiedz przez u�ytkownika
     **/
    public static class Question{
    	
    	public String content, picked_answer;
    	public String[] answers;
    	
    	public Question(String q, String[] ans){
    		this.content = q;
    		this.answers = ans;
    		this.picked_answer = null;
    	}
    	
    	public String getPickedAnswer() {
			return picked_answer;
		}
    	
    	public String getContent(){
    		return content;
    	}

		public String[] getAnswers() {
			return answers;
		}
		
		public void setPickedAnswer(String answer) {
			this.picked_answer = answer;
		}
		
		public static void setGUI(String question, String[] possibilities){
			RomanticUI.setGUI(question, possibilities);
		}
    }
}
