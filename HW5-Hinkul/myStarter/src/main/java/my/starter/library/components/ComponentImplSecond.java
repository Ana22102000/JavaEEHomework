package my.starter.library.components;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "my.property", havingValue = "second")
public class ComponentImplSecond implements ComponentInterface {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Second Component from starter loaded");

    }
}
