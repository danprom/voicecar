
package demo.sphinx.helloworld;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class HelloWorld {

    public static void main(String[] args) throws InterruptedException {
        try {
            URL url;
            if (args.length > 0) {
                url = new File(args[0]).toURI().toURL();
            } else {
                url = HelloWorld.class.getResource("helloworld.config.xml");
            }

            System.out.println("ładowanie...");

            ConfigurationManager cm = new ConfigurationManager(url);

	    Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
	    Microphone microphone = (Microphone) cm.lookup("microphone");
        recognizer.allocate();
       GpioController GPIO = GpioFactory.getInstance();
	    if (microphone.startRecording()) {
		while (true) {
		    System.out.println("Zacznij mówić:");

		    Result result = recognizer.recognize(); 
		    
		    
		    if (result != null) {
		    	String resultText = result.getBestFinalResultNoFiller();
			System.out.println("Powiedziałeś: jedz w"+resultText);
			
	
			if(resultText.equals("left"))
					{
				System.out.println("Powiedziałeś: jedz w lewo");
				GpioPinDigitalOutput pin29 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_29);
				GpioPinDigitalOutput pin28 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_28);
				GpioPinDigitalOutput pin27 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_27);
				GpioPinDigitalOutput pin26 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_26);
				pin29.high();
				pin28.low();
				pin27.high();
				pin26.low();
				Thread.sleep(1000);
				pin29.low();
				pin28.low();
				pin27.low();
				pin26.low();
				GPIO.shutdown();
				GPIO.unprovisionPin(pin29);
				GPIO.unprovisionPin(pin28);
				GPIO.unprovisionPin(pin27);
				GPIO.unprovisionPin(pin26);
				
					}
		//---------------------------------------				
			if(resultText.equals("right"))
			{
		System.out.println("Powiedziałeś jedz w prawo");
		GpioPinDigitalOutput pin29 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_29);
		GpioPinDigitalOutput pin28 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_28);
		GpioPinDigitalOutput pin27 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_27);
		GpioPinDigitalOutput pin26 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_26);
		pin29.low();
		pin28.high();
		pin27.low();
		pin26.high();
		Thread.sleep(1000);
		pin29.low();
		pin28.low();
		pin27.low();
		pin26.low();
		GPIO.shutdown();
		GPIO.unprovisionPin(pin29);
		GPIO.unprovisionPin(pin28);
		GPIO.unprovisionPin(pin27);
		GPIO.unprovisionPin(pin26);
			}
		//--------------------------------------
			
			if(resultText.equals("go"))
			{
		System.out.println("Powiedziałeś jedz do przodu");
		GpioPinDigitalOutput pin29 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_29);
		GpioPinDigitalOutput pin28 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_28);
		GpioPinDigitalOutput pin27 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_27);
		GpioPinDigitalOutput pin26 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_26);
		pin29.low();
		pin28.high();
		pin27.high();
		pin26.low();
		Thread.sleep(1000);
		pin29.low();
		pin28.low();
		pin27.low();
		pin26.low();
		GPIO.shutdown();
		GPIO.unprovisionPin(pin29);
		GPIO.unprovisionPin(pin28);
		GPIO.unprovisionPin(pin27);
		GPIO.unprovisionPin(pin26);
			}
	    //-------------------------------------		
			
			if(resultText.equals("down"))
			{
		System.out.println("Powiedziałeś jedz w tył");
		GpioPinDigitalOutput pin29 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_29);
		GpioPinDigitalOutput pin28 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_28);
		GpioPinDigitalOutput pin27 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_27);
		GpioPinDigitalOutput pin26 = GPIO.provisionDigitalOutputPin(RaspiPin.GPIO_26);
		pin29.high();
		pin28.low();
		pin27.low();
		pin26.high();
		Thread.sleep(1000);
		pin29.low();
		pin28.low();
		pin27.low();
		pin26.low();
		GPIO.shutdown();
		GPIO.unprovisionPin(pin29);
		GPIO.unprovisionPin(pin28);
		GPIO.unprovisionPin(pin27);
		GPIO.unprovisionPin(pin26);
			}
		//-------------------------------------		
			
		    } else System.out.println("Nie moge zidentyfikowac slowa");
		}
	    }
        
	     else {
		System.out.println("Mikrofon nie może wystartowac");
		recognizer.deallocate();
		System.exit(1);
	     }
		}
		catch (IOException e) {
            System.err.println("Problem problem z ładowaniem HelloWorld: " + e);
            e.printStackTrace();
        } catch (PropertyException e) {
            System.err.println("Problem z konfiguracją HelloWorld: " + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Problem z stworzeniem klasy HelloWorld: " + e);
            e.printStackTrace();
        
       }
    }
}

