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
    title = "Digging") 

    type Pos = (Int, Int)
    

    def block(pos: Pos)(color: JColor = JColor.gray): Unit = {
        val x = pos._1 * blockSize    //räkna ut fönstrets x-kordinat i pixlar isftf block
        val y = pos._2 * blockSize     //räkna ut y-koordinat i pixlar istf block
       
        window.fill(x,y, blockSize, blockSize, color) //blocken ritas ut lodrätt
    }

    def rectangle(leftTop: Pos)(size: (Int, Int))(color: JColor = JColor.gray): Unit = {

    for (y <- leftTop._2 to size._2 + leftTop._2){
        for (x <- leftTop._1 to size._1 + leftTop._1){
            block(x,y)(color)
        }
    }       

    }

    val maxWaitMillis = 10

    def waitForKey(): String = {
        window.awaitEvent(maxWaitMillis)
        while (window.lastEventType != PixelWindow.Event.KeyPressed) {
            window.awaitEvent(maxWaitMillis) //skip other events

        }
        println(s"KeyPressed: " + window.lastKey)
        window.lastKey
    }

}

object Mole {//Representerar en blockmullvad som kan gräva
    def dig(): Unit = {
        var x = BlockWindow.windowSize._1 / 2
        var y = BlockWindow.windowSize._2 / 2
        var quit = false

        
        while (!quit) {
            BlockWindow.block(x, y)(Color.mole)
            val key = BlockWindow.waitForKey()

            BlockWindow.rectangle(x,y)(size = (0,0))(Color.tunnel) // ritar en tunnel efter mullvaden på samma x,y .

            if      (key == "w") then y -= 1
            else if (key == "a") then x -= 1
            else if (key == "s") then y += 1
            else if (key == "d") then x += 1
            else if (key == "q") then quit = true
        }
    }
}

object Main{
    def drawWorld(): Unit = {//println("Ska rita ut underjorden!")
         //BlockWindow.block(1,0)()   
        BlockWindow.rectangle(0,0)(size = (30, 4))(Color.grass)
        BlockWindow.rectangle(0,4)(size = (30, 46))(Color.soil)
    }

    def main(args: Array[String]): Unit = {
        drawWorld()
        Mole.dig()

    }
}
