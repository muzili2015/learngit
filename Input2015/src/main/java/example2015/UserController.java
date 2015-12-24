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
     * 用户登陆 
     
     * @param username
     * @param time  
     * @return 
     */  
	
    @RequestMapping("login")
    public ModelAndView login(String username,String time1){  
        //验证传递过来的参数是否正确，否则返回到登陆页面。  
       
            //指定要返回的页面为succ.jsp  
            ModelAndView mav = new ModelAndView("succ");  
            //将参数返回给页面  
            Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format( now ); 
        int count=0;
    	
        boolean cleanup = false;
        Charset charset = Charset.forName("utf-8");
        String path = "E:/data/leveldb";

        //init
        DBFactory factory = Iq80DBFactory.factory;
        File dir = new File(path);
        //如果数据不需要reload，则每次重启，尝试清理磁盘中path下的旧数据。
        if(cleanup) {
            factory.destroy(dir,null);//清除文件夹内的所有文件。
        }
       Options options = new Options().createIfMissing(true);
        //重新open新的db
        DB db = factory.open(dir,options);

        Charset model;
		//write
        if(username !=null && !username.equals("")){
        	db.put(username.getBytes(charset),time.getBytes(charset));
        	model.put(username, time);

        }
        


        //读取当前snapshot，快照，读取期间数据的变更，不会反应出来
        Snapshot snapshot = db.getSnapshot();
        //读选项
        ReadOptions readOptions = new ReadOptions();
        readOptions.fillCache(false);//遍历中swap出来的数据，不应该保存在memtable中。
        readOptions.snapshot(snapshot);//默认snapshot为当前。
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


