<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.net.URLDecoder" %>
<%!
    private static final String[] urls = new String[]{"/assets/", "/r/"};
    private static final String WEBSPHERE_URI_ATTRIBUTE = "com.ibm.websphere.servlet.uri_non_decoded";
    private static final String FORWARD_REQUEST_URI_ATTRIBUTE = "javax.servlet.forward.request_uri";
    private static final Logger logger = LoggerFactory.getLogger("_ERROR_PAGE");

    private boolean urlBeginWithResource(HttpServletRequest request) {
        String url = null;
        if ((url = getOriginatingRequestUri(request)) == null)
            return false;

        logger.info("Scheme is ["+ request.getScheme() +"], host is ["+ request.getHeader("Host") +"], port is [" + request.getServerPort() + "], context path is [" + request.getContextPath() + "], originating request uri is [" + url + "]");

        for (String u : urls) {
            if (url.startsWith(request.getContextPath() + u))
                return true;
        }

        return false;
    }

    private String getOriginatingRequestUri(HttpServletRequest request) {
        String uri = (String) request.getAttribute(WEBSPHERE_URI_ATTRIBUTE);
        if (uri == null) {
            uri = (String) request.getAttribute(FORWARD_REQUEST_URI_ATTRIBUTE);
            if (uri == null) {
                uri = request.getRequestURI();
            }
        }
        return decodeAndCleanUriString(request, uri);
    }

    private String decodeAndCleanUriString(HttpServletRequest request, String uri) {
        uri = removeSemicolonContent(uri);
        uri = decodeRequestString(request, uri);
        return uri;
    }

    private String removeSemicolonContent(String requestUri) {
        return removeSemicolonContentInternal(requestUri);
    }

    private String removeSemicolonContentInternal(String requestUri) {
        int semicolonIndex = requestUri.indexOf(';');
        while (semicolonIndex != -1) {
            int slashIndex = requestUri.indexOf('/', semicolonIndex);
            String start = requestUri.substring(0, semicolonIndex);
            requestUri = (slashIndex != -1) ? start + requestUri.substring(slashIndex) : start;
            semicolonIndex = requestUri.indexOf(';', semicolonIndex);
        }
        return requestUri;
    }

    private String decodeRequestString(HttpServletRequest request, String source) {
        return decodeInternal(request, source);
    }

    @SuppressWarnings("deprecation")
    private String decodeInternal(HttpServletRequest request, String source) {
        String enc = determineEncoding(request);
        try {
            return decode(source, enc);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return URLDecoder.decode(source);
        }
    }

    private String determineEncoding(HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = "ISO-8859-1";
        }
        return enc;
    }

    private String decode(String source, String encoding) throws UnsupportedEncodingException {
        int length = source.length();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
        boolean changed = false;
        for (int i = 0; i < length; i++) {
            int ch = source.charAt(i);
            if (ch == '%') {
                if ((i + 2) < length) {
                    char hex1 = source.charAt(i + 1);
                    char hex2 = source.charAt(i + 2);
                    int u = Character.digit(hex1, 16);
                    int l = Character.digit(hex2, 16);
                    if (u == -1 || l == -1) {
                        throw new IllegalArgumentException("Invalid encoded sequence \"" + source.substring(i) + "\"");
                    }
                     bos.write((char) ((u << 4) + l));
                    i += 2;
                    changed = true;
                } else {
                    throw new IllegalArgumentException("Invalid encoded sequence \"" + source.substring(i) + "\"");
                }
            } else {
                bos.write(ch);
            }
        }
        return changed ? new String(bos.toByteArray(), encoding) : source;
    }
%>
<%
    if(exception != null) {
        StringWriter sw = new StringWriter();
        logger.error("错误跳转页异常", exception);
        try {
            exception.printStackTrace(new PrintWriter(sw));
        } finally {
            logger.error("exception.printStackTrace", sw.toString());
        }
    }
    if (!urlBeginWithResource(request)) {
        response.sendRedirect(request.getContextPath() + "/r/e/1?url=/index");
    } else {
        out.print("Not Found");
    }
%>