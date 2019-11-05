package br.com.prisma.requisicaovaga.model;

public class NotifyUserInput {

    private String notificationClass;
    private String notificationOrigin;
    private String notificationKind;
    private String notificationPriority;
    private String notificationSubject;
    private String notificationContent;
    private String sourceDomain;
    private String sourceService;
    private String destinationUser;
    private String link;

    public NotifyUserInput() {
    }

    public NotifyUserInput(String notificationClass, String notificationOrigin, String notificationKind, String notificationPriority, String notificationSubject, String notificationContent, String sourceDomain, String sourceService, String destinationUser, String link) {
        this.notificationClass = notificationClass;
        this.notificationOrigin = notificationOrigin;
        this.notificationKind = notificationKind;
        this.notificationPriority = notificationPriority;
        this.notificationSubject = notificationSubject;
        this.notificationContent = notificationContent;
        this.sourceDomain = sourceDomain;
        this.sourceService = sourceService;
        this.destinationUser = destinationUser;
        this.link = link;
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

    public String getNotificationKind() {
        return notificationKind;
    }

    public void setNotificationKind(String notificationKind) {
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

    @Override
    public String toString() {
        return "NotifyUserInput{" + "notificationClass=" + notificationClass + ", notificationOrigin=" + notificationOrigin + ", notificationKind=" + notificationKind + ", notificationPriority=" + notificationPriority + ", notificationSubject=" + notificationSubject + ", notificationContent=" + notificationContent + ", sourceDomain=" + sourceDomain + ", sourceService=" + sourceService + ", destinationUser=" + destinationUser + ", link=" + link + '}';
    }
}
