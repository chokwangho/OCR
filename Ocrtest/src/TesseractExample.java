
import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractExample
{
    public static void main(String[] args) {
        File imageFile = new File("samples/12.png");
  
		Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
		instance.setDatapath("C:/Users/Kwangho/git/OCR/Ocrtest/tessdata");
		instance.setLanguage("pj");
		//String x[]={"1x","2x","3x","4x"};
		
		//instance.setLanguage("o");
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
		  System.out.println(imageFile.canRead());
        try {
        	int i,j;
        	char c;
            String result = instance.doOCR(imageFile); //처음값
            String result2 =""; //결과값
            System.out.print(result);
          //나누기가 z로나와서 /로 치환
           if (result.indexOf("z")>=0){ 
            	result= result.replace("z","/");}
           //x가있는 방정식들을 solve 해주기
             if(result.indexOf("x")>=0){
            	 result2=result.replace("=","==");
              	 result2=result.replace(result,"Solve"+"["+result2+","+"x"+"]"); 
               }
             //x^n인 방정식 solve
             for(i=2; i<100; i++){ 
            	if(result.indexOf("x"+i)>=0){
                	 result2=result.replace("x"+i,"x^"+i);
                     result2=result2.replace("=","==");
                  	 result2=result.replace(result,"Solve"+"["+result2+","+"x"+"]"); 
            	    	}
                 	}
            //sinx 방적식들 solve
             if(result.indexOf("sinx")>=0){
            	 result2=result.replace("=","==");
              	 result2=result.replace(result,"Solve"+"["+result2+","+"sinx"+"]"); 
               }
             //cosx 방정식 solve
             if(result.indexOf("cosx")>=0){
            	 result2=result.replace("=","==");
              	 result2=result.replace(result,"Solve"+"["+result2+","+"cosx"+"]"); 
               }
             //tanx 방정식 solve
             if(result.indexOf("tanx")>=0){
            	 result2=result.replace("=","==");
              	 result2=result.replace(result,"Solve"+"["+result2+","+"tanx"+"]"); 
               }
             //Integrate // dx로  //범위포함
                 if(result.indexOf("Integrate")>=0){
            	 result2=result.replace("Integrate","Integrate[");
            	 if (result2.indexOf("dx")>=0){ //dx가 있으면 dx를 ]로 치환해라
                 	result2= result2.replace("dx",","+"x"+"]");}
            	//범위 있을 경우~
            	 for(j=-100;j<100; j++){
            		 for(i=-100;i<100; i++){
            			 if(result2.indexOf("]"+"\n"+j)>=0 && result2.indexOf(i+"\n"+"Integrate")>=0)
            			 { result2=result2.replace(i+"\n"+"Integrate","Integrate");
                    	   result2=result2.replace("]"+"\n"+j,"]");
                    	   result2=result2.replace(",x",","+"{x,"+j+","+i+"}");
                    		 }
                 	 	}
            	 }
             }
             //범위는 Integrate 앞  과  dx 마지막뒤에
             //if(result.indexOf("^4")>=0){ 
           // 	 result2=result.replace("^4","4승");}//지수승 그냥해본거
            // if(result.indexOf("Integrate")>=0){
            //	 result2=result.replace("Integrate","integrate[");  }
             
           //  for(i=0; i<100; i++){ // 1x~100x인것들 (1x)~(100x)로 치환
           //  if(result.indexOf(i+"x")>=0){
           // 	 result2=result.replace(i+"x","("+i+"x)"); 
            // }
            // }
                     //규칙찾기
                          
             System.out.print(result2);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
