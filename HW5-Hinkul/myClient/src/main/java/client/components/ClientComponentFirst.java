package client.components;

import my.starter.library.components.ComponentImplFirst;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(ComponentImplFirst.class)
public class ClientComponentFirst implements ClientComponentInterface {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ClientComponentFirst loaded");
    }
}
