package nz.ac.auckland.se206;

import javafx.scene.control.Label;
import org.controlsfx.control.PopOver;

public class PopOverBuilder {

  public static PopOver createPopOver(String text) {

    Label label = new Label(text);
    label.setStyle("-fx-padding: 5px;");
    PopOver popOver = new PopOver(label);
    popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
    return popOver;
  }
}
