package de.homedev.primefaces.frontend.bank;

import java.rmi.RemoteException;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import de.homedev.primefaces.api.fassade.IBlzFassade;
import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.frontend.common.IJsfController;
import de.homedev.primefaces.frontend.common.table.lazy.AbstractScrolLazyTableController;
import de.homedev.primefaces.frontend.config.FrontendSettings;
import de.homedev.primefaces.frontend.util.RmiClientUtil;

public class BlzTableController extends AbstractScrolLazyTableController<BlzEntity> {
    // -- serializable ---------------------------------------------------------

    private static final long serialVersionUID = 1L;

    private final IJsfController parentCb;

    public BlzTableController(BlzControllerBean parent) {
        super();
        this.parentCb = parent;
    }

    public IJsfController getParentCb() {
        return parentCb;
    }

    public Page<BlzEntity> getResultPageFromService(Map<String, Object> preparedFilterMap, PageRequest request,
                                                    Long lastSearchTotalElements) {
        try {
            IBlzFassade fassade = RmiClientUtil.getBlzFassade(FrontendSettings.getSettings());
            return fassade.findPage(preparedFilterMap, request);
        } catch (RemoteException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NamingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Page<BlzEntity> loadPage(final int first, final int pagesize, final String sortfield, final SortOrder sortOrder,
                                    final Map<String, Object> filterMap) {
        // if no sortColumn is set, use the first column
        String sortfieldToUse = sortfield;
        if (StringUtils.isEmpty(sortfieldToUse) && isDefaultSortingEnabled()) {
            sortfieldToUse = "id";
        }
        // remove keys that are not in the column list
        removeInvalidFilterKeys(filterMap);

        // for some columns a special filter preparation may be necessary
        Map<String, Object> preparedFilterMap = prepareFilterMap(filterMap);

        // create the page request with paging and sorting
        final int pagenum = first / pagesize;
        final Direction sortDirection = SortOrder.ASCENDING.equals(sortOrder) ? Direction.ASC : Direction.DESC;
        final Sort sort = getSort(sortfieldToUse, sortDirection);

        final PageRequest request = PageRequest.of(pagenum, pagesize, sort);

        Long lastSearchTotalElements = null;
        Page<BlzEntity> lastPage = this.getCurrentResultPage();
        if (lastPage != null && preparedFilterMap.equals(this.getLastFilterMap())) {
            lastSearchTotalElements = lastPage.getTotalElements();
        }
        Page<BlzEntity> resultPageFromService =
            getResultPageFromService(preparedFilterMap, request, lastSearchTotalElements);
        // save the request parameters for later use (e.g. for export)
        setLastSortfield(sortfieldToUse);
        setLastSortOrder(sortOrder);
        setLastFilterMap(preparedFilterMap);
        this.setCurrentResultPage(resultPageFromService);

        return resultPageFromService;
    }

    /**
     * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder,
     *      java.util.Map)
     */
    @Override
    public List<BlzEntity> load(final int first, final int pagesize, final String sortfield, final SortOrder sortOrder,
                                final Map<String, Object> filterMap) {

        if (first + pagesize <= 10000) {
            final Page<BlzEntity> resultPage = loadPage(first, pagesize, sortfield, sortOrder, filterMap);
            setCurrentResultPage(resultPage);
            setRowCount((int) resultPage.getTotalElements());
            return resultPage.getContent();
        }
        FacesMessage message =
            new FacesMessage(FacesMessage.SEVERITY_INFO, getParentCb().localize("common^header^info"), getParentCb().localize(
                "common^message^rowLimitReached"));
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        return Collections.emptyList();
    }

    /**
     * Returns <code>true</code> if the data should be sorted on the first column, when sorting is explicitly defined.
     * 
     * @return boolean
     */
    protected boolean isDefaultSortingEnabled() {
        return true;
    }

    /**
     * Remove keys that are not in the column list
     * 
     * @param filterMap
     */
    private void removeInvalidFilterKeys(final Map<String, Object> filterMap) {

    }

    private Map<String, Object> prepareFilterMap(Map<String, Object> filterMap) {
        return filterMap;
    }

    /**
     * Create a Sort option for a sortfield.<br />
     * If the sortField is a combined one (multiple database columns), then a combined sort option is created.
     * 
     * @param sortfield
     *                      the sortfield of the datatable (may be combined from multiple properties)
     * @param sortDirection
     *                      ASC/DESC
     * 
     * @return {@link Sort}
     */
    private Sort getSort(final String sortfield, final Direction sortDirection) {
        if (StringUtils.isNotEmpty(sortfield)) {
            List<Order> orderList = new ArrayList<Sort.Order>();
            Order oneOrder = new Order(sortDirection, sortfield);
            orderList.add(oneOrder);
            return Sort.by(orderList);

        }

        return null;
    }
}
