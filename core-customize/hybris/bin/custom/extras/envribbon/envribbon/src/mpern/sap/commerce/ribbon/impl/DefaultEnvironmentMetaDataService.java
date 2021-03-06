package mpern.sap.commerce.ribbon.impl;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import mpern.sap.commerce.ribbon.EnvironmentMetaDataService;
import mpern.sap.commerce.ribbon.EnvironmentMetaData;

public class DefaultEnvironmentMetaDataService implements EnvironmentMetaDataService {
    private final ConfigurationService configurationService;

    public DefaultEnvironmentMetaDataService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public EnvironmentMetaData getMetaData() {
        EnvironmentMetaData data = new EnvironmentMetaData();

        data.setCode(readEnvironmentFromConfig());
        data.setType(readTypeFromConfig());

        return data;
    }

    private String readEnvironmentFromConfig() {
        String env = configurationService.getConfiguration().getString("ribbon.environment.code", "");
        if (env.isEmpty()) {
            env = configurationService.getConfiguration().getString("modelt.project.code", "");
            env += ( env.isEmpty() ? "" : "-" ) + configurationService.getConfiguration().getString("modelt.environment.code", "");
        }
        return env;
    }

    private String readTypeFromConfig() {
        String type = configurationService.getConfiguration().getString("ribbon.environment.type", "");
        if (type.isEmpty()) {
            type = configurationService.getConfiguration().getString("modelt.environment.type", "");
        }
        return type.toLowerCase();
    }
}
