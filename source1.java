import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.WritableRaster;
import java.util.Scanner;

/**
 *
 * @author arvin
 */
public class source1 {
    static int bin_to_string(String s,int[] bin)
    {
        if(s.charAt(0)==' ')
        {
            for (int i = 0; i < 8; i++)
                bin[i]=0;
            return 0;
        }
        byte[] bytes = s.getBytes();
           int val = bytes[0];
           for (int i = 0; i < 8; i++)
           {
              bin[i]=((val & 128) == 0 ? 0 : 1);
              val <<= 1;
           }
        return 0;
    }
    public static void main(String args[]) throws IOException 
    {
        BufferedImage bi = null;
        BufferedImage tri = null;
        File f;
        File file;
        file = new File("note.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("");
        try {
            f = new File("test.png");
            bi = ImageIO.read(f);
            tri = ImageIO.read(f);
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }    
        int[] pixel;
        int[] bin = new int[8];
        int z=8,br;
        for(int y = 0; y < bi.getHeight(); y++) 
        {
           for (int x = 0; x < bi.getWidth(); x++)
           {
               pixel = bi.getRaster().getPixel(x, y, new int[4]);
               br=pixel[0]&1;
               //System.out.println((bi.getWidth()*y)+x);
               pixel[2]=pixel[2]>>1;
               pixel[2]=pixel[2]<<1;
               pixel[2]=pixel[2]|br;
               if(z==8 && sc.hasNext())
               { 
                    String temp=sc.next();
                    bin_to_string(temp,bin);
                    z=0;
                }
               if(z<8) pixel[0]=bin[z]^pixel[0];
               if(z==8) pixel[1]=0;
               WritableRaster raster = tri.getRaster();
               raster.setPixel(x, y, pixel);
               z++;
            }
        }
        try {
               f = new File("Output.png");
               ImageIO.write(tri, "png", f);
            } catch (IOException e) {
            System.out.println("Error: " + e);
            }
            sc.close();
   }
}
