package animals.domain;

import animals.localization.ResourceBundleFactory;

public enum FactVerb {
    CAN(ResourceBundleFactory.getResource().getStringArray("can")),
    HAS(ResourceBundleFactory.getResource().getStringArray("has")),
    IS(ResourceBundleFactory.getResource().getStringArray("is")),
    LIVE(ResourceBundleFactory.getResource().getStringArray("live"));

    private final String[] texts;

    FactVerb(String[] texts) {
        this.texts = texts;
    }

    public String getText() {
        return texts[0];
    }

    public String getNegateText() {
        return texts[1];
    }

    public String getInterrogativeText() {
        return String.format(texts[2], ResourceBundleFactory.getResource().getString("it"));
    }
}
