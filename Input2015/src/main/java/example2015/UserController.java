package example2015;


import org.springframework.stereotype.Controller;    
import org.springframework.ui.Model;    
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
@Controller  
public class UserController {  
  
    

	/*** 
     * �û���½ 
     
     * @param username  
     * @return 
     */  
    @RequestMapping("login")
    public ModelAndView login(String username){  
        //��֤���ݹ����Ĳ����Ƿ���ȷ�����򷵻ص���½ҳ�档  
       
            //ָ��Ҫ���ص�ҳ��Ϊsucc.jsp  
            ModelAndView mav = new ModelAndView("succ");  
            //���������ظ�ҳ��  
           
		mav.addObject("username",username);  
              
            return mav;  
        }  
    
    
}

