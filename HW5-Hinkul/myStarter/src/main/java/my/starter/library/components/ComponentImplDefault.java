package my.starter.library.components;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "my.property", havingValue = "default")
public class ComponentImplDefault implements ComponentInterface {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Default Component from starter loaded");

    }
}
