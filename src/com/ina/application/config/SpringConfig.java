/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.ina.application.config;

import com.zs.ina.admin.master.emailtemplate.dao.EmailTempDAO;
import com.zs.ina.admin.master.emailtemplate.dao.EmailTempIMPL;
import com.zs.ina.admin.master.emailtemplate.signature.dao.SignatureDAO;
import com.zs.ina.admin.master.emailtemplate.signature.dao.SignatureIMPL;
import com.zs.ina.common.email.dao.MailSentDAO;
import com.zs.ina.common.email.dao.MailSentIMPL;
import com.zs.ina.login.dao.ProfilePOJO;
import com.zs.ina.registration.documents.dao.DocumentVerifyDAO;
import com.zs.ina.registration.documents.dao.DocumentVerifyIMPL;
import java.util.prefs.Preferences;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import test.HelloWorld;
import test.InsertTodbDAO;
import test.InsertTodbIMPL;

/**
 *
 * @author SUMESH
 */
@Configuration
@ComponentScan("com.zs")
@PropertySource("DBPool.properties")
@Import({EmailConfig.class,SearchMasterBeansConfig.class,EmployerBeansConfig.class,EducationalProviderBeansConfig.class
,MigrationProviderBeansConfig.class,TrainingCourseBeansConfig.class,AccountsConfig.class})
//@Import(value = SearchMasterBeans.class)
//@ImportResource("classpath:email-configure.xml")
public class SpringConfig {

    private Preferences myfilePrefs;

    @Value("${pool-mysql.url}")
    String mysqlURL;
    @Value("${pool-mysql.user}")
    String user;
    @Value("${pool-mysql.password}")
    String password;

    @Bean
    ProfilePOJO profilePOJO() {
        return new ProfilePOJO();
    }

    @Bean
    HelloWorld helloWorld() {
        return new HelloWorld();
    }

    /**
     *
     * @return
     */
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        myfilePrefs = Preferences.userRoot();
        myfilePrefs = myfilePrefs.node("com.zs.ina.login.INALoginForm");

        if (myfilePrefs.get("server", "not found").equalsIgnoreCase("not found") || myfilePrefs.get("db", "not found").equalsIgnoreCase("not found")
                || myfilePrefs.get("user", "not found").equalsIgnoreCase("not found") || myfilePrefs.get("password", "not found").equalsIgnoreCase("not found")
                || myfilePrefs.get("server", "not found").equalsIgnoreCase("") || myfilePrefs.get("db", "").equalsIgnoreCase("")
                || myfilePrefs.get("user", "not found").equalsIgnoreCase("") || myfilePrefs.get("password", "not found").equalsIgnoreCase("")) {
            /* ====================== If no preference values found ====================== */
            dataSource.setUrl(mysqlURL);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            System.out.println("Spring Connected From Properties File :: " + mysqlURL);
        } else {
            dataSource.setUrl("jdbc:mysql://" + myfilePrefs.get("server", "not found") + "/" + myfilePrefs.get("db", "not found"));
            dataSource.setUsername(myfilePrefs.get("user", "not found"));
            dataSource.setPassword(myfilePrefs.get("password", "not found"));
            System.out.println("Spring Connected From Preference :: " + mysqlURL);
        }
        return dataSource;
    }

    /**
     *
     * @return
     */
    static @Bean
    public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[]{
            new ClassPathResource("DBPool.properties"),
            new ClassPathResource("log4j.properties"),};
        p.setLocations(resourceLocations);
        return p;
    }

    @Bean
    static InsertTodbDAO insertTodbDAO() {
        return new InsertTodbIMPL();
    }

    /**
     *
     * @return
     */
    @Bean
    public DocumentVerifyDAO documentVerfiyDAO() {
        return new DocumentVerifyIMPL();
    }

    @Bean
    MailSentDAO mailSentDAO() {
        return new MailSentIMPL();
    }

    @Bean
    EmailTempDAO emailTempDAO() {
        return new EmailTempIMPL();
    }

    @Bean
    SignatureDAO signatureDAO() {
        return new SignatureIMPL();
    }


}
