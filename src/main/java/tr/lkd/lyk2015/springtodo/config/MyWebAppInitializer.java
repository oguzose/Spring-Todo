package tr.lkd.lyk2015.springtodo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//Servletdir.
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    
    //conf bilgileri webconfig.class da
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    
    // / dan sonra gelen hersey bana gelsin diyebiliriz..
    // FrontController
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}