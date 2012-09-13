package dockfx.scala

import javafx.stage.StageStyle
import scalafx.Includes.mouseEventClosureWrapper
import scalafx.application.JFXApp
import scalafx.scene.Group.sfxGroup2jfx
import scalafx.scene.image.ImageView.sfxImageView2jfx
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.input.MouseEvent.sfxMouseEvent2jfx
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.HBox.sfxHBox2jfx
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color.TRANSPARENT
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.Group
import scalafx.scene.Scene
import scalafx.stage.Stage

object DockFX extends JFXApp {

  stage = new Stage {
    scene = new Scene(new Group(
	    new ImageView {
	      translateX = 100; translateY = 200
	      image = new Image("images/dock1.png")
	      onMouseClicked = (e: MouseEvent) =>
	        image = new Image("images/dock%s.png" format
	          (if (e isPrimaryButtonDown) "1" else "2"))
	    },
	    new HBox(20) {
	      translateX = 150; translateY = 220
	      content = for (name <- Seq("Browser", "Internet", "Mail", "User"))
	        yield new BouncingIcon("icons/" + name + "-32.png")
	    }
    ), 550, 300) {fill = TRANSPARENT}
    initStyle(StageStyle.TRANSPARENT)
  }

}