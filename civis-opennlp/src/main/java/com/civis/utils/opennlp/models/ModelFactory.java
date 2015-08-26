package com.civis.utils.opennlp.models;

import com.civis.utils.opennlp.models.address.AddressFinder;
import com.civis.utils.opennlp.models.address.AddressFinderMe;
import com.civis.utils.opennlp.models.contactperson.ContactPersonFinder;
import com.civis.utils.opennlp.models.contactperson.ContactPersonFinderMe;
import opennlp.tools.namefind.TokenNameFinderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by Sergej Meister on 7/9/15.
 */
public final class ModelFactory {

    private final static Logger LOG = LoggerFactory.getLogger(ModelFactory.class);

    private ModelFactory() {
        // private constructor.
    }

    /**
     * Init and returns model to find contact persons.
     *
     * @return ContactPersonFinder object or null if model could not be loaded!
     */
    public static ContactPersonFinder getContactPersonFinder() {
        try (InputStream tokenNameFinderModelInputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(ModelPath.DE_CONTACT_PERSON_BIN)) {
            TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(tokenNameFinderModelInputStream);
            return new ContactPersonFinderMe(tokenNameFinderModel);
        } catch (Exception e) {
            LOG.error("Models can not be loaded successfully!", e);
        }

        throw new ModelLoadException(ModelPath.DE_CONTACT_PERSON_BIN);
    }

    public static AddressFinder getAddressFinder() {
        try (InputStream tokenNameFinderModelInputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(ModelPath.DE_ADDRESS_BIN)) {
            TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(tokenNameFinderModelInputStream);
            return new AddressFinderMe(tokenNameFinderModel);
        } catch (Exception e) {
            LOG.error("Models can not be loaded successfully!", e);
        }

        throw new ModelLoadException(ModelPath.DE_ADDRESS_BIN);
    }
}
