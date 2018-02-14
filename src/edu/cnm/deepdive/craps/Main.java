package edu.cnm.deepdive.craps;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *  Class Main extends the Application class from the JavaFX Library
 /**
 *
 */
  public class Main extends Application {

  /**
   * classLoader is how we load something that refers to the class path. It helps us find things
   * ResourceBundle packages Strings so we can have multiple sets of Strings in different languages.
   * FXMLLoader loads our FXML files
   */
  private static final String RESOURCES_BUNDLE_PATH = "resources/strings";
  private static final String FXML_PATH = "resources/main.fxml";
  public static final String WINDOW_TITLE_KEY = "windowTitle";
  public static final String ICON_PATH = "resources/icon.png";
  private ClassLoader classLoader;
  private ResourceBundle bundle;
  private FXMLLoader fxmlLoader;
  private Controller controller;

  /**
   * invoking setupLoaders in the Start method. Same as saying this.setUpLoader but its redundant
   * because they are both non-static classes.
   * setUpStage has to pass 2 parameters. (stage is taken right from the method, and we need a Parent.
   * parent is established in the loadLayout method so we invoke the loadLayout method.
   * @param stage
   * @throws Exception
   */
  @Override
  public void start(Stage stage) throws Exception {
    setUpLoaders();
    setupStage(stage, loadLayout());

  }



  /**
   * setUpLoaders is an empty parameter Method. from the same class
   * resourcebundle comes from a different class because it comes from a different class.
   * fxmlLoader is creating a new object that passes a parameter classLoader that knows how to get
   * a resource and says hey this is what i want you to load. When you load this ), bundle i want
   * you to use the appropriate strings in the right language.
   */
  private void setUpLoaders() {
    classLoader = getClass().getClassLoader();
    bundle = ResourceBundle.getBundle(RESOURCES_BUNDLE_PATH);
    fxmlLoader = new FXMLLoader(classLoader.getResource(FXML_PATH), bundle);


  }

  /**
   * loadLayout method returns Parent from the javafx lib. Parent ex: VBox because it contains
   * "children" within it. Parent can have other parents within them.
   * the fxmlLoader comes from the previous class with its parameters and the .load basically tells
   * it to do stuff. and it set is to the Parent root because root is the id for the ENTIRE layout
   * in the VBOX of the main.fxml file.
   * Controller asks fxmlLoader to read the fxml file and sees that there is a fx:controller and loads
   * the edu.cnm.deepdive.craps.Controller
   * return root, returns the entire contents of the VBox from the fxml file.
   */
  private Parent loadLayout() throws IOException {
    Parent root = fxmlLoader.load();
    controller = fxmlLoader.getController();
    //TODO- do something with controller
    return root;
  }

  /**
   * setupStage is method that takes the parent root (everything we need to set up the stage) and
   * actually sets it up.
   * you have to set up the stage first (the first parameter), then you can set up the 'box'
   * Create a new scene and give it parameters that will show you what is on the scene. (root)
   * Stage says go to the resource bundle and get the string windowTitle from the string.properties
   * file. it gets the string from windowTitle key.
   * setResizable means that we cannot change the size of the scene.
   * stage.setSCene (scene) takes the object and puts it on the scene
   * stage.show() actually SHOWS the banner.
   *
   * @param node
   */
  private void setupStage (Stage stage, Parent root) {
    Scene scene = new Scene(root);
    stage.setTitle(bundle.getString(WINDOW_TITLE_KEY));
    stage.getIcons().add(new Image(classLoader.getResourceAsStream(ICON_PATH)));
    //TODO-set icon.
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();

  }

  /**
   * Main method, invokes the launch method from the Application that passes the same args.
   * @param args
   */
  public static void main(String[] args) {
    launch(args);

  }
}
