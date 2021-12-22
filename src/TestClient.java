import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) throws AWTException, IOException {
        BufferedImage screenShot = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        Socket socket = new Socket("localhost", 11111);
        MemoryCacheImageOutputStream byteArrayO = new MemoryCacheImageOutputStream(socket.getOutputStream());
        ImageIO.write(screenShot, "PNG", byteArrayO);
        byteArrayO.flush();
        socket.close();
    }
}
