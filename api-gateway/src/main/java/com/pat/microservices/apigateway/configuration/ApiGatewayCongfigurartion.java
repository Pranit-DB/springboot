package com.pat.microservices.apigateway.configuration;

//import java.util.function.Function;

//import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.PredicateSpec;
//import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayCongfigurartion {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
					.filters(f->f.addRequestHeader("MyHeader","MyUrl")
								.addRequestParameter("Param", "MyValue"))
					.uri("http://httpbin.org:80"))
				// can define another route here
				// note : app.properties> remove locator properties
				// below line will take care of that i.e.,URL ReWriting
				.route(p->p.path("/currency-exchange/**")
					.uri("lb://currency-exchange"))
				.route(p->p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.route(p->p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				.route(p->p.path("/currency-conversion-new/**")
						// Custom filter
						.filters(f->f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)",
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion"))
				.build();
		
// Note : Alt+shift+T --> inline... or Alt+shift+i  
		
//		@Bean
//		public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//			Function<PredicateSpec,Buildable<Route>> routeFunction =
//					p -> p.path("/get")
//						.filters(f->f.addRequestHeader("MyHeader","MyUrl")
//									.addRequestParameter("Param", "MyValue"))
//						.uri("http://httpbin.org:80");
//					
//			return builder.routes()
//					.route(routeFunction)
//					
//					.build();
	}
}
