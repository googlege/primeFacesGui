package de.homedev.primefaces.frontend.common.table.lazy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

public abstract class AbstractScrolLazyTableController<T> extends LazyDataModel<T> {

    private static final long serialVersionUID = 1843855411437011109L;
    // private static final Logger logger = LoggerFactory.getLogger(AbstractScrolLazyTableController.class);
    private String lastSortfield;

    private SortOrder lastSortOrder;

    private Map<String, Object> lastFilterMap;

    private Page<T> currentResultPage;

    private Map<T, String> objectToKeyMap;

    private T selectedElement;

    private static final java.util.List<Integer> SELECTABLE_PAGE_SIZES =
        Arrays.asList(Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(400), Integer.valueOf(800));

    private Integer selectedPageSize;

    private Map<String, Object> filterMap;

    public String getLastSortfield() {
        return lastSortfield;
    }

    public void setLastSortfield(String lastSortfield) {
        this.lastSortfield = lastSortfield;
    }

    public SortOrder getLastSortOrder() {
        return lastSortOrder;
    }

    public void setLastSortOrder(SortOrder lastSortOrder) {
        this.lastSortOrder = lastSortOrder;
    }

    public Map<String, Object> getLastFilterMap() {
        return lastFilterMap;
    }

    public void setLastFilterMap(Map<String, Object> lastFilterMap) {
        this.lastFilterMap = lastFilterMap;
    }

    public Page<T> getCurrentResultPage() {
        return currentResultPage;
    }

    public void setCurrentResultPage(Page<T> currentResultPage) {
        this.currentResultPage = currentResultPage;
    }

    /**
     * To access data table row key by given object.
     * 
     * @return {@link Map}
     */
    public Map<T, String> getObjectToKeyMap() {
        if (this.objectToKeyMap == null) {
            this.objectToKeyMap = new HashMap<T, String>();
        }
        return this.objectToKeyMap;
    }

    /**
     * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
     */
    @Override
    public Object getRowKey(final T obj) {
        return getObjectToKeyMap().get(obj);
    }

    public T getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(T selectedElement) {
        this.selectedElement = selectedElement;
    }

    /**
     * To access selected page size by user.
     * 
     * @return {@link Integer}
     */
    public Integer getSelectedPageSize() {
        if (this.selectedPageSize == null) {
            this.selectedPageSize = SELECTABLE_PAGE_SIZES.get(0);
        }
        return this.selectedPageSize;
    }

    /**
     * To set selected page size by user.
     * 
     * @param selectedPageSize
     *                         the selectedPageSize to set
     */
    public void setSelectedPageSize(Integer selectedPageSize) {
        this.selectedPageSize = selectedPageSize;
    }

    /**
     * To access data table filter map. This map contains all filter provided by user.
     * 
     * @return {@link Map}
     */
    public Map<String, Object> getFilterMap() {
        if (this.filterMap == null) {
            this.filterMap = new HashMap<String, Object>();
        }
        return this.filterMap;
    }

    public java.util.List<Integer> getSelectablePageSizes() {
        return SELECTABLE_PAGE_SIZES;
    }

}
