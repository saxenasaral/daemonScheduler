package net.one97.dmsch.config.db;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.one97.dmsch.client.modal.impl.DaemonData;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mobiviteEntityManagerFactory", transactionManagerRef = "mobiviteTransactionManager", basePackages = {
		"net.one97.dmsch.client.repo" })
public class MobiviteDBConfig {


	@Bean(name = "mobiviteDataSource")
	@ConfigurationProperties(prefix = "mobivite.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mobiviteEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("mobiviteDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages(DaemonData.class).persistenceUnit("mobivite").build();
	}

	@Bean(name = "mobiviteTransactionManager")
	public PlatformTransactionManager barTransactionManager(
			@Qualifier("mobiviteEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
		return new JpaTransactionManager(barEntityManagerFactory);
	}


}
