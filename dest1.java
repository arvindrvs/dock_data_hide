import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 *
 * @author arvin
 */
public class dest1 {
    static char bin_to_string(StringBuilder tem)
    {
        String x=tem.toString();
        String f="00000000";
        char d=' ';
        if(x.equals(f)) return d;
        int a=Integer.parseInt(x,2);
        char c=(char)a;
        return c;
    }
    public static void main(String args[]) throws IOException 
    {
        BufferedImage bi = null;
        File f;
        try {
            f = new File("Output.png");
            bi = ImageIO.read(f);
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }    
        int[] pixel;
        StringBuilder tem=new StringBuilder();
        //String binary;
        int z=0;
        int b,br,cr;
        char ch;
        //StringBuilder t=new StringBuilder();
        try{
        PrintWriter writer = new PrintWriter("/vol1/myfile.txt");
        writer.print("");
        writer.close();
        }catch(Exception e){}
        try(FileWriter fw = new FileWriter("/vol1/myfile.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
        for(int y = 0; y < bi.getHeight(); y++) 
        {
           for (int x = 0; x < bi.getWidth(); x++)
           {
               pixel = bi.getRaster().getPixel(x, y, new int[4]);
               br=pixel[2]&1;
               cr=pixel[0]&1;
               if(z==0 && pixel[1]==0) z=10;
               if(z<8) 
               {
                   b=br^cr;                   
                   tem.append(b);
               }
               if(z==7 && pixel[1]!=0)
               { 
                    ch=bin_to_string(tem);
                    out.print(ch);
                    z=-1;
                    tem.delete(0, 8);
                }
               z++;
            }
        }
        } catch (IOException e) {
    //exception handling left as an exercise for the reader
}
        System.out.println("success");
   }
}
