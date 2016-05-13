
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
            String result = instance.doOCR(imageFile); //ó����
            String result2 =""; //�����
            System.out.print(result);
          //�����Ⱑ z�γ��ͼ� /�� ġȯ
           if (result.indexOf("z")>=0){ 
            	result= result.replace("z","/");}
           //x���ִ� �����ĵ��� solve ���ֱ�
             if(result.indexOf("x")>=0){
            	 result2=result.replace("=","==");
              	 result2=result.replace(result,"Solve"+"["+result2+","+"x"+"]"); 
               }
             //x^n�� ������ solve
             for(i=2; i<100; i++){ 
            	if(result.indexOf("x"+i)>=0){
                	 result2=result.replace("x"+i,"x^"+i);
                     result2=result2.replace("=","==");
                  	 result2=result.replace(result,"Solve"+"["+result2+","+"x"+"]"); 
            	    	}
                 	}
            //sinx �����ĵ� solve
             if(result.indexOf("sinx")>=0){
            	 result2=result.replace("=","==");
              	 result2=result.replace(result,"Solve"+"["+result2+","+"sinx"+"]"); 
               }
             //cosx ������ solve
             if(result.indexOf("cosx")>=0){
            	 result2=result.replace("=","==");
              	 result2=result.replace(result,"Solve"+"["+result2+","+"cosx"+"]"); 
               }
             //tanx ������ solve
             if(result.indexOf("tanx")>=0){
            	 result2=result.replace("=","==");
              	 result2=result.replace(result,"Solve"+"["+result2+","+"tanx"+"]"); 
               }
             //Integrate // dx��  //��������
                 if(result.indexOf("Integrate")>=0){
            	 result2=result.replace("Integrate","Integrate[");
            	 if (result2.indexOf("dx")>=0){ //dx�� ������ dx�� ]�� ġȯ�ض�
                 	result2= result2.replace("dx",","+"x"+"]");}
            	//���� ���� ���~
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
             //������ Integrate ��  ��  dx �������ڿ�
             //if(result.indexOf("^4")>=0){ 
           // 	 result2=result.replace("^4","4��");}//������ �׳��غ���
            // if(result.indexOf("Integrate")>=0){
            //	 result2=result.replace("Integrate","integrate[");  }
             
           //  for(i=0; i<100; i++){ // 1x~100x�ΰ͵� (1x)~(100x)�� ġȯ
           //  if(result.indexOf(i+"x")>=0){
           // 	 result2=result.replace(i+"x","("+i+"x)"); 
            // }
            // }
                     //��Ģã��
                          
             System.out.print(result2);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
