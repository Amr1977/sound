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
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import logging.Logging;

import javax.swing.*;

import static java.nio.file.Paths.get;
import static logging.Logging.*;

public class Sound extends Application {

	public static JFXPanel fxPanel;
	public static JFrame frame;
	public static MediaPlayer mediaPlayer;

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
			Logging.log("media file: " + bip);
				Media hit = new Media(bip);
				mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
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
