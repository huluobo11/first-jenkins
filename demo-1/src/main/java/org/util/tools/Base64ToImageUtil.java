package org.util.tools;

import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
  
public class Base64ToImageUtil   
{  
    public static void main(String[] args)  
    {  
//        String strImg = GetImageStr();  
//        GenerateImage(strImg,"G://");
        {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        	System.out.println(Math.random());
        	
//        	
//        	
//            String imgFile = "G://83bOOOPIC72.jpg";//待处理的图片  
//            InputStream in = null;  
//            byte[] data = null;  
//            //读取图片字节数组  
//            try   
//            {  
//                in = new FileInputStream(imgFile);          
//                data = new byte[in.available()];  
//                in.read(data);  
//                in.close();  
//            }   
//            catch (IOException e)   
//            {  
//                e.printStackTrace();  
//            }  
//            //对字节数组Base64编码  
//            BASE64Encoder encoder = new BASE64Encoder();  
//            String str=encoder.encode(data);
//            String pa="json={\"cmd\": \"uploadPhoto\",\"params\": {\"userName\": \"15939034410\" , \"userImage\": \""+str+"\"} }";
//            System.out.println(pa);
//            pa=pa.replace("+", "");
//            System.out.println(pa);
//            String res;
//			try {
//				System.out.println(res);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
        }
    }  
    //图片转化成base64字符串  
    public static String GetImageStr()  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        String imgFile = "G://IMAG0488.jpg";//待处理的图片  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
      
    //base64字符串转化成图片  
    public static String GenerateImage(String imgStr,String path)  
    {   //对字节数组字符串进行Base64解码并生成图片  
    	imgStr=imgStr.replace(" ", "");
        if (imgStr == null || "".equals(imgStr)) //图像数据为空  
            return "";  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgName=StringUtils.getRandomString(20)+".jpg";
            String imgFilePath = path+imgName;//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return imgName;  
        }  catch (Exception e) {  
            return null;  
        }  
    } 
}  