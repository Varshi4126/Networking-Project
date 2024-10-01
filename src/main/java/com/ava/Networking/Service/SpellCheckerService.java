//package com.ava.Networking.Service;
//
//import org.languagetool.JLanguageTool;
//import org.languagetool.language.AmericanEnglish;
//import org.languagetool.rules.RuleMatch;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class SpellCheckerService {
//
//    private final JLanguageTool langTool;
//
//    public SpellCheckerService() {
//        this.langTool = new JLanguageTool(new AmericanEnglish());
//    }
//
//    public List<RuleMatch> checkSpelling(String text) throws IOException {
//        return langTool.check(text);
//    }
//}