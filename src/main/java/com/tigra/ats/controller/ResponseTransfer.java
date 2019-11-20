package com.tigra.ats.controller;

import lombok.Data;

@Data
public class ResponseTransfer {
    private String text;

    public ResponseTransfer(String text) {
        this.text = text;
    }
}
