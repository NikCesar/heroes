import config.ServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HeroApplication {

    public static void main(String[] args) {

        /*
        AnnotationConfigApplicationContext javaContext = new AnnotationConfigApplicationContext();
        javaContext.scan("service.impl");
        javaContext.refresh();
        */

       AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(ServiceConfig.class);

    }
}
