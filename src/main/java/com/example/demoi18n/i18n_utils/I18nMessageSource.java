package com.example.demoi18n.i18n_utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Slf4j
@Component
public class I18nMessageSource {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Object[] args, Locale locale, String defaultMessage) {
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
