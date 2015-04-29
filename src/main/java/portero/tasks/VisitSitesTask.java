package portero.tasks;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import portero.dao.OpportunityRepository;
import portero.domain.Opportunity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.String.format;

@Component
public class VisitSitesTask {

    public static final Log log = LogFactory.getLog(VisitSitesTask.class);

    @Autowired
    private OpportunityRepository repository;

    @Scheduled(fixedRateString = "${portero.visit.rate}")
    public void reportCurrentTime() throws IOException {

        for (Opportunity opportunity: repository.findByActive(true)) {
            log.info(format("visiting %s - %s - %s", opportunity.getId(), opportunity.getUrl(), opportunity.getCookies()));

            URL url = new URL(opportunity.getUrl());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Cookie", opportunity.getCookies());

            connection.connect();

            int responseCode = connection.getResponseCode();
            if( responseCode != 200){
                log.error(format("failed %s - %s with %d", opportunity.getId(), opportunity.getUrl(), responseCode));
                opportunity.setActive(false);
                repository.save(opportunity);
            }
        }

    }

}
