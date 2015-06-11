package com.EmailServiceAbstraction.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Email Data model for Json serialization
 * @author manish
 *
 */
@XmlRootElement
public class EmailData {
    @NotNull
    @Pattern(message="{Invalid format: From e-mail}", regexp="[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
    protected String from;
    @NotNull
    @Pattern(message="{Invalid format: To e-mail}", regexp="[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
    protected String to;
    @NotNull
    protected String text;
    @NotNull
    protected String html;
    @NotNull
    @Size(min=2, max=500)
    protected String subject;

    public EmailData() {
    }

    public EmailData(String from, String to, String text, String html, String subject) {
        this.setFrom(from);
        this.setTo(to);
        this.setText(text);
        this.setHtml(html);
        this.setHtml(subject);
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtml() {
        return this.html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}