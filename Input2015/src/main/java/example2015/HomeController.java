package example2015;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;    
import org.springframework.ui.Model;    
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView; 
@Controller
public class HomeController {
	@RequestMapping(value="home.do")  
    public ModelAndView index(){  
        //����ģ�͸���ͼ��������Ⱦҳ�档����ָ��Ҫ���ص�ҳ��Ϊhomeҳ��  
        ModelAndView mav = new ModelAndView("home");  
        return mav;  
    }  
	 
}
