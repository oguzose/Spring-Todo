package tr.lkd.lyk2015.springtodo.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

	public static final String MAIN_LAYOUT ="layout/main";
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String originalViewName = modelAndView.getViewName();
		
		if(isRedirectOrForward(originalViewName)){
			return;
		}
		
		//Redirect : Request response dönüşüyor. Sunucudan clienta gidiyor.
		//redirect header body gibi parametrelere gidiyoruz
		//Farword : Request bir servletten başka servlete geçiyoruz. kendi servletemiz içindeyiz
		
		modelAndView.setViewName(MAIN_LAYOUT);
		modelAndView.addObject("layout_main", originalViewName);
		
	}

	private boolean isRedirectOrForward(String originalViewName) {
		return originalViewName.startsWith("redirect:") || originalViewName.startsWith("forward:");
	}
	

}
