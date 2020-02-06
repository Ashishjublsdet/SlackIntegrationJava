package com.pilvo.apiServices.responsePojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateChannel {
    private boolean ok;

    private Channel channel;

    private boolean already_in_channel;

    public boolean isAlready_in_channel() {
        return already_in_channel;
    }

    public void setAlready_in_channel(boolean already_in_channel) {
        this.already_in_channel = already_in_channel;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean getOk() {
        return this.ok;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return this.channel;
    }
}



