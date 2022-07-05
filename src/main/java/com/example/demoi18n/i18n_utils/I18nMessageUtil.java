package com.example.demoi18n.i18n_utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Slf4j
@Component
public class I18nMessageUtil {
    private static final Locale defaultLocale = Locale.ENGLISH;

    @Resource
    private I18nMessageSource i18nMessageSource;


    public static String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, locale);
    }

    public static String getMessage(String code, Locale locale) {
        return getMessage(code, null, locale);
    }

    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, defaultLocale);
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return getMessage(code, args, locale, "null");
    }

    public static String getMessage(String code, Object[] args, Locale locale, String defaultMessage) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1);
        String content;
        try {
            content = messageSource.getMessage(code, args, locale);
        } catch (Exception e) {
            log.info("获取提示消息失败： ->", e);
            content = defaultMessage;
        }
        return content;

    }
}