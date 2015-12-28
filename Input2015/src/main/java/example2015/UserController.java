package example2015;


import example2015.LdbOperater;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.Map;

import org.springframework.stereotype.Controller;    
   
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller  
public class UserController {  
  
    

	/*** 
     * �û���½ 
     
     * @param username  
     * @return 
     */  
	@RequestMapping(value="login",method=RequestMethod.POST)
    public ModelAndView login(String username)throws IOException{  
        //��֤���ݹ����Ĳ����Ƿ���ȷ�����򷵻ص���½ҳ�档  
		if(this.checkParams(username)){
            //ָ��Ҫ���ص�ҳ��Ϊsucc.jsp  
    	Date now=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String time =df.format(now);
	    ModelAndView mav = new ModelAndView("succ");  
            //���������ظ�ҳ��
        mav.addObject("username",username);  
		mav.addObject("time", time); 
		
		LdbOperater operater=new LdbOperater();
		Map<String,String> beforeVisit1=operater.displayPeople(username);
		mav.addObject("map",beforeVisit1);
		mav.addObject("num",operater.getNum());
            return mav;  
        } 
		return new ModelAndView("home");
    
	}
	private boolean checkParams(String param){
		
		if(param==""||param==null||param.isEmpty()){
			return false;
		}
	
	return true;
}
}

