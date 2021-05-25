package com.tushar.cowin.notifier.model.dto.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class MailContent implements Serializable {
    private final List<MailItem> mailItems;
    private final String subject;
    private final String receiver;
}


