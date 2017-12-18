package com.mrd.drools.config;

import com.mrd.drools.business.entity.TdRule;
import com.mrd.drools.business.entity.TdRuleWithBLOBs;
import com.mrd.drools.business.service.TdRuleService;
import com.mrd.drools.utils.KieUtils;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.List;

@Configuration
public class DroolsConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    private Resource[] getRuleFilse() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:rules/**/*.drl");
    }

    @Bean
    public KieFileSystem kieFileSystem() throws Exception {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();

        TdRuleService tdRuleService = applicationContext.getBean("service/rules/opera", TdRuleService.class);

        List<TdRuleWithBLOBs> list = tdRuleService.selectTdRuleList(0);

        for (TdRuleWithBLOBs tdRule : list) {
            if (!ObjectUtils.isEmpty(tdRule) && StringUtils.isNotBlank(tdRule.getRule())) {
                kieFileSystem.write("src/main/resources/rules/" + tdRule.getName() +".drl", tdRule.getRule());
            }
        }

//        for (Resource resourceFile : getRuleFilse()) {
//            kieFileSystem.write(ResourceFactory.newClassPathResource("rules/" + resourceFile.getFilename(), "UTF-8"));
//        }
        return kieFileSystem;
    }

    @Bean
    public KieContainer kieContainer() throws Exception {
        final KieRepository kieRepository = getKieServices().getRepository();

        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });

        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();

        KieContainer kieContainer = getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
        KieUtils.setKieContainer(kieContainer);
        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    @Bean
    public KieBase kieBase() throws Exception {
        return kieContainer().getKieBase();
    }

    @Bean
    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(DroolsConfig.applicationContext == null) {
            DroolsConfig.applicationContext = applicationContext;
        }
    }
}

