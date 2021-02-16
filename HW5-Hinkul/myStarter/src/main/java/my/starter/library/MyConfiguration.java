package my.starter.library;

import my.starter.library.components.ComponentImplFirst;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ComponentImplFirst.class)
public class MyConfiguration {


}
