package com.pilvotest;

import com.pilvo.apiServices.BaseClass;
import com.pilvo.apiServices.apiPayLoad.PayLoad;
import com.pilvo.apiServices.customAssertion.CustomAssert;
import com.pilvo.apiServices.customReporting.ExtentReportManager;
import com.pilvo.apiServices.globalVariable.Constant;
import com.pilvo.apiServices.responsePojo.ArchiveChannel;
import com.pilvo.apiServices.responsePojo.ChannelList;
import com.pilvo.apiServices.responsePojo.CreateChannel;
import com.pilvo.apiServices.reusableService.RestHelper;
import com.pilvo.apiServices.statusCode.HttpStatusCode;
import com.pilvo.apiServices.util.Util;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

public class SlackIntegration extends BaseClass {

    Map<String, String> channelListMap;
    static String channelId;


    @Test(priority = 1, description = "Validate Create channels Slack API")
    public void VALIDATE_CREATE_CHANNEL() throws Exception {
        channelListMap = new LinkedHashMap<>();
        Map<String, String> param = Util.readAllPropertiesStartsWith("PARAM");
        param.put(Constant.CHANNEL_NAME, channelName);
        Response response = RestHelper.postRequestWithQueryParam(Constant.SLACK_CREATE_CHANNEL, param);
        RestHelper.ValidateStatusCode(response, HttpStatusCode.OK);
        System.out.println("Response of Slack Create API :" + response.prettyPrint());

        //Verify Channel
        CreateChannel[] createChannels = objectMapper.readValue(response.getBody().asString(), CreateChannel[].class);
        channelId = createChannels[0].getChannel().getId();
        String channelName = createChannels[0].getChannel().getName();

        CustomAssert.assertTrue(createChannels[0].getOk(), "Verify ok status from Slack Create channel");
        CustomAssert.assertTrue(this.channelName.equalsIgnoreCase(channelName), "Verify Created channel present in channel response");
    }

    @Test(priority = 2, description = "Validate Join api of Slack channels")
    public void VALIDATE_JOIN_CREATED_CHANNEL() throws Exception {

        Map<String, String> param = Util.readAllPropertiesStartsWith("PARAM");
        param.put(Constant.CHANNEL_NAME, channelName);
        Response response = RestHelper.postRequestWithQueryParam(Constant.SLACK_JOIN_CHANNEL, param);
        RestHelper.ValidateStatusCode(response, HttpStatusCode.OK);
        System.out.println("Response of Slack Join API :" + response.prettyPrint());
        CreateChannel[] createChannels = objectMapper.readValue(response.getBody().asString(), CreateChannel[].class);
        channelId = createChannels[0].getChannel().getId();
        String channelName = createChannels[0].getChannel().getName();
        CustomAssert.assertTrue(createChannels[0].getOk(), "Verify ok status from Slack Join channel");
        CustomAssert.assertTrue(createChannels[0].isAlready_in_channel(), "Verify user is join in channel");
        CustomAssert.assertTrue(this.channelName.equalsIgnoreCase(channelName), "Verify Join channel present in channel response");


    }


    @Test(priority = 3, description = "Validate Rename the Channel of Slack API")
    public void VALIDATE_RENAME_CHANNEL() throws Exception {
        Map<String, String> param = Util.readAllPropertiesStartsWith("PARAM");
        String renameChannelName = "SlackIntegrateRename" + Util.RandomGenerate(1000);
        param.put(Constant.CHANNEL, channelId);
        param.put(Constant.CHANNEL_NAME, renameChannelName);

        Response response = RestHelper.postRequestWithQueryParam(Constant.SLACK_RENAME_CHANNEL, param);
        RestHelper.ValidateStatusCode(response, HttpStatusCode.OK);
        System.out.println("Response of Slack Create API :" + response.prettyPrint());

        CreateChannel[] createChannels = objectMapper.readValue(response.getBody().asString(), CreateChannel[].class);
        String channelName = createChannels[0].getChannel().getName();
        CustomAssert.assertTrue(createChannels[0].getOk(), "Verify ok status from Slack Create channel");
        CustomAssert.assertTrue(renameChannelName.equalsIgnoreCase(channelName), "Verify Rename channel present in Rename channel response");

    }


    @Test(priority = 4, description = "Validate List of channels")
    public void VALIDATE_LIST_OF_CHANNEL() throws Exception {
        channelListMap = new LinkedHashMap<>();

        Map<String, String> param = Util.readAllPropertiesStartsWith("PARAM");
        Response response = RestHelper.getHttpRequest(Constant.SLACK_LIST_CHANNELS, param);
        RestHelper.ValidateStatusCode(response, HttpStatusCode.OK);
        ChannelList[] channelList = objectMapper.readValue(response.getBody().asString(), ChannelList[].class);
        channelList[0].getChannels().forEach(p -> channelListMap.put(p.getName(), p.getId()));
        System.out.println(channelListMap);
    }

    @Test(priority = 5, description = "Validate Archive the Channel of Slack API")
    public void VALIDATE_ARCHIVE_CHANNEL() throws Exception {
        Map<String, String> param = Util.readAllPropertiesStartsWith("PARAM");
        param.put(Constant.CHANNEL, channelId);
        Response response = RestHelper.postRequestWithQueryParam(Constant.SLACK_ARCHIVE_CHANNEL, param);
        ArchiveChannel[] archiveChannels = objectMapper.readValue(response.getBody().asString(), ArchiveChannel[].class);
        System.out.println("Response Archive channel API : " + response.prettyPrint());
        CustomAssert.assertTrue(archiveChannels[0].getOk(), "Validate channel is archived successfully");
    }


    @Test
    public void test() {
        String str = "Ashish";
        System.out.println(str + new Random().nextInt(1000));
    }
}
