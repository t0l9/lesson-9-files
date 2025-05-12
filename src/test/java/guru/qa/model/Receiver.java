package guru.qa.model;


public class Receiver {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBankIdentifierCode() {
        return bankIdentifierCode;
    }

    public void setBankIdentifierCode(String bankIdentifierCode) {
        this.bankIdentifierCode = bankIdentifierCode;
    }

//    public boolean isThirdPartyPayment() {
//        return isThirdPartyPayment;
//    }
//
//    public void setThirdPartyPayment(boolean thirdPartyPayment) {
//        this.isThirdPartyPayment = thirdPartyPayment;
//    }

//    public boolean isLegalEntity() {
//        return isLegalEntity;
//    }
//
//    public void setLegalEntity(boolean legalEntity) {
//        this.isLegalEntity = legalEntity;
//    }

    private String code;
    private String bankIdentifierCode;
    //private boolean isThirdPartyPayment;
    //private boolean isLegalEntity;


}
