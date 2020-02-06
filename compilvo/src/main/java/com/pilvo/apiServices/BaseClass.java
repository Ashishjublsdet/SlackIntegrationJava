package com.pilvo.apiServices;

import com.pilvo.apiServices.customListner.CustomListner;
import com.pilvo.apiServices.globalVariable.Constant;
import com.pilvo.apiServices.util.Util;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.Properties;
import java.util.Random;

@Listeners({CustomListner.class})
public class BaseClass {
    public static String Base_Url;
    public static String channelName;
    Properties properties;
    public static ObjectMapper objectMapper;

    @BeforeSuite
    public void InitVariables() throws Exception {
        System.out.println("Enter in Before Suit");
        String path = System.getProperty("user.dir") + Constant.ENV_FILE_PATH +"local.properties";
        properties = Util.loadProperty(path);
        this.objectMapper = Util.getObjectMapper();
        Base_Url = properties.getProperty("BASE_URL");
        channelName = properties.getProperty("CHANNEL_NAME")+Util.RandomGenerate(1000);
        System.out.println("Channel Name"+channelName);
        Constant.SLACK_LIST_CHANNELS = Base_Url + Constant.SLACK_LIST_CHANNELS;
        Constant.SLACK_JOIN_CHANNEL = Base_Url + Constant.SLACK_JOIN_CHANNEL;
        Constant.SLACK_RENAME_CHANNEL = Base_Url + Constant.SLACK_RENAME_CHANNEL;
        Constant.SLACK_CREATE_CHANNEL = Base_Url + Constant.SLACK_CREATE_CHANNEL;
        Constant.SLACK_ARCHIVE_CHANNEL = Base_Url + Constant.SLACK_ARCHIVE_CHANNEL;
    }
}
