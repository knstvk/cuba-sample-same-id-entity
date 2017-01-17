package com.company.sample.web.customer;

import com.company.sample.entity.Customer;
import com.company.sample.entity.Details;
import com.google.common.base.Strings;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.AbstractEditor;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.Map;

public class CustomerEdit extends AbstractEditor<Customer> {

    @Inject
    private Metadata metadata;

    @Override
    protected void postInit() {
        Customer customer = getItem();
        // always create Details to be able to enter into fields
        if (customer.getDetails() == null) {
            Details details = metadata.create(Details.class);
            details.setId(customer.getId());
            customer.setDetails(details);
        }
    }

    @Override
    public void init(Map<String, Object> params) {
        getDsContext().addBeforeCommitListener(context -> {
            Customer customer = getItem();
            // iterate through committed instances
            for (Iterator<Entity> it = context.getCommitInstances().iterator(); it.hasNext(); ) {
                Entity entity = it.next();
                if (entity instanceof Details) {
                    Details details = (Details) entity;
                    if (Strings.isNullOrEmpty(details.getAddress())
                            && Strings.isNullOrEmpty(details.getDescription())) {
                        // if Details instance is empty, remove it from the collection so it won't be committed
                        it.remove();
                        // if Details instance is not new, remove it from the database
                        if (!PersistenceHelper.isNew(details)) {
                            context.addInstanceToRemove(details);
                        }
                        // update Customer if we removed Details
                        customer.setDetails(null);
                        context.addInstanceToCommit(customer);
                    }
                }
            }
        });
    }
}