package com.kk.novel.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "base-config")
public class BaseProperties {
    private boolean openAopLog = true;
}
