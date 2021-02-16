package my.starter.library.components;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "my.property", havingValue = "first")
public class ComponentImplFirst implements ComponentInterface {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("First Component from starter loaded");

    }
}
