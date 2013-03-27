package dockfx

import scalafx.stage.StageStyle
import scalafx.Includes.actionEventClosureWrapper
import scalafx.Includes.at
import scalafx.Includes.when
import scalafx.Includes.double2DurationHelper
import scalafx.Includes.eventClosureWrapper
import scalafx.Includes.jfxDuration2sfx
import scalafx.Includes.jfxReadOnlyBooleanProperty2sfx
import scalafx.Includes.mouseEventClosureWrapper
import scalafx.animation.Tweenable.tweenable2KeyFrame
import scalafx.animation.Timeline
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.event.ActionEvent
import scalafx.scene.Group.sfxGroup2jfx
import scalafx.scene.effect.Reflection
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
import scalafx.beans.value.ObservableValue.sfxObservableValue2jfx

object DockFX extends JFXApp {

  stage = new PrimaryStage {
    scene = new Scene(new Group(
	    new ImageView {
	      translateX = 100; translateY = 200
	      val img1 = new Image("images/dock1.png")
	      val img2 = new Image("images/dock2.png")
	      image <== when (hover) choose img2 otherwise img1
	    },
	    new HBox(20) {
	      translateX = 150; translateY = 220
	      content = for (name <- Seq("Browser", "Internet", "Mail", "User"))
	        yield new ImageView {
	    	  image = new Image("icons/" + name + "-32.png")
			  effect = new Reflection
			  onMouseEntered = bouncer play
			  val bouncer = new Timeline {
			    keyFrames = Seq(
			      at(  0 ms)(Set(scaleX -> 1.2, scaleY -> 1.0, translateY ->   0.0)),
			      at(100 ms)(Set(scaleX -> 1.0, scaleY -> 1.2, translateY ->   0.0)),
			      at(300 ms)(Set(scaleX -> 1.0, scaleY -> 1.0, translateY -> -20.0)),
			      at(500 ms)(Set(scaleX -> 1.0, scaleY -> 1.2, translateY ->   0.0)),
			      at(600 ms)(Set(scaleX -> 1.2, scaleY -> 1.0, translateY ->   0.0))
			    )
			    onFinished = (_: ActionEvent) => if(hover()) play else scaleX = 1.0
			  }
	      	}
	    }
    ), 550, 300) {fill = TRANSPARENT}
  }
  stage.initStyle(StageStyle.TRANSPARENT)

}