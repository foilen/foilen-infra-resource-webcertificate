/*
    Foilen Infra Resource Web Certificate
    https://github.com/foilen/foilen-infra-resource-webcertificate
    Copyright (c) 2018 Foilen (http://foilen.com)

    The MIT License
    http://opensource.org/licenses/MIT

 */
package com.foilen.infra.resource.webcertificate;

import com.foilen.infra.plugin.v1.core.common.DomainHelper;
import com.foilen.infra.plugin.v1.core.context.ChangesContext;
import com.foilen.infra.plugin.v1.core.context.CommonServicesContext;
import com.foilen.infra.plugin.v1.core.eventhandler.AbstractCommonMethodUpdateEventHandler;
import com.foilen.infra.plugin.v1.core.eventhandler.CommonMethodUpdateEventHandlerContext;
import com.foilen.infra.resource.domain.Domain;

public class WebsiteCertificateUpdateHandler extends AbstractCommonMethodUpdateEventHandler<WebsiteCertificate> {

    @Override
    protected void commonHandlerExecute(CommonServicesContext services, ChangesContext changes, CommonMethodUpdateEventHandlerContext<WebsiteCertificate> context) {

        WebsiteCertificate resource = context.getResource();

        context.getManagedResourceTypes().add(Domain.class);

        for (String domainName : resource.getDomainNames()) {
            context.getManagedResources().add(new Domain(domainName, DomainHelper.reverseDomainName(domainName)));
        }

    }

    @Override
    public Class<WebsiteCertificate> supportedClass() {
        return WebsiteCertificate.class;
    }

}
