package com.sample;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
        	
        	 // KieServices is the factory for all KIE services
	        KieServices ks = KieServices.Factory.get();
	        
	        // From the kie services, a container is created from the classpath
    	    KieContainer kContainer = ks.getKieClasspathContainer();
            // go !
        	
        	new Message().init(kContainer, true);
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class Message {

		public void init(KieContainer kc, boolean exitOnClose) {
			
			//The callback is responsible for populating working memory and
	        // fireing all rules
	        RomanticUI ui = new RomanticUI( new CheckoutCallback( kc ) );
	        ui.createAndShowGUI(exitOnClose);
		}

		public static class RomanticUI extends JPanel {
			
			private static final long serialVersionUID = 510l;

	        private CheckoutCallback callback;    //Co bêdzie siê dzia³o po wciœnieciu przycisku

			public RomanticUI(CheckoutCallback callback) {
				
				this.callback = callback;
				
				this.setPreferredSize(new Dimension(800,600));
				
				String[] petStrings = { "My main squeeze", "My family"};
				
				for(int i = 0; i < petStrings.length; i++){
					JButton tmp = new JButton(petStrings[i]);
					tmp.setBounds(200 + 210*i, 400, 200, 100);
					this.add(tmp);
				}
				
				JLabel l1 = new JLabel("obrazek", SwingConstants.CENTER);
				l1.setBorder(LineBorder.createGrayLineBorder());
				l1.setBounds(10,10,780,200);
				
				this.add(l1);
				
				JLabel l2 = new JLabel("WHO ARE YOU WATHING IT WITH?", SwingConstants.CENTER);
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
			
		}
		
		public static class CheckoutCallback {
	        KieContainer kcontainer;
	        JTextArea     output;

	        public CheckoutCallback(KieContainer kcontainer) {
	            this.kcontainer = kcontainer;
	        }
		}

    }

}
