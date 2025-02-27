package faang.school.analytics.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "project-service", url = "${internal-services.project-service.host}:${internal-services.project-service.port}")
public interface ProjectServiceClient {

}
