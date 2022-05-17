package com.nsv.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public static final String SYSTEM = "system";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(SYSTEM));
    }
}
