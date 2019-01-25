package br.com.security;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.security.service.AuthCode;
import br.com.security.sms.Zenvia;

@SpringBootApplication
@EnableAutoConfiguration
public class WsSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsSecurityApplication.class, args);
	}

	@Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }

	@Bean
	public AuthCode authCode() {
		return new AuthCode();
	}

	@Bean
	public Zenvia getZenvia() {
		return new Zenvia();
	}
}
