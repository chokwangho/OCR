
import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractExample
{
    public static void main(String[] args) {
        File imageFile = new File("samples/sample.integrate.exp0.tif");
  
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		instance.setDatapath("C:/Users/Kwangho/workspace/Ocrtest/tessdata");
		instance.setLanguage("sample");
		//instance.setLanguage("o");
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		  System.out.println(imageFile.canRead());
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
