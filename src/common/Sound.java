package common;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import logging.Logging;

import javax.swing.*;

import static java.nio.file.Paths.get;
import javafx.util.Duration;
import static logging.Logging.*;

public class Sound extends Application {

	public static JFXPanel fxPanel;
	public static JFrame frame;
	private static MediaPlayer mediaPlayer;
        private static boolean PLAYING=false;
	static {
		frame = new JFrame("sound");
		fxPanel = new JFXPanel();
		frame.add(fxPanel);
		frame.setVisible(false);
		final StackPane layout = new StackPane();
		layout.setStyle("-fx-background-color: cornsilk; -fx-font-size: 20; -fx-padding: 20; -fx-alignment: center;");

		fxPanel.setScene(new Scene(layout, 80, 60));

	}
        
        

	public static void playMp3(String fileName) throws IOException {
		try {
			String bip = Paths.get(fileName).toUri().toString();
			Logging.log("Media start: " + bip);
				Media hit = new Media(bip);
				setMediaPlayer(new MediaPlayer(hit));
                                //getMediaPlayer().
                                getMediaPlayer().setOnEndOfMedia(new Runnable() {
                                    @Override public void run() {
                                       setPLAYING(false); 
                                       Logging.log("Media ended: "+bip);
                                        
                                    }
                                });
                                getMediaPlayer().setOnError(new Runnable() {
                                    @Override public void run() {
                                       setPLAYING(false); 
                                       Logging.log("MediaPlayer Error : ");
                                       Logging.log(getMediaPlayer().getError());
                                    }
                                }
                                );
                                getMediaPlayer().setOnHalted(new Runnable() {
                                    @Override public void run() {
                                       setPLAYING(false); 
                                       Logging.log("MediaPlayer Halted : ");
                                       Logging.log(getMediaPlayer().getError());
                                    }
                                }
                                );
                                setPLAYING(true); 
                                //Duration duration=getMediaPlayer().getCycleDuration();
				getMediaPlayer().play();
                                //Thread.sleep((long) (duration.toMillis()-1000));
                                //Logging.log("Duration-1000 reached : "+duration+" ms.");
                                //PLAYING=false;
                                while(isPLAYING()){
                                    Thread.sleep(10);
                                }
		}catch (Exception e){
			Logging.log(e);
		}


		/*if (new File(fileName).exists()){
			Logger.getLogger(BasicPlayer.class.getName()).setLevel(Level.OFF);
			BasicPlayer player = new BasicPlayer();
			try {
				player.open(new URL("file:///" + fileName));
				player.play();
			} catch (BasicPlayerException | MalformedURLException e) {
				log(e.toString());
			}
		}*/
		
	}

    /**
     * @return the mediaPlayer
     */
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * @param aMediaPlayer the mediaPlayer to set
     */
    public static void setMediaPlayer(MediaPlayer aMediaPlayer) {
        mediaPlayer = aMediaPlayer;
    }

    /**
     * @return the PLAYING
     */
    public static boolean isPLAYING() {
        return PLAYING;
    }

    /**
     * @param aPLAYING the PLAYING to set
     */
    public static void setPLAYING(boolean aPLAYING) {
        PLAYING = aPLAYING;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.setTitle("Sound");
		primaryStage.setScene(new Scene(root, 30, 30));
		primaryStage.show();
	}
	public static void main(String [] args){
		try {
			playMp3("D:\\001001.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
