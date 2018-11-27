package de.homedev.primefaces.frontend.enums;

/**
 * Enum for possible edit modes of pages.
 * 
 */
public enum EditMode {

    /**
     * Enum for creation mode
     */
    NEW("common^button^new"),

    /**
     * Enum for edit mode
     */
    EDIT("common^button^edit"),

    /**
     * Enum for clone mode
     */
    CLONE("common^button^clone"),

    /**
     * Enum for view mode
     */
    VIEW("common^button^view");

    private final String i18nKey;

    EditMode(final String i18nKey) {
        this.i18nKey = i18nKey;
    }

    public String getI18nKey() {
        return this.i18nKey;
    }

    /**
     * @return <code>true</code> for creation mode
     */
    public boolean isNewMode() {
        return NEW.equals(this);
    }

    /**
     * @return <code>true</code> for edit mode
     */
    public boolean isEditMode() {
        return EDIT.equals(this);
    }

    /**
     * @return <code>true</code> for view mode
     */
    public boolean isViewMode() {
        return VIEW.equals(this);
    }

    /**
     * Converts boolean to edit mode.
     * <p>
     * Returns {@link #EDIT} if <code>true</code> else {@link #VIEW}
     *
     * @param edit
     *             the edit mode indicator
     * @return the edit mode
     */
    public static EditMode toEditMode(final boolean edit) {
        return edit ? EDIT : VIEW;
    }
}
