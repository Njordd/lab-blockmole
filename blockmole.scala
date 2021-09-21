package blockmole

import java.awt.{Color as JColor}

object Color {
    //Skapar olika färger som behövs i övriga moduler
    val black   = new JColor(    0,   0,   0)
    val mole    = new JColor(   51,  51,   0)
    val soil    = new JColor(  153, 102,  51)
    val tunnel  = new JColor(  204, 153, 102)
    val grass   = new JColor(   25, 130,  35)
}

object BlockWindow {
//Har ett introprog.PixerlWindow och ritar blockgrafik
    import introprog.PixelWindow

    val windowSize = (30, 50)           // (width, height) in number of blocks
    val blockSize = 10                  // number of pixels per block

    val window = new PixelWindow(
    width = windowSize._1 * blockSize,
    height = windowSize._2 * blockSize,
    title = "Underjorden", 
    background = Color.black) 
}

object Mole {//Representerar en blockmullvad som kan gräva
    def dig(): Unit = println("Här ska det grävas!")
}

object Main{
    def drawWorld(): Unit = {//println("Ska rita ut underjorden!")
        BlockWindow.window.line(100, 10, 200, 20)
    }

    def main(args: Array[String]): Unit = {
        drawWorld()
        Mole.dig()
    }
}