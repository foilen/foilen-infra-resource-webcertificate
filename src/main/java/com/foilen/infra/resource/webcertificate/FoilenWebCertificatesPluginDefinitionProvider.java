/*
    Foilen Infra Resource Web Certificate
    https://github.com/foilen/foilen-infra-resource-webcertificate
    Copyright (c) 2018 Foilen (http://foilen.com)

    The MIT License
    http://opensource.org/licenses/MIT

 */
package com.foilen.infra.resource.webcertificate;

import java.util.Arrays;
import java.util.Calendar;

import com.foilen.infra.plugin.v1.core.context.CommonServicesContext;
import com.foilen.infra.plugin.v1.core.plugin.IPPluginDefinitionProvider;
import com.foilen.infra.plugin.v1.core.plugin.IPPluginDefinitionV1;

public class FoilenWebCertificatesPluginDefinitionProvider implements IPPluginDefinitionProvider {

    @Override
    public IPPluginDefinitionV1 getIPPluginDefinition() {
        IPPluginDefinitionV1 pluginDefinitionV1 = new IPPluginDefinitionV1("Foilen", "Web Certificate", "To manage web certificates", "1.0.0");

        pluginDefinitionV1.addCustomResource(WebsiteCertificate.class, "Website Certificate", //
                Arrays.asList(WebsiteCertificate.PROPERTY_THUMBPRINT), //
                Arrays.asList( //
                        WebsiteCertificate.PROPERTY_THUMBPRINT, //
                        WebsiteCertificate.PROPERTY_DOMAIN_NAMES, //
                        WebsiteCertificate.PROPERTY_CA_CERTIFICATE, //
                        WebsiteCertificate.PROPERTY_START, //
                        WebsiteCertificate.PROPERTY_END //
                ));

        pluginDefinitionV1.addTranslations("/com/foilen/infra/resource/webcertificate/messages");
        pluginDefinitionV1.addResourceEditor(new ManualWebsiteCertificateEditor(), ManualWebsiteCertificateEditor.EDITOR_NAME);
        pluginDefinitionV1.addResourceEditor(new SelfSignedWebsiteCertificateEditor(), SelfSignedWebsiteCertificateEditor.EDITOR_NAME);

        pluginDefinitionV1.addUpdateHandler(new WebsiteCertificateUpdateHandler());

        pluginDefinitionV1.addTimer(new SelfSignedWebsiteCertificateRefreshTimer(), //
                SelfSignedWebsiteCertificateRefreshTimer.TIMER_NAME, //
                Calendar.DAY_OF_YEAR, 1, //
                false, true);

        return pluginDefinitionV1;
    }

    @Override
    public void initialize(CommonServicesContext commonServicesContext) {
    }

}
