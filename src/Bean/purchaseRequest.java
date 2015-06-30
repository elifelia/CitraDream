package Bean;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elifelia
 */
public class purchaseRequest {
    private String qtyRequest;
    private String qtyOnHand;
    private String unit;
    private String description;
    private String remarks;
    private String leaderRemarks;
    private String pr;
    private String deptRequest;
    private Date date;
    private Date deliveryDate;

    public String getQtyRequest() {
        return qtyRequest;
    }

    public void setQtyRequest(String qtyRequest) {
        this.qtyRequest = qtyRequest;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLeaderRemarks() {
        return leaderRemarks;
    }

    public void setLeaderRemarks(String leaderRemarks) {
        this.leaderRemarks = leaderRemarks;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getDeptRequest() {
        return deptRequest;
    }

    public void setDeptRequest(String deptRequest) {
        this.deptRequest = deptRequest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
