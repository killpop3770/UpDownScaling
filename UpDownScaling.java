import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class UpDownScaling 
{

    static String fileName;
    static int width;
    static int height;

    public static void resize(BufferedImage source, int w2,int h2) throws Exception 
    {
        BufferedImage result = new BufferedImage(w2, h2, source.getType());

        int w1 = source.getWidth();
        int h1 = source.getHeight();
        
        int x_ratio = (int)((w1<<16)/w2)+1;
        int y_ratio = (int)((h1<<16)/h2)+1;
    
        int x2, y2;
        for (int i=0; i<h2; i++)
        {
            for (int j=0; j<w2; j++) 
            {
        
                x2 = ((j*x_ratio)>>16) ;
                y2 = ((i*y_ratio)>>16) ;

                result.setRGB(j, i, source.getRGB(x2,y2));
            }                
        }  

        File output = new File("bit.jpg");
        ImageIO.write(result, "jpg", output);
        System.out.println("Name of your file : bit.jpg!");
    }

    public static void scan()
    {

        Scanner scan = new Scanner(System.in);
        System.out.print("Input name of file: ");
        fileName = scan.nextLine();
        System.out.printf("Name of file: %s \n", fileName);

        System.out.print("Input a width: ");
        width = scan.nextInt();
        System.out.printf("Your width: %d \n", width);
        
        System.out.print("Input a height: ");
        height = scan.nextInt();
        System.out.printf("Your height: %d \n", height);

        scan.close();

    }


    public static void main(String[] args) throws Exception
    {
        scan();

        File file = new File(fileName);
        BufferedImage source = ImageIO.read(file);

        resize(source, width, height);
    }


}
