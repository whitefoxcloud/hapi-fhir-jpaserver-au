package ca.uhn.fhir.jpa.starter.interceptors;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;
import org.hl7.fhir.r4.model.CapabilityStatement;
import org.hl7.fhir.instance.model.api.IBaseConformance;
import org.springframework.stereotype.Component;

@Interceptor
@Component
public class AllowedFormatInterceptor {
	@Hook(Pointcut.SERVER_CAPABILITY_STATEMENT_GENERATED)
	public void customizeCapabilityStatement(IBaseConformance capabilityStatement, RequestDetails requestDetails, ServletRequestDetails servletRequestDetails) {
		if (capabilityStatement instanceof CapabilityStatement cs) {
			cs.getFormat().removeIf(
				format -> format.getCode().equals("html/json") || format.getCode().equals("html/xml")
			);
		}
	}
}