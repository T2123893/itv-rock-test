package com.itv.conf;

import java.util.HashMap;
import java.util.Map;

import com.itv.service.Offer;
import com.itv.service.impl.DefaultOffer;
import com.itv.service.impl.SpecialOfferA;
import com.itv.service.impl.SpecialOfferB;

/**
 * All available offers may register in here.
 * If next week any offer changed, simply replace the offer implementation that associates with the SKU item.
 * <br/>
 * There are two {@link com.itv.service.Offer} configured while initialising this class.
 */
public class AvailableOffer {

    private static final Map<SKU, Offer> availableOfferMap = new HashMap<>(SKU.values().length);
    private static final Offer DEFAULT_OFFER = new DefaultOffer();
    private static final AvailableOffer INSTANCE = new AvailableOffer();

    private AvailableOffer() {
        availableOfferMap.put(SKU.A, new SpecialOfferA());
        availableOfferMap.put(SKU.B, new SpecialOfferB());
    }

    /**
     * Find an associated offer from configured offer map. If no special offer associates with specified SKU, then use
     * a {@link com.itv.service.impl.DefaultOffer}
     *
     * @param item the SKU item
     * @param quantity the quantity of the items
     * @return an instance of a {@link com.itv.service.Offer}
     */
    public Offer get(SKU item, int quantity) {
        Offer offer = availableOfferMap.get(item);

        if (offer == null) {
            offer = DEFAULT_OFFER;
        }

        return offer.applyTo(item).withQuantity(quantity);
    }

    /**
     * Associates the specified {@link com.itv.service.Offer} with the specified {@link SKU}
     * If the offer map previously contained a mapping for the SKU, the old offer will be replaced by the
     * newly specified offer.
     *
     * @param item with which the specified offer is to be associated
     * @param specialOffer an instance to be associated with the specified SKU item.
     * @return the previous offer associated with SKU, or null if there was no mapping for SKU.
     */
    public Offer update(SKU item, Offer specialOffer) {
        return availableOfferMap.put(item, specialOffer);
    }

    public static AvailableOffer getInstance() {
        return INSTANCE;
    }
}
