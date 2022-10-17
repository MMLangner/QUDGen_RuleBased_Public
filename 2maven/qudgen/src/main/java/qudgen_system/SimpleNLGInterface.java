package qudgen_system;

import simplenlgde.lexicon.Lexicon;
import simplenlgde.realiser.Realiser;
import simplenlgde.phrasespec.*;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Arrays;

import simplenlgde.framework.*;


public class SimpleNLGInterface {
    private Lexicon lexicon;
    private NLGFactory nlgFactory;
    private Realiser realiser;

    public SimpleNLGInterface() {
        this.lexicon = Lexicon.getDefaultLexicon();
        this.nlgFactory = new NLGFactory(lexicon);
        this.realiser = new Realiser(lexicon);
    }

    public String realiseScheme(Scheme s) {
        if (s.pickSubj().equals("") || s.getVerb().equals("") || s.pickObj().equals(""))
            return "error on picking obj of scheme";
        
        String subj_s = s.pickSubj();
        NPPhraseSpec subj = nlgFactory.createNounPhrase(subj_s);
        if (!Arrays.asList("er", "es").contains(subj_s))
            subj.setDeterminer("der");
        if (!s.pickSubjPremod().equals("")){
            subj.addPreModifier(s.pickSubjPremod());
        }
        if (!s.pickSubjPostmod().equals("")){
            subj.addPostModifier(s.pickSubjPostmod());
        }

        VPPhraseSpec v = nlgFactory.createVerbPhrase(s.getVerb());
        NPPhraseSpec obj = nlgFactory.createNounPhrase(s.pickObj());
        if (!s.getObjArt().equals("")){
            if (s.getObjArt().equals("indef"))
                obj.setDeterminer("ein");
            else
                obj.setDeterminer("der");
        }
        if (!s.pickObjPremod().equals("")){
            obj.addPreModifier(s.pickObjPremod());
        }
        if (!s.pickObjPostmod().equals("")){
            obj.addPostModifier(s.pickObjPostmod());
        }
        return createSVOOutput(subj, v, obj, s);
    }

    public NPPhraseSpec createDataObj(String value, String unit) {
        NPPhraseSpec obj = nlgFactory.createNounPhrase(unit);
        obj.addFrontModifier(value);
        return obj;
    }

    public String simpleSVO(String subject, String verb, String object) {
        NPPhraseSpec subj = nlgFactory.createNounPhrase(subject);
        VPPhraseSpec v = nlgFactory.createVerbPhrase(verb);
        NPPhraseSpec obj = nlgFactory.createNounPhrase(object);
        
        return createSVOOutput(subj, v, obj);
    }

    public String simpleSVO(String subject, String verb, String object, String objectMod) {
        NPPhraseSpec subj = nlgFactory.createNounPhrase(subject);
        VPPhraseSpec v = nlgFactory.createVerbPhrase(verb);
        NPPhraseSpec obj = nlgFactory.createNounPhrase(object);
        obj.addFrontModifier(objectMod);
        
        return createSVOOutput(subj, v, obj);
    }

    public String SV_doubleVMod (String subject, String verb, String mod1, NPPhraseSpec mod2) {
        NPPhraseSpec subj = nlgFactory.createNounPhrase(subject);
        VPPhraseSpec v = nlgFactory.createVerbPhrase(verb);
        v.addPostModifier(mod1);
        v.addPostModifier(mod2);
        
        return createSVOutput(subj, v);

    }

    public String SV_singleVMod (String subject, String verb, NPPhraseSpec mod1) {
        NPPhraseSpec subj = nlgFactory.createNounPhrase(subject);
        VPPhraseSpec v = nlgFactory.createVerbPhrase(verb);
        v.addPostModifier(mod1);
        
        return createSVOutput(subj, v);

    }

    public String SVO_singleVMod (String subject, String verb, String mod1, NPPhraseSpec object) {
        NPPhraseSpec subj = nlgFactory.createNounPhrase(subject);
        VPPhraseSpec v = nlgFactory.createVerbPhrase(verb);
        NPPhraseSpec obj = nlgFactory.createNounPhrase(object);
        v.addPostModifier(mod1);
        
        return createSVOOutput(subj, v, obj);

    }

    private String createSVOOutput(NPPhraseSpec subj, VPPhraseSpec v, NPPhraseSpec obj) {
        SPhraseSpec sentence = nlgFactory.createClause();
        sentence.setSubject(subj);
        sentence.setVerb(v);
        sentence.setObject(obj);

        String output = realiser.realiseSentence(sentence);
        return output;
    }

    private String createSVOOutput(NPPhraseSpec subj, VPPhraseSpec v, NPPhraseSpec obj, Scheme s) {
        SPhraseSpec sentence = nlgFactory.createClause();
        sentence.setSubject(subj);
        sentence.setVerb(v);
        sentence.setObject(obj);

        if (s.getSentMod() != null && !s.getSentMod().equals("")) {
            if (ThreadLocalRandom.current().nextInt(0, 2) == 1)
                sentence.addFrontModifier(s.getSentMod());
            else
                v.addPostModifier(s.getSentMod());
        }
            

        String output = realiser.realiseSentence(sentence);
        return output;
    }

    private String createSVOutput(NPPhraseSpec subj, VPPhraseSpec v ) {
        SPhraseSpec sentence = nlgFactory.createClause();
        sentence.setSubject(subj);
        sentence.setVerb(v);

        String output = realiser.realiseSentence(sentence);
        return output;
    }
}