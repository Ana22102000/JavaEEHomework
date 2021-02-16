package client.components;

import my.starter.library.components.ComponentImplFirst;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ComponentImplFirst.class)
public class ClientComponentNotFirst implements ClientComponentInterface {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ClientComponentNotFirst loaded");
    }
}
