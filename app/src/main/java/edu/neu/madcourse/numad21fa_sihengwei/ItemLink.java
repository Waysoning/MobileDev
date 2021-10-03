package edu.neu.madcourse.numad21fa_sihengwei;

public class ItemLink implements ItemLinkClickListener {

    private final String linkName;
    private final String linkUrl;

    public ItemLink(String linkName, String linkUrl) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;
    }

    public String getLinkName() {
        return linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    @Override
    public void onItemLinkClick(int position) {

    }
}
