package com.codehidder.user_demo.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "default"})
public class DummyNotificationServiceImpl implements NotificationService {

    @Value("${notification.message}")
    private String notificationMessage;

    @Override
    public void sendNotification(String msg) {
        System.out.println(notificationMessage + "-->" + msg);
    }
}
