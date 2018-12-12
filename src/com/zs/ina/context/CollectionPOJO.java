/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.context;

import com.zs.ina.admin.dao.BranchPOJO;
import com.zs.ina.admin.dao.LoginPOJO;
import com.zs.ina.admin.dao.PrivilagePOJO;
import com.zs.ina.admin.dao.RolePOJO;
import com.zs.ina.admin.dao.SourcePOJO;
import com.zs.ina.admin.master.batchtime.dao.BatchTimeBean;
import com.zs.ina.admin.master.functionalarea.dao.FunctionalAreaPOJO;
import com.zs.ina.admin.master.qualification.dao.QualificationBean;
import com.zs.ina.admin.profession.dao.ProfessionBean;
import com.zs.ina.agency.dao.AgencyPOJO;
import com.zs.ina.assesment.model.AssessmentStatusBEAN;
import com.zs.ina.assesment.model.EnquiryAssesmentSslcBEAN;
import com.zs.ina.assesment.model.AssessmentPersonBEAN;
import com.zs.ina.assesment.plus2.model.AssessmentPlus2BEAN;
import com.zs.ina.common.inbox.retrieve.InboxCountsBEAN;
import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import com.zs.ina.enquiry.search.dao.EnquirySearchPOJO;
import com.zs.ina.login.dao.ProfilePOJO;
import com.zs.ina.notifications.dao.NewOffersPOJO;
import com.zs.ina.notifications.dao.NoticePOJO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import org.controlsfx.control.PopOver;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author user
 */
public class CollectionPOJO {

    private ProfilePOJO profilePOJO;
    private BranchPOJO branchPOJO;
    private PrivilagePOJO privilagePOJO;
    private RolePOJO rolePOJO;
    private LoginPOJO loginPOJO;
    private AssessmentPersonBEAN enquiryAssessmentPersonPOJO;
    private AssessmentStatusBEAN enquiryAssementStatusPojo;
    private NewOffersPOJO newOffersPOJO;
    private NoticePOJO noticePOJO;
    private CounselorDetailsBEAN counselorDetailsPOJO;
    private AgencyPOJO agencyPOJO;

    private FunctionalAreaPOJO functionalAreaPOJO;
    private SourcePOJO sourcePOJO;
    private EnquiryAssesmentSslcBEAN assesmentSslcBEAN;
    private AssessmentPlus2BEAN assessmentPlus2POJO;
    private CounselorDetailsBEAN counselorDetailsBEAN;
    private CounselorDetailsBEAN counselorDetailsTaskBEAN;
    private QualificationBean qualificationBean;
    private ProfessionBean professionBean;
    private BatchTimeBean batchTimeBean;
    private EnquirySearchPOJO enquirySearchPOJO;
    private PopOver popOver;
    private String currentTab;
    private Node node;
    private String previewCurrentEdit;
    private String loggedIn;
    private  ApplicationContext springContext;
    
    private final StringProperty invitationAcceptance = new SimpleStringProperty();
    private final StringProperty eventCancels = new SimpleStringProperty();
    private final StringProperty documentVerifyDisplay = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getDocumentVerifyDisplay() {
        return documentVerifyDisplay.get();
    }

    /**
     *
     * @param value
     */
    public void setDocumentVerifyDisplay(String value) {
        documentVerifyDisplay.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty documentVerifyDisplayProperty() {
        return documentVerifyDisplay;
    }
    
    
    private InboxCountsBEAN inboxCountsBEAN;

    /**
     *
     * @return
     */
    public ApplicationContext getSpringContext() {
        return springContext;
    }

    /**
     *
     * @param springContext
     */
    public void setSpringContext(ApplicationContext springContext) {
        this.springContext = springContext;
    }

    /**
     *
     * @return
     */
    public InboxCountsBEAN getInboxCountsBEAN() {
        return inboxCountsBEAN;
    }

    /**
     *
     * @param inboxCountsBEAN
     */
    public void setInboxCountsBEAN(InboxCountsBEAN inboxCountsBEAN) {
        this.inboxCountsBEAN = inboxCountsBEAN;
    }

    /**
     *
     * @return
     */
    public String getEventCancels() {
        return eventCancels.get();
    }

    /**
     *
     * @param value
     */
    public void setEventCancels(String value) {
        eventCancels.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty eventCancelsProperty() {
        return eventCancels;
    }
    
    /**
     *
     * @return
     */
    public String getInvitationAcceptance() {
        return invitationAcceptance.get();
    }

    /**
     *
     * @param value
     */
    public void setInvitationAcceptance(String value) {
        invitationAcceptance.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty invitationAcceptanceProperty() {
        return invitationAcceptance;
    }

    /**
     *
     * @return
     */
    public String getLoggedIn() {
        return loggedIn;
    }

    /**
     *
     * @param loggedIn
     */
    public void setLoggedIn(String loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     *
     * @return
     */
    public String getPreviewCurrentEdit() {
        return previewCurrentEdit;
    }

    /**
     *
     * @param previewCurrentEdit
     */
    public void setPreviewCurrentEdit(String previewCurrentEdit) {
        this.previewCurrentEdit = previewCurrentEdit;
    }

    /**
     *
     * @return
     */
    public CounselorDetailsBEAN getCounselorDetailsTaskBEAN() {
        return counselorDetailsTaskBEAN;
    }

    /**
     *
     * @param counselorDetailsTaskBEAN
     */
    public void setCounselorDetailsTaskBEAN(CounselorDetailsBEAN counselorDetailsTaskBEAN) {
        this.counselorDetailsTaskBEAN = counselorDetailsTaskBEAN;
    }

    /**
     *
     * @return
     */
    public Node getNode() {
        return node;
    }

    /**
     *
     * @param node
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /**
     *
     * @return
     */
    public String getCurrentTab() {
        return currentTab;
    }

    /**
     *
     * @param currentTab
     */
    public void setCurrentTab(String currentTab) {
        this.currentTab = currentTab;
    }

    /**
     *
     * @return
     */
    public PopOver getPopOver() {
        return popOver;
    }

    /**
     *
     * @param popOver
     */
    public void setPopOver(PopOver popOver) {
        this.popOver = popOver;
    }

    /**
     *
     * @return
     */
    public QualificationBean getQualificationBean() {
        return qualificationBean;
    }

    /**
     *
     * @param qualificationBean
     */
    public void setQualificationBean(QualificationBean qualificationBean) {
        this.qualificationBean = qualificationBean;
    }

    /**
     *
     * @return
     */
    public EnquirySearchPOJO getEnquirySearchPOJO() {
        return enquirySearchPOJO;
    }

    /**
     *
     * @param enquirySearchPOJO
     */
    public void setEnquirySearchPOJO(EnquirySearchPOJO enquirySearchPOJO) {
        this.enquirySearchPOJO = enquirySearchPOJO;
    }

    /**
     *
     * @return
     */
    public ProfessionBean getProfessionBean() {
        return professionBean;
    }

    /**
     *
     * @param professionBean
     */
    public void setProfessionBean(ProfessionBean professionBean) {
        this.professionBean = professionBean;
    }

    /**
     *
     * @return
     */
    public BatchTimeBean getBatchTimeBean() {
        return batchTimeBean;
    }

    /**
     *
     * @param batchTimeBean
     */
    public void setBatchTimeBean(BatchTimeBean batchTimeBean) {
        this.batchTimeBean = batchTimeBean;
    }

    /**
     *
     * @return
     */
    public CounselorDetailsBEAN getCounselorDetailsBEAN() {
        return counselorDetailsBEAN;
    }

    /**
     *
     * @param counselorDetailsBEAN
     */
    public void setCounselorDetailsBEAN(CounselorDetailsBEAN counselorDetailsBEAN) {
        this.counselorDetailsBEAN = counselorDetailsBEAN;
    }

    /**
     *
     * @return
     */
    public EnquiryAssesmentSslcBEAN getAssesmentSslcBEAN() {
        return assesmentSslcBEAN;
    }

    /**
     *
     * @param assesmentSslcBEAN
     */
    public void setAssesmentSslcBEAN(EnquiryAssesmentSslcBEAN assesmentSslcBEAN) {
        this.assesmentSslcBEAN = assesmentSslcBEAN;
    }

    /**
     *
     * @return
     */
    public AssessmentPlus2BEAN getAssessmentPlus2POJO() {
        return assessmentPlus2POJO;
    }

    /**
     *
     * @param assessmentPlus2POJO
     */
    public void setAssessmentPlus2POJO(AssessmentPlus2BEAN assessmentPlus2POJO) {
        this.assessmentPlus2POJO = assessmentPlus2POJO;
    }

    /**
     *
     * @return
     */
    public FunctionalAreaPOJO getFunctionalAreaPOJO() {
        return functionalAreaPOJO;
    }

    /**
     *
     * @param functionalAreaPOJO
     */
    public void setFunctionalAreaPOJO(FunctionalAreaPOJO functionalAreaPOJO) {
        this.functionalAreaPOJO = functionalAreaPOJO;
    }

    /**
     *
     * @return
     */
    public SourcePOJO getSourcePOJO() {
        return sourcePOJO;
    }

    /**
     *
     * @param sourcePOJO
     */
    public void setSourcePOJO(SourcePOJO sourcePOJO) {
        this.sourcePOJO = sourcePOJO;
    }

    /**
     *
     * @return
     */
    public AgencyPOJO getAgencyPOJO() {
        return agencyPOJO;
    }

    /**
     *
     * @param agencyPOJO
     */
    public void setAgencyPOJO(AgencyPOJO agencyPOJO) {
        this.agencyPOJO = agencyPOJO;
    }

    /**
     *
     * @return
     */
    public CounselorDetailsBEAN getCounselorDetailsPOJO() {
        return counselorDetailsPOJO;
    }

    /**
     *
     * @param counselorDetailsPOJO
     */
    public void setCounselorDetailsPOJO(CounselorDetailsBEAN counselorDetailsPOJO) {
        this.counselorDetailsPOJO = counselorDetailsPOJO;
    }

    /**
     *
     * @return
     */
    public RolePOJO getRolePOJO() {
        return rolePOJO;
    }

    /**
     *
     * @return
     */
    public NoticePOJO getNoticePOJO() {
        return noticePOJO;
    }

    /**
     *
     * @param noticePOJO
     */
    public void setNoticePOJO(NoticePOJO noticePOJO) {
        this.noticePOJO = noticePOJO;
    }

    /**
     *
     * @return
     */
    public AssessmentStatusBEAN getEnquiryAssementStatusPojo() {
        return enquiryAssementStatusPojo;
    }

    /**
     *
     * @param enquiryAssementStatusPojo
     */
    public void setEnquiryAssementStatusPojo(AssessmentStatusBEAN enquiryAssementStatusPojo) {
        this.enquiryAssementStatusPojo = enquiryAssementStatusPojo;
    }

    /**
     *
     * @return
     */
    public LoginPOJO getLoginPOJO() {
        return loginPOJO;
    }

    /**
     *
     * @param loginPOJO
     */
    public void setLoginPOJO(LoginPOJO loginPOJO) {
        this.loginPOJO = loginPOJO;
    }

    /**
     *
     * @param rolePOJO
     */
    public void setRolePOJO(RolePOJO rolePOJO) {
        this.rolePOJO = rolePOJO;
    }

    /**
     *
     * @return
     */
    public BranchPOJO getBranchPOJO() {
        return branchPOJO;
    }

    /**
     *
     * @param branchPOJO
     */
    public void setBranchPOJO(BranchPOJO branchPOJO) {
        this.branchPOJO = branchPOJO;
    }

    /**
     *
     * @return
     */
    public ProfilePOJO getProfilePOJO() {
        return profilePOJO;
    }

    /**
     *
     * @param profilePOJO
     */
    public void setProfilePOJO(ProfilePOJO profilePOJO) {
        this.profilePOJO = profilePOJO;
    }

    /**
     *
     * @return
     */
    public PrivilagePOJO getPrivilagePOJO() {
        return privilagePOJO;
    }

    /**
     *
     * @return
     */
    public NewOffersPOJO getNewOffersPOJO() {
        return newOffersPOJO;
    }

    /**
     *
     * @param newOffersPOJO
     */
    public void setNewOffersPOJO(NewOffersPOJO newOffersPOJO) {
        this.newOffersPOJO = newOffersPOJO;
    }

    /**
     *
     * @param privilagePOJO
     */
    public void setPrivilagePOJO(PrivilagePOJO privilagePOJO) {
        this.privilagePOJO = privilagePOJO;
    }

    /**
     *
     * @return
     */
    public AssessmentPersonBEAN getEnquiryAssessmentPersonPOJO() {
        return enquiryAssessmentPersonPOJO;
    }

    /**
     *
     * @param enquiryAssessmentPersonPOJO
     */
    public void setEnquiryAssessmentPersonPOJO(AssessmentPersonBEAN enquiryAssessmentPersonPOJO) {
        this.enquiryAssessmentPersonPOJO = enquiryAssessmentPersonPOJO;
    }


}
