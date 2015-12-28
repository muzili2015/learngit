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
     * 用户登陆 
     
     * @param username  
     * @return 
     */  
	@RequestMapping(value="login",method=RequestMethod.POST)
    public ModelAndView login(String username)throws IOException{  
        //验证传递过来的参数是否正确，否则返回到登陆页面。  
		if(this.checkParams(username)){
            //指定要返回的页面为succ.jsp  
    	Date now=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time =df.format(now);
	    ModelAndView mav = new ModelAndView("succ");  
            //将参数返回给页面
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

