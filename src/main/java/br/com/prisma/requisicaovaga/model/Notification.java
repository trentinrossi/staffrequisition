package br.com.prisma.requisicaovaga.model;

import java.util.List;

public class Notification {

    private String notificationClass;
    private String notificationOrigin;
    private NotificationKind notificationKind;
    private String notificationPriority;
    private String notificationSubject;
    private String notificationContent;
    private String sourceDomain;
    private String sourceService;
    private List<String> destinationUsers;
    private String destinationUser;
    private String link;
    private String _discriminator;

    public Notification() {
    }

    public Notification(String notificationClass, String notificationOrigin, NotificationKind notificationKind, String notificationPriority, String notificationSubject, String notificationContent, String sourceDomain, String sourceService, List<String> destinationUsers, String destinationUser, String link, String _discriminator) {
        this.notificationClass = notificationClass;
        this.notificationOrigin = notificationOrigin;
        this.notificationKind = notificationKind;
        this.notificationPriority = notificationPriority;
        this.notificationSubject = notificationSubject;
        this.notificationContent = notificationContent;
        this.sourceDomain = sourceDomain;
        this.sourceService = sourceService;
        this.destinationUsers = destinationUsers;
        this.destinationUser = destinationUser;
        this.link = link;
        this._discriminator = _discriminator;
    }

    public String getNotificationClass() {
        return notificationClass;
    }

    public void setNotificationClass(String notificationClass) {
        this.notificationClass = notificationClass;
    }

    public String getNotificationOrigin() {
        return notificationOrigin;
    }

    public void setNotificationOrigin(String notificationOrigin) {
        this.notificationOrigin = notificationOrigin;
    }

    public NotificationKind getNotificationKind() {
        return notificationKind;
    }

    public void setNotificationKind(NotificationKind notificationKind) {
        this.notificationKind = notificationKind;
    }

    public String getNotificationPriority() {
        return notificationPriority;
    }

    public void setNotificationPriority(String notificationPriority) {
        this.notificationPriority = notificationPriority;
    }

    public String getNotificationSubject() {
        return notificationSubject;
    }

    public void setNotificationSubject(String notificationSubject) {
        this.notificationSubject = notificationSubject;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getSourceDomain() {
        return sourceDomain;
    }

    public void setSourceDomain(String sourceDomain) {
        this.sourceDomain = sourceDomain;
    }

    public String getSourceService() {
        return sourceService;
    }

    public void setSourceService(String sourceService) {
        this.sourceService = sourceService;
    }

    public List<String> getDestinationUsers() {
        return destinationUsers;
    }

    public void setDestinationUsers(List<String> destinationUsers) {
        this.destinationUsers = destinationUsers;
    }

    public String getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(String destinationUser) {
        this.destinationUser = destinationUser;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDiscriminator() {
        return _discriminator;
    }

    public void setDiscriminator(String _discriminator) {
        this._discriminator = _discriminator;
    }

}
