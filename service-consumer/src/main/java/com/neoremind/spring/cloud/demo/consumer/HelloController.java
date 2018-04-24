package com.neoremind.spring.cloud.demo.consumer;

import com.neoremind.spring.cloud.demo.provider.dto.Greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * not in use
   */
  @Autowired
  DiscoveryClient client;

  @Autowired
  LoadBalancerClient loadBalancerClient;

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    return restTemplate.getForEntity("http://SERVICE-HELLO/hello", String.class).getBody();
  }

  @RequestMapping("/greeting")
  public Greeting greeting() {
    ServiceInstance instance = this.loadBalancerClient.choose("SERVICE-HELLO");
    log.info("{}", instance);
    URI helloUri = URI.create(String.format("http://%s:%s/greeting", instance.getHost(), instance.getPort()));
    log.info("Target service uri = {}. ", helloUri.toString());
    return new RestTemplate().getForObject(helloUri, Greeting.class);
  }

  /**
   * 2018-04-22 18:53:38.374 ERROR 19260 --- [nio-8090-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.IllegalStateException: No instances available for 192.168.1.108] with root cause
   *
   * java.lang.IllegalStateException: No instances available for 192.168.1.108
   * at org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient.execute(RibbonLoadBalancerClient.java:75) ~[spring-cloud-netflix-core-1.3.1.RELEASE.jar:1.3.1.RELEASE]
   * at org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor.intercept(LoadBalancerInterceptor.java:55) ~[spring-cloud-commons-1.2.2.RELEASE.jar:1.2.2.RELEASE]
   * at org.springframework.http.client.InterceptingClientHttpRequest$InterceptingRequestExecution.execute(InterceptingClientHttpRequest.java:86) ~[spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
   * at org.springframework.cloud.netflix.metrics.MetricsClientHttpRequestInterceptor.intercept(MetricsClientHttpRequestInterceptor.java:64) ~[spring-cloud-netflix-core-1.3.1.RELEASE.jar:1.3.1.RELEASE]
   * at org.springframework.http.client.InterceptingClientHttpRequest$InterceptingRequestExecution.execute(InterceptingClientHttpRequest.java:86) ~[spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
   */
  //FIXME
  @RequestMapping("/greeting2")
  public Greeting greeting2() {
    List<ServiceInstance> list = client.getInstances("SERVICE-HELLO");
    log.info("{}", list);
    if (list != null && list.size() > 0) {
      URI uri = list.get(0).getUri();
      if (uri != null) {
        return restTemplate.getForObject(uri, Greeting.class);
      }
    }
    return null;
  }
}
