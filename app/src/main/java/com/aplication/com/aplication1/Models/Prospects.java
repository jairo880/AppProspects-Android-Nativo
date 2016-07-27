package com.aplication.com.aplication1.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Prospects {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("schProspectIdentification")
    @Expose
    private String schProspectIdentification;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("statusCd")
    @Expose
    private Integer statusCd;
    @SerializedName("zoneCode")
    @Expose
    private String zoneCode;
    @SerializedName("neighborhoodCode")
    @Expose
    private String neighborhoodCode;
    @SerializedName("cityCode")
    @Expose
    private String cityCode;
    @SerializedName("sectionCode")
    @Expose
    private String sectionCode;
    @SerializedName("roleId")
    @Expose
    private Integer roleId;
    @SerializedName("appointableId")
    @Expose
    private Object appointableId;
    @SerializedName("rejectedObservation")
    @Expose
    private Object rejectedObservation;
    @SerializedName("observation")
    @Expose
    private String observation;
    @SerializedName("disable")
    @Expose
    private Boolean disable;
    @SerializedName("visited")
    @Expose
    private Boolean visited;
    @SerializedName("callcenter")
    @Expose
    private Boolean callcenter;
    @SerializedName("acceptSearch")
    @Expose
    private Boolean acceptSearch;
    @SerializedName("campaignCode")
    @Expose
    private String campaignCode;
    @SerializedName("userId")
    @Expose
    private Object userId;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     * The surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     * The telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone
     * The telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     *
     * @return
     * The schProspectIdentification
     */
    public String getSchProspectIdentification() {
        return schProspectIdentification;
    }

    /**
     *
     * @param schProspectIdentification
     * The schProspectIdentification
     */
    public void setSchProspectIdentification(String schProspectIdentification) {
        this.schProspectIdentification = schProspectIdentification;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The statusCd
     */
    public Integer getStatusCd() {
        return statusCd;
    }

    /**
     *
     * @param statusCd
     * The statusCd
     */
    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }

    /**
     *
     * @return
     * The zoneCode
     */
    public String getZoneCode() {
        return zoneCode;
    }

    /**
     *
     * @param zoneCode
     * The zoneCode
     */
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    /**
     *
     * @return
     * The neighborhoodCode
     */
    public String getNeighborhoodCode() {
        return neighborhoodCode;
    }

    /**
     *
     * @param neighborhoodCode
     * The neighborhoodCode
     */
    public void setNeighborhoodCode(String neighborhoodCode) {
        this.neighborhoodCode = neighborhoodCode;
    }

    /**
     *
     * @return
     * The cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     *
     * @param cityCode
     * The cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     *
     * @return
     * The sectionCode
     */
    public String getSectionCode() {
        return sectionCode;
    }

    /**
     *
     * @param sectionCode
     * The sectionCode
     */
    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    /**
     *
     * @return
     * The roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     *
     * @param roleId
     * The roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     *
     * @return
     * The appointableId
     */
    public Object getAppointableId() {
        return appointableId;
    }

    /**
     *
     * @param appointableId
     * The appointableId
     */
    public void setAppointableId(Object appointableId) {
        this.appointableId = appointableId;
    }

    /**
     *
     * @return
     * The rejectedObservation
     */
    public Object getRejectedObservation() {
        return rejectedObservation;
    }

    /**
     *
     * @param rejectedObservation
     * The rejectedObservation
     */
    public void setRejectedObservation(Object rejectedObservation) {
        this.rejectedObservation = rejectedObservation;
    }

    /**
     *
     * @return
     * The observation
     */
    public String getObservation() {
        return observation;
    }

    /**
     *
     * @param observation
     * The observation
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     *
     * @return
     * The disable
     */
    public Boolean getDisable() {
        return disable;
    }

    /**
     *
     * @param disable
     * The disable
     */
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    /**
     *
     * @return
     * The visited
     */
    public Boolean getVisited() {
        return visited;
    }

    /**
     *
     * @param visited
     * The visited
     */
    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    /**
     *
     * @return
     * The callcenter
     */
    public Boolean getCallcenter() {
        return callcenter;
    }

    /**
     *
     * @param callcenter
     * The callcenter
     */
    public void setCallcenter(Boolean callcenter) {
        this.callcenter = callcenter;
    }

    /**
     *
     * @return
     * The acceptSearch
     */
    public Boolean getAcceptSearch() {
        return acceptSearch;
    }

    /**
     *
     * @param acceptSearch
     * The acceptSearch
     */
    public void setAcceptSearch(Boolean acceptSearch) {
        this.acceptSearch = acceptSearch;
    }

    /**
     *
     * @return
     * The campaignCode
     */
    public String getCampaignCode() {
        return campaignCode;
    }

    /**
     *
     * @param campaignCode
     * The campaignCode
     */
    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    /**
     *
     * @return
     * The userId
     */
    public Object getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The userId
     */
    public void setUserId(Object userId) {
        this.userId = userId;
    }

}