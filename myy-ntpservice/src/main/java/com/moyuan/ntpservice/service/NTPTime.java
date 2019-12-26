package com.moyuan.ntpservice.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

public class NTPTime {

    /**
     * @功能：获取网络最新时间
     * @return
     */
    public static Date getDateTime() {

        try {
            NTPUDPClient timeClient = new NTPUDPClient();
            InetAddress timeServerAddress = InetAddress.getByName(MyConstants.NTPDEMAIN);
            TimeInfo timeInfo = timeClient.getTime(timeServerAddress);
            TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp();
            return timeStamp.getDate();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return new Date();
        } catch (IOException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
