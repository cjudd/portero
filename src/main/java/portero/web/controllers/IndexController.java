package portero.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import portero.dao.OpportunityRepository;
import portero.domain.Opportunity;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private static Log log = LogFactory.getLog(IndexController.class);

    @Autowired
    OpportunityRepository repository;

    @RequestMapping({"", "home"})
    public String index(Model model) {
        List<Opportunity> opportunities = repository.findByActive(true);
        for (Opportunity opportunity: opportunities) {
            log.error(opportunity);
        }

        model.addAttribute("opportunities", opportunities);

        return "home";
    }

}