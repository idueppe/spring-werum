package io.crowdcode.speedbay.auction.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DataSourceConfiguration.class)
@IfProfileValue(name = "stage", values = {"docker", "h2"})
public class DataSourceConfigurationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testForExistingDatasource() throws Exception {

        this.getClass().getResource("/x.sql");
        new ClassPathResource("scripts/x.sql");
        assertNotNull(dataSource);
    }

    @Test
    public void testSelect() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer found = jdbcTemplate.queryForObject("SELECT 2", Integer.class);
        assertThat(found, is(2));

    }


}