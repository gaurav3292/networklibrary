package com.kmgrv.networklibrary.bean;

import com.google.gson.internal.LinkedTreeMap;


import java.io.Serializable;

public class TermsBean  implements Serializable {

    private String terms;
    private String privacy;
    private String faq;

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getFaq() {
        return faq;
    }

    public void setFaq(String faq) {
        this.faq = faq;
    }
}
