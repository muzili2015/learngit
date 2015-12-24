package example2015;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.iq80.leveldb.*;
import org.iq80.leveldb.impl.Iq80DBFactory;
import static org.iq80.leveldb.impl.Iq80DBFactory.*;
import java.io.*;


import java.nio.charset.Charset;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import jdk.nashorn.internal.runtime.options.Options;

@Controller  
public class UserController {  
  
    

	/*** 
     * �û���½ 
     
     * @param username
     * @param time  
     * @return 
     */  
	
    @RequestMapping("login")
    public ModelAndView login(String username,String time1){  
        //��֤���ݹ����Ĳ����Ƿ���ȷ�����򷵻ص���½ҳ�档  
       
            //ָ��Ҫ���ص�ҳ��Ϊsucc.jsp  
            ModelAndView mav = new ModelAndView("succ");  
            //���������ظ�ҳ��  
            Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//���Է�����޸����ڸ�ʽ
        String time = dateFormat.format( now ); 
        int count=0;
    	
        boolean cleanup = false;
        Charset charset = Charset.forName("utf-8");
        String path = "E:/data/leveldb";

        //init
        DBFactory factory = Iq80DBFactory.factory;
        File dir = new File(path);
        //������ݲ���Ҫreload����ÿ���������������������path�µľ����ݡ�
        if(cleanup) {
            factory.destroy(dir,null);//����ļ����ڵ������ļ���
        }
       Options options = new Options().createIfMissing(true);
        //����open�µ�db
        DB db = factory.open(dir,options);

        Charset model;
		//write
        if(username !=null && !username.equals("")){
        	db.put(username.getBytes(charset),time.getBytes(charset));
        	model.put(username, time);

        }
        


        //��ȡ��ǰsnapshot�����գ���ȡ�ڼ����ݵı�������ᷴӦ����
        Snapshot snapshot = db.getSnapshot();
        //��ѡ��
        ReadOptions readOptions = new ReadOptions();
        readOptions.fillCache(false);//������swap���������ݣ���Ӧ�ñ�����memtable�С�
        readOptions.snapshot(snapshot);//Ĭ��snapshotΪ��ǰ��
        DBIterator iterator = db.iterator(readOptions);
        while (iterator.hasNext()) {
            Map.Entry<byte[],byte[]> item = iterator.next();
            String key = new String(item.getKey(),charset);
            String value = new String(item.getValue(),charset);//null,check.
            System.out.println(key + ":" + value);
          	model.put(key, value);
            count++;
        }
        iterator.close();//must be
     
        //
        db.close();

        
    
    	mav.addObject("model",model);
    	
    	mav.addObject("count",count);
    
        mav.addObject("username",username);   
		mav.addObject("time",time);
        return mav;
    }
    
   
}


