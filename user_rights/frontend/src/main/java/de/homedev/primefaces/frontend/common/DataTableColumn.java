
package de.homedev.primefaces.frontend.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTableColumn {

    // -- logger ---------------------------------------------------------------

    private static final Logger logger = LoggerFactory.getLogger(DataTableColumn.class);

    // -- variables -----------------------------------------------------------

    /**
     * property for the header
     */
    private final String header;

    /**
     * property for the name of the attribute to be used for sorting and filtering.
     */
    private final String property;

    /**
     * Property for the name of the attribute of the entity to be shown. This may be different from the attribute
     * "property"
     */
    private final String displayProperty;

    /**
     * property to enable / disable the column filter
     */
    private boolean filterAvailable = true;

    /**
     * property to enable / disable the column sorting
     */
    private boolean sortAvailable = true;

    private String[] sortProperties;

    private String cssStyle;

    private String columnWidth;

    private String resetTimeStampMethod;

    private String timeStampFilterStateMethod;

    /**
     * Enum for the available search types
     */
    public enum StringSearchType {
        /**
         * Search for '%filter%'
         */
        CONTAINS,

        /**
         * Search for 'filter%'
         */
        STARTSWITH,

        /**
         * Search for 'filter'
         */
        EXACT,

        /**
         * Search for '%filter'
         */
        ENDS_WITH;
    }

    private boolean displayNameNeedsLocalization;

    private StringSearchType stringSearchType = StringSearchType.CONTAINS;

    private boolean searchCaseSensitive = false;

    // -- constructors --------------------------------------------------------

    /**
     * Constructor for a new data table column
     * 
     * @param header
     *                 property for the header
     * @param property
     *                 Property for the name of the attribute to be shown
     */
    public DataTableColumn(final String header, final String property) {
        this(header, property, property, true);
    }

    /**
     * Constructor for a new data table column
     * 
     * @param header
     *                        Property for the header
     * @param property
     *                        property for the name of the attribute to be used for sorting and filtering
     * @param displayProperty
     *                        Property for the name of the attribute of the entity to be shown. This may be different
     *                        from <code>property</code>
     * @param filterAvailable
     *                        enable/disable filter
     * @param sortProperties
     *                        Property list for sorting
     */
    public DataTableColumn(final String header,
                           final String property,
                           String displayProperty,
                           boolean filterAvailable,
                           String... sortProperties) {
        this(header, property, displayProperty, filterAvailable, true, sortProperties);
    }

    /**
     * Constructor for a new data table column
     * 
     * @param header
     *                        Property for the header
     * @param property
     *                        property for the name of the attribute to be used for sorting and filtering
     * @param displayProperty
     *                        Property for the name of the attribute of the entity to be shown. This may be different
     *                        from <code>property</code>
     */
    public DataTableColumn(final String header, final String property, final String displayProperty) {
        this(header, property, displayProperty, true, true, property);
    }

    /**
     * Constructor for a new data table column
     * 
     * @param header
     *                        Property for the header
     * @param property
     *                        property for the name of the attribute to be used for sorting and filtering
     * @param displayProperty
     *                        Property for the name of the attribute of the entity to be shown. This may be different
     *                        from <code>property</code>
     * @param filterAvailable
     *                        enable/disable filter
     * @param sortAvailable
     *                        enable/disable sorting by field value
     * @param sortProperties
     *                        Property list for sorting
     */
    public DataTableColumn(final String header,
                           final String property,
                           String displayProperty,
                           boolean filterAvailable,
                           boolean sortAvailable,
                           String... sortProperties) {
        this.header = header;
        this.property = property;
        this.displayProperty = displayProperty;
        this.filterAvailable = filterAvailable;
        this.sortAvailable = sortAvailable;
        this.sortProperties = sortProperties;

        if ((this.sortProperties == null) || (this.sortProperties.length == 0)) {
            if (sortAvailable) {
                this.sortProperties = new String[] {
                        property
                };
            } else {
                this.sortProperties = new String[] {};
            }
        }
    }

    // -- properties ----------------------------------------------------------

    /**
     * @return the timeStampFilterStateMethod
     */
    public String getTimeStampFilterStateMethod() {
        return this.timeStampFilterStateMethod;
    }

    /**
     * @param timeStampFilterStateMethod
     * @return {@link DataTableColumn}
     */
    public DataTableColumn setTimeStampFilterStateMethod(String timeStampFilterStateMethod) {
        this.timeStampFilterStateMethod = timeStampFilterStateMethod;
        return this;
    }

    /**
     * @return {@link String}
     */
    public String getResetTimeStampMethod() {
        return this.resetTimeStampMethod;
    }

    /**
     * @param resetTimeStampMethodMethod
     * @return {@link DataTableColumn}
     */
    public DataTableColumn setTimeStampResetMethod(String resetTimeStampMethodMethod) {
        this.resetTimeStampMethod = resetTimeStampMethodMethod;
        return this;
    }

    /**
     * property for the header
     * 
     * @return header
     */
    public String getHeader() {
        return this.header;
    }

    /**
     * Property for the name of the attribute to be used for sorting, filtering
     * 
     * @return property
     */
    public String getProperty() {
        return this.property;
    }

    /**
     * Property for the name of the attribute to be used for display
     * 
     * @return property
     */
    public String getDisplayProperty() {
        return this.displayProperty;
    }

    /**
     * property to enable / disable the column filter
     * 
     * @return <code>true</code> if filter is available
     */
    public boolean isFilterAvailable() {
        logger.trace("Column: {}, isFilterAvailable(): {}", this.property, Boolean.valueOf(this.filterAvailable));
        return this.filterAvailable;
    }

    /**
     * Set the property if filtering is available for this column
     * 
     * @param filterAvailable
     *                        boolean
     * @return this column
     */
    @SuppressWarnings("unchecked")
    public <T extends DataTableColumn> T setFilterAvailable(boolean filterAvailable) {
        this.filterAvailable = filterAvailable;
        return (T) this;
    }

    /**
     * Set the property if sorting is available for this column
     * 
     * @param sortAvailable
     *                      boolean
     * @return this column
     */
    @SuppressWarnings("unchecked")
    public <T extends DataTableColumn> T setSortAvailable(boolean sortAvailable) {
        this.sortAvailable = sortAvailable;
        return (T) this;
    }

    /**
     * property to enable / disable the column sorting
     * 
     * @return <code>true</code> if sort is available
     */
    public boolean isSortAvailable() {
        logger.trace("Column: {}, isSortAvailable(): {}", this.property, Boolean.valueOf(this.sortAvailable));
        return this.sortAvailable;
    }

    /**
     * Returns the selected sort-properties
     * 
     * @return Array of sort properties
     */
    public String[] getSortProperties() {
        return this.sortProperties;
    }

    /**
     * Property for the search type used to filter on String columns
     * 
     * @return stringSearchType search type (Contains, StartsWith, ...)
     */
    public StringSearchType getStringSearchType() {
        return this.stringSearchType;
    }

    /**
     * Property indicating if filter should be case sensitive or not.
     * 
     * @param searchCaseSensitive
     *                            the new state to set
     * @return this column
     */
    @SuppressWarnings("unchecked")
    public <T extends DataTableColumn> T setSearchCaseSensitive(boolean searchCaseSensitive) {
        this.searchCaseSensitive = searchCaseSensitive;
        return (T) this;
    }

    /**
     * Property indicating if filter should be case sensitive or not.
     * 
     * @return <code>true</code> if search should be case sensitive
     */
    public boolean isSearchCaseSensitive() {
        return this.searchCaseSensitive;
    }

    /**
     * Property for the search type used to filter on String columns
     * 
     * @param stringSearchType
     *                         search type (Contains, StartsWith, ...)
     * @return this column
     */
    @SuppressWarnings("unchecked")
    public <T extends DataTableColumn> T setStringSearchType(StringSearchType stringSearchType) {
        this.stringSearchType = stringSearchType;
        return (T) this;
    }

    /**
     * Allows access to search attributes
     * 
     * @return {@link String} array
     */
    public String[] getSearchAttributes() {
        return new String[] {
                getProperty()
        };
    }

    /**
     * Property for an optional css-style for the column. Is empty by default
     * 
     * @return css-Style
     */
    public String getCssStyle() {
        return this.cssStyle;
    }

    /**
     * Property for an optional css-style for the column. Is empty by default
     * 
     * @param cssStyle
     *                 style
     * @return the modified {@link DataTableColumn}-object
     */
    @SuppressWarnings("unchecked")
    public <T extends DataTableColumn> T setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
        return (T) this;
    }

    /**
     * Property for an optional column width. Is empty by default
     * 
     * @return the columnWidth
     */
    public String getColumnWidth() {
        return this.columnWidth;
    }

    /**
     * Allows to set property for an optional column width.
     * 
     * @param columnWidth
     *                    the columnWidth to set
     * @return the modified {@link DataTableColumn}-object
     */
    @SuppressWarnings("unchecked")
    public <T extends DataTableColumn> T setColumnWidth(String columnWidth) {
        this.columnWidth = columnWidth;
        return (T) this;
    }

    // -- methods -------------------------------------------------------------

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getProperty();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return getProperty().hashCode();
    }

    /**
     * @see com.vwg.atld.common.interfaces.IDisplaynameProvider#getDisplayName()
     */

    public String getDisplayName() {
        return getHeader();
    }

    /**
     * @see com.vwg.atld.common.interfaces.IDisplaynameProvider#getDisplayNameLong()
     */

    public String getDisplayNameLong() {
        return getDisplayName();
    }

    /**
     * @see com.vwg.atld.common.interfaces.IDisplayNameIdProvider#isDisplayNameNeedsLocalization()
     */

    public boolean isDisplayNameNeedsLocalization() {
        return this.displayNameNeedsLocalization;
    }

    /**
     * Indicates if display name need localization.
     * 
     * @param displayNameNeedsLocalization
     *                                     the displayNameNeedsLocalization to set
     * @return {@link DataTableColumn}
     */
    @SuppressWarnings("unchecked")
    public <T extends DataTableColumn> T setDisplayNameNeedsLocalization(boolean displayNameNeedsLocalization) {
        this.displayNameNeedsLocalization = displayNameNeedsLocalization;
        return (T) this;
    }

    /**
     * prepare filter map values
     * 
     * @param filterMap
     * 
     * @return {@link Map} with key value pairs
     */
    public Map<String, Object> prepareFilterMap(Map<String, Object> filterMap) {
        // nothing to do
        return filterMap;
    }

}
