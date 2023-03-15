package cn.j.chloe.utils.i18n_utils;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * Nacos配置管理器
 * @author zz
 * @date 2022/06/14 10:22
 **/
@Slf4j
@Component
public class NacosConfig {
    /**
     * 应用名称
     */
    private static final String applicationName = "chloe";
    /**
     * 命名空间
     */
    private String dNamespace;

    @Resource
    private ConfigurableApplicationContext applicationContext;

    private static final String I18N_GROUP = "i18n";
    private static final String DEFAULT_NAMESPACE = "7f42db8d-9779-488f-9f29-c74500482f41";
    private final String SUPPORT_LANGUAGES = "support_languages";
    Properties properties = new Properties();

    @PostConstruct
    public void init() {
        //服务器地址
        String serverAddr = applicationContext.getEnvironment().getProperty("nacos.host");
        dNamespace = applicationContext.getEnvironment().getProperty("nacos.namespace");
        if (StringUtils.isEmpty(dNamespace)) {
            dNamespace = DEFAULT_NAMESPACE;
        }
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, dNamespace);
        try {
            //根据i18n下dataId为support_languages的zhi来初始化properties
            ConfigService configService = NacosFactory.createConfigService(properties);
            initSupportLanguages(configService.getConfig(SUPPORT_LANGUAGES, I18N_GROUP, 1000));
            //监听dataId：support_languages变动
            setSupportLanguageListener(configService);
        } catch (NacosException e) {
            log.error("获取nacos配置信息错误!异常信息:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("初始化系统参数成功!应用名称:{},Nacos地址:{},提示语命名空间:{}", applicationName, serverAddr, dNamespace);
    }

    //根据support_languages初始化
    private void initSupportLanguages(String supportLanguages) {
        String[] languages = supportLanguages.split(",");
        for(String lang: languages){
            initTip(lang.trim());
        }
    }

    //设置support_languages监听
    private void setSupportLanguageListener(ConfigService configService) throws com.alibaba.nacos.api.exception.NacosException {
        configService.addListener(SUPPORT_LANGUAGES, I18N_GROUP, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("接收到新增的语言配置!配置内容:{}", configInfo);
                try {
                    initSupportLanguages(configInfo);
                } catch (Exception e) {
                    log.error("初始化国际化配置异常!异常信息:{}", ExceptionUtils.getStackTrace(e));
                }
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }

    //更新本地配置
    private void initTip(String dataId) {
        String content;
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            content = configService.getConfig(dataId, I18N_GROUP, 1000);
            if (StringUtils.isEmpty(content)) {
                log.warn("配置内容为空,跳过初始化!dataId:{}", dataId);
                return;
            }
            log.info("初始化国际化配置!配置内容:{}", content);
            saveAsFileWriter(dataId, content);
            setListener(configService, dataId);
        } catch (Exception e) {
            log.error("初始化国际化配置异常!异常信息:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    //语言配置文件监听，变动则更新对应配置
    private void setListener(ConfigService configService, String dataId) throws com.alibaba.nacos.api.exception.NacosException {
        configService.addListener(dataId, I18N_GROUP, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("接收到新的国际化配置!配置内容:{}", configInfo);
                try {
                    initTip(dataId);
                } catch (Exception e) {
                    log.error("初始化国际化配置异常!异常信息:{}", ExceptionUtils.getStackTrace(e));
                }
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }

    private void saveAsFileWriter(String fileName, String content) {
        String path = System.getProperty("user.dir") + File.separator + "src/main/resources/i18n/";
        try {
            fileName = path + fileName;
            File file = new File(fileName);
            FileUtils.writeStringToFile(file, content, "UTF-8");
            log.info("国际化配置已更新!本地文件路径:{}", fileName);
        } catch (IOException e) {
            log.error("初始化国际化配置异常!本地文件路径:{}异常信息:{}", fileName, ExceptionUtils.getStackTrace(e));
        }
    }

}
