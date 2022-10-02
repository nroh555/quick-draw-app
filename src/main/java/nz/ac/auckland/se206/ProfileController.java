package nz.ac.auckland.se206;

import ai.djl.translate.TranslateException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.models.Level;
import nz.ac.auckland.se206.profile.User;

public class ProfileController {

  @FXML private Label welcomeLabel;

  @FXML private Label infoLabel;

  @FXML private TextArea usedWordsBox;

  @FXML private MenuButton accuracySettingButton;

  @FXML private MenuButton wordsSettingButton;

  @FXML private MenuButton timeSettingButton;

  @FXML private MenuButton confidenceSettingButton;

  protected void updateLabels() {
    // Get current user
    FXMLLoader menuLoader = SceneManager.getMenuLoader();
    MenuController menuController = menuLoader.getController();
    User currentUser = menuController.getCurrentUser();

    // Update welcome label
    welcomeLabel.setText("Welcome, " + currentUser.getUsername() + "!");

    // Update info label
    infoLabel.setText(currentUser.formatUserDetails());

    // Update used words label
    usedWordsBox.setText(currentUser.getUsedWordsString());

    // Update the menu button with the current user difficulty settings
    accuracySettingButton.setText(
        "Accuracy: " + currentUser.getDifficultyString(currentUser.getAccuracySetting()));
    wordsSettingButton.setText(
        "Words: " + currentUser.getDifficultyString(currentUser.getWordsSetting()));
    timeSettingButton.setText(
        "Time: " + currentUser.getDifficultyString(currentUser.getTimeSetting()));
    confidenceSettingButton.setText(
        "Confidence: " + currentUser.getDifficultyString(currentUser.getConfidenceSetting()));
  }

  /**
   * Button to switch to the menu page
   *
   * @param event
   * @throws IOException
   * @throws TranslateException
   */
  @FXML
  private void onSwitchToMenu(ActionEvent event) throws IOException, TranslateException {
    // Changes the scene to canvas
    Button btnThatWasClicked = (Button) event.getSource();
    Scene sceneThatThisButtonIsIn = btnThatWasClicked.getScene();
    sceneThatThisButtonIsIn.setRoot(SceneManager.getUi(AppUi.MENU));
  }

  /**
   * Button to switch to the dashboard page
   *
   * @param event
   * @throws IOException
   * @throws TranslateException
   */
  @FXML
  private void onSwitchToDashboard(ActionEvent event) throws IOException, TranslateException {
    // Changes the scene to canvas
    Button btnThatWasClicked = (Button) event.getSource();
    Scene sceneThatThisButtonIsIn = btnThatWasClicked.getScene();
    sceneThatThisButtonIsIn.setRoot(SceneManager.getUi(AppUi.DASHBOARD));
  }

  @FXML
  private void onAccuracySetEasy() {
    accuracySettingButton.setText("Accuracy: Easy");
    updateCurrentUser("Accuracy", Level.EASY);
  }

  @FXML
  private void onAccuracySetMedium() {
    accuracySettingButton.setText("Accuracy: Medium");
    updateCurrentUser("Accuracy", Level.MEDIUM);
  }

  @FXML
  private void onAccuracySetHard() {
    accuracySettingButton.setText("Accuracy: Hard");
    updateCurrentUser("Accuracy", Level.HARD);
  }

  @FXML
  private void onWordsSetEasy() {
    wordsSettingButton.setText("Words: Easy");
    updateCurrentUser("Words", Level.EASY);
  }

  @FXML
  private void onWordsSetMedium() {
    wordsSettingButton.setText("Words: Medium");
    updateCurrentUser("Words", Level.MEDIUM);
  }

  @FXML
  private void onWordsSetHard() {
    wordsSettingButton.setText("Words: Hard");
    updateCurrentUser("Words", Level.HARD);
  }

  @FXML
  private void onWordsSetMaster() {
    wordsSettingButton.setText("Words: Master");
    updateCurrentUser("Words", Level.MASTER);
  }

  @FXML
  private void onTimeSetEasy() {
    timeSettingButton.setText("Time: Easy");
    updateCurrentUser("Time", Level.EASY);
  }

  @FXML
  private void onTimeSetMedium() {
    timeSettingButton.setText("Time: Medium");
    updateCurrentUser("Time", Level.MEDIUM);
  }

  @FXML
  private void onTimeSetHard() {
    timeSettingButton.setText("Time: Hard");
    updateCurrentUser("Time", Level.HARD);
  }

  @FXML
  private void onTimeSetMaster() {
    timeSettingButton.setText("Time: Master");
    updateCurrentUser("Time", Level.MASTER);
  }

  @FXML
  private void onConfidenceSetEasy() {
    confidenceSettingButton.setText("Confidence: Easy");
    updateCurrentUser("Confidence", Level.EASY);
  }

  @FXML
  private void onConfidenceSetMedium() {
    confidenceSettingButton.setText("Confidence: Medium");
    updateCurrentUser("Confidence", Level.MEDIUM);
  }

  @FXML
  private void onConfidenceSetHard() {
    confidenceSettingButton.setText("Confidence: Hard");
    updateCurrentUser("Confidence", Level.HARD);
  }

  @FXML
  private void onConfidenceSetMaster() {
    confidenceSettingButton.setText("Confidence: Master");
    updateCurrentUser("Confidence", Level.MASTER);
  }

  private void updateCurrentUser(String difficultyCategory, Level levelSetting) {
    // Get current user
    FXMLLoader menuLoader = SceneManager.getMenuLoader();
    MenuController menuController = menuLoader.getController();
    User currentUser = menuController.getCurrentUser();

    // Update appropriate difficulty category for the current user
    if (difficultyCategory == "Accuracy") {
      currentUser.setAccuracySetting(levelSetting);
    } else if (difficultyCategory == "Words") {
      currentUser.setWordsSetting(levelSetting);
    } else if (difficultyCategory == "Time") {
      currentUser.setTimeSetting(levelSetting);
    } else {
      currentUser.setConfidenceSetting(levelSetting);
    }

    System.out.println("set");
  }
}
