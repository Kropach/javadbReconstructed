package KR.Config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCDispatcherServletInitializor extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{HibernateConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {Config.class};
    }
    //любой юрл который напишет наш пользователь в браузере когда обращается к нашему серверу должен перенаправляться
    //на наш диспетчер сервлет
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
