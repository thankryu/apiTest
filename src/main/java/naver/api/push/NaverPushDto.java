package naver.api.push;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class NaverPushDto {
    private Target target;
    private Message message;

    public NaverPushDto(){

    }

    public NaverPushDto(Target target, Message message) {
        this.target = target;
        this.message = message;
    }

    public static class Target {
        private String type;
        private String deviceType;

        public Target(String type, String deviceType) {
            this.type = type;
            this.deviceType = deviceType;
        }
    }

    public static class Message {
        private DefaultMessage defaultMessage;
        private GcmMessage gcmMessage;
        private ApnsMessage apnsMessage;

        public Message(DefaultMessage defaultMessage, GcmMessage gcmMessage, ApnsMessage apnsMessage) {
            this.defaultMessage = defaultMessage;
            this.gcmMessage = gcmMessage;
            this.apnsMessage = apnsMessage;
        }

        public static class DefaultMessage {
            private String content;
            private Custom custom;
            private Option option;

            public DefaultMessage(String content, Custom custom, Option option) {
                this.content = content;
                this.custom = custom;
                this.option = option;
            }
        }

        public static class GcmMessage {
            private String content;
            private Custom custom;
            private Option option;
            private I18n i18n;

            public GcmMessage(String content, Custom custom, Option option, I18n i18n) {
                this.content = content;
                this.custom = custom;
                this.option = option;
                this.i18n = i18n;
            }
        }

        public static class ApnsMessage {
            private String content;
            private Custom custom;
            private Option option;
            private I18n i18n;

            public ApnsMessage(String content, Custom custom, Option option, I18n i18n) {
                this.content = content;
                this.custom = custom;
                this.option = option;
                this.i18n = i18n;
            }
        }

        public static class Custom {
            private String customKey1;
            private String customKey2;

            public Custom(String customKey1, String customKey2) {
                this.customKey1 = customKey1;
                this.customKey2 = customKey2;
            }
        }

        public static class Option {
            // Option class fields here
        }

        public static class I18n {
            private DefaultI18n defaultI18n;
            private Map<String, LanguageI18n> languageI18nMap;

            public I18n(DefaultI18n defaultI18n, Map<String, LanguageI18n> languageI18nMap) {
                this.defaultI18n = defaultI18n;
                this.languageI18nMap = languageI18nMap;
            }

            public static class DefaultI18n {
                private String content;
                private Custom custom;

                public DefaultI18n(String content, Custom custom) {
                    this.content = content;
                    this.custom = custom;
                }
            }

            public static class LanguageI18n {
                private String content;
                private Custom custom;

                public LanguageI18n(String content, Custom custom) {
                    this.content = content;
                    this.custom = custom;
                }
            }
        }
    }
}