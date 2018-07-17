/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月16日
 * 修改历史：
 * 		1、[2018年7月16日]创建文件 by zhaocs
 */
package com.tikie.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * @author zhaocs
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
@ComponentScan("com.tikie")
public class ThymeleafConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
      this.applicationContext = applicationContext;
    }

    @Bean
    public ViewResolver viewResolver() {
      ThymeleafViewResolver resolver = new ThymeleafViewResolver();
      resolver.setTemplateEngine((ISpringTemplateEngine) templateEngine());
      resolver.setCharacterEncoding("UTF-8");
      return resolver;
    }

    @Bean
    public TemplateEngine templateEngine() {
      SpringTemplateEngine engine = new SpringTemplateEngine();
      engine.setEnableSpringELCompiler(true);
      engine.setTemplateResolver(templateResolver());
      return engine;
    }

    private ITemplateResolver templateResolver() {
      SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
      resolver.setApplicationContext(applicationContext);
      resolver.setPrefix("/WEB-INF/templates/");
      resolver.setTemplateMode(TemplateMode.HTML);
      return resolver;
    }

}
