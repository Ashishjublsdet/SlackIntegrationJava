package com.pilvo.apiServices.responsePojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchiveChannel {
    private boolean ok;

    public ArchiveChannel() {
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean getOk() {
        return this.ok;
    }
}
