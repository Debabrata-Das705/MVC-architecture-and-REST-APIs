package com.mvc.MVCDemo.Auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AudittorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Debabrata Das");
    }
}
