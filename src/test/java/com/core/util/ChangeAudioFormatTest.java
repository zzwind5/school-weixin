package com.core.util;

import org.junit.Test;

public class ChangeAudioFormatTest {

	@Test
	public void changeToMp3() {
		String sourcePath = "D:\\Downloads\\wx.amr";
		String targetPath = "D:\\Downloads\\wx.mp3";
        Runtime run = null;    
        try {    
            run = Runtime.getRuntime();    
            long start=System.currentTimeMillis();    
            Process p=run.exec("D:\\Program Files\\ffmpeg\\bin\\ffmpeg -i "+sourcePath+" -acodec libmp3lame "+targetPath);//执行ffmpeg.exe,前面是ffmpeg.exe的地址，中间是需要转换的文件地址，后面是转换后的文件地址。-i是转换方式，意思是可编码解码，mp3编码方式采用的是libmp3lame  
            //释放进程    
            p.getOutputStream().close();    
            p.getInputStream().close();    
            p.getErrorStream().close();    
            p.waitFor();    
            long end=System.currentTimeMillis();    
            System.out.println(sourcePath+" convert success, costs:"+(end-start)+"ms");    
            //删除原来的文件    
            //if(file.exists()){    
                //file.delete();    
            //}    
        } catch (Exception e) {    
            e.printStackTrace();    
        }finally{    
            //run调用lame解码器最后释放内存    
            run.freeMemory();    
        }  
    }  
}
