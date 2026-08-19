package com.lld.SOLID.DIP.Bad;

public class NotificationService {
    private final EmailService emailService;
    private final SMSService smsService;

    public NotificationService() {
        this.emailService = new EmailService();
        this.smsService = new SMSService();
    }

    public void notifyByEmail(String message) {
        emailService.sendEmail(message);
    }

    public void notifyBySMS(String message) {
        smsService.sendSMS(message);
    }
}
