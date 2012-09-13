package dockfx.scala

import scalafx.Includes._
import scalafx.animation.Tweenable.tweenable2KeyFrame
import scalafx.animation.Timeline
import scalafx.event.ActionEvent
import scalafx.scene.effect.Reflection
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.input.MouseEvent

class BouncingIcon(url: String) extends ImageView {

  image = new Image(url)
  effect = new Reflection
  onMouseEntered = bouncer play

  val bouncer = new Timeline {
    keyFrames = Seq(
      at (  0 ms) {Set(scaleX -> 1.2, scaleY -> 1.0, translateY ->   0.0)},
      at (100 ms) {Set(scaleX -> 1.0, scaleY -> 1.2, translateY ->   0.0)},
      at (300 ms) {Set(scaleX -> 1.0, scaleY -> 1.0, translateY -> -20.0)},
      at (500 ms) {Set(scaleX -> 1.0, scaleY -> 1.2, translateY ->   0.0)},
      at (600 ms) {Set(scaleX -> 1.2, scaleY -> 1.0, translateY ->   0.0)}
    )
    onFinished = (_: ActionEvent) => if(hover()) play else scaleX = 1.0
  }

}