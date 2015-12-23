package example2015;


import org.springframework.stereotype.Controller;    
import org.springframework.ui.Model;    
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
@Controller  
public class UserController {  
  
    

	/*** 
     * 用户登陆 
     
     * @param username  
     * @return 
     */  
    @RequestMapping("login")
    public ModelAndView login(String username){  
        //验证传递过来的参数是否正确，否则返回到登陆页面。  
       
            //指定要返回的页面为succ.jsp  
            ModelAndView mav = new ModelAndView("succ");  
            //将参数返回给页面  
           
		mav.addObject("username",username);  
              
            return mav;  
        }  
    
    
}

