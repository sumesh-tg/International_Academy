/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.notifications.dao;

import com.zs.ina.common.UiiDGenerator;
import com.zs.ina.context.Context;
import com.zs.ina.utility.ClosingResourcesInDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import zs.com.ina.db.mysql.pool.DBPool;

/**
 *
 * @author zscomp1
 */
public class NotificationsDAO {

    private static boolean idExists;

    /**
     *
     */
    public NotificationsDAO() {
    }

    //Method for insert new offers
    /**
     *
     * @return
     */
    public static int insertNewOffers() {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "insert into newoffers_table(created_date,users,title,description,expired_date,branch_id) "
                    + "values((Select curdate()),(select role_id from role_tbl where role='" + Context.getInstance().currentProfile().getNewOffersPOJO().getUsers()
                    + "'),?,?,?,(Select branch_id from branch_tbl where branch_name='" + Context.getInstance().currentProfile().getNewOffersPOJO().getBranch() + "'))";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getNewOffersPOJO().getTitle());
            ps.setString(2, Context.getInstance().currentProfile().getNewOffersPOJO().getDescription());
            ps.setDate(3, (new java.sql.Date(Context.getInstance().currentProfile().getNewOffersPOJO().getExpiration_date().getTime())));
            System.out.println(query.toString());
            row = ps.executeUpdate();
            if (row > 0) {
                setCounter("offer");

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for check Date...
    /**
     *
     * @param chekDate
     * @return
     */
    public static int checkExpireDate(java.sql.Date chekDate) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int checkFlag = -1;
        con = DBPool.getInstance().getConn();
        String query = " select datediff('" + chekDate + "',CURDATE())";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            checkFlag = rs.getInt(1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return checkFlag;
    }
//Method for update new offers

    /**
     *
     * @param offerID
     * @return
     */
    public static int updateNewOffers(int offerID) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "update newoffers_table set users=(select role_id from role_tbl where role='" + Context.getInstance().currentProfile().getNewOffersPOJO().getUsers() + "'),"
                    + "title=?,description=?,expired_date=?,branch_id=(Select branch_id from branch_tbl where branch_name='" + Context.getInstance().currentProfile().getNewOffersPOJO().getBranch() + "')"
                    + "   where id=" + offerID + "";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getNewOffersPOJO().getTitle());
            ps.setString(2, Context.getInstance().currentProfile().getNewOffersPOJO().getDescription());
            ps.setDate(3, (new java.sql.Date(Context.getInstance().currentProfile().getNewOffersPOJO().getExpiration_date().getTime())));
            System.out.println(query.toString());
            row = ps.executeUpdate();
            if (row > 0) {
                setCounter("offer");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for delete particular offer
    /**
     *
     * @param offerId
     * @return
     */
    public static int deleteOffer(int offerId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from newoffers_table where id=" + offerId + "";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row > 0) {
                setCounter("offer");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for insert new notice
    /**
     *
     * @return
     */
    public static int insertNewNotice() {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String generate_notice_id = "";
        generate_notice_id = "not_" + UiiDGenerator.getUIID8();

        try {
            String query = "insert into notice_table(created_date,title,description,expired_date,branch_id,id) "
                    + "values((Select curdate()),"
                    + "?,?,?,(Select branch_id from branch_tbl where branch_name='" + Context.getInstance().currentProfile().getNoticePOJO().getBranch() + "'),?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getNoticePOJO().getTitle());
            ps.setString(2, Context.getInstance().currentProfile().getNoticePOJO().getDescription());
            ps.setDate(3, (new java.sql.Date(Context.getInstance().currentProfile().getNoticePOJO().getExpiration_date().getTime())));
            ps.setString(4, generate_notice_id);
            System.out.println(query.toString());
            row = ps.executeUpdate();
            if (row > 0) {
                setCounter("notice");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

//Method for update new offers
    /**
     *
     * @param noticeId
     * @return
     */
    public static int updateNewNotice(String noticeId) {
        int row = 0;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        try {
            String query = "update notice_table set title=?,description=?,expired_date=?,"
                    + "branch_id=(Select branch_id from branch_tbl where branch_name='" + Context.getInstance().currentProfile().getNoticePOJO().getBranch() + "')"
                    + "   where id='" + noticeId + "'";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Context.getInstance().currentProfile().getNoticePOJO().getTitle());
            ps.setString(2, Context.getInstance().currentProfile().getNoticePOJO().getDescription());
            ps.setDate(3, (new java.sql.Date(Context.getInstance().currentProfile().getNoticePOJO().getExpiration_date().getTime())));
            System.out.println(query.toString());
            row = ps.executeUpdate();
            if (row > 0) {
                setCounter("notice");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return row;
    }

    //Method for delete particular notice
    /**
     *
     * @param noticeId
     * @return
     */
    public static int deleteNotice(String noticeId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int row = 0;
        con = DBPool.getInstance().getConn();
        try {
            String query = "Delete from notice_table where id='" + noticeId + "'";
            stmt = con.createStatement();
            row = stmt.executeUpdate(query);
            if (row > 0) {
                setCounter("notice");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return row;
    }

    /**
     *
     * @param branch
     * @return
     */
    public static List<NoticePOJO> getNotices(String branch) {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        ResultSet rsnotice = null;
        List<NoticePOJO> noticelist = new ArrayList<>();
        try {
            String query = "SELECT	* FROM\n"
                    + "	notice_table\n"
                    + "WHERE\n"
                    + "	expired_date >= now()\n"
                    + "AND (	branch_id = (\n"
                    + "		SELECT\n"
                    + "			branch_id\n"
                    + "		FROM\n"
                    + "			branch_tbl\n"
                    + "		WHERE\n"
                    + "			branch_name = '" + branch + "'	)\n"
                    + "	OR branch_id IS NULL)\n"
                    + "ORDER BY	created_date ASC";
            System.out.println("Check Notice :: " + query);
            PreparedStatement ps = con.prepareStatement(query);
            //  ps.setString(1, user.getUsername());
            //  System.out.println("notice query " + query);
            rsnotice = ps.executeQuery();
            while (rsnotice.next()) {
                NoticePOJO notice = new NoticePOJO();
                notice.setId(rsnotice.getString(1));
                notice.setCreated_date(rsnotice.getDate(2));
                notice.setTitle(rsnotice.getString(3));
                notice.setDescription(rsnotice.getString(4));
                notice.setExpiration_date(rsnotice.getDate(5));
                noticelist.add(notice);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return noticelist;
    }

    /**
     *
     * @return
     */
    public static List<NewOffersPOJO> getOffers() {
        Connection con = null;
        con = DBPool.getInstance().getConn();
        ResultSet rsofr = null;
        List<NewOffersPOJO> offerslist = new ArrayList<>();
        try {
            String query = "select * from newoffers_table where expired_date >= now() ORDER BY created_date ASC";
            PreparedStatement ps = con.prepareStatement(query);
            //  ps.setString(1, user.getUsername());
            rsofr = ps.executeQuery();
            while (rsofr.next()) {
                NewOffersPOJO offers = new NewOffersPOJO();
                offers.setId(rsofr.getInt(1));
                offers.setCreated_date(rsofr.getDate(2));
                offers.setUsers(rsofr.getString(3));
                offers.setTitle(rsofr.getString(4));
                offers.setDescription(rsofr.getString(5));
                offers.setExpiration_date(rsofr.getDate(6));
                offerslist.add(offers);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{null}, new ResultSet[]{null}, stackTraceElements);
        }
        return offerslist;
    }
//Method for get all offers

    /**
     *
     * @return
     */
    public static List<String> getAllOffers() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allBranches = new ArrayList<>();
        try {
            String query = "select title from newoffers_table";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allBranches.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allBranches;
    }

    //Method for get all Notices
    /**
     *
     * @return
     */
    public static List<String> getAllNotice() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<String> allBranches = new ArrayList<>();
        try {
            String query = "select title from notice_table";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                allBranches.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return allBranches;
    }

    //Method for get all details who is already in database
    /**
     *
     * @param title
     * @return
     */
    public static List<OfferDetails> getOfferDetails(String title) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<OfferDetails> offerList = new ArrayList<OfferDetails>();
        try {
            String query = "select * from newoffers_table where title='" + title + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                OfferDetails offerDetails = new OfferDetails();
                offerDetails.setOfferId(rs.getInt(1));
                offerDetails.setOfferTitle(rs.getString(4));
                offerDetails.setDescription(rs.getString(5));
                offerDetails.setRole(rs.getString(3));
                offerDetails.setBranch(rs.getString(7));
                offerDetails.setOfferDate(rs.getDate(6));
                offerList.add(offerDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return offerList;
    }

    //Method for get all details who is already in database
    /**
     *
     * @param title
     * @return
     */
    public static List<NoticeDetails> getNoticeDetails(String title) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        List<NoticeDetails> noticeList = new ArrayList<NoticeDetails>();
        try {
            String query = "select * from notice_table where title='" + title + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                NoticeDetails noticeDetails = new NoticeDetails();
                noticeDetails.setNoticeId(rs.getString(1));
                noticeDetails.setNoticeTitle(rs.getString(3));
                noticeDetails.setDescription(rs.getString(4));
                noticeDetails.setBranch(rs.getString(6));
                noticeList.add(noticeDetails);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return noticeList;
    }

    /**
     *
     * @param whichField
     */
    public static void setCounter(String whichField) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String sql = "SELECT\n"
                + "	l.user_name\n"
                + "FROM\n"
                + "	login_tbl l,\n"
                + "	login_details_tbl ld\n"
                + "WHERE\n"
                + "	l.login_id = ld.login_id";
        String counterSql = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            Statement statementCounter = con.createStatement();

            while (rs.next()) {
                idExists = isIdexists(rs.getString(1));
                String generate_count_id = "";
                generate_count_id = "counter_" + UiiDGenerator.getUIID8();
                System.out.println("dgsgds" + idExists);
                if (!idExists) {
                    if (whichField.equals("notice")) {
                        counterSql = "insert into counselor_counter_tbl values('" + generate_count_id + "','" + rs.getString(1) + "',1,0)";

                    } else if (whichField.equals("offer")) {
                        counterSql = "insert into counselor_counter_tbl values('" + generate_count_id + "','" + rs.getString(1) + "',0,1)";
                    }
                } else {
                    counterSql = "UPDATE counselor_counter_tbl\n"
                            + "SET " + whichField + " = (" + whichField + " + 1)\n"
                            + "WHERE\n"
                            + "user_name = '" + rs.getString(1) + "'";
                }
                statementCounter.execute(counterSql);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
    }

    /**
     *
     * @param checkId
     * @return
     */
    public static boolean isIdexists(String checkId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        String sql = "SELECT\n"
                + "	*\n"
                + "FROM\n"
                + "	counselor_counter_tbl\n"
                + "WHERE\n"
                + "	user_name = '" + checkId + "'";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return false;
    }

    /**
     *
     * @param username
     * @param whichField
     * @return
     */
    public static boolean resetCounter(String username, String whichField) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBPool.getInstance().getConn();
        String sql = "UPDATE counselor_counter_tbl\n"
                + "SET " + whichField + " = 0\n"
                + "WHERE\n"
                + "user_name = '" + username + "'";

        try {
            stmt = con.createStatement();
            if (stmt.execute(sql)) {
                return true;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return false;
    }

    /**
     *
     * @param username
     * @return
     */
    public static Map<String, Integer> checkCounter(String username) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = DBPool.getInstance().getConn();
        Map<String, Integer> counterMap = new HashMap<>();
        String sql = "SELECT\n"
                + "	notice,offer\n"
                + "FROM\n"
                + "	counselor_counter_tbl\n"
                + "WHERE\n"
                + "	user_name = '" + username + "';";
        System.out.println("notice :: " + sql);

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                counterMap.put("notice", rs.getInt(1));
                counterMap.put("offer", rs.getInt(2));
                //   System.out.println("Test values :: " + rs.getInt(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            ClosingResourcesInDB.closingAllResources(con, new Statement[]{stmt}, new ResultSet[]{rs}, stackTraceElements);
        }
        return counterMap;
    }

    //Inner Class for Notice
    /**
     *
     */
    public static class NoticeDetails {

        String noticeId;
        String noticeTitle, description, branch;

        /**
         *
         * @return
         */
        public String getNoticeId() {
            return noticeId;
        }

        /**
         *
         * @param noticeId
         */
        public void setNoticeId(String noticeId) {
            this.noticeId = noticeId;
        }

        /**
         *
         * @return
         */
        public String getNoticeTitle() {
            return noticeTitle;
        }

        /**
         *
         * @param noticeTitle
         */
        public void setNoticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
        }

        /**
         *
         * @return
         */
        public String getDescription() {
            return description;
        }

        /**
         *
         * @param description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         *
         * @return
         */
        public String getBranch() {
            return branch;
        }

        /**
         *
         * @param branch
         */
        public void setBranch(String branch) {
            this.branch = branch;
        }

    }

    //Inner Class for New Offer
    /**
     *
     */
    public static class OfferDetails {

        int offerId;
        String offerTitle, description, role, branch;
        java.util.Date offerDate;

        /**
         *
         * @return
         */
        public int getOfferId() {
            return offerId;
        }

        /**
         *
         * @param offerId
         */
        public void setOfferId(int offerId) {
            this.offerId = offerId;
        }

        /**
         *
         * @return
         */
        public String getOfferTitle() {
            return offerTitle;
        }

        /**
         *
         * @param offerTitle
         */
        public void setOfferTitle(String offerTitle) {
            this.offerTitle = offerTitle;
        }

        /**
         *
         * @return
         */
        public String getDescription() {
            return description;
        }

        /**
         *
         * @param description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         *
         * @return
         */
        public String getRole() {
            return role;
        }

        /**
         *
         * @param role
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         *
         * @return
         */
        public String getBranch() {
            return branch;
        }

        /**
         *
         * @param branch
         */
        public void setBranch(String branch) {
            this.branch = branch;
        }

        /**
         *
         * @return
         */
        public Date getOfferDate() {
            return offerDate;
        }

        /**
         *
         * @param offerDate
         */
        public void setOfferDate(Date offerDate) {
            this.offerDate = offerDate;
        }

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //  LoginFormDAO.getNotices(null);
        System.out.println("test");
        setCounter("offer");
        // resetCounter("a", "offer");
        Map<String, Integer> ss = checkCounter("a");
        System.out.println("testtt" + ss.get("notice") + ss.get("offer"));
    }
}
