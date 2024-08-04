package springex.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppTest {
    @Test
    void filterScan()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean(BeanA.class);
        assertThat(beanA).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class,()->ac.getBean(BeanB.class));

    }
    @Configuration
    @ComponentScan(
            includeFilters =@Filter(classes=MyincludeComp.class), //MyincludeComp붙은 빈 등록됨
            excludeFilters =@Filter(type= FilterType.ANNOTATION,classes=MyexcludeComp.class)

    )
    static class ComponentFilterAppConfig{

    }

}
