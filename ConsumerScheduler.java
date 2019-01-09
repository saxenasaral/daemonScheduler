package net.one97.dmsch.config.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.ConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.one97.dmsch.exception.DataInconsistentException;
import net.one97.dmsch.service.impl.ConsumerServiceImpl;

@Component
public class ConsumerScheduler {

	private static final Logger log = LoggerFactory.getLogger(ConsumerScheduler.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ConsumerServiceImpl consumerService;

	@Scheduled(fixedDelay = 1000) // fixedDelay = 100000 cron="0 10 * * *?"
	public void scheduleConsumer() throws ConfigurationException, DataInconsistentException, Exception {
		log.info("Consumer scheduler is started at: ", dateFormat.format(new Date()));
		consumerService.startConsumer();
		log.info("Consumer scheduler is finished at: ", dateFormat.format(new Date()));
	}

}
