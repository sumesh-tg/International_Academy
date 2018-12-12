/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.ina.application.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author 100018
 */
public class SpringTest {

    private static JdbcTemplate mySqlJdbcTemplate;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
//        DataSource dataSource = ctx.getBean(DataSource.class);
//        mySqlJdbcTemplate = new JdbcTemplate(dataSource);
//        String sql = "INSERT INTO `ia`.`log_table` (`id`, `operation_key`, `opertaion`, `tablename`, `operation_status`, `system_name`, `log_time`, `client_access`) VALUES (0, 'from spring', 'insert', 'history_enquiry_assigning_tbl', '0', 'localhost', '2016-03-04 08:54:16', NULL);";
//        mySqlJdbcTemplate.execute(sql);
        InsertTodbDAO insertTodbDAO = ctx.getBean(InsertTodbDAO.class);
        insertTodbDAO.insertData("INSERT INTO `ia_mod`.`log_table` (`id`, `operation_key`, `opertaion`, `tablename`, `operation_status`, `system_name`, `log_time`, `client_access`) VALUES (0, 'from spring', 'insert', 'history_enquiry_assigning_tbl', '0', 'localhost', '2016-03-04 08:54:16', NULL)");
        helloWorld.setMessage("Hello World!");
        helloWorld.getMessage();
    }
}
