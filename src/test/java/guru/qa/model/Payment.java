package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {
    private Integer id;

    @JsonProperty("value_date")
    private String valueDate;

    @JsonProperty("ndoc")
    private String nDoc;
    private String currency;
    private Integer amount;
    private Receiver receiver;
    private String signatureA;
    private String signatureB;
    private String narrative;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getnDoc() {
        return nDoc;
    }

    public void setnDoc(String nDoc) {
        this.nDoc = nDoc;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public String getSignatureA() {
        return signatureA;
    }

    public void setSignatureA(String signatureA) {
        this.signatureA = signatureA;
    }

    public String getSignatureB() {
        return signatureB;
    }

    public void setSignatureB(String signatureB) {
        this.signatureB = signatureB;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }
}