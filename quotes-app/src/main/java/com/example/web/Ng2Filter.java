package com.example.web;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Ng2Filter implements Filter {
	private final String ng2ContextPath;
	private final Map<String, String> extensionTypeMap;
	private boolean enabled;

	public Ng2Filter(String ng2ContextPath) {
		if (Strings.isNullOrEmpty(ng2ContextPath)) {
			throw new RuntimeException("contextPath cannot be null or empty");
		}
		this.ng2ContextPath = ng2ContextPath;
		extensionTypeMap = ImmutableMap.<String, String>builder().
			put("js", "application/javascript").
			put("css", "text/css").
			put("html", "text/html").
			put("png", "image/png").
			put("jpg", "image/jpeg").
			put("gif", "image/gif").
			put("svg", "image/svg+xml").
			put("eot", "application/vnd.ms-fontobject").
			put("ttf", "application/font-sfnt").
			put("otf", "application/font-sfnt").
			put("woff", "application/font-woff").
			put("woff2", "application/font-woff2").
			put("ico", "image/vnd.microsoft.icon").
			build();

		enabled = false;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (enabled && request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String pathWithinApp = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
			String decodedPathWithinApp = URLDecoder.decode(pathWithinApp, "UTF-8");

			if (serveNg2(decodedPathWithinApp, response)) {
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private boolean serveNg2(String decodedPathWithinApp, ServletResponse response) throws IOException {
		if (!decodedPathWithinApp.startsWith(ng2ContextPath)) {
			return false;
		}
		decodedPathWithinApp = decodedPathWithinApp.substring(ng2ContextPath.length());
		if (decodedPathWithinApp.contains("/")) {
			return false;
		}

		String ext = Files.getFileExtension(decodedPathWithinApp);
		if (!extensionTypeMap.containsKey(ext)) {
			return false;
		}

		try {
			URL url = new URL(String.format("http://127.0.0.1:4200/%s", decodedPathWithinApp));
			setContentTypeBasedOnExtension(response, ext);
			Resources.asByteSource(url).copyTo(response.getOutputStream());
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private void setContentTypeBasedOnExtension(ServletResponse response, String ext) {
		String contentType = extensionTypeMap.get(ext);
		if (contentType != null) {
			response.setContentType(contentType);
		}
	}

	@Override
	public void destroy() {
	}
}
