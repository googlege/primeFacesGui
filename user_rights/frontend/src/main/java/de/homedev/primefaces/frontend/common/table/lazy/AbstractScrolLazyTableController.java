package de.homedev.primefaces.frontend.common.table.lazy;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

public abstract class AbstractScrolLazyTableController<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1843855411437011109L;
	// private static final Logger logger =
	// LoggerFactory.getLogger(AbstractScrolLazyTableController.class);
	private String lastSortfield;

	private SortOrder lastSortOrder;

	private Map<String, Object> lastFilterMap;

	private Page<T> currentResultPage;

	private Map<String, T> keyToObjectMap;

	private Map<T, String> objectToKeyMap;

	private T selectedElement;

	private long lastId = 0;

	private static final java.util.List<Integer> SELECTABLE_PAGE_SIZES = Arrays.asList(Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(400),
			Integer.valueOf(800));

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
			this.objectToKeyMap = new HashMap<>();
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

	/**
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public T getRowData(String rowKey) {
		return getKeyToObjectMap().get(rowKey);
	}

	/**
	 * To access row data by given row key.
	 * 
	 * @return {@link Map}
	 */
	public Map<String, T> getKeyToObjectMap() {
		if (this.keyToObjectMap == null) {
			this.keyToObjectMap = new HashMap<>();
		}
		return this.keyToObjectMap;
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
	 *            the selectedPageSize to set
	 */
	public void setSelectedPageSize(Integer selectedPageSize) {
		this.selectedPageSize = selectedPageSize;
	}

	/**
	 * To access data table filter map. This map contains all filter provided by
	 * user.
	 * 
	 * @return {@link Map}
	 */
	public Map<String, Object> getFilterMap() {
		if (this.filterMap == null) {
			this.filterMap = new HashMap<>();
		}
		return this.filterMap;
	}

	public java.util.List<Integer> getSelectablePageSizes() {
		return SELECTABLE_PAGE_SIZES;
	}

	protected void reset() {
		this.keyToObjectMap = null;
		this.objectToKeyMap = null;
		this.lastId = 0;
	}

	/**
	 * @see javax.faces.model.ListDataModel#setWrappedData(java.lang.Object)
	 */
	@Override
	public void setWrappedData(Object data) {
		// generate internal IDs for elements and store association in maps
		reset();
		@SuppressWarnings("unchecked")
		Collection<T> dataList = (Collection<T>) data;
		if (data != null) {
			for (T element : dataList) {
				String idString = String.valueOf(getNextId());
				getKeyToObjectMap().put(idString, element);
				getObjectToKeyMap().put(element, idString);
			}
		}
		if ((dataList == null) || (dataList.isEmpty())) {
			this.setSelectedElement(null);
		}
		super.setWrappedData(data);
	}

	/**
	 * To access and generate a unique key for each data table row.
	 * 
	 * @return long
	 */
	protected long getNextId() {
		return this.lastId++;
	}

	public boolean isDisableMenuContextAndTableToolbar() {
		return (this.getSelectedElement() == null);
	}

	public void onSort() {

	}

	public void onFilter() {

	}
}
