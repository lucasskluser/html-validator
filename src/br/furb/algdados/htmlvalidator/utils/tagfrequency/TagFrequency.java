package br.furb.algdados.htmlvalidator.utils.tagfrequency;

public class TagFrequency {
    private String tag;
    private int frequency;

    public TagFrequency(String tag) {
        this.tag = tag;
        frequency = 0;
    }

    public String getTag() {
        return tag;
    }

    public int getFrequency() {
        return frequency;
    }

    public void count() {
        this.frequency++;
    }

    @Override
    public boolean equals(Object o) {
        TagFrequency object = (TagFrequency) o;
        return this.tag.equals(object.getTag());
    }

    public String toString() {
        return String.format("%s: %d", tag, frequency);
    }
}
