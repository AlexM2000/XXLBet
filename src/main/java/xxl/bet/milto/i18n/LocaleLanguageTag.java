package xxl.bet.milto.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxl.bet.milto.exceptions.PropertyNotFoundException;
import xxl.bet.milto.utils.PropertyLoader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class LocaleLanguageTag extends TagSupport {

    private String key;
    private static final Logger LOG = LoggerFactory.getLogger(LocaleLanguageTag.class);

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        String language = "en";

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie temp : cookies) {
                if (temp.getName().equals("language")) {
                    language = temp.getValue();
                }
            }

            String message = PropertyLoader.getInstance().getStringProperty("/messages_" + language + ".properties", key)
                    .orElseThrow(() -> new PropertyNotFoundException("No message for  locale found! key - " + key));

            try {
                out.print(message);
            } catch (IOException e) {
                LOG.error("Could not print message! locale {} message {}", language, message, e);
            }
        }

        return EVAL_PAGE;
    }


    public void setKey(String key) {
        this.key = key;
    }
}