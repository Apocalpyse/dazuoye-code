package po;

import java.io.Serializable;

/**
 * Created by 常德隆 on 2016/11/19.
 */
public class CustomerPO implements Serializable{
    private String customerName;
    private int[] customerPhone;
    private boolean isNormalMember;
    private boolean isCompanyMember;
    private String birthday;
    private String companyName;
    private double credit;
    private long id;
    private int creditNum;
    private String[] creditRecord;
    private long[] orderId;

    public CustomerPO(){}

    public CustomerPO(String customerName,int[] customerPhone,boolean isNormalMember,boolean isCompanyMember,
    String birthday,String companyName,double credit){
        this.customerName=customerName;
        this.customerPhone=customerPhone;
        this.isNormalMember=isNormalMember;
        this.isCompanyMember=isCompanyMember;
        this.birthday=birthday;
        this.companyName=companyName;
        this.credit=credit;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int[] getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(int[] customerPhone) {
        this.customerPhone = customerPhone;
    }

    public boolean getIsNormalMember() {
        return isNormalMember;
    }

    public void setIsNormalMember(boolean normalMember) {
        isNormalMember = normalMember;
    }

    public boolean getIsCompanyMember() {
        return isCompanyMember;
    }

    public void setIsCompanyMember(boolean companyMember) {
        isCompanyMember = companyMember;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreditNum(int creditNum) {
        this.creditNum = creditNum;
    }

    public int getCreditNum() {
        return creditNum;
    }

    public String[] getCreditRecord() {
        return creditRecord;
    }

    public void setCreditRecord(String[] creditRecord) {
        this.creditRecord = creditRecord;
    }

    public long[] getOrderId() {
        return orderId;
    }

    public void setOrderId(long[] orderId) {
        this.orderId = orderId;
    }
}
