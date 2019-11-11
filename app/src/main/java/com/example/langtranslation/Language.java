package com.example.langtranslation;

import androidx.annotation.NonNull;

import java.security.PublicKey;
import java.util.Locale;


public class Language {

    private String code;

    public Language(String code1){
       this.code = code1;
    }

    String getDisplayName() { return new Locale(code).getDisplayName(); }

    public String getCode(){
        return this.code;
    }


    @NonNull
    public String toString() {
        return code + " - " + getDisplayName();
    }

    @Override
    public int hashCode() { return code.hashCode(); }
}

