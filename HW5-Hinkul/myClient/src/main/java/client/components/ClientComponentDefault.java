package client.components;

import my.starter.library.components.ComponentImplDefault;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(ComponentImplDefault.class)
public class ClientComponentDefault implements ClientComponentInterface {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ClientComponentDefault loaded");
    }
}
