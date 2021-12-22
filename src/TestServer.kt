import java.awt.FlowLayout
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.net.InetAddress
import java.net.ServerSocket
import javax.imageio.ImageIO
import javax.imageio.stream.MemoryCacheImageOutputStream
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel


object TestServer {

    @Throws(IOException::class, ClassNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val ss = ServerSocket(9090, 5, InetAddress.getByName(InetAddress.getLocalHost().hostAddress))
        ss.use { ss ->
            println("address is ${InetAddress.getLocalHost()} ${InetAddress.getLocalHost().hostName}")
            println("host is ${ss.inetAddress.hostAddress} ${ss.inetAddress.hostName}")
            val s = ss.accept()
            println("hello, reach here?")
//            val screenShot = Robot().createScreenCapture(Rectangle(Toolkit.getDefaultToolkit().screenSize))
//            val byteArrayO = MemoryCacheImageOutputStream(s.getOutputStream())
//            ImageIO.write(screenShot, "PNG", byteArrayO)
//            byteArrayO.flush()

//            val `is` = s.getInputStream()
//            val ois = MemoryCacheImageInputStream(`is`)
//            displayImage(ImageIO.read(ois))

            val file = File("G:\\intellijProj\\SocketTest\\src\\breadmilk.png")
            val bi = ImageIO.read(file)
            val newImage = BufferedImage(bi.width, bi.height, BufferedImage.TYPE_INT_ARGB)
            val g = newImage.createGraphics()
            g.drawImage(bi, 0, 0, null)
            g.dispose()

            val byteArrayO = MemoryCacheImageOutputStream(s.getOutputStream())
            ImageIO.write(newImage, "PNG", byteArrayO)
            byteArrayO.flush()

            println("send done ${byteArrayO.length()} ${newImage.width} ${newImage.height}")

            s.close()
        }
    }

    private fun displayImage(bufferedImage: BufferedImage) {
        val icon = ImageIcon(bufferedImage)
        val frame = JFrame()
        frame.layout = FlowLayout()
        frame.setSize(200, 300)
        val lbl = JLabel()
        lbl.icon = icon
        frame.add(lbl)
        frame.isVisible = true
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }
}