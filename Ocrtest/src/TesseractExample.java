
import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractExample
{
    public static void main(String[] args) {
        File imageFile = new File("samples/sample.integrate.exp0.tif");
  
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		instance.setDatapath("C:/Users/Kwangho/git/OCR/Ocrtest/tessdata");
		instance.setLanguage("sample");
		//instance.setLanguage("o");
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		  System.out.println(imageFile.canRead());
        try {
            String result = instance.doOCR(imageFile);
            	System.out.print(result);
             if (result.indexOf("dx")>=0){ //dx가 있으면 dx를 ]로 치환해라
            	result= result.replace("dx","]");}
             
             if(result.indexOf("^4")>=0){
            	 result=result.replace("^4","4승");}
             if(result.indexOf("integrate")>=0){
            	 result=result.replace("integrate","integrate[");  }
             //규칙찾기
             System.out.print(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
