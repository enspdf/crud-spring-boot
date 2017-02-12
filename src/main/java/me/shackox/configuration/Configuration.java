package me.shackox.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * Created by SHACKOX on 22/01/17.
 */

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}
