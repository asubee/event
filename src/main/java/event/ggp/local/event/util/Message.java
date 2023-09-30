package event.ggp.local.event.util;

public enum Message {
    M0001("{0}は必須です。", "{0} is required.")
    ,
    ;

    private String messageJa;
    private String messageEn;
    
    private Message(String messageJa, String messageEn) {
        this.messageJa = messageJa;
        this.messageEn = messageEn;
    }
}