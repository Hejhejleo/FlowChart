



                           /*---------------------------Bugs---------------------------*/
//Högerklick -> Delete funkar konstigt om man högerklickar på två shapes som interceptar
//Man kan "undo" med backspace. Man får error om man tar bort ett objekt med högerklick och sedan använder sig av backspace.
package flowchartfxupg;

import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author LeoAsp
 */
public class FlowChartFXUpg extends Application {

    ArrayList<Shape> shapes = new ArrayList<>();
    double xCord = 0;
    double yCord = 0;
    double startX = 0;
    double startY = 0;
    double endX = 0;
    double endY = 0;

    @Override
    public void start(Stage primaryStage) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

        primaryStage.setTitle("Flowchart 3.0");
        VBox root = new VBox();
        Scene scene = new Scene(root, 1000, 1000);
        AnchorPane workspace = new AnchorPane();
        workspace.setStyle("-fx-background-color: white");
        workspace.setPrefSize(1000, 1000);

        //cirlce
        MenuBar mainMenu = new MenuBar();
        ToolBar toolBar = new ToolBar();

        //Toolbar btns
        Button spawnRec = new Button();
        Button spawnCir = new Button();
        Button spawnDia = new Button();
        Button spawnTxt = new Button();
        ToggleButton toggleLine = new ToggleButton("Line");

        //Create btn icons and other things
        Rectangle btnRecIcon = new Rectangle(13, 11);
        Ellipse btnEllIcon = new Ellipse(8, 6);
        Rectangle btnDiaIcon = new Rectangle(13, 13);
        Rotate diaIconRot = new Rotate(45, 6, 7);
        btnDiaIcon.getTransforms().add(diaIconRot);
        Text btnTxtIcon = new Text("Txt");

        //icon design
        btnRecIcon.setFill(Color.WHITE);
        btnRecIcon.setStroke(Color.BLACK);
        btnEllIcon.setFill(Color.WHITE);
        btnEllIcon.setStroke(Color.BLACK);
        btnDiaIcon.setStroke(Color.BLACK);
        btnDiaIcon.setFill(Color.WHITE);
        btnTxtIcon.setFill(Color.BLACK);
        btnTxtIcon.setStroke(Color.BLACK);

        //icon -> btns
        spawnCir.setGraphic(btnEllIcon);
        spawnRec.setGraphic(btnRecIcon);
        spawnDia.setGraphic(btnDiaIcon);
        spawnTxt.setGraphic(btnTxtIcon);

        //add all the components
        root.getChildren().addAll(mainMenu, toolBar, workspace);

        //Option Menu
        ContextMenu op = new ContextMenu();
        MenuItem opDel = new MenuItem("Delete");

        Menu changeColor = new Menu("Change Color");
        MenuItem normal = new MenuItem("Normal");
        MenuItem white = new MenuItem("White");
        MenuItem black = new MenuItem("Black");
        MenuItem red = new MenuItem("Red");
        MenuItem green = new MenuItem("Green");
        changeColor.getItems().addAll(normal, white, black, red, green);

        op.getItems().addAll(opDel, changeColor);

        //Meny
        Menu file = new Menu("File");
        MenuItem newFile = new MenuItem("Reset");
        MenuItem exitApp = new MenuItem("Exit");
        file.getItems().addAll(newFile, exitApp);

        Menu edit = new Menu("Test1");
        MenuItem properties = new MenuItem("1");
        edit.getItems().add(properties);

        Menu help = new Menu("Test2");
        MenuItem visitWebsite = new MenuItem("1");
        help.getItems().add(visitWebsite);

        mainMenu.getMenus().addAll(file);
        toolBar.getItems().addAll(spawnRec, spawnCir, spawnDia, spawnTxt, toggleLine);

        primaryStage.setScene(scene);
        primaryStage.show();

        //File Exit
        exitApp.setOnAction((event) -> {
            primaryStage.close();

        });
        newFile.setOnAction((event) -> {
            workspace.getChildren().clear();
            shapes.clear();

        });

        //TempRectangle
        Rectangle rectangleTemp = new Rectangle( 100, 60);
             rectangleTemp.setFill(Color.CADETBLUE);
            rectangleTemp.setStroke(Color.BLACK);
            rectangleTemp.setArcHeight(15);
            rectangleTemp.setArcWidth(15);
            rectangleTemp.setOpacity(0.2);
        spawnRec.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventTemp ->{
            workspace.getChildren().remove(rectangleTemp);
            rectangleTemp.setX(eventTemp.getX());
            rectangleTemp.setY(eventTemp.getY());
            workspace.getChildren().add(rectangleTemp); 
        });
        //Rectangle object
        spawnRec.addEventHandler(MouseEvent.MOUSE_RELEASED, eventSpawn->{
            workspace.getChildren().remove(rectangleTemp);
            
            toggleLine.setSelected(false);
            Rectangle rectangle = new Rectangle(eventSpawn.getX(),eventSpawn.getY(), 100, 60);
            //rectangle
            rectangle.setEffect(dropShadow);
            rectangle.setFill(Color.CADETBLUE);
            rectangle.setStroke(Color.BLACK);
            rectangle.setArcHeight(15);
            rectangle.setArcWidth(15);
            shapes.add(rectangle);
            workspace.getChildren().addAll(shapes.get(shapes.size() - 1));
            rectangle.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventMD -> {
                if (!toggleLine.isSelected()) {
                    rectangle.setX(eventMD.getX());
                    rectangle.setY(eventMD.getY());
                }
            });
        });
        
        //Temp Cirlce
        Ellipse circleTemp = new Ellipse(50, 30);
             circleTemp.setFill(Color.CADETBLUE);
            circleTemp.setStroke(Color.BLACK);
            circleTemp.setOpacity(0.2);
        spawnCir.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventTemp ->{
            workspace.getChildren().remove(circleTemp);
            circleTemp.setCenterX(eventTemp.getX());
            circleTemp.setCenterY(eventTemp.getY());
            workspace.getChildren().add(circleTemp); 
        });
        //btnCircle
        spawnCir.addEventHandler(MouseEvent.MOUSE_RELEASED, eventSpawn-> {
        
            workspace.getChildren().remove(circleTemp);
            toggleLine.setSelected(false);
            Ellipse ellipse = new Ellipse(eventSpawn.getX(),eventSpawn.getY(), 50, 30);
            ellipse.setEffect(dropShadow);
            ellipse.setFill(Color.CADETBLUE);
            ellipse.setStroke(Color.BLACK);
            shapes.add(ellipse);
            workspace.getChildren().addAll(shapes.get(shapes.size() - 1));
            ellipse.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventMD -> {
                if (!toggleLine.isSelected()) {
                    ellipse.setCenterX(eventMD.getX());
                    ellipse.setCenterY(eventMD.getY());
                }
            });
        });

        //Temp Diamond
//        Rectangle diamondTemp = new Rectangle(50, 50);
//             Rotate rot = new Rotate(45);
//            diamondTemp.setOpacity(0.2);
//            diamondTemp.getTransforms().add(rot);
//            diamondTemp.setFill(Color.CADETBLUE);
//            diamondTemp.setStroke(Color.BLACK);
//            diamondTemp.setScaleX(2);
//        spawnDia.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventTemp ->{
//            workspace.getChildren().remove(diamondTemp);
//            diamondTemp.setLayoutX(eventTemp.getX());
//            diamondTemp.setLayoutY(eventTemp.getY());
//            workspace.getChildren().add(diamondTemp); 
//        });
        //btnDia
//        spawnDia.addEventHandler(MouseEvent.MOUSE_RELEASED, eventSpawn-> {
//            workspace.getChildren().remove(diamondTemp);
        spawnDia.setOnAction(eventSpawn->{
            Rotate rot = new Rotate(45);
            toggleLine.setSelected(false);
            Rectangle diamond = new Rectangle(300,0, 50, 50);
            
            diamond.getTransforms().add(rot);
            diamond.setEffect(dropShadow);

            diamond.setFill(Color.CADETBLUE);
            diamond.setStroke(Color.BLACK);
            diamond.setScaleX(2);
            shapes.add(diamond);
            workspace.getChildren().addAll(shapes.get(shapes.size() - 1));
            diamond.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventMD -> {
                if (!toggleLine.isSelected()) {
                    diamond.setX(eventMD.getX());
                    diamond.setY(eventMD.getY());
                }
            });
        });
        
        //temp text
        Text textTemp = new Text("Text");
        spawnTxt.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventTemp ->{
            workspace.getChildren().remove(textTemp);
            textTemp.setX(eventTemp.getX());
            textTemp.setY(eventTemp.getY());
            workspace.getChildren().add(textTemp); 
        });
        //btnTxt
        spawnTxt.addEventHandler(MouseEvent.MOUSE_RELEASED, event ->{
            workspace.getChildren().remove(textTemp);
            toggleLine.setSelected(false);
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Input");
            dialog.setContentText("Text to add");

            Optional<String> result = dialog.showAndWait();
            Text txt = new Text(result.get());
            txt.setX(event.getX());
            txt.setY(event.getY());
            shapes.add(txt);
            workspace.getChildren().addAll(shapes.get(shapes.size() - 1));
            txt.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventMD -> {
                if (!toggleLine.isSelected()) {
                    txt.setX(eventMD.getX());
                    txt.setY(eventMD.getY());
                }
            });

        });

        //start linje
        workspace.addEventHandler(MouseEvent.MOUSE_PRESSED, eventS -> {
            if (toggleLine.isSelected()) {
                if (eventS.getButton() == MouseButton.PRIMARY) {
                    startX = eventS.getX();
                    startY = eventS.getY();

                }
            }
        });
        workspace.addEventHandler(MouseEvent.MOUSE_DRAGGED, eventE -> {
            if (toggleLine.isSelected()) {
                if (eventE.getButton() == MouseButton.PRIMARY) {
                    endX = eventE.getX();
                    endY = eventE.getY();
                }
            }
        });

        //avsluta linjen
        workspace.addEventHandler(MouseEvent.MOUSE_RELEASED, eventE -> {
            if (toggleLine.isSelected()) {
                if (eventE.getButton() == MouseButton.PRIMARY) {
                    endX = eventE.getX();
                    endY = eventE.getY();
                    Line line = new Line(startX, startY, endX, endY);
                    shapes.add(line);

                    workspace.getChildren().addAll(shapes.get(shapes.size() - 1));
                    line.toBack();
                }
            }

        });

        //Delete CM
        opDel.setOnAction(event -> {
            workspace.getChildren().remove(coll(xCord, yCord));
        });

        //Change Color
        normal.setOnAction(event -> {
            coll(xCord, yCord).setFill(Color.CADETBLUE);
        });

        black.setOnAction(event -> {
            coll(xCord, yCord).setFill(Color.BLACK);
        });

        white.setOnAction(event -> {
            coll(xCord, yCord).setFill(Color.WHITE);
        });

        green.setOnAction(event -> {
            coll(xCord, yCord).setFill(Color.GREEN);
        });

        red.setOnAction(event -> {
            coll(xCord, yCord).setFill(Color.RED);
        });

        workspace.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            for (Shape i : shapes) {
                if (i.getBoundsInParent().intersects((new Rectangle(event.getX(), event.getY(), 1, 1)).getBoundsInParent())) {
                    xCord = event.getX();
                    yCord = event.getY();

                    if (event.getButton() == MouseButton.SECONDARY) {
                        try {
                            op.show(i, event.getScreenX(), event.getScreenY());
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                    }
                }
            }
        });

        root.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (shapes.size() > 0) {
                if (event.getCode() == KeyCode.BACK_SPACE) {

                    workspace.getChildren().remove(shapes.remove(shapes.size() - 1));
                } else {

                }
            }
        });

    }

    public Shape coll(double x, double y) {
        for (Shape i : shapes) {
            if (i.getBoundsInParent().intersects((new Rectangle(x, y, 1, 1)).getBoundsInParent())) {
                return i;

            }

        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
