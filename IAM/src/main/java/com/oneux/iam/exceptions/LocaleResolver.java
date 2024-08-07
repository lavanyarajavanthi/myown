package com.oneux.iam.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class LocaleResolver extends AcceptHeaderLocaleResolver {

	private static final List<Locale> LOCALES = Arrays.asList(new Locale("en"));

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String language = request.getHeader("Accept-Language");
		if (language == null || language.isEmpty()) {
			return Locale.getDefault();
		}
		List<Locale.LanguageRange> list = Locale.LanguageRange.parse(language);
		return Locale.lookup(list, LOCALES);
	}

	public Locale resolveLocaleWebReq(WebRequest request) {
		String language = request.getHeader("Accept-Language");
		if (language == null || language.isEmpty()) {
			return Locale.getDefault();
		}
		List<Locale.LanguageRange> list = Locale.LanguageRange.parse(language);
		return Locale.lookup(list, LOCALES);
	}

}